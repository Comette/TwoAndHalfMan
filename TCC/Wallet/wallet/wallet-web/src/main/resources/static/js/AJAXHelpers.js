'use strict';

var paginaAtualEsteMesFiltrado = 0;
var paginaAtualProximoMesFiltrado = 0;
var $formAtualSelect = $('#formFiltrarAtual select');
var $formProximoSelect = $('#formFiltrarProximo select');



function buscaGerentes() {
    $.ajax({
        type: 'GET',
        url: '/buscar-usuarios-ativos'
    }).done(function (data) {
        $.each(data, function (i, gerenteDTO) {
            $formAtualSelect.append($('<option>').val(gerenteDTO.id).text(gerenteDTO.nome));
            $formProximoSelect.append($('<option>').val(gerenteDTO.id).text(gerenteDTO.nome));
        });
    });
}

var getProxPaginaServicos = function ($container, mes, filtroAtual, $verMaisAtual) {
    debugger;
    var url;
    if (mes === 'ATUAL') {
        url = filtroAtual !== null ?
                '/servicos-mes-atual?idUsuario=' + filtroAtual + '&page=' + ++paginaAtualEsteMesFiltrado
                : '/servicos-mes-atual?page=' + ++paginaAtualEsteMesFiltrado;
    } else if (mes === 'PROXIMO') {
        url = filtroAtual !== null ?
                '/servicos-proximo-mes?idUsuario=' + filtroAtual + '&page=' + ++paginaAtualProximoMesFiltrado
                : '/servicos-proximo-mes?page=' + ++paginaAtualProximoMesFiltrado;
    }
    $.ajax({
        type: 'GET',
        url: url
    }).done(function (data) {
        if (typeof data === 'string') {
            location.reload();
        } else {

            if (data.length < 4) {
                toggleBtnVerMais('Não existem mais serviços.', $verMaisAtual, false);
            }
            renderizaListaServicos($container, data);
            adicionarOnClickExcluirServico($('.service-delete-btn'));
        }
    });
};

var adicionarOnClickExcluirAJAX = function () {
    var btnExclusao = $('#btnPrincipal');
    btnExclusao.val(parseInt($(this).val()));

    $.ajax({
        url: '/count-servicos-by-usuario',
        type: 'GET',
        data: {
            idUsuario: btnExclusao.val()
        }
    }).done(function (data) {
        var texto =
                data > 0 ? 'Este usuário tem serviços ativos em sua supervisão. Se inativá-lo, os serviços aos quais ele gerencia serão cancelados.' :
                'Deseja realmente inativar este usuário?';
        alterarModal(texto, 'Inativar', false, 'Inativar');
        $('#modalCoringa').modal('show');
    });
};

var checarSeUsuarioTemServicos = function (idUsuario) {
    $.ajax({
        url: '/count-servicos-by-usuario',
        type: 'GET',
        data: {
            idUsuario: idUsuario
        }
    }).done(function (data) {
        return data;
    });
};

function fazerRequestUsuarios() {
    $.ajax({
        type: 'GET',
        url: '/buscar-usuarios-ativos'
    }).done(function (data) {
        var lista1 = [];
        var lista2 = [];
        lista1 = data.splice(0, Math.ceil((data.length / 2)));
        lista2 = data;

        appendarUsuariosNaLista(lista1, $containerProximoMes);
        appendarUsuariosNaLista(lista2, $containerMesAtual);

        adicionarOnClickExcluir($('button[name="btn-excluir-gerente"]'));
        $('#btnPrincipal').click(function () {
            chamarExclusao($(this), true);
        });
    });
}

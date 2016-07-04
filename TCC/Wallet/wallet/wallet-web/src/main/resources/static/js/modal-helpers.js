'use strict';

var $lista1 = $('#lista-gerentes-1');
var $lista2 = $('#lista-gerentes-2');


function alterarModal(mensagem, titulo, displayBotaoModal, textoBotaoPrincipalModal) {

    var $btnPrincipal = $('#btnPrincipal');
    if (displayBotaoModal) {
        $btnPrincipal.hide();
    }
    if (!displayBotaoModal) {
        $btnPrincipal.show();
    }
    $('#modalCabecalho').text(titulo);
    $('#modalCorpo').text(mensagem);
    $btnPrincipal.text(textoBotaoPrincipalModal);
}
;

function chamarExclusao($button, trueParaUsuarioEFalseParaServico) {
    var idObjetoAtual = parseInt($button.val());

    var values = trueParaUsuarioEFalseParaServico ? {idUsuario: idObjetoAtual} : {idServico: idObjetoAtual};
    excluirObjeto(values, trueParaUsuarioEFalseParaServico);
}

function excluirObjeto(values, trueParaUsuarioEFalseParaServico) {
    var url = trueParaUsuarioEFalseParaServico ? '/inativar-usuario' : '/cancelar-servico';

    $.ajax({
        url: url,
        type: 'POST',
        data: values
    }).done(function () {
        if (trueParaUsuarioEFalseParaServico)
            fazerRequestUsuarios();
        else
            getDadosDashboard();
        alterarModal('Operação concluída com sucesso!', 'Sucesso', true, '');
    });
}
;

function adicionarOnClickExcluirServico($btn) {
    $btn.on("click", function (e) {
        if ($(this).hasClass('disabled'))
            return false;
        else {
            var $btnExclusao = $('#btnPrincipal');
            $btnExclusao.val(parseInt($(this).val()));

            alterarModal('Deseja realmente cancelar este serviço?', 'Cancelar Serviço', false,'Confirmar');
            $('#modalCoringa').modal('show');
        }
    });
}

function adicionarOnClickExcluir($btn) {
    $btn.on("click", function (e) {
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

        e.preventDefault();
    });
}
;

function fazerRequestUsuarios() {
    $.ajax({
        type: 'GET',
        url: '/buscar-usuarios-ativos'
    }).done(function (data) {
        var lista1 = [];
        var lista2 = [];
        lista1 = data.splice(0, Math.ceil((data.length / 2)));
        lista2 = data;

        appendarUsuariosNaLista(lista1, $lista1);
        appendarUsuariosNaLista(lista2, $lista2);

        adicionarOnClickExcluir($('button[name="btn-excluir-gerente"]'));
        $('#btnPrincipal').click(function () {
            chamarExclusao($(this), true);
        });
    });
}

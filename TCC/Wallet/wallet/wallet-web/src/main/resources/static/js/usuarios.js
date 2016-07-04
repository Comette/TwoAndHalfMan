'use strict';

var $lista1 = $('#lista-gerentes-1');
var $lista2 = $('#lista-gerentes-2');

$(function (e) {
    $.ajax({
        type: 'GET',
        url: '/buscar-todos-usuarios'
    }).done(function (data) {
        var lista1 = [];
        var lista2 = [];
        lista1 = data.splice(0, Math.ceil((data.length / 2)));
        lista2 = data;

        appendarUsuariosNaLista(lista1, $lista1);
        appendarUsuariosNaLista(lista2, $lista2);

        adicionarOnClickExcluir($('button[name="btn-excluir-gerente"]'));
        $('#btnPrincipal').click(function () {
            chamarExclusao($(this),true);
        });
    });
});

function appendarUsuariosNaLista(usuarios, $lista) {
    $.each(usuarios, function (i, usuario) {
        $lista.append(
                $('<a href="/usuario?idUsuario=' + usuario.id + '" class="list-group-item">').append(
                $('<a class="btn btn-warning glyphicon glyphicon-pencil" name="btn-editar-gerente" href="/editar-usuario?idUsuario=' + usuario.id + '">'))
                .append($('<h4>').text(usuario.nome))
                .append('<button class="btn btn-danger glyphicon glyphicon-remove" name="btn-excluir-gerente" value="' + usuario.id + '">'));
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
                    data > 0 ? 'Este usuário tem serviços em sua supervisão. Se inativá-lo, os serviços aos quais ele gerencia serão cancelados.' :
                    'Deseja realmente inativar este usuário?';

            alterarModal(texto, 'Inativar', false, 'Inativar');
            $('#modalCoringa').modal();
        });

        e.preventDefault();
    });
};



function excluirListItem(idBotaoMaisProximo) {
    $('.list-group :button').filter(function () {
        return this.value === idBotaoMaisProximo;
    }).closest('.list-group-item').remove();
}

function checarSeUsuarioTemServicos(idUsuario) {
    $.ajax({
        url: '/count-servicos-by-usuario',
        type: 'GET',
        data: {
            idUsuario: idUsuario
        }
    }).done(function (data) {
        return data;
    });
}

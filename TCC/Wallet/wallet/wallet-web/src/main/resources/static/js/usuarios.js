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
            chamarExclusao($(this));
        });
    });
});

function appendarUsuariosNaLista(usuarios, $lista) {
    $.each(usuarios, function (i, usuario) {
        $lista.append(
                $('<a href="/usuario?idUsuario=' + usuario.id + '" class="list-group-item">').append(
                $('<button class="btn btn-warning glyphicon glyphicon-pencil" name="btn-editar-gerente" value="' + usuario.id + '">'))
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
            if (data > 0) {
                alterarModal('Este usuário tem serviços em sua supervisão. Se deseja inativá-lo, por favor, altere os serviços aos quais este usuário supervisiona.', 'Inativar', true, 'Inativar');
            } else {
                alterarModal('Deseja realmente inativar este usuário?', 'Inativar', false, 'Inativar');
            }
        $('#modalCoringa').modal();
        });

        e.preventDefault();
    });
}
;

function alterarModal(mensagem, titulo, displayBotaoModal, textoBotaoFecharModal) {

    if (displayBotaoModal) {
        $('#btnPrincipal').hide();
    }
    if (!displayBotaoModal) {
        $('#btnPrincipal').show();
    }

    $('#modalCabecalho').text(titulo);
    $('#modalCorpo').text(mensagem);
    $('#btnPrincipal').text(textoBotaoFecharModal);
}
;

function chamarExclusao($button) {
    var idObjetoAtual = parseInt($button.val());
    var values = {
        idUsuario: idObjetoAtual
    };
    excluirUsuario(values);
}

function excluirUsuario(values) {

    var idExclusao = parseInt(values.idUsuario);
    $.ajax({
        url: '/inativar-usuario',
        type: 'POST',
        data: values
    }).done(function (res) {
        if (res) {
            excluirListItem(idExclusao);
        }
    });
}

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

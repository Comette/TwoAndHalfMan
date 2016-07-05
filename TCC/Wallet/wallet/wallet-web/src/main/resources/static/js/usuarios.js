'use strict';


$(function () {
    fazerRequestUsuarios();
});



function appendarUsuariosNaLista(usuarios, $lista) {
    $lista.html('');
    $.each(usuarios, function (i, usuario) {
        var $listGroup = $('<a href="/usuario?idUsuario=' + usuario.id + '" class="list-group-item">');
        var $buttonExcluir = $('<button class="btn btn-danger glyphicon glyphicon-remove" name="btn-excluir-gerente" value="' + usuario.id + '">');
        var $small = $('<small>').text('( ' + usuario.permissao + ' ) ').attr('style', 'font-weight: bold; margin-left: 10px;');
        var $btnEditar = $('<a class="btn btn-warning glyphicon glyphicon-pencil" name="btn-editar-gerente" href="/editar-usuario?idUsuario=' + usuario.id + '">');
        var $h4 = $('<h4>').text(usuario.username);

        if (usuario.situacao === 'INATIVO')
            $lista.append($listGroup.append($btnEditar).append($h4).append($small));
        else
            $lista.append($listGroup.append($btnEditar).append($h4).append($buttonExcluir).append($small));
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

            alterarModal(texto, 'Inativar', false,'Inativar');
            $('#modalCoringa').modal('show');
        });

        e.preventDefault();
    });
}
;


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

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
;

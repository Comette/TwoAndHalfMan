'use strict';

var $containerUsuarios1 = $('#lista-gerentes-1');
var $containerUsuarios2 = $('#lista-gerentes-2');
var idUsuario;

$(function () {
    idUsuario = $('#inputIdUsuario').val();
    fazerRequestUsuarios();
});

var appendarUsuariosNaLista = function(usuarios, $lista) {
    $lista.html('');
    $.each(usuarios, function (i, usuario) {
        var $listGroup = $('<a href="/usuario?idClient=' + usuario.id + '" class="list-group-item">');
        var $buttonExcluir = $('<button class="btn btn-danger glyphicon glyphicon-remove" name="btn-excluir-gerente" value="' + usuario.id + '">');
        var $small = $('<small>').text('( ' + usuario.permission + ' ) ').attr('style', 'font-weight: bold; margin-left: 10px;');
        var $btnEditar = $('<a class="btn btn-warning glyphicon glyphicon-pencil" name="btn-editar-gerente" href="/editar-usuario?idClient=' + usuario.id + '">');
        var $h4 = $('<h4>').text(usuario.username);
        debugger;
        if (usuario.id === parseInt(idUsuario))
            $lista.append($listGroup.append($btnEditar).append($h4).append($small));
        else
            $lista.append($listGroup.append($btnEditar).append($h4).append($buttonExcluir).append($small));
    });
}
;

var fazerRequestUsuarios = function() {
    AJAXgetByUrl('/buscar-usuarios-ativos').done(function (data) {
        
        var listas = separarNasDuasListas(data);
        
        appendarUsuariosNaLista(listas[0], $containerUsuarios1);
        appendarUsuariosNaLista(listas[1], $containerUsuarios2);

        adicionarOnClickExcluir($('button[name="btn-excluir-gerente"]'), 'USUARIO');
        
        $('#btnPrincipal').click(function () {
            chamarExclusao($(this),'USUARIOS');
        });
    });
}
;

var separarNasDuasListas = function (data) {
    var listaPai = [];
    listaPai[0] = data.splice(0, Math.ceil((data.length / 2)));
    listaPai[1] = data;
    
    return listaPai;
};

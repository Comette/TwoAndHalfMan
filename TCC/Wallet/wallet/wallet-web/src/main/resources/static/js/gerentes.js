'use strict';

var $lista1 = $('#lista-gerentes-1');
var $lista2 = $('#lista-gerentes-2');

var $btnEditar = $('button[name="btn-editar-gerente"');
var $btnExcluir = $('button[name="btn-excluir-gerente"');

$(function(){
   debugger;
    $.ajax({
        type: 'GET',
        url: '/buscar-gerentes'
    }).done(function(data){
        var lista1 = [];
        var lista2 = [];
        debugger;
        lista1 = data.splice(0,(data.length / 2).toFixed(1));
        lista2 = data;
        
        appendarGerentesNaLista(lista1,$lista1);
        appendarGerentesNaLista(lista2,$lista2);
    });
});

function appendarGerentesNaLista(gerentes, $lista) {
    $.each(gerentes, function(i,gerente){
    $lista.append(
                $('<a href="/gerente?idGerente=' + gerente.id +'" class="list-group-item">').append(
                    $('<button class="btn btn-warning glyphicon glyphicon-pencil" name="btn-editar-gerente">'))
                .append($('<h4>').text(gerente.nome))
                .append('<button class="btn btn-danger glyphicon glyphicon-remove" name="btn-excluir-gerente">'));
    });
}
/* 
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
'use strict';

var $form = $('#form-cotacao');
var $moeda = $('#moeda-selecionada');
var $cotacao = $('#cotacao');
var $alimentar = $('#alimentar-banco');
var $confirmarAlimentacao = $('#confirma-alimentar');

var confirmarAlimentacaoBanco = function () {   
    $('#mensagem').modal();
};

var alimentarBanco = function () {
    $.ajax({
        url: "/alimentar",
        type: "GET"
    }).done(function (res) {
        $('#conteudo-mensagem-confirmacao').text(res);
        $('#mensagem-confirmacao').modal();
    });
};

var cotar = function (moeda) {
    $.ajax({
        url: "/cotar",
        type: "POST",
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(moeda)
    }).done(function (data) {
        $moeda.text(moeda);
        $cotacao.text(data);
    });
};

$(function () {
    
    $form.submit(function (e) {
        var moeda = e.target[0].value;
        cotar(moeda);
        e.preventDefault();
    });

    $alimentar.click(function () {
        confirmarAlimentacaoBanco();
    });

    $confirmarAlimentacao.click(function () {
        alimentarBanco();        
    });
});


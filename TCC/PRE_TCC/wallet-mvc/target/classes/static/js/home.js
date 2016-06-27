/* 
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
'use strict';

var $form = $('#form-cotacao');
var $moeda = $('#moeda-selecionada');
var $cotacao = $('#cotacao');

var $formMedia = $('#form-media');
var $moedaMedia = $('#moeda-selecionada-media');
var $cotacaoMedia = $('#cotacao-media');

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

var media = function (moeda) {
    $.ajax({
        url: "/media",
        type: "POST",
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(moeda)
    }).done(function (data) {
        $moedaMedia.text(moeda);
        var soma = data.reduce(function(anterior, atual){ return anterior + atual; }, 0);
        var media = accounting.toFixed((soma / data.length), 6);        
        $cotacaoMedia.text(media);
    });
};

$(function () {
    
    $form.submit(function (e) {
        var moeda = e.target[0].value;
        cotar(moeda);
        e.preventDefault();
    });
    
    $formMedia.submit(function (e) {
        var moeda = e.target[0].value;
        media(moeda);
        e.preventDefault();
    });
});


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

var $gastoTotalAtualUSD = $('#gt-atual-usd');
var $gastoTotalAtualBRL = $('#gt-atual-brl');

var $gastoTotalProximoMesUSD = $('#gt-proximo-usd');
var $gastoTotalProximoMesBRL = $('#gt-proximo-brl');

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
        var media = accounting.toFixed(data, 6);
        $cotacaoMedia.text(media);
    });
};

var getGastoTotalAtual = function(){
    $.ajax({
        url: "/gasto-mensal",
        type: "GET"
    }).done(function(res){
        var usd = accounting.toFixed(res.usd, 6);
        $gastoTotalAtualUSD.text(usd);
        var brl = accounting.toFixed(res.brl, 6);
        $gastoTotalAtualBRL.text(brl);
    });
};

var getGastoTotalProximoMes = function(){
    $.ajax({
        url: "/gasto-mensal-proximo-mes",
        type: "GET"
    }).done(function(res){
        var usd = accounting.toFixed(res.usd, 6);
        $gastoTotalProximoMesUSD.text(usd);
        var brl = accounting.toFixed(res.brl, 6);
        $gastoTotalProximoMesBRL.text(brl);
    });
};

var esperadoAtualUSD

$(function () {
    
    getGastoTotalAtual();
    getGastoTotalProximoMes();
    
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


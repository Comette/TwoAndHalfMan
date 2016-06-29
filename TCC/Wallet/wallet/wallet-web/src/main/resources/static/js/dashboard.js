'use strict';

var $containerMesAtual = $('#container-mes-atual');
var listaServicosMesAtual;
var $containerGraficoMesAtual = $('#grafico-mes-atual');

var $containerProximoMes = $('#container-proximo-mes');
var listaServicosProximoMes;
var $containerGraficoProximoMes = $('#grafico-proximo-mes');

var $nomeServicoMaisCaro = $('#servico-mais-caro-nome');
var $valorServicoMaisCaro = $('#servico-mais-caro-valor');

var $gastoTotalMesAtual = $('#preco-total-mes');
var $gastoTotalProximoMes = $('#preco-total-proximo-mes');

var paginaAtual = 0;

var rederizaListaServicos = function (containerLista, servicos) {
    $.each(servicos, function (i, servico) {
        containerLista.find('#services-container-list').append(
                $('<section>').addClass('col-md-6').addClass('single-service-container').addClass('list-group-item')
                .append($('<h5>').addClass('service-name').addClass('text-center').text(servico.nome))
                .append($('<h5>').addClass('service-value').addClass('text-center').text('R$ ' + servico.custoMensal))
                .append($('<a>').addClass('btn').addClass('btn-warning').addClass('service-edit-btn')
                        .append($('<span>').addClass('glyphicon').addClass('glyphicon-edit').attr('aria-hidden', true))
                        )
                .append($('<a>').addClass('btn').addClass('btn-danger').addClass('service-delete-btn')
                        .append($('<span>').addClass('glyphicon').addClass('glyphicon-trash').attr('aria-hidden', true))
                        )
                );
    });
};



var getDadosDashboard = function () {
    $.ajax({
        url: "/dashboard?page=0",
        type: "GET"
    }).done(function (dados) {
        //$nomeServicoMaisCaro
        //$valorServicoMaisCaro
        $gastoTotalMesAtual.text(dados.gastoTotalAtual);
        $gastoTotalProximoMes.text(dados.gastoTotalProximoMes);
        listaServicosMesAtual = dados.servicosMesAtual;
        rederizaListaServicos($containerMesAtual, listaServicosMesAtual);
        listaServicosProximoMes = dados.servicosProximoMes;
        rederizaListaServicos($containerProximoMes, listaServicosProximoMes);
    });
};

$(function () {
    getDadosDashboard();
    buscarDadosEChamarGraficos($containerGraficoMesAtual, $containerGraficoProximoMes);
});

function setarOnClickBotaoVerMais(){
    $('#btnVerMais').click(function(){
        
    });
}
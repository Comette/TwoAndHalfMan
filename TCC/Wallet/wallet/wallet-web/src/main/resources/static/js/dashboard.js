'use strict';

// MES ATUAL
var $containerMesAtual = $('#container-mes-atual');
var $containerGraficoMesAtual = $('#grafico-mes-atual');
var $gastoTotalMesAtual = $('#preco-total-mes');
var $verMaisServicosMesAtual = $('#btnVerMaisAtual');
var paginaAtualMesAtual = 0;
var listaServicosMesAtual;

// PROXIMO MES
var $containerProximoMes = $('#container-proximo-mes');
var $containerGraficoProximoMes = $('#grafico-proximo-mes');
var $gastoTotalProximoMes = $('#preco-total-proximo-mes');
var $verMaisServicosProximoMes = $('#btnVerMaisProximo');
var paginaAtualProximoMes = 0;
var listaServicosProximoMes;

//GERAL
var $nomeServicoMaisCaro = $('#servico-mais-caro-nome');
var $valorServicoMaisCaro = $('#servico-mais-caro-valor');

var $gastoTotalMesAtual = $('#preco-total-mes');
var $gastoTotalProximoMes = $('#preco-total-proximo-mes');

var paginaAtualFiltrado = 0;


var $btnPesquisarAtual = $('#btnPesquisarAtual');
var $btnPesquisarProximo = $('#btnPesquisarProximo');

var $formAtual = $('#formFiltrarAtual');
var $formAtualSelect = $('#formFiltrarAtual select');
var $formProximo = $('#formFiltrarProximo');
var $formProximoSelect = $('#formFiltrarProximo select');

var select = $('#select-gerentes');

var rederizaListaServicos = function (containerLista, servicos) {
    $.each(servicos, function (i, servico) {
        containerLista.find('#services-container-list').append(
                $('<section>').addClass('col-md-6').addClass('single-service-container').addClass('list-group-item')
                .append($('<h5>').addClass('service-name').addClass('text-center').text(servico.nome))
                .append($('<h5>').addClass('service-value').addClass('text-center').text(accounting.formatMoney(servico.custoMensal, "R$ ", 2, ".", ",")))
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
        $nomeServicoMaisCaro.text(dados.servicoMaisCaroContratado.nome + " - ");
        $valorServicoMaisCaro.text(accounting.formatMoney(dados.servicoMaisCaroContratado.custoMensal, "R$ ", 2, ".", ","));
        $gastoTotalMesAtual.text(accounting.formatMoney(dados.gastoTotalAtual, "R$ ", 2, ".", ","));
        $gastoTotalProximoMes.text(accounting.formatMoney(dados.gastoTotalProximoMes, "R$ ", 2, ".", ","));
        listaServicosMesAtual = dados.servicosMesAtual;
        rederizaListaServicos($containerMesAtual, listaServicosMesAtual);
        listaServicosProximoMes = dados.servicosProximoMes;
        rederizaListaServicos($containerProximoMes, listaServicosProximoMes);
    });
};

var getProxPaginaServicosMesAtual = function () {
    var valor = $formAtual.children('select').val();
    var url = '/servicos-mes-atual?page=' + ++paginaAtualMesAtual;
    if (paginaAtualFiltrado !== 0) {
        url = '/filtrar-por-gerente?idGerente=' + valor + '&page=' + paginaAtualFiltrado;
    }

    $.ajax({
        type: 'GET',
        url: url
    }).done(function (data) {
        if (data.length < 4) {
            $('#btnVerMaisAtual').text("Não existem mais serviços");
            $('#btnVerMaisAtual').addClass('disabled');
        }
        rederizaListaServicos($containerMesAtual, data);
    });
};

var getProxPaginaServicosProximoMes = function () {
    $.ajax({
        type: 'GET',
        url: '/servicos-proximo-mes?page=' + ++paginaAtualProximoMes
    }).done(function (data) {
        if (data.length < 4) {
            $('#btnVerMaisProximo').text("Não existem mais serviços");
            $('#btnVerMaisProximo').addClass('disabled');
        }
        rederizaListaServicos($containerProximoMes, data);
    });
};

$(function () {

    $formAtual.hide();
    $formProximo.hide();

    getDadosDashboard();

    buscarDadosEChamarGraficos($containerGraficoMesAtual, $containerGraficoProximoMes);

    $verMaisServicosMesAtual.click(function () {
        getProxPaginaServicosMesAtual();
    });

    $verMaisServicosProximoMes.click(function () {
        getProxPaginaServicosProximoMes();
    });

    setarOnClickBotaoPesquisar($btnPesquisarAtual, $btnPesquisarProximo);
    buscaGerentes();
});

function setarOnClickBotaoPesquisar($btnPesquisarAtual, $btnPesquisarProximo) {
    debugger;
    $btnPesquisarAtual.click(function () {
        $formAtual.fadeIn(2000);
    });
    $btnPesquisarProximo.click(function () {
        $formProximo.fadeIn(2000);
    });

}

function buscaGerentes() {
    $.ajax({
        type: 'GET',
        url: '/buscar-gerentes'
    }).done(function (data) {

        $.each(data, function (i, gerenteDTO) {
            select.append($('<option>').val(gerenteDTO.id).text(gerenteDTO.nome));
        });
    });
}

$formAtual.submit(function (e) {
    var valor = $formAtual.children('select').val();
    debugger;
    $.ajax({
        type: 'GET',
        url: '/filtrar-por-gerente?idGerente=' + valor + '&page=' + paginaAtualFiltrado
    }).done(function (data) {
        paginaAtualFiltrado++;
        rederizaListaServicos($containerMesAtual, data);
    });
    e.preventDefault();
});


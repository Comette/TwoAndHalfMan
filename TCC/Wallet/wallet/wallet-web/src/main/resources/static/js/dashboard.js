'use strict';
// MES ATUAL
//containeres
var $containerMesAtual = $('#container-mes-atual');
var $containerGraficoMesAtual = $('#grafico-mes-atual');
//spans
var $gastoTotalMesAtual = $('#preco-total-mes');
//buttons
var $verMaisServicosMesAtual = $('#btnVerMaisAtual');
var $btnPesquisarAtual = $('#btnPesquisarAtual');
//paginação
var paginaAtualMesAtual = 0;
var paginaAtualEsteMesFiltrado = 0;
//filtragem
var filtroAtual = null;
var $formAtual = $('#formFiltrarAtual');
var $formAtualSelect = $('#formFiltrarAtual select');
//listagem
var listaServicosMesAtual;
// PROXIMO MES
//containeres
var $containerProximoMes = $('#container-proximo-mes');
var $containerGraficoProximoMes = $('#grafico-proximo-mes');
//spans
var $gastoTotalProximoMes = $('#preco-total-proximo-mes');
//buttons
var $verMaisServicosProximoMes = $('#btnVerMaisProximo');
var $btnPesquisarProximo = $('#btnPesquisarProximo');
//paginação
var paginaAtualProximoMes = 0;
var paginaAtualProximoMesFiltrado = 0;
//filtragem
var filtroProximoMes = null;
var $formProximo = $('#formFiltrarProximo');
var $formProximoSelect = $('#formFiltrarProximo select');
//listagem
var listaServicosProximoMes;
//GERAL
var roleUsuarioLogado = $('#role-usuario-logado').val();
var $servicoMaisCaro = $('#servico-mais-caro');

var getDadosDashboard = function () {
    $.ajax({
        url: "/dashboard?page=0",
        type: "GET"
    }).done(function (dados) {
        limparContainer($servicoMaisCaro);
        $servicoMaisCaro.append($('<a>').html($('<h1>').attr('style', 'display: inline; color: #FBAF41; font-weight: bold;').text(dados.servicoMaisCaroContratado.nome)).attr('href', '/servico?idServico=' + dados.servicoMaisCaroContratado.id))
                .append($('<h1>').attr('style', 'display: inline; color: #434343; font-weight: bold;').text(" - " + accounting.formatMoney(dados.servicoMaisCaroContratado.custoMensal, "R$ ", 2, ".", ",")));
        $gastoTotalMesAtual.text(accounting.formatMoney(dados.gastoTotalAtual, "R$ ", 2, ".", ","));
        $gastoTotalProximoMes.text(accounting.formatMoney(dados.gastoTotalProximoMes, "R$ ", 2, ".", ","));
        listaServicosMesAtual = dados.servicosMesAtual;
        limparContainer($containerMesAtual.find('#services-container-list'));
        toggleBtnVerMais('Ver Mais', $verMaisServicosMesAtual, true);
        renderizaListaServicos($containerMesAtual, listaServicosMesAtual);
        limparContainer($containerProximoMes.find('#services-container-list'));
        toggleBtnVerMais('Ver Mais', $verMaisServicosProximoMes, true);
        listaServicosProximoMes = dados.servicosProximoMes;
        renderizaListaServicos($containerProximoMes, listaServicosProximoMes);
        adicionarOnClickExcluirServico($('.service-delete-btn'));
        setarOnClickBotoesVerMais();
        $('#btnPrincipal').click(function () {
            chamarExclusao($(this), false);
        });
    });
};

$(function () {
    $formAtual.hide();
    $formProximo.hide();
    getDadosDashboard();
    buscarDadosEChamarGraficos($containerGraficoMesAtual, $containerGraficoProximoMes);
    setarOnClickBotaoPesquisar($btnPesquisarAtual, $btnPesquisarProximo);
    buscaGerentes();
});

var setarOnClickBotaoPesquisar = function ($btnPesquisarAtual, $btnPesquisarProximo) {
    $btnPesquisarAtual.click(function () {
        $formAtual.toggle(250, 'linear');
    });
    $btnPesquisarProximo.click(function () {
        $formProximo.toggle(250, 'linear');
    });
};

var setarOnClickBotoesVerMais = function () {
    $verMaisServicosMesAtual.click(function () {
        getProxPaginaServicos($containerMesAtual, 'ATUAL', filtroAtual, $verMaisServicosMesAtual);
    });
    $verMaisServicosProximoMes.click(function () {
        getProxPaginaServicos($containerProximoMes, 'PROXIMO', filtroProximoMes, $verMaisServicosProximoMes);
    });
};


{
    $formAtual.submit(function (e) {
        var idGerente = $formAtualSelect.val();
        paginaAtualEsteMesFiltrado = -1;
        filtroAtual = idGerente;
        limparContainer($containerMesAtual.find('#services-container-list'));
        $verMaisServicosMesAtual.removeClass('disabled');
        $verMaisServicosMesAtual.text('Ver mais');
        getProxPaginaServicos($containerMesAtual, 'ATUAL', filtroAtual, $verMaisServicosMesAtual);
        e.preventDefault();
    });
    $formProximo.submit(function (e) {
        var idGerente = $formProximoSelect.val();
        paginaAtualProximoMesFiltrado = -1;
        filtroProximoMes = idGerente;
        limparContainer($containerProximoMes.find('#services-container-list'));
        $verMaisServicosProximoMes.removeClass('disabled');
        $verMaisServicosProximoMes.text('Ver mais');
        getProxPaginaServicos($containerProximoMes, 'PROXIMO', filtroProximoMes, $verMaisServicosProximoMes);
        e.preventDefault();
    });
}


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
var $servicoMaisCaro = $('#servico-mais-caro');

var getDadosDashboard = function () {
    AJAXgetByUrl("/dashboard?page=0").done(function (dados) {
        limparContainer($servicoMaisCaro);
        $servicoMaisCaro.append($('<a>').html($('<h1>').attr('style', 'display: inline; color: #FBAF41; font-weight: bold;').text(dados.mostExpensiveContract.name)).attr('href', '/servico?idContract=' + dados.mostExpensiveContract.id))
                .append($('<h1>').attr('style', 'display: inline; color: #434343; font-weight: bold;').text(" - " + accounting.formatMoney(dados.mostExpensiveContract.monthlyExpense, "R$ ", 2, ".", ",")));

        $gastoTotalMesAtual.text(accounting.formatMoney(dados.thisMonthAmountExpense, "R$ ", 2, ".", ","));
        $gastoTotalProximoMes.text(accounting.formatMoney(dados.nextMonthAmountExpense, "R$ ", 2, ".", ","));

        listaServicosMesAtual = dados.thisMonthContractDTOs;
        listaServicosProximoMes = dados.nextMonthContractDTOs;

        limparContainer($containerMesAtual.find('#services-container-list'));
        toggleBtnVerMais('Ver Mais', $verMaisServicosMesAtual, true);
        renderizaListaServicos($containerMesAtual, listaServicosMesAtual);

        limparContainer($containerProximoMes.find('#services-container-list'));
        toggleBtnVerMais('Ver Mais', $verMaisServicosProximoMes, true);
        renderizaListaServicos($containerProximoMes, listaServicosProximoMes);

        adicionarOnClickExcluir($('.service-delete-btn'), 'SERVICO');

        setarOnClickBotoesVerMais();

        $('#btnPrincipal').click(function () {
            chamarExclusao($(this), 'SERVICO');
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

var formSubmit = function ($form, $formSelect, $container, $btnVerMais, paginaAtualFiltrado, mes) {
    $form.submit(function (e) {
        var idGerente = $formSelect.val();
        paginaAtualFiltrado = -1;
        filtroAtual = idGerente;
        limparContainer($container.find('#services-container-list'));
        toggleBtnVerMais('Ver mais', $btnVerMais, true);
        getProxPaginaServicos($container, mes, filtroAtual, $btnVerMais);
        e.preventDefault();
    });
};

{
    formSubmit($formAtual,$formAtualSelect,$containerMesAtual,$verMaisServicosMesAtual, paginaAtualEsteMesFiltrado, 'ATUAL');
    formSubmit($formProximo,$formProximoSelect,$containerProximoMes,$verMaisServicosProximoMes,paginaAtualProximoMesFiltrado, 'PROXIMO');
//    $formAtual.submit(function (e) {
//        var idGerente = $formAtualSelect.val();
//        paginaAtualEsteMesFiltrado = -1;
//        filtroAtual = idGerente;
//        limparContainer($containerMesAtual.find('#services-container-list'));
//        toggleBtnVerMais('Ver mais', $verMaisServicosMesAtual, true);
//        getProxPaginaServicos($containerMesAtual, 'ATUAL', filtroAtual, $verMaisServicosMesAtual);
//        e.preventDefault();
//    });
//    $formProximo.submit(function (e) {
//        var idGerente = $formProximoSelect.val();
//        paginaAtualProximoMesFiltrado = -1;
//        filtroProximoMes = idGerente;
//        limparContainer($containerProximoMes.find('#services-container-list'));
//        toggleBtnVerMais('Ver mais', $verMaisServicosProximoMes, true);
//        getProxPaginaServicos($containerProximoMes, 'PROXIMO', filtroProximoMes, $verMaisServicosProximoMes);
//        e.preventDefault();
//    });
}

var getProxPaginaServicos = function ($container, mes, filtroAtual, $verMaisAtual) {
    var url = verificaURL(mes, filtroAtual);
    AJAXgetByUrl(url).done(function (data) {
        if (typeof data === 'string') {
            location.reload();
        } else {
            if (data.length < 4) {
                toggleBtnVerMais('Não existem mais serviços.', $verMaisAtual, false);
            }
            renderizaListaServicos($container, data);
            adicionarOnClickExcluir($('.service-delete-btn'), 'SERVICO');
        }
    });
};

var verificaURL = function (mes, filtroAtual) {
    if (mes === 'ATUAL') {
        return filtroAtual !== null ?
                '/servicos-mes-atual?idUsuario=' + filtroAtual + '&page=' + ++paginaAtualEsteMesFiltrado
                : '/servicos-mes-atual?page=' + ++paginaAtualEsteMesFiltrado;
    } else if (mes === 'PROXIMO') {
        return filtroAtual !== null ?
                '/servicos-proximo-mes?idUsuario=' + filtroAtual + '&page=' + ++paginaAtualProximoMesFiltrado
                : '/servicos-proximo-mes?page=' + ++paginaAtualProximoMesFiltrado;
    }
};

var buscaGerentes = function () {
    AJAXgetByUrl('/buscar-usuarios-ativos').done(function (data) {
        $.each(data, function (i, clientDTO) {
            $formAtualSelect.append($('<option>').val(clientDTO.id).text(clientDTO.name));
            $formProximoSelect.append($('<option>').val(clientDTO.id).text(clientDTO.name));
        });
    });
};
'use strict';

var $containerMesAtual = $('#container-mes-atual');
var listaServicosMesAtual;

var $containerProximoMes = $('#container-proximo-mes');
var listaServicosProximoMes;

var $nomeServicoMaisCaro = $('#servico-mais-caro-nome');
var $valorServicoMaisCaro = $('#servico-mais-caro-valor');

var $gastoTotalMesAtual = $('#preco-total-mes');
var $gastoTotalProximoMes = $('#preco-total-proximo-mes');

// implementando
function renderizaGrafico(servicos) {
    var ctx = $('#grafico');
    var data = [];
    $.each(servicos, function(i,servico){
        data[i] = {
            value : servico.porcentagemCustoTotal,
            color : "#FBAF41",
            highlight : "orange",
            label : servico.nome
        };
    });
    
    var piechart = new Chart(ctx).Pie(data);
}

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
        url: "/dashboard",
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
        renderizaGrafico(dados.servicosMesAtual);
    });
};

$(function () {
    getDadosDashboard();
});
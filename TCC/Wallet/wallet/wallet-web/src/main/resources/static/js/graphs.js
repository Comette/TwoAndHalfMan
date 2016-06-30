function renderizarGrafico($container, listaServicos) {
    var labels = [];
    var data = [];
    var colors = [
        '#FBAF41',
        '#B0B5B6',
        '#434343',
        '#D1603D',
        '#FCB97D',
        '#EDD892',
        '#272838',
        '#E5E059',
        '#BDD358',
        '#6D213C',
        '#946846',
        '#729B79',
        '#9F7E69'
    ];
    $.each(listaServicos, function (i, servico) {
        labels[i] = servico.nome;
        data[i] = accounting.toFixed(servico.porcentagemCustoTotal, 2);
    });

    var ctx = $container;
    var grafico = new Chart(ctx, {
        type: 'pie',
        animation: {
            animateRotate: true
        },
        data: {
            labels: labels,
            datasets: [{
                    data: data,
                    backgroundColor: colors
                }]
        }
    });
}

function buscarDadosEChamarGraficos($container1, $container2) {
    $.ajax({
        url: '/servicos-inflar-grafico',
        type: 'GET'
    }).done(function (data) {
        renderizarGrafico($container1, data.servicosDesteMes);
        renderizarGrafico($container2, data.servicosProximoMes);
    });
}

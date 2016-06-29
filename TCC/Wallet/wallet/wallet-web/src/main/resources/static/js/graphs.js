function renderizarGrafico($container, listaServicos) {
    var labels = [];
    var data = [];
    $.each(listaServicos, function (i, servico) {
        labels[i] = servico.nome;
        data[i] = accounting.toFixed(servico.porcentagemCustoTotal, 2);
    });

    var ctx = $container;
    var grafico = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                    label: 'Custo Mensal',
                    data: data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 1)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)'
                    ],
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
            }
        }
    });
}

function buscarDadosEChamarGraficos($container1, $container2){
    $.ajax({
       url: '/servicos-inflar-grafico',
       type: 'GET'
    }).done(function(data){
        renderizarGrafico($container1,data.servicosDesteMes);
        renderizarGrafico($container2, data.servicosProximoMes);
    });
}

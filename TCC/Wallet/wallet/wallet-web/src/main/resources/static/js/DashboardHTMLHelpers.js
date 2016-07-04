'use strict';

//geral
var roleUsuarioLogado = $('#role-usuario-logado').val();

var renderizaListaServicos = function ($containerLista, servicos) {
    {
        var res;
        var botoes;
        var custoMensal;
        var nomeUsuario;
        var $div = $('<div>');
        var $a = $('<a>');
        var $h5 = $('<h5>');
        var $lista = $containerLista.find('#services-container-list');
        var $section = $('<section>').fadeIn(400).addClass('col-md-6').addClass('single-service-container').addClass('list-group-item');
                       
    }
    $.each(servicos, function (i, servico) {

        res = servico.nome.length > 13 ? servico.nome.substring(0, 11) + '...' : servico.nome;
        custoMensal = accounting.formatMoney(servico, custoMensal, "R$ ", 2, ".", ",");
        botoes = retornarBotoesConfigurados(servico);
        nomeUsuario = servico.nomeUsuarioResponsavel;
        
        $lista.append(
                $section.append(
                    $div.append(
                        $div.addClass('text-center').attr('style', 'border: 1px solid #e6e6e6; border-radius: 0px;').append(
                            $a.html($h5.addClass('service-name').text(servico.nome)).attr('href', '/servico?idServico=' + servico.id)).append(
                            $h5.text(nomeUsuario).addClass('word-break')).append(
                            $h5.text(servico.situacao)).append(
                            $h5.addClass('service-value').text(custoMensal))
                        )
                    ).append(
                        $div.attr('style','margin-bottom:30px;').append(
                            botoes[1]).append(
                            botoes[0]
                        )
                    )
                );
        });
};

var retornarBotoesConfigurados = function (servico) {
    var $buttons = [];
    var $span = $('<span>').attr('aria-hidden', true).addClass('glyphicon');
    var $btnDelete = $('<button>').addClass('btn').addClass('btn-danger').addClass('service-delete-btn');
    var $btnEdit = $('<button>').addClass('btn').addClass('btn-warning').addClass('service-edit-btn');

    if (servico.situacao === 'CANCELADO' || roleUsuarioLogado === 'GERENTE') {
        $btnDelete = $btnDelete.attr('value', servico.id).append($span.addClass('glyphicon-trash')).addClass('disabled');
        $btnEdit = $btnEdit.attr('action', '/editar-servico?idServico=' + servico.id).append($span.addClass('glyphicon-pencil')).addClass('disabled');
    } else {
        $btnDelete = $btnDelete.attr('value', servico.id).append($span.addClass('glyphicon-trash'));
        $btnEdit = $btnEdit.attr('action', '/editar-servico?idServico=' + servico.id).append($span.addClass('glyphicon-pencil'));
    }

    $buttons[0] = $btnDelete;
    $buttons[1] = $btnEdit;

    return $buttons;

};

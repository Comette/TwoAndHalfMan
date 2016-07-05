'use strict';

//geral
var roleUsuarioLogado = $('#role-usuario-logado').val();

var renderizaListaServicos = function ($containerLista, servicos) {
    {
        var res;
        var custoMensal;
        var nomeUsuario;
        var $lista = $containerLista.find('#services-container-list');
        var checkSituacao;
    }
    $.each(servicos, function (i, servico) {

        res = servico.nome.length > 13 ? servico.nome.substring(0, 11) + '...' : servico.nome;
        custoMensal = accounting.formatMoney(servico.custoMensal, "R$ ", 2, ".", ",");
        nomeUsuario = servico.nomeUsuarioResponsavel;
        checkSituacao = servico.situacao === 'CANCELADO' || roleUsuarioLogado === 'GERENTE';
        
        
        $lista.append(
                $('<section>').fadeIn(400).addClass('col-md-6').addClass('single-service-container').addClass('list-group-item')
                .append($('<div>')
                        .append($('<div>').addClass('text-center').attr('style', 'border: 0.2px solid #B0B5B8; border-radius: 0px;')
                                .append($('<a>').html($('<h5>').addClass('service-name').text(res)).attr('href', '/servico?idServico=' + servico.id))
                                .append($('<h5>').text(servico.nomeUsuarioResponsavel).attr('style','color: #434343; font-family: Open Sans, sans-serif;').addClass('word-break'))
                                .append($('<h6>').text(servico.situacao).attr('style','color: #777777;').addClass('word-break'))
                                .append($('<h5>').addClass('service-value').text(custoMensal)))
                        )
                
                .append($('<div>').attr('style', 'margin-bottom: 30px;')
                        .append($('<a>').addClass('btn').addClass('btn-warning').addClass('service-edit-btn').addClass(checkSituacao ? 'disabled' : '').attr('href','/editar-servico?idServico=' + servico.id)
                                .append($('<span>').addClass('glyphicon').addClass('glyphicon-pencil').attr('aria-hidden', true))
                                )
                        .append($('<button>').addClass('btn').addClass('btn-danger').addClass('service-delete-btn').addClass(checkSituacao ? 'disabled' : '').attr('value',servico.id)
                                .append($('<span>').addClass('glyphicon').addClass('glyphicon-trash').attr('aria-hidden', true))
                                )


                        ));
    });
};
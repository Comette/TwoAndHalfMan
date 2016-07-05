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

        res = servico.name.length > 13 ? servico.name.substring(0, 11) + '...' : servico.name;
        custoMensal = accounting.formatMoney(servico.monthlyExpense, "R$ ", 2, ".", ",");
        nomeUsuario = servico.responsibleUserName;
        checkSituacao = servico.state === 'CANCELED' || roleUsuarioLogado === 'MANAGER';
        $lista.append(
                $('<section>').fadeIn(400).addClass('col-md-6').addClass('single-service-container').addClass('list-group-item')
                .append($('<div>')
                        .append($('<div>').addClass('text-center').attr('style', 'border: 0.2px solid #B0B5B8; border-radius: 0px;')
                                .append($('<a>').html($('<h5>').addClass('service-name').text(res)).attr('href', '/servico?idServico=' + servico.id))
                                .append($('<h5>').text(nomeUsuario).attr('style', 'color: #434343; font-family: Open Sans, sans-serif;').addClass('word-break'))
                                .append($('<h6>').text(servico.state).attr('style', 'color: #777777;').addClass('word-break'))
                                .append($('<h5>').addClass('service-value').text(custoMensal)))
                        )

                .append($('<div>').attr('style', 'margin-bottom: 30px;')
                        .append($('<a>').addClass('btn').addClass('btn-warning').addClass('service-edit-btn').addClass(checkSituacao ? 'disabled' : '').attr('href', '/editar-servico?idServico=' + servico.id)
                                .append($('<span>').addClass('glyphicon').addClass('glyphicon-pencil').attr('aria-hidden', true))
                                )
                        .append($('<button>').addClass('btn').addClass('btn-danger').addClass('service-delete-btn').addClass(checkSituacao ? 'disabled' : '').attr('value', servico.id)
                                .append($('<span>').addClass('glyphicon').addClass('glyphicon-trash').attr('aria-hidden', true))
                                )


                        ));
    });
};

var toggleBtnVerMais = function (text, $btn, estadoClasse) {
    $btn.text(text).attr('style', 'margin-left: 31%; margin-right: 40%;');
    $btn.toggleClass('disabled');
    estadoClasse ? $btn.removeClass('disabled') : $btn.addClass('disabled');
};

var limparContainer = function ($container) {
    $container.html('');
};

var adicionarOnClickExcluir = function ($btn, entidade) {
    $btn.on("click", function (e) {
        var btnExclusao = $('#btnPrincipal');
        btnExclusao.val(parseInt($(this).val()));
        var texto;
        if (entidade === 'USUARIO') {
            texto = 'Deseja realmente inativar este usuário? Qualquer serviço supervisionado por ele será cancelado.';
        } else
            texto = 'Deseja realmente cancelar este serviço?';

        alterarModal(texto, 'Confirmar ação', false, 'Confirmar');
        mostrarModal();
        e.preventDefault();
    });
};

var mostrarModal = function () {
    $('#modalCoringa').modal('show');
};

var chamarExclusao = function ($button, entidade) {
    var idObjetoAtual = parseInt($button.val());

    var values = entidade === 'USUARIO' ? {idUsuario: idObjetoAtual} : {idServico: idObjetoAtual};
    excluirEntidade(values, entidade);
};

var excluirEntidade = function (values, entidade) {
    var url = entidade === 'USUARIO' ? '/inativar-usuario' : '/cancelar-servico';

    AJAXPost(url, values).done(function () {
        if (typeof getDadosDashboard() === 'undefined' || typeof fazerRequestUsuarios() === 'undefined') {
            alterarModal('Operação concluída com sucesso!', 'Sucesso', true, '');
            mostrarModal();
        } else
            entidade === 'USUARIO' ? fazerRequestUsuarios() : getDadosDashboard();
            alterarModal('Operação concluída com sucesso!', 'Sucesso', true, '');
            mostrarModal();
    });
};
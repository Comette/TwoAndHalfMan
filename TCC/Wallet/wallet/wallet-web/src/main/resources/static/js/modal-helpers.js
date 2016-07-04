'use strict';
function alterarModal(mensagem, titulo, displayBotaoModal, textoBotaoFecharModal) {

    if (displayBotaoModal) {
        $('#btnPrincipal').hide();
    }
    if (!displayBotaoModal) {
        $('#btnPrincipal').show();
    }

    $('#modalCabecalho').text(titulo);
    $('#modalCorpo').text(mensagem);
    $('#btnPrincipal').text(textoBotaoFecharModal);
}
;

function chamarExclusao($button, trueParaUsuarioEFalseParaServico) {
    var idObjetoAtual = parseInt($button.val());

    var values = trueParaUsuarioEFalseParaServico ? {idUsuario: idObjetoAtual} : {idServico: idObjetoAtual};
    excluirObjeto(values, trueParaUsuarioEFalseParaServico);
}

function excluirObjeto(values, trueParaUsuarioEFalseParaServico) {
    var url = trueParaUsuarioEFalseParaServico ? '/inativar-usuario' : '/cancelar-servico';

    var idExclusao = parseInt(values.idUsuario);
    $.ajax({
        url: url,
        type: 'POST',
        data: values
    }).done(function (res) {
        if (res) {
            if (trueParaUsuarioEFalseParaServico)
                excluirListItemUsuario(idExclusao);
        }
        alterarModal('Operação concluída com sucesso!', 'Sucesso', true, 'Fechar');
        $('#modalCoringa').modal();
        if (url === '/cancelar-servico')
            window.location.reload();

    });
}

function adicionarOnClickExcluirServico($btn) {
    $btn.on("click", function (e) {
        if ($(this).hasClass('disabled'))
            return false;
        else {
            var $btnExclusao = $('#btnPrincipal');
            $btnExclusao.val(parseInt($(this).val()));

            alterarModal('Deseja realmente cancelar este serviço?', 'Cancelar Serviço', false, 'Confirmar');
            $('#modalCoringa').modal();
        }
            e.preventDefault();
    });
}

function adicionarOnClickExcluir($btn) {
    $btn.on("click", function (e) {
        var btnExclusao = $('#btnPrincipal');
        btnExclusao.val(parseInt($(this).val()));

        $.ajax({
            url: '/count-servicos-by-usuario',
            type: 'GET',
            data: {
                idUsuario: btnExclusao.val()
            }
        }).done(function (data) {
            var texto =
                    data > 0 ? 'Este usuário tem serviços ativos em sua supervisão. Se inativá-lo, os serviços aos quais ele gerencia serão cancelados.' :
                    'Deseja realmente inativar este usuário?';

            alterarModal(texto, 'Inativar', false, 'Inativar');
            $('#modalCoringa').modal();
//            window.location.reload();
        });

        e.preventDefault();
    });
}
;

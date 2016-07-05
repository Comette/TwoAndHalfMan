'use strict';

var $lista1 = $('#lista-gerentes-1');
var $lista2 = $('#lista-gerentes-2');


function alterarModal(mensagem, titulo, displayBotaoModal, textoBotaoPrincipalModal) {

    var $btnPrincipal = $('#btnPrincipal');
    if (displayBotaoModal) {
        $btnPrincipal.hide();
    }
    if (!displayBotaoModal) {
        $btnPrincipal.show();
    }
    $('#modalCabecalho').text(titulo);
    $('#modalCorpo').text(mensagem);
    $btnPrincipal.text(textoBotaoPrincipalModal);
}
;

function chamarExclusao($button, trueParaUsuarioEFalseParaServico) {
    var idObjetoAtual = parseInt($button.val());

    var values = trueParaUsuarioEFalseParaServico ? {idUsuario: idObjetoAtual} : {idServico: idObjetoAtual};
    excluirObjeto(values, trueParaUsuarioEFalseParaServico);
}

function excluirObjeto(values, trueParaUsuarioEFalseParaServico) {
    var url = trueParaUsuarioEFalseParaServico ? '/inativar-usuario' : '/cancelar-servico';

    $.ajax({
        url: url,
        type: 'POST',
        data: values
    }).done(function () {
        if (trueParaUsuarioEFalseParaServico)
            fazerRequestUsuarios();
        else
            getDadosDashboard();
        alterarModal('Operação concluída com sucesso!', 'Sucesso', true, '');
    });
}
;
;


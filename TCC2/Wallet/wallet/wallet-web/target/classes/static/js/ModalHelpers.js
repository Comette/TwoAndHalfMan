'use strict';

var $lista1 = $('#lista-gerentes-1');
var $lista2 = $('#lista-gerentes-2');


var alterarModal = function(mensagem, titulo, displayBotaoModal, textoBotaoPrincipalModal) {

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
};

var chamarExclusao = function($button, entidade) {
    var idObjetoAtual = parseInt($button.val());

    var values = entidade === 'USUARIO' ? {idUsuario: idObjetoAtual} : {idServico: idObjetoAtual};
    excluirObjeto(values, entidade);
};

var excluirObjeto = function(values, entidade) {
    var url = entidade === 'USUARIO' ? '/inativar-usuario' : '/cancelar-servico';

    AJAXPost(url,values).done(function () {
        entidade === 'USUARIO' ? fazerRequestUsuarios() : getDadosDashboard();
        alterarModal('Operação concluída com sucesso!', 'Sucesso', true, '');
        mostrarModal();
    });
};
    



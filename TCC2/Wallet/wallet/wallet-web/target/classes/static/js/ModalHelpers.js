'use strict';

var $lista1 = $('#lista-gerentes-1');
var $lista2 = $('#lista-gerentes-2');


var alterarModal = function (mensagem, titulo, displayBotaoModal, textoBotaoPrincipalModal) {

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

var chamarExclusao = function ($button, entidade) {
    var idObjetoAtual = parseInt($button.val());

    var values = entidade === 'USUARIO' || entidade === 'USUARIOS' ? {idClient: idObjetoAtual} : {idContract: idObjetoAtual};

    excluirEntidade(values, entidade);
};




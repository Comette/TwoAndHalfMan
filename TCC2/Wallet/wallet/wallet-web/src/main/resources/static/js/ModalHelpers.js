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

    var values = entidade === 'USUARIO' || entidade === 'USUARIOS' ? {idUser: idObjetoAtual} : {idContract: idObjetoAtual};

    excluirEntidade(values, entidade);
};

//var excluirObjeto = function (values, entidade) {
//    var url = entidade === 'USUARIO' || entidade === 'USUARIOS' ? '/inativar-usuario' : '/cancelar-servico';
//
//    AJAXPost(url, values).done(function () {
//        switch (entidade) {
//            case 'USUARIO' || 'SERVICO':
//                window.location.reload();
//                break;
//            case 'USUARIOS':
//                fazerRequestUsuarios();
//                break;
//            case 'SERVICOS':
//                getDadosDashboard();
//                break;
//            default:
//                window.location.reload();
//
//        }
//        entidade === 'USUARIOS' ? fazerRequestUsuarios() : getDadosDashboard();
//        entidade === 'USUARIO' ? window.location.reload() : window.location.reload();
//        alterarModal('Operação concluída com sucesso!', 'Sucesso', true, '');
//        mostrarModal();
//    });
//};




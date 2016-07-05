'use strict';
$(function () {
    adicionarOnClickExcluir($('.btn-excluir-gerente'), 'USUARIO');
    $('#btnPrincipal').click(function () {
        chamarExclusao($(this), 'USUARIO');
    });
});



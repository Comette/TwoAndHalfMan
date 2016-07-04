'use strict';
$(function () {
    adicionarOnClickExcluir($('.btn-excluir-gerente'));
    $('#btnPrincipal').click(function () {
        chamarExclusao($(this), true);
    });
});



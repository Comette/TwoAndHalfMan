'use strict';
$(function () {
    adicionarOnClickExcluir($('.btn-desativar-servico'));
    $('#btnPrincipal').click(function () {
        chamarExclusao($(this), false);
    });
});


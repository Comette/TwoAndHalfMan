'use strict';
$(function () {
    adicionarOnClickExcluirServico($('.btn-desativar-servico'));
    $('#btnPrincipal').click(function () {
        chamarExclusao($(this), false);
    });
});


'use strict';
$(function () {
    adicionarOnClickExcluir($('.btn-desativar-servico'),'SERVICO');
    $('#btnPrincipal').click(function () {
        chamarExclusao($(this), 'SERVICO');
    });
});


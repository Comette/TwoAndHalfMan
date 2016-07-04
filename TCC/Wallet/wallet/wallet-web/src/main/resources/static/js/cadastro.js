'use strict';


$(function () {
    
    tabManager();

    updateCountdown();
    $('#txtDescricao').change(updateCountdown);
    $('#txtDescricao').keyup(updateCountdown);
});



var updateCountDown = function () {
    var remaining = 800 - jQuery('#txtDescricao').val().length;
    jQuery('.countdown').text(remaining + '/800');
};

var tabManager = function () {
    var guia = $('#guia-selecionada').text();
    if (guia === "usuario")
        $('.nav-tabs a[href="#usuario"]').tab('show');
    else if (guia === "servico")
        $('.nav-tabs a[href="#servico"]').tab('show');
};


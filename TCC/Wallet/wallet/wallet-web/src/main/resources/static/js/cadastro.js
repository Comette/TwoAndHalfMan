'use strict';

$(function () {
    var guia = $('#guia-selecionada').text();
    if(guia === "usuario"){
        $('.nav-tabs a[href="#usuario"]').tab('show');
    }else if(guia === "servico"){
        $('.nav-tabs a[href="#servico"]').tab('show');
    }
});

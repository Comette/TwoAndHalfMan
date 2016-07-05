'use strict';

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

var checkEditionMode = function(){
    debugger;
    var idClient = $('#id-usuario').val();
    if(idClient !== null && idClient > 0){
        $('#txtSenha').hide();
        $('#txtConfirmacaoSenha').hide();
    }
};

$(function () {
    
    tabManager();

    updateCountDown();
    $('#txtDescricao').change(updateCountDown);
    $('#txtDescricao').keyup(updateCountDown);
    
    checkEditionMode();
});



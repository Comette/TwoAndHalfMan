'use strict';

var $formServico = $('#form-servico');
var $formUsuario = $('#form-usuario');

$(function () {
    var guia = $('#guia-selecionada').text();
    if (guia === "usuario") {
        $('.nav-tabs a[href="#usuario"]').tab('show');
    } else if (guia === "servico") {
        $('.nav-tabs a[href="#servico"]').tab('show');
    }
    
    validarFormServico();
    
    updateCountdown();
    $('#txtDescricao').change(updateCountdown);
    $('#txtDescricao').keyup(updateCountdown);
});

function updateCountdown() {
    var remaining = 800 - jQuery('#txtDescricao').val().length;
    jQuery('.countdown').text(remaining + '/800');
}

function validarFormServico() {
    $formServico.validate({
        rules: {
            nome_servico: {
                required : true,
                minlength : 1,
                maxlength : 255
            },
            website_servico: {
                required : true,
                minlength : 1,
                maxlength : 255
            }
        },
        messages : {
            nome_servico: {
                required : "Entre com seu nome.",
                minlength : "O seu nome deve conter, no mínimo, 1 caracter."
            },
            website_servico:  {
               required: "Entre com o website.",
               minlength : "O seu website deve conter, no mínimo, 1 caracter.",
               maxlength : "O seu website deve conter, no maximo, 255 caracteres."
            }
        }
    });
}

function validarFormUsuario() {

}


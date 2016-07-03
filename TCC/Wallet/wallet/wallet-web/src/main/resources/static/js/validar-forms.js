'use strict';

var $formServico = $('#form-servico');
var $formUsuario = $('#form-usuario');

$(document).ready(function () {
    $formServico.validate({
        rules: {
            nome: {
                required: true,
                minlength: 1,
                maxlength: 255
            },
            webSite: {
                required: true,
                minlength: 1,
                maxlength: 255
            },
            periodicidade: {
                required: true
            },
            descricao: {
                required: true,
                minlength: 1,
                maxlength: 800
            },
            moeda: {
                required: true
            },
            valorTotal: {
                required: true,
                min: 0
            },
            idUsuarioResponsavel: {
                required: true
            }
        },
        messages: {
            nome: {
                required: "O campo nome é obrigatório.",
                minlength: "O valor mínimo do campo é de 1.",
                maxlength: "O valor máximo do campo é de 255."
            },
            webSite: {
                required: "O campo website é obrigatório.",
                minlength: "O valor mínimo do campo é de 1.",
                maxlength: "O valor máximo do campo é de 255."
            },
            periodicidade: {
                required: "Selecione uma periodicidade"
            },
            descricao: {
                required: "O campo descrição é obrigatório.",
                minlength: "O valor mínimo do campo é de 1.",
                maxlength: "O valor máximo do campo é de 800."
            },
            moeda: {
                required: "Selecione uma moeda."
            },
            valorTotal: {
                required: "O campo valor é obrigatório.",
                min: "O valor mínimo é de 0."
            },
            idUsuarioResponsavel: {
                required: "Selecione um usuário responsável."
            }
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
});


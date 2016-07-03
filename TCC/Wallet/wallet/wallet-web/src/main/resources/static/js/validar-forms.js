'use strict';

var $formServico = $('#form-servico');
var $formUsuario = $('#form-usuario');

$(function () {

    jQuery.validator.addMethod("checkSelect", function (value, element) {
        return (value === '0' || value === 0) ? false : true;
    }, "Selecione uma opção válida!");

//    jQuery.validator.addMethod("checkUsernameAvailability", function (value, element) {
//        var username = value;
//        $.ajax({
//            url: "/check-username",
//            type: "GET",
//            contentType: "application/json",
//            dataType: 'json',
//            data: JSON.stringify(username)
//        }).done(function (res) {
//            return res;
//        });
//    }, "Username já está sendo utilizado!");

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
                required: true,
                checkSelect: true
            },
            descricao: {
                required: true,
                minlength: 1,
                maxlength: 800
            },
            moeda: {
                required: true,
                checkSelect: true
            },
            valorTotal: {
                required: true,
                min: 0
            },
            idUsuarioResponsavel: {
                required: true,
                checkSelect: true
            }
        },
        messages: {
            nome: {
                required: "O campo nome é obrigatório.",
                minlength: "O tamanho mínimo é de 1.",
                maxlength: "O tamanho máximo é de 255."
            },
            webSite: {
                required: "O campo website é obrigatório.",
                minlength: "O tamanho mínimo é de 1.",
                maxlength: "O tamanho máximo é de 255."
            },
            periodicidade: {
                required: "Selecione uma periodicidade"
            },
            descricao: {
                required: "O campo descrição é obrigatório.",
                minlength: "O tamanho mínimo é de 1.",
                maxlength: "O tamanho máximo é de 800."
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
        submitHandler: function ($formServico) {
            $formServico.submit();
        }
    });

    $formUsuario.validate({
        rules: {
            nome: {
                required: true,
                minlength: 1,
                maxlength: 255
            },
            email: {//TODO REGEX EMAIL
                required: true,
                email: true
            },
            username: {
                required: true,
                minlength: 1,
                maxlength: 255,
                remote: {
                    url: "/check-username",
                    type: "GET",
                    data: {username: function(){return $('#txtUserName').val();}}
                }
            },
            permissao: {
                required: true
            },
            senha: {
                required: true,
                minlength: 8,
                maxlength: 255
            },
            confirmacaoSenha: {
                required: true,
                equalTo: '#txtSenha'
            }
        },
        messages: {
            nome: {
                required: "O campo nome é obrigatório.",
                minlength: "O tamanho mínimo é de 1.",
                maxlength: "O tamanho máximo é de 255."
            },
            email: {
                required: "O campo email é obrigatório.",
                email: "Email inválido!"
            },
            username: {
                required: "O campo usuário é obrigatório.",
                minlength: "O tamanho mínimo é de 1.",
                maxlength: "O tamanho máximo é de 255.",
                remote: "O usuário informado já está sendo utilizado."
            },
            permissao: {
                required: "Selecione uma permissao!"
            },
            senha: {
                required: "O campo senha é obrigatório.",
                minlength: "O tamanho mínimo é de 8.",
                maxlength: "O tamanho máximo é de 255."
            },
            confirmacaoSenha: {
                required: "O campo Confirmação de Senha é obrigatório.",
                equalTo: "As senhas não coincidem!"
            }
        },
        errorPlacement: function (error, element) {
            if (element.attr("name") === "permissao") {
                error.insertAfter("#ultima-permissao");
            } else {
                error.insertAfter(element);
            }
        },
        submitHandler: function ($formUsuario) {
            $formUsuario.submit();
        }
    });
});


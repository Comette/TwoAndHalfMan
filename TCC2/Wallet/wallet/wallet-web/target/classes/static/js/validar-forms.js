'use strict';

var $formServico = $('#form-servico');
var $formUsuario = $('#form-usuario');

$(function () {

    $('#numValorTotal').maskMoney({
        thousands: '.',
        decimal: ',',
        allowZero: false
    });

    jQuery.validator.addMethod("checkSelect", function (value, element) {
        return (value === '0' || value === 0) ? false : true;
    }, "Selecione uma opção válida!");

    jQuery.validator.addMethod("checkEmail", function (value, element) {
        var regex = /^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/;
        return regex.test(value);
    }, "Email inválido!");
    
    jQuery.validator.addMethod("checkWebsite", function (value, element) {
        var regex = /https?:\/\/[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)/;
        return regex.test(value);
    }, "Website inválido!");
    
    jQuery.validator.addMethod("checkPassword", function (value, element) {
        var findIdClient = function(){return $('#id-usuario').val();};
        var idClient = findIdClient();
        if(idClient > 0){
            return true;
        }else{
            return (value.length > 7 && value.length < 255);
        }
    }, "Senha muito curta!");

    $formServico.validate({
        rules: {
            name: {
                required: true,
                minlength: 1,
                maxlength: 255
            },
            webSite: {
                required: true,
                checkWebsite: true,
                minlength: 1,
                maxlength: 255
            },
            periodicity: {
                required: true,
                checkSelect: true
            },
            description: {
                required: true,
                minlength: 1,
                maxlength: 800
            },
            coin: {
                required: true,
                checkSelect: true
            },
            amountCost: {
                required: true,
                min: 0
            },
            responsibleUserID: {
                required: true,
                checkSelect: true
            }
        },
        messages: {
            name: {
                required: "O campo nome é obrigatório.",
                minlength: "O tamanho mínimo é de 1.",
                maxlength: "O tamanho máximo é de 255."
            },
            webSite: {
                required: "O campo website é obrigatório.",
                minlength: "O tamanho mínimo é de 1.",
                maxlength: "O tamanho máximo é de 255." 
            },
            periodicity: {
                required: "Selecione uma periodicidade"
            },
            description: {
                required: "O campo descrição é obrigatório.",
                minlength: "O tamanho mínimo é de 1.",
                maxlength: "O tamanho máximo é de 800."
            },
            coin: {
                required: "Selecione uma moeda."
            },
            amountCost: {
                required: "O campo valor é obrigatório.",
                min: "O valor mínimo é de 0."
            },
            responsibleUserID: {
                required: "Selecione um usuário responsável."
            }
        },
        submitHandler: function ($formServico) {
            var $input = $('#numValorTotal');
            var novoValor = $input.maskMoney('unmasked')[0].toString();
            $input.val(novoValor);
            $formServico.submit();
        }
    });

    $formUsuario.validate({
        rules: {
            name: {
                required: true,
                minlength: 1,
                maxlength: 255
            },
            email: {//TODO REGEX EMAIL
                required: true,
                checkEmail: true
            },
            username: {
                required: true,
                minlength: 1,
                maxlength: 255,
                remote: {
                    url: "/check-username",
                    type: "GET",
                    data: {username: function(){return $('#txtUserName').val();}, id: function(){return $('#id-usuario').val() || 0;}}
                }
            },
            permission: {
                required: true
            },
            password: {
                checkPassword: true,
                maxlength: 255
            },
            passwordCheck: {
                equalTo: '#txtSenha'
            }
        },
        messages: {
            name: {
                required: "O campo nome é obrigatório.",
                minlength: "O tamanho mínimo é de 1.",
                maxlength: "O tamanho máximo é de 255."
            },
            email: {
                required: "O campo email é obrigatório."
            },
            username: {
                required: "O campo usuário é obrigatório.",
                minlength: "O tamanho mínimo é de 1.",
                maxlength: "O tamanho máximo é de 255.",
                remote: "O usuário informado já está sendo utilizado."
            },
            permission: {
                required: "Selecione uma permissao!"
            },
            password: {
                maxlength: "O tamanho máximo é de 255."
            },
            passwordCheck: {                
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


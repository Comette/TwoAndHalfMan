'use strict';

var $formServico = $('#form-servico');
var $formUsuario = $('#form-usuario');

$(function () {
    $('#numValorTotal').maskMoney({
        thousands: '.',
        decimal: ',',
        allowZero: false
    });

    $('#slMoeda').change(function () {
        var self = $(this);
        $('#numValorTotal').maskMoney({
            thousands: '.',
            decimal: ',',
            allowZero: false,
            suffix: ' ' + self.val()
        });
    });

    jQuery.validator.addMethod("checkSelect", function (value, element) {
        return (value === '0' || value === 0) ? false : true;
    }, "Selecione uma opção válida!");

    jQuery.validator.addMethod("checkEmail", function (value, element) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(value);
    }, "Email inválido!");
    
    jQuery.validator.addMethod("checkWebsite", function (value, element) {
        var regex = /https?:\/\/[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)/;
        return regex.test(value);
    }, "Website inválido!");
    
    $formServico.validate({
        rules: {
            nome: {
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
                minlength: 0
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
            var $input = $('#numValorTotal');
            var novoValor = $input.maskMoney('unmasked')[0].toString();
            $input.val(novoValor);
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
            email: {
                required: true,
                checkEmail: true
            },
            username: {
                required: true,
                minlength: 1,
                maxlength: 30,
                remote: {
                    url: "/check-username",
                    type: "GET",
                    data: {username: function(){return $('#txtUserName').val();}, id: function(){return $('#id-usuario').val() || 0;}}
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
                required: "O campo email é obrigatório."
            },
            username: {
                required: "O campo usuário é obrigatório.",
                minlength: "O tamanho mínimo é de 1.",
                maxlength: "O tamanho máximo é de 30.",
                remote: "Esse username já está sendo utilizado!"
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


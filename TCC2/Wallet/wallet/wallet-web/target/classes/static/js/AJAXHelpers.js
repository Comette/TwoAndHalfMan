'use strict';

var AJAXgetByUrl = function (url) {
    return $.ajax({
        type: 'GET',
        url: url
    });
};

var AJAXPost = function (url, data) {
    return $.ajax({
        type: 'POST',
        url: url,
        data: data
    });
};

var checarSeUsuarioTemServicos = function (idUsuario) {
    return $.ajax({
        url: '/count-servicos-by-usuario',
        type: 'GET',
        data: {
            idUsuario: idUsuario
        }
    });
};


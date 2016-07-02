'use strict';

$(function () {
    var url = document.location.toString();
    var str = url.substring(url.indexOf("?") + 1, url.indexOf("="));
    $('.nav-tabs a[href="#' + str + '"]').tab('show');

    $('.nav-tabs a').on('shown.bs.tab', function (e) {
        window.location.hash = e.target.hash;
    });
});

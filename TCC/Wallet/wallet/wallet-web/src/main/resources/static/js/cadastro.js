'use strict';

$(function () {
    var url = document.location.toString();
    $('.nav-tabs a[href="#' + url.split('#')[1] + '"]').tab('show');

    $('.nav-tabs a').on('shown.bs.tab', function (e) {
        window.location.hash = e.target.hash;
    });
});

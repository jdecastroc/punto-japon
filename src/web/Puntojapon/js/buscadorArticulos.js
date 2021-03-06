var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};


$(document).ready(function() {

    var textSearch = getUrlParameter('titulo');
    var showData = $('#show-data');
    var maxArticles = 10;
    var articulosCargados = 0;
    var loading = false;

    $('#moreArticles100').hide();

    $('#show-data').fadeOut('slow', function() {
        loadArticles(10);
    });

    $(document).ajaxStart(function() {
        $("#cargandoArticulos").show();
    });

    $(document).ajaxStop(function() {
        $('#cargandoArticulos').hide();
    });

    function loadArticles(showArticles) {

        $.ajax({
            type: 'GET',
            url: 'http://www.infojapon.com:8081/articulos/buscar',
            data: {
                titulo: textSearch
            }, //Especifica los datos que se enviarán al servidor
            async: true, //Cuidado con el true! esto es asíncrono puede generar problemas con otros fragmentos de código. Hace que el código se ejecute de manera concurrente
            beforeSend: function(xhr) {
                if (xhr && xhr.overrideMimeType) {
                    xhr.overrideMimeType('application/json;charset=utf-8');
                }
            },
            dataType: 'json',
            success: function(data, status) {

                //Do stuff with the JSON data

                if (status == "success") {
                    //  showData.empty(); //Refresh the div where the articles are stored
                    if (data.hits.hits[0] != null) {
                        var jsonData = data.hits.hits[0].inner_hits.data.hits.hits; //parse json
                        var output = "";
                        if (articulosCargados < jsonData.length) {
                            maxArticles = data.hits.hits[0].inner_hits.data.hits.total;
                            for (i = articulosCargados; i < showArticles && i < maxArticles; i++) {
                                articulosCargados++;
                                if (i % 2 == false) {
                                    output += '<div class="col_half">';
                                    output += '<h3>' + jsonData[i]._source.Title + '</h3>';
                                    output += '<div class="testi-image pull-left img-responsive"><a href="#"><img src="img/articulos/' + jsonData[i]._source.Author + '.jpg" alt="' + jsonData[i]._source.Author + '"></a></div>';

                                    output += ' <ul class="entry-meta clearfix"> <li><i class="icon-calendar3"></i>' + jsonData[i]._source.PubDate + '</li> <li><a href="#"><i class="icon-user"></i>' + jsonData[i]._source.Author + '</a></li></ul>';
                                    output += '<article><p> ' + jsonData[i]._source.Description + '<p></article>';
                                    output += '<a class="btn btn-blog pull-right marginBottom10" href=" ' + jsonData[i]._source.Link + '">Seguir leyendo</a>';

                                    //output+='<div class="divider"><i class="icon-circle"></i></div>';//Separador de artículos
                                    output += '</div>';
                                } else {
                                    output += '<div class="col_half col_last">';
                                    output += '<h3>' + jsonData[i]._source.Title + '</h3>';
                                    output += '<div class="testi-image pull-left img-responsive"><a href="#"><img src="img/articulos/' + jsonData[i]._source.Author + '.jpg" alt="' + jsonData[i]._source.Author + '"></a></div>';

                                    output += ' <ul class="entry-meta clearfix"> <li><i class="icon-calendar3"></i>' + jsonData[i]._source.PubDate + '</li> <li><a href="#"><i class="icon-user"></i>' + jsonData[i]._source.Author + '</a></li></ul>';
                                    output += '<article><p> ' + jsonData[i]._source.Description + '<p></article>';
                                    output += '<a class="btn btn-blog pull-right marginBottom10" href=" ' + jsonData[i]._source.Link + '">Seguir leyendo</a>';

                                    //output+='<div class="divider"><i class="icon-circle"></i></div>';//Separador de artículos
                                    output += '</div>';
                                }
                            }
                            /*                    output += "</ul>";*/

                        }
                    } else {
                      var output = "<ul>";
                      output += '<div class="alert alert-danger">';
                      output += '<i class="icon-remove-sign"></i><strong>No se han encontrado resultados';
                      output += '</div>';
                      output += "</ul>";
                    }
                    showData.append(output);
                }
            },
            error: function(xhr) {
                mostrarOfertas.empty(); //Refresh the div where the jobs are stored
                jsonErrorValue = jQuery.parseJSON(xhr.responseText);
                if (jsonErrorValue.message === "Search results were empty") {
                    var output = 'No se han encontrado resultados.';
                } else {
                    var output = 'No se han encontrado resultados.';
                }
                mostrarOfertas.append(output);
                mostrarOfertas.show();
            },
            timeout: 10000 // sets timeout to 10 seconds
        });
        $('#show-data').fadeIn('slow');
    }
    //----------Articulos cargados----------

    $('#header').hide();

    $(window).scroll(function() {


        var articleOffset = $("#show-data").offset().top;
        var articleOffsetBottom = $("#show-data").offset().top + $("#show-data").height();

        if ($(this).scrollTop() > articleOffset) {
            $('#header').fadeIn();
        } else {
            $('#header').fadeOut();
        }
        if (maxArticles > 10) {
            if (!loading && $(this).scrollTop() >= ($('#carga').offset().top + $('#carga').outerHeight() - window.innerHeight - 100)) {
                loading = true;
                loadArticles(articulosCargados + 10);
                loading = false;
            }
        }
    });

});

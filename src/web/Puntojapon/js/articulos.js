$(document).ready(function() {


    var showData = $('#show-data');
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

    $('#textSearch').keyup(function() {
        document.getElementById("buscarArticulos").setAttribute('href','buscador?titulo=' + $(this).val());
    });

    function loadArticles(showArticles) {

        $.ajax({
            type: 'GET',
            url: 'http://www.jdecastroc.ovh:8081/articulos',
            data: '', //Especifica los datos que se enviarán al servidor
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
                    var jsonData = data.data; //parse json
                    var output = "";
                    if (jsonData != null) {
                        if (articulosCargados < jsonData.length) {
                            for (i = articulosCargados; i < showArticles; i++) {
                                articulosCargados++;
                                if (i % 2 == false) {
                                    output += '<div class="col_half">';
                                    output += '<h3>' + jsonData[i].Title + '</h3>';
                                    output += '<div class="testi-image pull-left img-responsive"><a href="#"><img src="img/articulos/' + jsonData[i].Author + '.jpg" alt="' + jsonData[i].Author + '"></a></div>';

                                    output += ' <ul class="entry-meta clearfix"> <li><i class="icon-calendar3"></i>' + jsonData[i].PubDate + '</li> <li><a href="#"><i class="icon-user"></i>' + jsonData[i].Author + '</a></li></ul>';
                                    output += '<article><p> ' + jsonData[i].Description + '<p></article>';
                                    output += '<a class="btn btn-blog pull-right marginBottom10" href=" ' + jsonData[i].Link + '">Seguir leyendo</a>';

                                    //output+='<div class="divider"><i class="icon-circle"></i></div>';//Separador de artículos
                                    output += '</div>';
                                } else {
                                    output += '<div class="col_half col_last">';
                                    output += '<h3>' + jsonData[i].Title + '</h3>';
                                    output += '<div class="testi-image pull-left img-responsive"><a href="#"><img src="img/articulos/' + jsonData[i].Author + '.jpg" alt="' + jsonData[i].Author + '"></a></div>';

                                    output += ' <ul class="entry-meta clearfix"> <li><i class="icon-calendar3"></i>' + jsonData[i].PubDate + '</li> <li><a href="#"><i class="icon-user"></i>' + jsonData[i].Author + '</a></li></ul>';
                                    output += '<article><p> ' + jsonData[i].Description + '<p></article>';
                                    output += '<a class="btn btn-blog pull-right marginBottom10" href=" ' + jsonData[i].Link + '">Seguir leyendo</a>';

                                    //output+='<div class="divider"><i class="icon-circle"></i></div>';//Separador de artículos
                                    output += '</div>';
                                }
                            }
                            /*                    output += "</ul>";*/
                            showData.append(output);
                        }
                    } else {
                        var output = "<ul>";
                        output += '<div class="alert alert-warning">';
                        output += '<i class="icon-remove-sign"></i><strong>El servidor de datos se encuentra en mantenimiento. Intentelo de nuevo mas tarde.';
                        output += '</div>';
                        output += "</ul>";
                    }
                    showData.append(output);

                }
            },
            error: function(xhr) {
                mostrarViviendas.empty(); //Refresh the div where the jobs are stored
                jsonErrorValue = jQuery.parseJSON(xhr.responseText);
                if (jsonErrorValue.message === "Search results were empty") {
                    var output = 'No se han encontrado resultados.';
                } else {
                    var output = 'No se ha podido conectar con el servidor de datos. Intentelo de nuevo más tarde.';
                }
                mostrarViviendas.append(output);
                mostrarViviendas.show();
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

        if (!loading && $(this).scrollTop() >= ($('#carga').offset().top + $('#carga').outerHeight() - window.innerHeight - 100)) {
            loading = true;
            loadArticles(articulosCargados + 10);
            loading = false;
        }
    });
});

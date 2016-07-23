$(document).ready(function() {

    $('#cargandoOfertas').hide();

    // Variable general
    var fuente = $('#fuente');
    var paginaOferta = 1;
    var semaforo = 1;
    var elementoPrimerPaso = $('#ui-id-1');
    var elementoSegundoPaso = $('#ui-id-2');
    var elementoUltimoPaso = $('#ui-id-3');

    // Elementos primer tab
    var especialidad = $('#specialtySelector');

    // Elementos segundo tab
    var infoPrefectura = $('#infoPrefectura');
    var prefecturaSeleccion = $("#prefectureSelector");

    // Elementos ultima pagina
    var mostrarOfertas = $('#mostrarOfertas');


    $(document).on('click', '.loadSchoolDiv', function() {
        cargarEscuelas($(this).text());
    });

    $(document).on('click', '#paginaAnterior', function() {
        paginaOferta--;
        if (document.getElementById("prefectureSelector").value != "" && document.getElementById("specialtySelector").value != "") {
            loadOffers();
        } else {
            mostrarOfertas.empty();
            var output = "<ul>";
            output += '<div class="alert alert-danger">';
            output += '<i class="icon-remove-sign"></i><strong>¡Oh vaya!</strong> Se te ha olvidado rellenar la información en los pasos previos.';
            output += '</div>';
            output += "</ul>";
            mostrarOfertas.append(output);
            mostrarOfertas.show();
        }
    });

    $(document).on('click', '#paginaSiguiente', function() {
        paginaOferta++;
        if (document.getElementById("prefectureSelector").value != "" && document.getElementById("specialtySelector").value != "") {
            loadOffers();
        } else {
            mostrarOfertas.empty();
            var output = "<ul>";
            output += '<div class="alert alert-danger">';
            output += '<i class="icon-remove-sign"></i><strong>¡Oh vaya!</strong> Se te ha olvidado rellenar la información en los pasos previos.';
            output += '</div>';
            output += "</ul>";
            mostrarOfertas.append(output);
            mostrarOfertas.show();
        }
    });

    elementoUltimoPaso.click(function() {
        fuente.hide();
        if (document.getElementById("prefectureSelector").value != "" && document.getElementById("specialtySelector").value != "") {
            paginaOferta = 1;
            loadOffers();
        } else {
            mostrarOfertas.empty();
            var output = "<ul>";
            output += '<div class="alert alert-danger">';
            output += '<i class="icon-remove-sign"></i><strong>¡Oh vaya!</strong> Se te ha olvidado rellenar la información en los pasos previos.';
            output += '</div>';
            output += "</ul>";
            mostrarOfertas.append(output);
            mostrarOfertas.show();
        }
    });

    $('#empezarBusqueda').click(function() {
        fuente.hide();
        if (document.getElementById("prefectureSelector").value != "" && document.getElementById("specialtySelector").value != "") {
            paginaOferta = 1;
            loadOffers();
        } else {
            mostrarOfertas.empty();
            var output = "<ul>";
            output += '<div class="alert alert-danger">';
            output += '<i class="icon-remove-sign"></i><strong>¡Oh vaya!</strong> Se te ha olvidado rellenar la información en los pasos previos.';
            output += '</div>';
            output += "</ul>";
            mostrarOfertas.append(output);
            mostrarOfertas.show();
        }
    });

    prefecturaSeleccion.change(function() {
        if (document.getElementById("prefectureSelector").value != "all") {

            var playListURL = 'https://es.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&callback=?&rvparse=&titles=' + document.getElementById("prefectureSelector").value;

            $.getJSON(playListURL, function(data) {
                $.each(data.query.pages, function(i, item) {
                    document.getElementById("tituloPrefectura").innerText = item.title;
                    document.getElementById("descripcionPrefectura").innerText = item.extract;
                    document.getElementById("wikipedia").innerText = 'https://es.wikipedia.org/wiki/' + document.getElementById("prefectureSelector").value;
                });
            });
            infoPrefectura.fadeIn('slow');
        }
    });

    $('.pop').on('click', function() {
        document.getElementById("imagepreviewId").setAttribute("src", $(this).attr('src'));
        $('#imagemodal').modal('show');
    });

    $(document).ajaxStart(function() {
        $("#cargandoOfertas").show();
    });

    $(document).ajaxStop(function() {
        $('#cargandoOfertas').hide();
    });

    function loadOffers() {
        fuente.hide();
        mostrarOfertas.empty();
        semaforo--;
        var prefectureSearch = document.getElementById('prefectureSelector').options[document.getElementById('prefectureSelector').selectedIndex].text.slice(3).trim();

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8081/trabajo/' + prefectureSearch + '/' + document.getElementById("specialtySelector").value,
            data: {
                page: paginaOferta,
            }, //Especifica los datos que se enviarán al servidor
            async: true, //Cuidado con el true! esto es asíncrono puede generar problemas con otros fragmentos de código. Hace que el código se ejecute de manera concurrente
            beforeSend: function(xhr) {
                if (xhr && xhr.overrideMimeType) {
                    xhr.overrideMimeType('application/json;charset=utf-8');
                }
            },
            dataType: 'json',
            success: function(data, status) {
                if (status == "success" && data.searchState === true) {
                    mostrarOfertas.empty(); //Refresh the div where the jobs are stored
                    var jsonData = data.jobsList; //parse data array from json
                    var output = '<div id="postlist">';

                    for (var i = 0; i < data.jobsList.length; i++) {
                        output += '<div class="col_half">';

                        output += '<div class="title-block">';
                        output += '<h2><a href=' + data.jobsList[i].link + '>' + data.jobsList[i].name + '</a></h2>';
                        output += '<span>' + data.jobsList[i].company + '</span>';
                        output += "</div>";
                        output += '<i class="icon-calendar"></i><span style="padding-left: 5px; padding-right: 5px;"><b>Fecha de publicación:</b></span>' + data.jobsList[i].publishDate + '<br>';
                        output += '<i class="icon-map-marker"></i><span style="padding-left: 5px; padding-right: 5px;"><b>Lugar:</b></span>' + data.jobsList[i].tags[2] + '<br>';
                        output += '<i class="icon-line-briefcase"></i><span style="padding-left: 5px; padding-right: 5px;"><b>Tipo de contrato:</b></span>' + data.jobsList[i].tags[0] + '<br>';
                        output += '<i class="icon-flag"></i><span style="padding-left: 5px; padding-right: 5px;"><b>Salario:</b></span>' + data.jobsList[i].tags[1] + '<br>';
                        output += "</div>";
                        output += '<div class="col_half col_last">';
                        output += ((data.jobsList[i].tags[3] == null) ? "" : "<img style=margin-top: 3%; display: block; src=" + data.jobsList[i].tags[3] + ">");
                        output += "</div>";
                        //output += "</div>";
                        output += "<div class='clear'></div>";
                    }
                    output += "</div>";
                    if (paginaOferta > 1)
                        output += '<a id="paginaAnterior" class="button button-3d nomargin tab-linker pull-left">Anterior</a>';
                    if (data.hasNextPage === true)
                        output += '<a id="paginaSiguiente" class="button button-3d nomargin tab-linker pull-right">Siguiente</a>';

                    semaforo++;
                    $("html, body").animate({
                        scrollTop: 0
                    }, "slow");
                    mostrarOfertas.append(output);
                    mostrarOfertas.show();
                    fuente.show();
                }
            },
            timeout: 10000, // sets timeout to 10 seconds
            error: function(xhr) {
                mostrarOfertas.empty(); //Refresh the div where the jobs are stored
                if (jQuery.parseJSON(xhr.responseText) != "") {
                    jsonErrorValue = jQuery.parseJSON(xhr.responseText);
                    if (jsonErrorValue.message === "Search results were empty") {
                        var output = 'No se han encontrado resultados.';
                    } else {
                        var output = 'No se ha podido conectar con el servidor de datos. Intentelo de nuevo más tarde.';
                    }
                } else {
                    var output = 'No se ha podido conectar con el servidor de datos. Intentelo de nuevo más tarde.';
                }
                mostrarOfertas.append(output);
                mostrarOfertas.show();
            }
        });
    };
});

$(document).ready(function() {

    $('#cargandoOfertas').hide();

    // Variable general
    var paginaOferta = 0;
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
        if (document.getElementById("prefectureSelector").value != "" && document.getElementById("specialtySelector").value != "") {
            paginaOferta = 0;
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
        mostrarOfertas.empty();
        semaforo--;
        var prefectureSearch = document.getElementById('prefectureSelector').options[document.getElementById('prefectureSelector').selectedIndex].text.slice(3).trim();
        $.ajax({
            type: 'GET',
            url: 'http://www.jdecastroc.ovh:8081/trabajo/' + prefectureSearch + '/' + document.getElementById("specialtySelector").value,
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
                //Do stuff with the JSON data
                if (status == "success" && data.searchState === true) {
                    mostrarOfertas.empty(); //Refresh the div where the jobs are stored
                    var jsonData = data.jobsList; //parse data array from json
                    var output = '<div id="postlist">';

                    for (var i = 0; i < data.jobsList.length; i++) {
                        output += '<div class="panel"><div class="panel-heading"><div class="text-center"><div class="row"><div class="col-sm-9">';
                        output += '<h3 class="pull-left"><a href="' + data.jobsList[i].link + '">' + data.jobsList[i].name + '</a></h3>';
                        output += '<h5 class="pull-left" style="margin-left: 5%; margin-top: 1%;">' + data.jobsList[i].company + ' - ' + data.jobsList[i].location + '</h5>';
                        output += '</div><div class="col-sm-3"><h4 class="pull-right">' + data.jobsList[i].publishDate + '</h4></div></div></div></div>';

                        output += '<div class="panel-body">' + data.jobsList[i].description + '<a href="' + data.jobsList[i].link + '">   [Ir a oferta]</a></div>';

                        output += '<div class="panel-footer">';
                        for (var p = 0; p < data.jobsList[i].tags.length; p++) {
                            output += '<span class="label label-default" style="margin-right: 2%;">' + data.jobsList[i].tags[p] + '</span>';
                        }
                        output += '</div></div>';
                    }
                    output += "</div>";
                    if (paginaOferta > 0) {
                        output += '<a id="paginaAnterior" class="button button-3d nomargin tab-linker pull-left">Anterior</a>';
                    }
                    output += '<a id="paginaSiguiente" class="button button-3d nomargin tab-linker pull-right">Siguiente</a>';

                    semaforo++;
                    $("html, body").animate({
                        scrollTop: 0
                    }, "slow");
                    mostrarOfertas.append(output);
                    mostrarOfertas.show();
                }
            },
            error: function() {
                mostrarOfertas.empty(); //Refresh the div where the jobs are stored
                var output = 'No se ha podido conectar con el servidor de datos. Intentelo de nuevo más tarde.';
                mostrarOfertas.append(output);
                mostrarOfertas.show();
            },
            timeout: 10000 // sets timeout to 10 seconds
        });
    };
});

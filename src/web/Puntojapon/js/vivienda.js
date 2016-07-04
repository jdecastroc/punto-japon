$(document).ready(function() {

    $('#cargandoViviendas').hide();
    $('#mapModal').hide();

    var fuente = $('#fuente');

    // Variable general
    var paginaVivienda = 1; //The pages start from 1 in the crawled website
    var semaforo = 1;
    var elementoPrimerPaso = $('#ui-id-1');
    var elementoSegundoPaso = $('#ui-id-2');
    var elementoUltimoPaso = $('#ui-id-3');

    // Elementos primer tab
    var infoPrefectura = $('#infoPrefectura');
    var prefecturaSeleccion = $("#prefectureSelector");

    // Elementos segunda tab
    var empezarBusqueda = $('#empezarBusquedaViviendas');

    // Elementos ultima pagina
    var mostrarViviendas = $('#mostrarViviendas');


    elementoUltimoPaso.click(function() {
        fuente.hide();
        if (document.getElementById("prefectureSelector").value != "") {
            paginaVivienda = 1;
            cargarViviendas();
        } else {
            mostrarViviendas.empty();
            var output = "<ul>";
            output += '<div class="alert alert-danger">';
            output += '<i class="icon-remove-sign"></i><strong>¡Oh vaya!</strong> Se te ha olvidado rellenar la información en los pasos previos.';
            output += '</div>';
            output += "</ul>";
            mostrarViviendas.append(output);
            mostrarViviendas.show();
        }
    });

    $('#empezarBusquedaViviendas').click(function() {
        fuente.hide();
        if (document.getElementById("prefectureSelector").value != "") {
            paginaVivienda = 1;
            cargarViviendas();
        } else {
            mostrarViviendas.empty();
            var output = "<ul>";
            output += '<div class="alert alert-danger">';
            output += '<i class="icon-remove-sign"></i><strong>¡Oh vaya!</strong> Se te ha olvidado rellenar la información en los pasos previos.';
            output += '</div>';
            output += "</ul>";
            mostrarViviendas.append(output);
            mostrarViviendas.show();
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
        $("#cargandoViviendas").show();
    });

    $(document).ajaxStop(function() {
        $('#cargandoViviendas').hide();
    });

    $(document).on('click', '#paginaAnterior', function() {
        paginaVivienda--;
        if (document.getElementById("prefectureSelector").value != "") {
            cargarViviendas();
        } else {
            mostrarViviendas.empty();
            var output = "<ul>";
            output += '<div class="alert alert-danger">';
            output += '<i class="icon-remove-sign"></i><strong>¡Oh vaya!</strong> Se te ha olvidado rellenar la información en los pasos previos.';
            output += '</div>';
            output += "</ul>";
            mostrarViviendas.append(output);
            mostrarViviendas.show();
        }
    });

    $(document).on('click', '#paginaSiguiente', function() {
        paginaVivienda++;
        if (document.getElementById("prefectureSelector").value != "") {
            cargarViviendas();
        } else {
            mostrarViviendas.empty();
            var output = "<ul>";
            output += '<div class="alert alert-danger">';
            output += '<i class="icon-remove-sign"></i><strong>¡Oh vaya!</strong> Se te ha olvidado rellenar la información en los pasos previos.';
            output += '</div>';
            output += "</ul>";
            mostrarViviendas.append(output);
            mostrarViviendas.show();
        }
    });

    function cargarViviendas() {
        mostrarViviendas.empty();
        fuente.hide();
        semaforo--;
        var prefectureSearch = document.getElementById('prefectureSelector').options[document.getElementById('prefectureSelector').selectedIndex].text.slice(3).trim();

        $.ajax({
            type: 'GET',
            url: 'http://www.infojapon.com:8081/vivir/' + prefectureSearch,
            data: {
                page: paginaVivienda,
                min_price: document.getElementById('min_price').value,
                max_price: document.getElementById('max_price').value,
                min_meter: document.getElementById('min_meter').value,
                rooms: document.getElementById('rooms').value,
                distance_station: document.getElementById('distance_station').value,
                building_type: document.getElementById('building_type').value,
                building_age: document.getElementById('building_age').value,
                low_initial_costs: (document.getElementById("checkbox-low_initial_costs").checked) ? '1' : '',
                no_guarantor: (document.getElementById("checkbox-no_guarantor").checked) ? '1' : '',
                no_key_money: (document.getElementById("checkbox-no_key_money").checked) ? '1' : '',
                pets: (document.getElementById("checkbox-pets").checked) ? '1' : '',
                no_deposit: (document.getElementById("checkbox-no_deposit").checked) ? '1' : '',
                short_term_ok: (document.getElementById("checkbox-short_term_ok").checked) ? '1' : '',
                no_agency_fee: (document.getElementById("checkbox-no_agency_fee").checked) ? '1' : '',
                furnished: (document.getElementById("checkbox-furnished").checked) ? '1' : '',
                internet: (document.getElementById("checkbox-internet").checked) ? '1' : '',
                wifi: (document.getElementById("checkbox-wifi").checked) ? '1' : '',
                credit_card: (document.getElementById("checkbox-credit_card").checked) ? '1' : '',

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
                    mostrarViviendas.empty(); //Refresh the div where the jobs are stored
                    //var jsonData = data.jobsList; //parse data array from json
                    var output = '<div id="houselist">';


                    for (var i = 0; i < data.houseList.length; i++) {

                        output += '<div class="col_half">';

                        output += '<div class="title-block">';
                        output += '<h2><a href=' + data.houseList[i].link + '>' + data.houseList[i].buildingType + '</a></h2>';
                        output += '<span>' + data.houseList[i].place + '</span>';
                        output += "</div>";



                        output += '<i class="icon-line-bar-graph-2"></i><span style="padding-left: 5px; padding-right: 5px;"><b>Alquiler:</b></span>' + data.houseList[i].rent + '<br>';
                        output += '<i class="icon-line2-home"></i><span style="padding-left: 5px; padding-right: 5px;"><b>Tamaño:</b></span>' + data.houseList[i].size + '<br>';
                        output += '<i class="icon-lock"></i><span style="padding-left: 5px; padding-right: 5px;"><b>Depósito:</b></span>' + data.houseList[i].deposit + '<br>';
                        output += '<i class="icon-flag"></i><span style="padding-left: 5px; padding-right: 5px;"><b>Señal:</b></span>' + data.houseList[i].keyMoney + '<br>';
                        output += '<i class="icon-line-layers"></i><span style="padding-left: 5px; padding-right: 5px;"><b>Piso:</b></span>' + data.houseList[i].floor + '<br>';
                        output += '<i class="icon-line-briefcase"></i><span style="padding-left: 5px; padding-right: 5px;"><b>Coste mantenimiento:</b></span>' + data.houseList[i].maintenanceFee + '<br>';
                        output += '<i class="icon-map-marker"></i><span style="padding-left: 5px; padding-right: 5px;"><b>Estación más cercana:</b></span>' + data.houseList[i].nearestStation + '<br>';
                        output += '<i class="icon-line-head"></i><span style="padding-left: 5px; padding-right: 5px;"><b>Agente:</b></span>' + data.houseList[i].agent + '<br>';
                        output += '<a id="mapEvent" href="https://www.google.es/maps/search/' + data.houseList[i].map + '"> Ver localización en el mapa </a><br><br>';
                        output += '<h5><a href="' + data.houseList[i].link + '">Más información</a></h5>';


                        output += "</div>";

                        output += '<div class="col_half col_last">';
                        output += '<img style="margin-top: 3%; display: block;" src="' + data.houseList[i].imageUrl + '">';
                        //output += "<div class='map-responsive'><iframe  frameborder='0' style='border:0' src='https://www.google.com/maps/embed/v1/place?q=" + data.houseList[i].map + "&key= AIzaSyBuvPXxGbEVF1BSE1NqlDX6ZVjXxFW2S5A ' allowfullscreen></iframe></div>";
                        output += "</div>";
                        //output += "</div>";
                        output += "<div class='clear'></div>";

                    }

                    output += "</div>";
                    if (paginaVivienda > 1) {
                        output += '<div class="col_half">';
                        output += '<a id="paginaAnterior" class="button button-3d nomargin tab-linker pull-left">Anterior</a>';
                        output += "</div>";
                    } else if (data.hasNextPage === true) {
                        output += '<div class="col_half col_last">';
                        output += '<a id="paginaSiguiente" class="button button-3d nomargin tab-linker pull-right">Siguiente</a>';
                        output += "</div>";
                    }


                    semaforo++;
                    $("html, body").animate({
                        scrollTop: 0
                    }, "slow");
                    mostrarViviendas.append(output);
                    mostrarViviendas.show();
                    fuente.show();
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

    };



});

//Coger información de URL
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

function getRandomColor() {
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}



$(document).ready(function() {

    var tipo = getUrlParameter('tipo');
    var id = getUrlParameter('id');
    var departamento = getUrlParameter('id_depart');
    var elementos = {
        estadisticas: $('.estadisticas')
    };

    elementos.estadisticas.hide();

    switch (tipo) {
        case "univ":
            $("#cargando").show();
            $.ajax({
                type: 'GET',
                url: 'http://jdecastroc.ovh:8081/universidades/id/' + id,
                data: {}, //Especifica los datos que se enviarán al servidor
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
                        var output = "";

                        if (data.search == true) {
                            output += '<div class="col-md-10 blogShort">';
                            output += '<h2>' + data.name + ' - ' + data.japaneseName + '</h2>';
                            output += '<em>' + data.prefecture + ' / ' + data.type + '</em>';

                            if (data.guideUrl != "") {
                                output += ' / <a target="_blank" href="http://www.jpss.jp' + data.guideUrl + '">Guía universitaria</a>';
                            }
                            if (data.imageUrl != "") {
                                output += '<img class="pull-left img-responsive thumb img-thumbnail" width="230" height="186" style="border:0; padding-right: 10px;" alt="' + data.name + '" src="http://www.jpss.jp/' + data.imageUrl + '">';
                            }

                            output += '<h4>' + data.title + '</h4>';
                            output += '<article><p>' + data.description + '</p></article></br>';
                            //Departamentos
                            output += '<div style="padding-top: 20px;">';
                            for (j = 0; j < data.faculties.collegeFacultyList.length; j++) {
                                var array_split = data.faculties.collegeFacultyList[j].facultyUrl.split("/");
                                var id_univ = array_split["3"];
                                var id_depart = array_split["4"];
                                output += '<a class="button button-3d button-mini button-rounded button-aqua" target="_blank" href="' + "departamento?tipo=univ&id=" + id_univ + "&id_depart=" + id_depart + '">' + data.faculties.collegeFacultyList[j].facultyName + '</a>';
                            }
                            output += '</div>';
                            output += '</div>';
                            output += '<div class="divider"><i class="icon-circle"></i></div>';
                        }
                    }
                    $("#cargando").hide();
                    $('#mostrarEscuela').append(output);
                }
            });
            //AJAX ACCESO FUNCION
            $.ajax({
                type: 'GET',
                url: 'http://jdecastroc.ovh:8081/universidades/id/' + id + '/' + departamento + '/access',
                data: {}, //Especifica los datos que se enviarán al servidor
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
                        var output = "";
                        if (data.search == true) {
                            output += "Nombre departamento = " + data.name + "<br>";
                            output += "Noticias = " + data.news + "<br>";

                            output += "<h4>Acceso a facultad</h4>";
                            for (p = 0; p < data.facultyAccess.maps.length; p++) {
                                output += "Nombre: " + data.facultyAccess.maps[p].name + "<br>";
                                output += "Longitud: " + data.facultyAccess.maps[p].lng + "<br>";
                                output += "Latitud: " + data.facultyAccess.maps[p].lat + "<br>";
                                output += "Diracción: " + data.facultyAccess.maps[p].address + "<br>";
                                output += "Sitios cercanos: " + data.facultyAccess.maps[p].nearbyPlaces + "<br>";
                                output += "Otra información: " + data.facultyAccess.maps[p].otherInfo + "<br>";
                            }
                        } else {
                            output += "No se ha encontrado información relacionada";
                        }
                    }
                    $('#acceso').append(output);
                }
            });
            //AJAX FUNCION ADMISIONES
            $.ajax({
                type: 'GET',
                url: 'http://jdecastroc.ovh:8081/universidades/id/' + id + '/' + departamento + '/admissions',
                data: {}, //Especifica los datos que se enviarán al servidor
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
                        var output = "";
                        if (data.search == true) {
                            output += "Nombre departamento = " + data.name + "<br>";
                            output += "Noticias = " + data.news + "<br>";

                            output += "<h4>Admisiones</h4>";
                            output += data.facultyAdmissions.lastUpdate + "<br>";

                            for (p = 0; p < data.facultyAdmissions.rowTable.length; p++) {
                                output += "<b>" + data.facultyAdmissions.rowTable[p].register + "</b>:  " + data.facultyAdmissions.rowTable[p].content + "<br>";
                            }
                        } else {
                            output += "No se ha encontrado información relacionada";
                        }
                    }
                    $('#admisiones').append(output);
                }
            });
            //AJAX FUNCION INSTALACIONES
            $.ajax({
                type: 'GET',
                url: 'http://jdecastroc.ovh:8081/universidades/id/' + id + '/' + departamento + '/facilities',
                data: {}, //Especifica los datos que se enviarán al servidor
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
                        var output = "";
                        if (data.search == true) {
                            $('#departamento-principal').append("<h2>" + data.name + "</h2>" + data.news);
                            output += "Nombre departamento = " + data.name + "<br>";
                            output += "Noticias = " + data.news + "<br>";

                            output += "<h4>Instalaciones</h4>";


                            for (p = 0; p < data.facultyFacilities.objectTable.length; p++) {
                                output += "<b>" + data.facultyFacilities.objectTable[p].register + "</b>:  " + data.facultyFacilities.objectTable[p].content + "<br>";
                            }
                        } else {
                            output += "No se ha encontrado información relacionada";
                        }
                    }
                    $('#instalaciones').append(output);
                }
            });
            //AJAX FUNCION AYUDA AL ESTUDIANTE
            $.ajax({
                type: 'GET',
                url: 'http://jdecastroc.ovh:8081/universidades/id/' + id + '/' + departamento + '/support',
                data: {}, //Especifica los datos que se enviarán al servidor
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
                        var output = "";
                        if (data.search == true) {
                            $('#departamento-principal').append("<h2>" + data.name + "</h2>" + data.news);

                            output += "<h4>Ayuda al estudiante</h4>";
                            for (p = 0; p < data.facultySupport.objectTable.length; p++) {
                                output += "<b>" + data.facultySupport.objectTable[p].register + "</b>:  " + data.facultySupport.objectTable[p].content + "<br>";
                            }
                        } else {
                            output += "No se ha encontrado información relacionada";
                        }
                    }
                    $('#ayudaEstudiante').append(output);
                }
            });

            //AJAX FUNCION informacion
            $.ajax({
                type: 'GET',
                url: 'http://jdecastroc.ovh:8081/universidades/id/' + id + '/' + departamento + '/info',
                data: {}, //Especifica los datos que se enviarán al servidor
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
                        var output = "";
                        if (data.search == true) {
                            output += "<h4>Información</h4>";
                            for (p = 0; p < data.facultyInfo.objectTable.length; p++) {
                                output += "<b>" + data.facultyInfo.objectTable[p].register + "</b>:  " + data.facultyInfo.objectTable[p].content + "<br>";
                            }

                            var globalGraphSettings = {
                                animation: Modernizr.canvas,
                                responsive: true,
                                sliceVisibilityThreshold: 0,
                                animationEasing: "easeOutQuart",
                                tooltipTemplate: "<%if (label){%><%=label%>: <%}%><%= value %>",
                                segmentStrokeColor: "#f9f9f9",
                                legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li style=\"list-style-type: none;\"><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
                            };
                            var datosNacionalidades = [];

                            for (k = 0; k < data.facultyInfo.regStudentsList.length; k++) {
                                datosNacionalidades.push({
                                    label: data.facultyInfo.regStudentsList[k].register,
                                    value: data.facultyInfo.regStudentsList[k].content,
                                    color: getRandomColor(),
                                });
                            }

                            function mostrarNacionalidades() {
                                var ctx = document.getElementById("canvasNacionalidades").getContext("2d");
                                var graficaInternacional = new Chart(ctx).Pie(datosNacionalidades, globalGraphSettings);
                                document.getElementById("graficaNacionalidades-legend").innerHTML = graficaInternacional.generateLegend();
                            }
                            $('#pieChart').appear(function() {
                                $(this).css({
                                    opacity: 1
                                });
                                setTimeout(mostrarNacionalidades, 300);
                            }, {
                                accX: 0,
                                accY: -155
                            }, 'easeInCubic');

                            elementos.estadisticas.show();

                            output += "<h3>Lista de departamentos</h3>";
                            for (m = 0; m < data.facultyInfo.departmentList.length; m++) {
                              output += "<br><b>" + data.facultyInfo.departmentList[m].register + "</b>:  " + data.facultyInfo.departmentList[m].content + "<br>";
                            }

                        } else {
                            output += "No se ha encontrado información relacionada";
                        }
                    }
                    $('#info').append(output);
                }
            });
            break;


        case "grad":
            $("#cargando").show();
            $.ajax({
                type: 'GET',
                url: 'http://jdecastroc.ovh:8081/posgrado/id/' + id,
                data: {}, //Especifica los datos que se enviarán al servidor
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
                        var output = "";

                        if (data.search == true) {
                            output += '<div class="col-md-10 blogShort">';
                            output += '<h2>' + data.name + ' - ' + data.japaneseName + '</h2>';
                            output += '<em>' + data.prefecture + ' / ' + data.type + '</em>';

                            if (data.guideUrl != "") {
                                output += ' / <a target="_blank" href="http://www.jpss.jp' + data.guideUrl + '">Guía universitaria</a>';
                            }
                            if (data.imageUrl != "") {
                                output += '<img class="pull-left img-responsive thumb img-thumbnail" width="230" height="186" style="border:0; padding-right: 10px;" alt="' + data.name + '" src="http://www.jpss.jp/' + data.imageUrl + '">';
                            }

                            output += '<h4>' + data.title + '</h4>';
                            output += '<article><p>' + data.description + '</p></article></br>';
                            //Departamentos
                            output += '<div style="padding-top: 20px;">';
                            for (j = 0; j < data.faculties.collegeFacultyList.length; j++) {
                                var array_split = data.faculties.collegeFacultyList[j].facultyUrl.split("/");
                                var id_grad = array_split["3"];
                                var id_depart = array_split["4"];
                                output += '<a class="button button-3d button-mini button-rounded button-aqua" target="_blank" href="' + "departamento?tipo=grad&id=" + id_grad + "&id_depart=" + id_depart + '">' + data.faculties.collegeFacultyList[j].facultyName + '</a>';
                            }
                            output += '</div>';
                            output += '</div>';
                            output += '<div class="divider"><i class="icon-circle"></i></div>';
                        }
                    }
                    $("#cargando").hide();
                    $('#mostrarEscuela').append(output);
                }
            });
            //AJAX ACCESO FUNCION
            break;

        case "tech":
            $("#cargando").show();
            $.ajax({
                type: 'GET',
                url: 'http://jdecastroc.ovh:8081/fp/id/' + id,
                data: {}, //Especifica los datos que se enviarán al servidor
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
                        var output = "";

                        if (data.search == true) {
                            output += '<div class="col-md-10 blogShort">';
                            output += '<h2>' + data.name + ' - ' + data.japaneseName + '</h2>';
                            output += '<em>' + data.prefecture + ' / ' + data.type + '</em>';

                            if (data.guideUrl != "") {
                                output += ' / <a target="_blank" href="http://www.jpss.jp' + data.guideUrl + '">Guía universitaria</a>';
                            }
                            if (data.imageUrl != "") {
                                output += '<img class="pull-left img-responsive thumb img-thumbnail" width="230" height="186" style="border:0; padding-right: 10px;" alt="' + data.name + '" src="http://www.jpss.jp/' + data.imageUrl + '">';
                            }

                            output += '<h4>' + data.title + '</h4>';
                            output += '<article><p>' + data.description + '</p></article></br>';
                            //Departamentos
                            output += '<div style="padding-top: 20px;">';
                            for (j = 0; j < data.faculties.collegeFacultyList.length; j++) {
                                var array = data.faculties.collegeFacultyList[j].facultyUrl.replace("#", "").split("/");
                                var id_fp = array[3];
                                var id_depart = array[5];
                                output += '<a class="button button-3d button-mini button-rounded button-aqua" target="_blank" href="' + "departamento?tipo=tech&id=" + id_fp + "&id_depart=" + id_depart + '">' + data.faculties.collegeFacultyList[j].facultyName + '</a>';
                            }
                            output += '</div>';
                            output += '</div>';
                            output += '<div class="divider"><i class="icon-circle"></i></div>';
                        }
                    }
                    $('#mostrarEscuela').append(output);
                }
            });

            //AJAX ACCESO


            break;
    }
    $("#cargando").hide();
});

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

function areResults(aprobados, suspensos) {
    if ((aprobados == "0") && (suspensos == "0")) {
        return false;
    } else {
        return true;
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

    var id = getUrlParameter('id');
    var tipo = getUrlParameter('tipo');
    var elementos = {
        estadisticas: $('.estadisticas'),
        labelNoResultsN1: $('.noResultsN1'),
        labelNoResultsN2: $('.noResultsN2'),
        labelNoResultsN3: $('.noResultsN3'),
        labelNoResultsN4: $('.noResultsN4'),
        labelNoResultsN5: $('.noResultsN5')
    };

    //On load
    elementos.labelNoResultsN1.hide();
    elementos.labelNoResultsN2.hide();
    elementos.labelNoResultsN3.hide();
    elementos.labelNoResultsN4.hide();
    elementos.labelNoResultsN5.hide();
    elementos.estadisticas.hide();


    switch (tipo) {
        case "univ":
            $("#cargando").show();
            $.ajax({
                type: 'GET',
                url: 'http://www.infojapon.com:8081/universidades/id/' + id,
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

            break;
        case "grad":
            $("#cargando").show();
            $.ajax({
                type: 'GET',
                url: 'http://www.infojapon.com:8081/posgrado/id/' + id,
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
                                output += '<a class="button button-3d button-mini button-rounded button-aqua" href="' + "departamento?tipo=grad&id=" + id_grad + "&id_depart=" + id_depart + '">' + data.faculties.collegeFacultyList[j].facultyName + '</a>';
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
            break;
        case "tech":
            $("#cargando").show();
            $.ajax({
                type: 'GET',
                url: 'http://www.infojapon.com:8081/fp/id/' + id,
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
                    $("#cargando").hide();
                    $('#mostrarEscuela').append(output);
                }
            });
            break;
        case "japones":
            $("#cargando").show();
            $.ajax({
                type: 'GET',
                url: 'http://www.infojapon.com:8081/escuelasIdiomas/id/' + id,
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
                            output += "<div class='container clearfix'>";
                            output += "<div><h2>" + data.name + "</h2><h3>" + data.japaneseName + "</h3></div>";
                            output += "<div class='col_half'>";
                            output += "<h4>Información de la escuela</h4>";
                            output += "<tbody>";
                            output += "<tr>";

                            output += "<td>";
                            output += "<i class='icon-line2-home'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Dirección:</b></span>" + data.address + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-phone-sign'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Teléfono:</b></span>" + data.phone + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-line-paper'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Fax:</b></span>" + data.fax + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-line-globe'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Web oficial:</b></span>" + data.officialUrl + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-line-mail'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Email:</b></span>" + data.email + "<br>";
                            output += "</td>";

                            output += "<br>";

                            output += "<td>";
                            output += "<i class='icon-line-briefcase'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Tipo de establecimiento:</b></span>" + data.establishmentType + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-chat-3'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Nombre del representante:</b></span>" + data.representativeName + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-line-head'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Nombre del director:</b></span>" + data.principalName + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-line-ribbon'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Fundación:</b></span>" + data.startingDate + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-stopwatch'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Periodo de licencia:</b></span>" + data.validityTerm + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-users'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Número de profesores:</b></span>" + data.teachersNumber + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-users2'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Capacidad:</b></span>" + data.capacity + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-home'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Alojamiento:</b></span>" + data.accommodations + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-file-settings'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Estado bajo la ley de educación escolar japonesa:</b></span>" + data.schoolStatus + "<br>";
                            output += "</td>";

                            output += "<br>";

                            output += "<td>";
                            output += "<i class='icon-line-square-check'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Requisitos de admisión:</b></span><br>" + data.admissionRequirements + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-line-target'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Proceso de selección:</b></span><br>" + data.selectionProcess + "<br>";
                            output += "</td>";

                            output += "<td>";
                            output += "<i class='icon-line-square-plus'></i>";
                            output += "<span style='padding-left: 5px; padding-right: 5px;'><b>Otros cursos:</b></span><br>" + data.otherCourses + "<br>";
                            output += "</td>";


                            output += "</tr>";
                            output += "</tbody>";
                            output += "</div>";
                            output += "<div class='col_half col_last'><div class='map-responsive'><iframe  frameborder='0' style='border:0' src='https://www.google.com/maps/embed/v1/place?q=" + data.japaneseName + "&key= AIzaSyBuvPXxGbEVF1BSE1NqlDX6ZVjXxFW2S5A ' allowfullscreen></iframe></div>";
                            output += "<br><i class='icon-map-marker' style='padding-top: 10px;'></i><span style='padding-right: 5px;'><b>Como llegar:</b></span><br>" + data.howToArrive + "</div>";
                            output += "</div>";

                            output += "<div class='container clearfix'>";

                            output += "<h4>Cursos</h4>";
                            output += "<div class='table-responsive'>";
                            output += "<table class='table-striped table'>";
                            output += "<thead><tr><th>Curso</th><th>Proposito</th><th>Duración</th><th>Horas de clase</th><th>Semanas</th><th>Meses de admisión</th><th>Tasa de selección</th><th>Tasa de admisión</th><th>Tasa de clases</th><th>Otros pagos</th><th>Pago total</th></tr></thead>";
                            output += "<tbody>";

                            for (i = 0; i < data.authorizedCourseList.length; i++) {
                                output += "<tr>";
                                output += "<td>" + data.authorizedCourseList[i].course + "</td>";
                                output += "<td>" + data.authorizedCourseList[i].purpose + "</td>";
                                output += "<td>" + data.authorizedCourseList[i].length + "</td>";
                                output += "<td>" + data.authorizedCourseList[i].classHours + " h</td>";
                                output += "<td>" + data.authorizedCourseList[i].weeks + "</td>";
                                output += "<td>" + data.authorizedCourseList[i].monthAdmission + "</td>";
                                output += "<td>" + data.authorizedCourseList[i].selectionFee + "¥</td>";
                                output += "<td>" + data.authorizedCourseList[i].admissionFee + "¥</td>";
                                output += "<td>" + data.authorizedCourseList[i].tuitionFee + "¥</td>";
                                output += "<td>" + data.authorizedCourseList[i].othersFee + "¥</td>";
                                output += "<td>" + data.authorizedCourseList[i].totalFee + "¥</td>";
                                output += "</tr>";
                            }
                            output += "</div>"; //tabla responsive
                            output += "</tbody>";
                            output += "</table>";
                            output += "</div>";
                            output += "<div>";
                            output += "<h4>Características</h4>";
                            output += "<tbody>";
                            output += "<tr>";
                            if (data.schoolFeatures["1"] != "") output += "<td><i class='icon-line-square-plus'></i><span style='padding-left: 5px; padding-right: 5px;'></span>" + data.schoolFeatures["1"] + "</td><br>";
                            if (data.schoolFeatures["2"] != "") output += "<td><i class='icon-line-square-plus'></i><span style='padding-left: 5px; padding-right: 5px;'></span>" + data.schoolFeatures["2"] + "</td><br>";
                            if (data.schoolFeatures["3"] != "") output += "<td><i class='icon-line-square-plus'></i><span style='padding-left: 5px; padding-right: 5px;'></span>" + data.schoolFeatures["3"] + "</td><br>";
                            output += "</tr>";
                            output += "</tbody>";
                            output += "</div><br>";

                            output += "<h4>Estadísticas</h4>";


                            // Gráfica de estudiantes internacionales
                            var datosNacionalidades = [];
                            const MINIMO_ESTUDIANTES = 2;

                            if (data.registeredStudents.Myanmar > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Birmania',
                                    value: data.registeredStudents.Myanmar,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Singapore > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Singapur',
                                    value: data.registeredStudents.Singapore,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Cambodia > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Cambodia',
                                    value: data.registeredStudents.Cambodia,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Malaysia > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Malasia',
                                    value: data.registeredStudents.Malaysia,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Thailand > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Tailandia',
                                    value: data.registeredStudents.Thailand,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.USA > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'U.S.A',
                                    value: data.registeredStudents.USA,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.SaudiArabia > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Arabia Saudí',
                                    value: data.registeredStudents.SaudiArabia,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Vietnam > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Vietnam',
                                    value: data.registeredStudents.Vietnam,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Mongolia > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Mongolia',
                                    value: data.registeredStudents.Mongolia,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Sweden > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Suecia',
                                    value: data.registeredStudents.Sweden,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.China > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'China',
                                    value: data.registeredStudents.China,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.France > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Francia',
                                    value: data.registeredStudents.France,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Nepal > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Nepal',
                                    value: data.registeredStudents.Nepal,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Korea > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Corea',
                                    value: data.registeredStudents.Korea,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Others > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Otros',
                                    value: data.registeredStudents.Others,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Philippines > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Filipinas',
                                    value: data.registeredStudents.Philippines,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Switzerland > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Suiza',
                                    value: data.registeredStudents.Switzerland,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.India > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'India',
                                    value: data.registeredStudents.India,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Spain > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'España',
                                    value: data.registeredStudents.Spain,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Canada > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Canada',
                                    value: data.registeredStudents.Canada,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Bangladesh > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Bangladesh',
                                    value: data.registeredStudents.Bangladesh,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Taiwan > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Taiwan',
                                    value: data.registeredStudents.Taiwan,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Italy > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Italia',
                                    value: data.registeredStudents.Italy,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Australia > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Australia',
                                    value: data.registeredStudents.Australia,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Germany > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Alemania',
                                    value: data.registeredStudents.Germany,
                                    color: getRandomColor(),
                                });
                            };
                            if (data.registeredStudents.Indonesia > MINIMO_ESTUDIANTES) {
                                datosNacionalidades.push({
                                    label: 'Indonesia',
                                    value: data.registeredStudents.Indonesia,
                                    color: getRandomColor(),
                                });
                            };

                            var globalGraphSettings = {
                                animation: Modernizr.canvas,
                                responsive: true,
                                sliceVisibilityThreshold: 0,
                                animationEasing: "easeOutQuart",
                                tooltipTemplate: "<%if (label){%><%=label%>: <%}%><%= value %>",
                                segmentStrokeColor: "#f9f9f9",
                                legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li style=\"list-style-type: none;\"><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
                            };


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

                            //Gráfica de destino de los graduados
                            var datosGraduados = [{
                                label: 'Escuela superior',
                                value: data.studentsDestination['Graduate School'],
                                color: getRandomColor(),
                            }, {
                                label: 'Universidad',
                                value: data.studentsDestination.University,
                                color: getRandomColor(),
                            }, {
                                label: 'Escuela técnica',
                                value: data.studentsDestination['Technical School'],
                                color: getRandomColor(),
                            }, {
                                label: 'Escuela secundaria',
                                value: data.studentsDestination['Junior College'],
                                color: getRandomColor(),
                            }, {
                                label: 'Escuelas profesionales superiores (FP-II)',
                                value: data.studentsDestination['Special Training School'],
                                color: getRandomColor(),
                            }, {
                                label: 'Escuelas profesionales (FP)',
                                value: data.studentsDestination['Training School'],
                                color: getRandomColor(),
                            }, {
                                label: 'Otros',
                                value: data.studentsDestination.Other,
                                color: getRandomColor(),
                            }];

                            function mostrarGraficoGraduados() {
                                var ctx = document.getElementById("canvasGraduados").getContext("2d");
                                var graficaGraduados = new Chart(ctx).Pie(datosGraduados, globalGraphSettings);
                                document.getElementById("graficaGraduados-legend").innerHTML = graficaGraduados.generateLegend();
                            }
                            $('#graficoGraduado').appear(function() {
                                $(this).css({
                                    opacity: 1
                                });
                                setTimeout(mostrarGraficoGraduados, 300);
                            }, {
                                accX: 0,
                                accY: -155
                            }, 'easeInCubic');

                            //Grafico N1
                            if (areResults(data.n1Stadistics.Certified, data.n1Stadistics.Failed)) {
                                var datosExaminadosN1 = [{
                                    label: 'Suspensos',
                                    value: data.n1Stadistics.Failed,
                                    color: getRandomColor(),
                                }, {
                                    label: 'Aprobados',
                                    value: data.n1Stadistics.Certified,
                                    color: getRandomColor(),
                                }];

                                function mostrarGraficoN1() {
                                    var ctx = document.getElementById("graficoCanvasN1").getContext("2d");
                                    var graficaN1 = new Chart(ctx).Pie(datosExaminadosN1, globalGraphSettings);
                                    document.getElementById("graficoN1-legend").innerHTML = graficaN1.generateLegend();
                                }
                                $('#graficoN1').appear(function() {
                                    $(this).css({
                                        opacity: 1
                                    });
                                    setTimeout(mostrarGraficoN1, 300);
                                }, {
                                    accX: 0,
                                    accY: -155
                                }, 'easeInCubic');
                            } else {
                                $('#graficoCanvasN1').hide();
                                elementos.labelNoResultsN1.show();
                            }

                            //Grafico N2
                            if (areResults(data.n2Stadistics.Certified, data.n2Stadistics.Failed)) {
                                var datosExaminadosN2 = [{
                                    label: 'Suspensos',
                                    value: data.n2Stadistics.Failed,
                                    color: getRandomColor(),
                                }, {
                                    label: 'Aprobados',
                                    value: data.n2Stadistics.Certified,
                                    color: getRandomColor(),
                                }];

                                function mostrarGraficoN2() {
                                    var ctx = document.getElementById("graficoCanvasN2").getContext("2d");
                                    var graficaN2 = new Chart(ctx).Pie(datosExaminadosN2, globalGraphSettings);
                                    document.getElementById("graficoN2-legend").innerHTML = graficaN2.generateLegend();
                                }
                                $('#graficoN2').appear(function() {
                                    $(this).css({
                                        opacity: 1
                                    });
                                    setTimeout(mostrarGraficoN2, 300);
                                }, {
                                    accX: 0,
                                    accY: -155
                                }, 'easeInCubic');
                            } else {
                                $('#graficoCanvasN2').hide();
                                elementos.labelNoResultsN2.show();
                            }

                            //Grafico N3
                            if (areResults(data.n3Stadistics.Certified, data.n3Stadistics.Failed)) {

                                var datosExaminadosN3 = [{
                                    label: 'Suspensos',
                                    value: data.n3Stadistics.Failed,
                                    color: getRandomColor(),
                                }, {
                                    label: 'Aprobados',
                                    value: data.n3Stadistics.Certified,
                                    color: getRandomColor(),
                                }];

                                function mostrarGraficoN3() {
                                    var ctx = document.getElementById("graficoCanvasN3").getContext("2d");
                                    var graficaN3 = new Chart(ctx).Pie(datosExaminadosN3, globalGraphSettings);
                                    document.getElementById("graficoN3-legend").innerHTML = graficaN3.generateLegend();
                                }
                                $('#graficoN3').appear(function() {
                                    $(this).css({
                                        opacity: 1
                                    });
                                    setTimeout(mostrarGraficoN3, 300);
                                }, {
                                    accX: 0,
                                    accY: -155
                                }, 'easeInCubic');
                            } else {
                                $('#graficoCanvasN3').hide();
                                elementos.labelNoResultsN3.show();
                            }

                            //Grafico N4
                            if (areResults(data.n4Stadistics.Certified, data.n4Stadistics.Failed)) {

                                var datosExaminadosN4 = [{
                                    label: 'Suspensos',
                                    value: data.n4Stadistics.Failed,
                                    color: getRandomColor(),
                                }, {
                                    label: 'Aprobados',
                                    value: data.n4Stadistics.Certified,
                                    color: getRandomColor(),
                                }];

                                function mostrarGraficoN4() {
                                    var ctx = document.getElementById("graficoCanvasN4").getContext("2d");
                                    var graficaN4 = new Chart(ctx).Pie(datosExaminadosN4, globalGraphSettings);
                                    document.getElementById("graficoN4-legend").innerHTML = graficaN4.generateLegend();
                                }
                                $('#graficoN4').appear(function() {
                                    $(this).css({
                                        opacity: 1
                                    });
                                    setTimeout(mostrarGraficoN4, 300);
                                }, {
                                    accX: 0,
                                    accY: -155
                                }, 'easeInCubic');
                            } else {
                                $('#graficoCanvasN4').hide();
                                elementos.labelNoResultsN4.show();
                            }

                            //Grafico N5
                            if (areResults(data.n5Stadistics.Certified, data.n5Stadistics.Failed)) {

                                var datosExaminadosN5 = [{
                                    label: 'Suspensos',
                                    value: data.n5Stadistics.Failed,
                                    color: getRandomColor(),
                                }, {
                                    label: 'Aprobados',
                                    value: data.n5Stadistics.Certified,
                                    color: getRandomColor(),
                                }];

                                function mostrarGraficoN5() {
                                    var ctx = document.getElementById("graficoCanvasN5").getContext("2d");
                                    var graficaN5 = new Chart(ctx).Pie(datosExaminadosN5, globalGraphSettings);
                                    document.getElementById("graficoN5-legend").innerHTML = graficaN5.generateLegend();
                                }
                                $('#graficoN5').appear(function() {
                                    $(this).css({
                                        opacity: 1
                                    });
                                    setTimeout(mostrarGraficoN5, 300);
                                }, {
                                    accX: 0,
                                    accY: -155
                                }, 'easeInCubic');
                            } else {
                                $('#graficoCanvasN5').hide();
                                elementos.labelNoResultsN5.show();
                            }

                            elementos.estadisticas.show();
                            output += "</div>"; //estadísticas
                            output += "</div>";
                        }

                    }
                    $("#cargando").hide();
                    $('#mostrarEscuela').append(output);
                }
            });
            break;

        default:
            alert("No se ha introducido información válida");
            break;
    }
});

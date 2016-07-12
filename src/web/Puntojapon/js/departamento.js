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
        estadisticas: $('.estadisticas'),
        cargando: $("#cargando"),
        deparamento_info: $('#tab-9')
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

    var datosNacionalidades = [];

    elementos.estadisticas.hide();
    //  elementos.departamento_info.hide();

    switch (tipo) {
        case "univ":
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
                            output += "<article><p>";

                            if (data.description.indexOf('●') > -1) {
                                var description_split = data.description.split("●");
                                for (p = 1; p < description_split.length; p++) {
                                    output += "● " + description_split[p] + "<br><br>";
                                }
                            } else {
                                output += data.description;
                            }
                            output += "</p></article>";

                            //Departamentos
                            output += '<div style="padding-top: 20px;">';
                            for (j = 0; j < data.faculties.collegeFacultyList.length; j++) {
                                var array_split = data.faculties.collegeFacultyList[j].facultyUrl.split("/");
                                var id_univ = array_split["3"];
                                var id_depart = array_split["4"];
                                output += '<a class="button button-3d button-mini button-rounded button-aqua" href="' + "departamento?tipo=univ&id=" + id_univ + "&id_depart=" + id_depart + '">' + data.faculties.collegeFacultyList[j].facultyName + '</a>';
                            }
                            output += '</div>';
                            output += '</div>';
                            output += '<div class="divider"><i class="icon-circle"></i></div>';
                        }
                    }
                    $('#mostrarEscuela').append(output);
                }
            });

            //AJAX ACCESO UNIVERSIDAD
            $.ajax({
                type: 'GET',
                url: 'http://www.infojapon.com:8081/universidades/id/' + id + '/' + departamento + '/access',
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
                            $('#departamento-principal').append("<h2>" + data.name + "</h2>" + "<h4>Noticias</h4>" + data.news + "<br>");

                            output += "<h4>Acceso a facultad</h4>";


                            for (p = 0; p < data.facultyAccess.maps.length; p++) {
                                output += "<div class='col_half'>";
                                output += "<h4>" + data.facultyAccess.maps[p].name + "</h4><br>";
                                output += "<b>Dirección:</b> " + data.facultyAccess.maps[p].address + "<br>";
                                //output += "<b>Sitios cercanos:</b> " + data.facultyAccess.maps[p].nearbyPlaces + "<br>";
                                output += "<b>Sitios cercanos:</b><br>";

                                if (data.facultyAccess.maps[p].nearbyPlaces.indexOf('•') > -1) {
                                    var description_split = data.facultyAccess.maps[p].nearbyPlaces.split("•");
                                    for (var b = 1; b < description_split.length; b++) {
                                        output += "• " + description_split[b] + "<br><br>";
                                    }
                                }

                                if (data.facultyAccess.maps[p].nearbyPlaces.indexOf('■') > -1) {
                                    var description_split = data.facultyAccess.maps[p].nearbyPlaces.split("■");
                                    for (var b = 1; b < description_split.length; b++) {
                                        output += "■ " + description_split[b] + "<br><br>";
                                    }
                                }

                                if (data.facultyAccess.maps[p].nearbyPlaces.indexOf('■') <= -1 && data.facultyAccess.maps[p].nearbyPlaces.indexOf('■') <= -1) {
                                    output += data.facultyAccess.maps[p].nearbyPlaces;
                                }

                                output += "<br><b>Otra información:</b><br>"; //Split
                                if (data.facultyAccess.maps[p].otherInfo.indexOf('•') > -1) {
                                    var description_split_otherInfo = data.facultyAccess.maps[p].otherInfo.split("•");
                                    for (var b = 1; b < description_split_otherInfo.length; b++) {
                                        output += "• " + description_split_otherInfo[b] + "<br><br>";
                                        console.log(description_split_otherInfo[b]);
                                    }
                                }

                                if (data.facultyAccess.maps[p].otherInfo.indexOf('■') > -1) {
                                    var description_split_otherInfo = data.facultyAccess.maps[p].otherInfo.split("■");
                                    for (var b = 1; b < description_split_otherInfo.length; b++) {
                                        output += "■  " + description_split_otherInfo[b] + "<br><br>";
                                        console.log(description_split_otherInfo[b]);
                                    }
                                }

                                if (data.facultyAccess.maps[p].otherInfo.indexOf('■') <= -1 && data.facultyAccess.maps[p].otherInfo.indexOf('■') <= -1) {
                                    output += data.facultyAccess.maps[p].otherInfo;
                                }

                                output += "</div>";
                                output += "<div class='col_half col_last'><div class='map-responsive'><iframe  frameborder='0' style='border:0' src='https://www.google.com/maps/embed/v1/place?q=" + data.facultyAccess.maps[p].address + "&key= AIzaSyBuvPXxGbEVF1BSE1NqlDX6ZVjXxFW2S5A ' allowfullscreen></iframe></div></div>";

                            }
                        } else {
                            output += "No se ha encontrado información relacionada";
                        }
                    }
                    $('#acceso').append(output);
                }
            });
            //AJAX ADMISIONES UNIVERSIDAD
            $.ajax({
                type: 'GET',
                url: 'http://www.infojapon.com:8081/universidades/id/' + id + '/' + departamento + '/admissions',
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
                            output += "<h4>Admisiones</h4>";
                            output += data.facultyAdmissions.lastUpdate + "<br>";

                            for (p = 0; p < data.facultyAdmissions.rowTable.length; p++) {
                                output += "<b>" + data.facultyAdmissions.rowTable[p].register + "</b><br>" + data.facultyAdmissions.rowTable[p].content + "<br><br>";
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
                url: 'http://www.infojapon.com:8081/universidades/id/' + id + '/' + departamento + '/facilities',
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
                            output += "<h4>Instalaciones</h4>";
                            for (p = 0; p < data.facultyFacilities.objectTable.length; p++) {
                                output += "<b>" + data.facultyFacilities.objectTable[p].register + "</b><br>" + data.facultyFacilities.objectTable[p].content + "<br><br>";
                            }
                            for (q = 0; q < data.facultyFacilities.imagesLinks.length; q++) {
                                output += "<div class='col-md-4'>";
                                output += "<img src= http://www.jpss.jp/" + data.facultyFacilities.imagesLinks[q] + " class='img-responsive inline-block thumbnail' style='text-align:center;'>";
                                output += "</div>";
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
                url: 'http://www.infojapon.com:8081/universidades/id/' + id + '/' + departamento + '/support',
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
                            output += "<h4>Ayuda al estudiante</h4>";
                            for (p = 0; p < data.facultySupport.objectTable.length; p++) {
                                output += "<b>" + data.facultySupport.objectTable[p].register + "</b><br>  " + data.facultySupport.objectTable[p].content + "<br><br>";
                            }


                            for (q = 0; q < data.facultySupport.imagesLinks.length; q++) {
                                output += "<div class='col-md-4'>";
                                output += "<img src= http://www.jpss.jp/" + data.facultySupport.imagesLinks[q] + " class='img-responsive inline-block thumbnail' style='text-align:center;'>";
                                output += "</div>";
                            }

                        } else {
                            output += "No se ha encontrado información relacionada";
                        }
                    }
                    $('#ayudaEstudiante').append(output);
                }
            });

            //AJAX informacion universidades
            $.ajax({
                type: 'GET',
                url: 'http://www.infojapon.com:8081/universidades/id/' + id + '/' + departamento + '/info',
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
                                output += "<b>" + data.facultyInfo.objectTable[p].register + "</b><br>" + data.facultyInfo.objectTable[p].content + "<br><br>";
                            }

                            for (k = 0; k < data.facultyInfo.regStudentsList.length; k++) {
                                datosNacionalidades.push({
                                    label: data.facultyInfo.regStudentsList[k].register,
                                    value: data.facultyInfo.regStudentsList[k].content,
                                    color: getRandomColor(),
                                });
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

                            // Don't show the students nationality chart if there is empty content
                            if (data.facultyInfo.regStudentsList[0].content != "") {
                                elementos.estadisticas.show();
                            } else {
                                elementos.estadisticas.hide();
                            }

                            output += "<h3>Lista de departamentos</h3>";
                            for (m = 0; m < data.facultyInfo.departmentList.length; m++) {
                                var registerData = data.facultyInfo.departmentList[m].content.split("●");
                                output += "<br><b>" + data.facultyInfo.departmentList[m].register + "</b><br>";
                                output += "<ul>";
                                for (n = 1; n < registerData.length; n++) {
                                    output += "<li>" + registerData[n] + "</li>";
                                }
                                output += "</ul>";
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
                            output += "<article><p>";

                            if (data.description.indexOf('●') > -1) {
                                var description_split = data.description.split("●");
                                for (p = 1; p < description_split.length; p++) {
                                    output += "● " + description_split[p] + "<br><br>";
                                }
                            } else {
                                output += data.description;
                            }
                            output += "</p></article>";

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
                    $('#mostrarEscuela').append(output);
                }
            });
            //AJAX ACCESO FUNCION
            $.ajax({
                type: 'GET',
                url: 'http://www.infojapon.com:8081/posgrado/id/' + id + '/' + departamento + '/access',
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
                            $('#departamento-principal').append("<h2>" + data.name + "</h2>" + "<h4>Noticias</h4>" + data.news + "<br>");

                            output += "<h4>Acceso a facultad</h4>";


                            for (p = 0; p < data.facultyAccess.maps.length; p++) {
                                output += "<div class='col_half'>";
                                output += "<h4>" + data.facultyAccess.maps[p].name + "</h4><br>";
                                output += "<b>Dirección:</b> " + data.facultyAccess.maps[p].address + "<br>";
                                //output += "<b>Sitios cercanos:</b> " + data.facultyAccess.maps[p].nearbyPlaces + "<br>";
                                output += "<b>Sitios cercanos:</b><br>";

                                if (data.facultyAccess.maps[p].nearbyPlaces.indexOf('•') > -1) {
                                    var description_split = data.facultyAccess.maps[p].nearbyPlaces.split("•");
                                    for (var b = 1; b < description_split.length; b++) {
                                        output += "• " + description_split[b] + "<br><br>";
                                    }
                                }

                                if (data.facultyAccess.maps[p].nearbyPlaces.indexOf('■') > -1) {
                                    var description_split = data.facultyAccess.maps[p].nearbyPlaces.split("■");
                                    for (var b = 1; b < description_split.length; b++) {
                                        output += "■ " + description_split[b] + "<br><br>";
                                    }
                                }

                                if (data.facultyAccess.maps[p].nearbyPlaces.indexOf('■') <= -1 && data.facultyAccess.maps[p].nearbyPlaces.indexOf('■') <= -1) {
                                    output += data.facultyAccess.maps[p].nearbyPlaces;
                                }

                                output += "<br><b>Otra información:</b><br>"; //Split
                                if (data.facultyAccess.maps[p].otherInfo.indexOf('•') > -1) {
                                    var description_split_otherInfo = data.facultyAccess.maps[p].otherInfo.split("•");
                                    for (var b = 1; b < description_split_otherInfo.length; b++) {
                                        output += "• " + description_split_otherInfo[b] + "<br><br>";
                                        console.log(description_split_otherInfo[b]);
                                    }
                                }

                                if (data.facultyAccess.maps[p].otherInfo.indexOf('■') > -1) {
                                    var description_split_otherInfo = data.facultyAccess.maps[p].otherInfo.split("■");
                                    for (var b = 1; b < description_split_otherInfo.length; b++) {
                                        output += "■  " + description_split_otherInfo[b] + "<br><br>";
                                        console.log(description_split_otherInfo[b]);
                                    }
                                }

                                if (data.facultyAccess.maps[p].otherInfo.indexOf('■') <= -1 && data.facultyAccess.maps[p].otherInfo.indexOf('■') <= -1) {
                                    output += data.facultyAccess.maps[p].otherInfo;
                                }

                                output += "</div>";
                                output += "<div class='col_half col_last'><div class='map-responsive'><iframe  frameborder='0' style='border:0' src='https://www.google.com/maps/embed/v1/place?q=" + data.facultyAccess.maps[p].address + "&key= AIzaSyBuvPXxGbEVF1BSE1NqlDX6ZVjXxFW2S5A ' allowfullscreen></iframe></div></div>";

                            }
                        } else {
                            output += "No se ha encontrado información relacionada";
                        }
                    }
                    $('#acceso').append(output);
                }
            });

            //AJAX ADMISIONES UNIVERSIDAD
            $.ajax({
                type: 'GET',
                url: 'http://www.infojapon.com:8081/posgrado/id/' + id + '/' + departamento + '/admissions',
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
                            output += "<h4>Admisiones</h4>";
                            output += data.facultyAdmissions.lastUpdate + "<br>";

                            for (p = 0; p < data.facultyAdmissions.rowTable.length; p++) {
                                output += "<b>" + data.facultyAdmissions.rowTable[p].register + "</b><br>" + data.facultyAdmissions.rowTable[p].content + "<br><br>";
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
                url: 'http://www.infojapon.com:8081/posgrado/id/' + id + '/' + departamento + '/facilities',
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
                            output += "<h4>Instalaciones</h4>";
                            for (p = 0; p < data.facultyFacilities.objectTable.length; p++) {
                                output += "<b>" + data.facultyFacilities.objectTable[p].register + "</b><br>" + data.facultyFacilities.objectTable[p].content + "<br><br>";
                            }
                            for (q = 0; q < data.facultyFacilities.imagesLinks.length; q++) {
                                output += "<div class='col-md-4'>";
                                output += "<img src= http://www.jpss.jp/" + data.facultyFacilities.imagesLinks[q] + " class='img-responsive inline-block thumbnail' style='text-align:center;'>";
                                output += "</div>";
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
                url: 'http://www.infojapon.com:8081/posgrado/id/' + id + '/' + departamento + '/support',
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
                            output += "<h4>Ayuda al estudiante</h4>";
                            for (p = 0; p < data.facultySupport.objectTable.length; p++) {
                                output += "<b>" + data.facultySupport.objectTable[p].register + "</b><br>  " + data.facultySupport.objectTable[p].content + "<br><br>";
                            }


                            for (q = 0; q < data.facultySupport.imagesLinks.length; q++) {
                                output += "<div class='col-md-4'>";
                                output += "<img src= http://www.jpss.jp/" + data.facultySupport.imagesLinks[q] + " class='img-responsive inline-block thumbnail' style='text-align:center;'>";
                                output += "</div>";
                            }

                        } else {
                            output += "No se ha encontrado información relacionada";
                        }
                    }
                    $('#ayudaEstudiante').append(output);
                }
            });

            //AJAX informacion universidades
            $.ajax({
                type: 'GET',
                url: 'http://www.infojapon.com:8081/posgrado/id/' + id + '/' + departamento + '/info',
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
                                output += "<b>" + data.facultyInfo.objectTable[p].register + "</b><br>" + data.facultyInfo.objectTable[p].content + "<br><br>";
                            }

                            for (k = 0; k < data.facultyInfo.regStudentsList.length; k++) {
                                datosNacionalidades.push({
                                    label: data.facultyInfo.regStudentsList[k].register,
                                    value: data.facultyInfo.regStudentsList[k].content,
                                    color: getRandomColor(),
                                });
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

                            // Don't show the students nationality chart if there is empty content
                            if (data.facultyInfo.regStudentsList[0].content != "") {
                                elementos.estadisticas.show();
                            } else {
                                elementos.estadisticas.hide();
                            }

                            output += "<h3>Lista de departamentos</h3>";
                            for (m = 0; m < data.facultyInfo.departmentList.length; m++) {
                                var registerData = data.facultyInfo.departmentList[m].content.split("●");
                                output += "<br><b>" + data.facultyInfo.departmentList[m].register + "</b><br>";
                                output += "<ul>";
                                for (n = 1; n < registerData.length; n++) {
                                    output += "<li>" + registerData[n] + "</li>";
                                }
                                output += "</ul>";
                            }

                        } else {
                            output += "No se ha encontrado información relacionada";
                        }
                    }
                    $('#info').append(output);
                }
            });
            break;

        case "tech":

            $('#tabAdmisiones').addClass("ui-tabs-active ui-state-active");
/*          $('#admisiones').attr('style','display: block;'); //NOT WORKING
            $('#acceso').attr('style','display: none;'); //NOT WORKING*/
            document.getElementById("admisiones").style.display = '"block"';
            document.getElementById("acceso").style.display = '"none"';


            //Hide options
            $('#tabAcceso').hide();
            $('#tabInstalaciones').hide();
            $('#tabAcceso').hide();


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
                            output += "<article><p>";

                            if (data.description.indexOf('●') > -1) {
                                var description_split = data.description.split("●");
                                for (p = 1; p < description_split.length; p++) {
                                    output += "● " + description_split[p] + "<br><br>";
                                }
                            } else {
                                output += data.description;
                            }
                            output += "</p></article>";

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

            //ADMISIONES FP
            $.ajax({
                type: 'GET',
                url: 'http://www.infojapon.com:8081/fp/id/' + id + '/admissions',
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
                            $('#departamento-principal').append("<h2>" + data.name + "</h2>" + "<h4>Noticias</h4>" + data.news + "<br>");
                            output += "<h4>Admisiones</h4>";

                            output += "<b>Dirección y contacto: </b><br>";
                            output += data.facultyTechEssentialInfo.addressInfo.content + "<br>";

                            for (var a = 0; a < data.facultyTechEssentialInfo.registerStudents.content.length; a++) {
                                output += "<b>Estudiantes del " + data.facultyTechEssentialInfo.registerStudents.content[a].register.replace("year", "") + "</b><br>";
                                output += data.facultyTechEssentialInfo.registerStudents.content[a].content + "<br><br>";
                            }

                            output += "<h4>Información de acceso y admisión</h4>";
                            for (p = 0; p < data.facultyTechEssentialInfo.entranceInfo.content.length; p++) {
                                output += "<b>" + data.facultyTechEssentialInfo.entranceInfo.content[p].register + ": </b><br>";
                                output += data.facultyTechEssentialInfo.entranceInfo.content[p].content + "<br>";
                            }

                        } else {
                            output += "No se ha encontrado información relacionada";
                        }
                    }
                    $('#admisiones').append(output);
                }
            });

            //INFORMACIÓN FP
            $.ajax({
                type: 'GET',
                url: 'http://www.infojapon.com:8081/fp/id/' + id + '/info',
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
                            output += "<h4>Información de facultades y prácticas</h4>";

                            for (var p = 0; p < data.facultyTechInfo.courses.length; p++) {
                                output += "<br><h4>" + data.facultyTechInfo.courses[p].courseName + "</h4>";
                                output += "<b>Descripción: </b><br>";
                                output += data.facultyTechInfo.courses[p].courseDescription + "<br>";
                                output += "<b>Tipo: </b>";
                                output += data.facultyTechInfo.courses[p].courseWorkPermitted + "<br>";
                                for (var o = 0; o < data.facultyTechInfo.courses[p].courseAdmissionInfo.length; o++) {
                                  output += "<b>" + data.facultyTechInfo.courses[p].courseAdmissionInfo[o].register + ": </b> ";
                                  output += data.facultyTechInfo.courses[p].courseAdmissionInfo[o].content + "<br>";
                                }
                            }

                        } else {
                            output += "No se ha encontrado información relacionada";
                        }
                    }
                    $('#info').append(output);
                }
            });

            //Apoyo FP
            $.ajax({
                type: 'GET',
                url: 'http://www.infojapon.com:8081/fp/id/' + id + '/support',
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
                            output += "<h4>Ayudas a estudiantes internacionales</h4>";
                            for (var k = 0; k < data.facultyTechSupport.objectInfo.length; k++) {
                              output += "<b>" + data.facultyTechSupport.objectInfo[k].register + "</b><br>";
                              output +=  data.facultyTechSupport.objectInfo[k].content + "<br>";
                            }

                        } else {
                            output += "No se ha encontrado información relacionada";
                        }
                    }
                    $('#ayudaEstudiante').append(output);
                }
            });

            break;
    }
    elementos.cargando.hide();


    function mostrarNacionalidades() {
        var ctx = document.getElementById("canvasNacionalidades").getContext("2d");
        var graficaInternacional = new Chart(ctx).Pie(datosNacionalidades, globalGraphSettings);
        document.getElementById("graficaNacionalidades-legend").innerHTML = graficaInternacional.generateLegend();
    }
});

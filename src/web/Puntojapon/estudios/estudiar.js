$(document).ready(function() {

    //Semaforo
    var semaforo = 1;

    //Pasos
    var elementoPrimerPaso = $('#ui-id-1');
    var elementoSegundoPaso = $('#ui-id-2');
    var elementoTercerPaso = $('#ui-id-3');
    var elementoUltimoPaso = $('#ui-id-4');

    var pasoTipoEstudios = $('#desContainer');

    //Primera pagina
    var desContainer = $('#desContainer');
    var showFp = $('#fp');
    var showGrado = $('#grado');
    var showPosgrado = $('#posgrado');
    var showJapones = $('#lenguaJaponesa');

    //Segunda pagina

    var infoPrefectura = $('#infoPrefectura');

    var nivelEstudios = $("#studiesSelector");
    var prefecturaSeleccion = $("#prefectureSelector");
    var languageAreaSeleccion = $("#languageAreaSelector");
    var imagenPrefecturas = $("#prefecturasImg");
    var imagenTokyo = $("#tokyoImg");

    imagenTokyo.hide();
    languageAreaSeleccion.hide();
    showGrado.show();
    showPosgrado.hide();
    showJapones.hide();
    showFp.hide();
    desContainer.fadeIn('slow');
    infoPrefectura.hide();

    //Tercera pagina
    var areaGrado = $('#areaEstudioGrado');
    var areaPosgrado = $('#areaEstudioPosgrado');
    var areaFp = $('#areaEstudioFp');
    var areaJapones = $('#areaEstudioJapones');
    var areaDefault = $('#areaDefault');

    var tipoGrado = $('#areaSelector');
    var idiomaGrado = $('#idiomaSelector');

    var siguienteUltimoPaso = $('#buscar');


    var avanzadasUni = $('#avanzadasUni');
    var avanzadasFp = $('#avanzadasFp');
    var avanzadasPosgrado = $('#avanzadasPosgrado');
    var avanzadasJapones = $('#avanzadasJapones');

    avanzadasUni.hide();
    avanzadasFp.hide();
    avanzadasPosgrado.hide();
    avanzadasJapones.hide();


    areaGrado.hide();
    areaPosgrado.hide();
    areaFp.hide();
    areaJapones.hide();
    areaDefault.show();

    //Cuarta página
    var mostrarEscuelas = $('#mostrarEscuelas');

    var cargaEscuelas = $('#cargandoEscuelas');

    var escuelas = $('div.escuelas');




    cargaEscuelas.hide();

    //Eventos botones
    elementoPrimerPaso.click(function() {
        mostrarEscuelas.empty();
    });

    elementoSegundoPaso.click(function() {
        mostrarEscuelas.empty();
    });

    elementoTercerPaso.click(function() {
        mostrarEscuelas.empty();
    });

    elementoUltimoPaso.click(function() {
        if (semaforo > 0 && document.getElementById("prefectureSelector").value != "" && nivelEstudios.value != "") {
            loadColleges();
        } else {
            var output = "<ul>";
            output += '<div class="alert alert-danger">';
            output += '<i class="icon-remove-sign"></i><strong>¡Oh vaya!</strong> Se te ha olvidado rellenar la información en los pasos previos.';
            output += '</div>';
            output += "</ul>";
            mostrarEscuelas.append(output);
            mostrarEscuelas.show();
        }
    });

    siguienteUltimoPaso.click(function() {
        if (semaforo > 0 && document.getElementById("prefectureSelector").value != "" && nivelEstudios.value != "") {
            loadColleges();
        } else {
            var output = "<ul>";
            output += '<div class="alert alert-danger">';
            output += '<i class="icon-remove-sign"></i><strong>¡Oh vaya!</strong> Se te ha olvidado rellenar la información en los pasos previos.';
            output += '</div>';
            output += "</ul>";
            mostrarEscuelas.append(output);
            mostrarEscuelas.show();
        }
    });


    $(document).on('change', '#prefectureSelector', function() {
        //alert("valor prefectura: " + document.getElementById('prefectureSelector').options[document.getElementById('prefectureSelector').selectedIndex].value);
        //alert("valor contenedor: " + document.getElementById('desContainer').value);
        if ((document.getElementById('prefectureSelector').options[document.getElementById('prefectureSelector').selectedIndex].value == "Tokio") && document.getElementById('studiesSelector').value == "japones") {

            imagenPrefecturas.hide();
            imagenTokyo.show();
            languageAreaSeleccion.fadeIn('slow');

        } else {
            imagenTokyo.hide();
            imagenPrefecturas.show();
            languageAreaSeleccion.hide();
        }
    });

    $('.pop').on('click', function() {
        //$('.imagepreview').attr('src', $(this).find('img').attr('src'));
        document.getElementById("imagepreviewId").setAttribute("src", $(this).attr('src'));
        $('#imagemodal').modal('show');
    });


    // Paginacion de escuelas
    function cargarEscuelas(lista) {
        mostrarEscuelas.show();
        var i = 1;
        while (document.getElementById("pag-estudios-" + i) != null) {
            if (i == lista) {
                document.getElementById("pag-estudios-" + i).removeAttribute("style");
                document.getElementById("pag-estudios-" + i).querySelectorAll("li#pagination-li")[--lista].className = "active";
            } else {
                document.getElementById("pag-estudios-" + i).setAttribute("style", "display: none");
            }
            i++;
        }
        mostrarEscuelas.fadeIn('slow');
    }

    //Paginacion de escuelas
    $(document).on('click', '.loadSchoolDiv', function() {
        cargarEscuelas($(this).text());
    });


    //Wikipedia get Info
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


    nivelEstudios.change(function() {
        switch (document.getElementById("studiesSelector").value) {
            case "fp":
                showGrado.hide();
                showPosgrado.hide();
                showJapones.hide();
                showFp.show();
                desContainer.fadeIn('slow');

                areaDefault.hide();
                areaGrado.hide();
                areaPosgrado.hide();
                areaFp.show();
                areaJapones.hide();

                avanzadasUni.hide();
                avanzadasFp.show();
                areaPosgrado.hide();
                avanzadasJapones.hide();

                $('option#todasPrefecturas').show();
                document.getElementById('prefectureSelector').getElementsByTagName('option')[0].selected = 'selected';
                document.getElementById("tituloPrefectura").innerText = "";
                document.getElementById("descripcionPrefectura").innerText = "";
                document.getElementById("wikipedia").innerText = "";
                break;

            case "grado":
                showGrado.show();
                showPosgrado.hide();
                showJapones.hide();
                showFp.hide();
                desContainer.fadeIn('slow');


                areaDefault.hide();
                areaGrado.show();
                areaPosgrado.hide();
                areaFp.hide();
                areaJapones.hide();

                avanzadasUni.show();
                avanzadasFp.hide();
                avanzadasPosgrado.hide();
                avanzadasJapones.hide();

                $('option#todasPrefecturas').show();
                document.getElementById('prefectureSelector').getElementsByTagName('option')[0].selected = 'selected';
                document.getElementById("tituloPrefectura").innerText = "";
                document.getElementById("descripcionPrefectura").innerText = "";
                document.getElementById("wikipedia").innerText = "";
                break;

            case "posgrado":
                showGrado.hide();
                showPosgrado.show();
                areaPosgrado.show();
                showJapones.hide();
                showFp.hide();
                desContainer.fadeIn('slow');

                areaDefault.hide();
                areaGrado.hide();
                areaPosgrado.show();
                areaFp.hide();
                areaJapones.hide();

                avanzadasUni.hide();
                avanzadasFp.hide();
                avanzadasPosgrado.show();
                avanzadasJapones.hide();

                $('option#todasPrefecturas').show();
                document.getElementById('prefectureSelector').getElementsByTagName('option')[0].selected = 'selected';
                document.getElementById("tituloPrefectura").innerText = "";
                document.getElementById("descripcionPrefectura").innerText = "";
                document.getElementById("wikipedia").innerText = "";
                break;
            case "japones":
                showGrado.hide();
                showPosgrado.hide();
                showJapones.show();
                areaJapones.show();
                showFp.hide();
                desContainer.fadeIn('slow');

                areaDefault.hide();
                areaGrado.hide();
                areaPosgrado.hide();
                areaFp.hide();
                areaJapones.show();

                avanzadasUni.hide();
                avanzadasFp.hide();
                avanzadasPosgrado.hide();
                avanzadasJapones.show();

                $('option#todasPrefecturas').hide();
                document.getElementById('prefectureSelector').getElementsByTagName('option')[0].selected = 'selected';
                document.getElementById("tituloPrefectura").innerText = "";
                document.getElementById("descripcionPrefectura").innerText = "";
                document.getElementById("wikipedia").innerText = "";
                break;

            case "empty":
                areaDefault.show();
                areaGrado.hide();
                areaPosgrado.hide();
                areaFp.hide();
                areaJapones.hide();

                avanzadasUni.hide();
                avanzadasFp.hide();
                avanzadasPosgrado.hide();
                avanzadasJapones.hide();

                $('option#todasPrefecturas').show();
                document.getElementById('prefectureSelector').getElementsByTagName('option')[0].selected = 'selected';
                document.getElementById("tituloPrefectura").innerText = "";
                document.getElementById("descripcionPrefectura").innerText = "";
                document.getElementById("wikipedia").innerText = "";
                break;

            default:
                areaDefault.show();
                areaGrado.hide();
                areaPosgrado.hide();
                areaFp.hide();
                areaJapones.hide();

                avanzadasUni.hide();
                avanzadasFp.hide();
                avanzadasPosgrado.hide();
                avanzadasJapones.hide();

                $('option#todasPrefecturas').show();
                document.getElementById('prefectureSelector').getElementsByTagName('option')[0].selected = 'selected';
                document.getElementById("tituloPrefectura").innerText = "";
                document.getElementById("descripcionPrefectura").innerText = "";
                document.getElementById("wikipedia").innerText = "";
                break;
        }
    });


    function loadColleges() {

        cargaEscuelas.show();
        semaforo--;
        var prefectureSearch = document.getElementById('prefectureSelector').options[document.getElementById('prefectureSelector').selectedIndex].text.slice(3).trim();

        switch (document.getElementById("studiesSelector").value) {
            case "grado":
                $.ajax({
                    type: 'GET',
                    url: 'http://www.jdecastroc.ovh:8081/universidades/' + prefectureSearch + '/' + document.getElementById("areaSelector").value,
                    data: {
                        nameUni: document.getElementById("nombreUni").value,
                        typeUni: document.getElementById("typeUni").value,
                        admisionMonth: document.getElementById("admision").value,
                        deadLine: document.getElementById("fechaLimite").value,
                        eju: document.getElementById("eju").value,
                        engExam: document.getElementById("examenEng").value,
                        admisionUni: document.getElementById("admisionApp").value,
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

                            console.log(data);
                            mostrarEscuelas.empty(); //Refresh the div where the articles are stored
                            cargaEscuelas.hide();

                            var jsonData = data.collegeList; //parse data array from json
                            var output = "<ul>";

                            if (data.searchFound > 0) {

                                //TEMA PAGINACION
                                var elementosPagina = 5;
                                var paginas = Math.ceil(data.collegeList.length / elementosPagina);
                                var elementosRestantes = 0;
                                var elemento = 0;
                                var limite = 0;

                                for (p = 1; p <= paginas; p++) { //Numero de divs
                                    //alert("Pagina + " + p);

                                    output += '<div class="escuelas" id="pag-estudios-' + p + '" style="display: none;">';

                                    limite = 0;

                                    if ((data.collegeList.length - elemento) > elementosPagina) {
                                        elementosRestantes = elementosPagina;
                                    } else {
                                        elementosRestantes = data.collegeList.length - elemento;
                                    }

                                    for (i = elemento; limite < elementosRestantes; i++, elemento++, limite++) { //Elementos por pagina

                                        output += '<div class="col-md-10 blogShort">';
                                        output += '<h2>' + jsonData[i].name + ' - ' + jsonData[i].japaneseName + '</h2>';
                                        output += '<em>' + jsonData[i].prefecture + ' / ' + jsonData[i].type + '</em>';

                                        if (jsonData[i].guideUrl != "") {
                                            output += ' / <a target="_blank" href="' + jsonData[i].guideUrl + '">Guía universitaria</a>';
                                        }
                                        if (jsonData[i].imageUrl != "") {
                                            output += '<img class="pull-left img-responsive thumb img-thumbnail" style="border:0; padding-right: 10px;" width="230" height="186" alt="' + jsonData[i].name + '" src="' + jsonData[i].imageUrl + '">';
                                        }

                                        output += '<h4>' + jsonData[i].title + '</h4>';

                                        //Coge el substring de more... para enlazar con la universidad
                                        var more = jsonData[i].description.substring(jsonData[i].description.lastIndexOf("["), jsonData[i].description.lastIndexOf(";"));

                                        output += '<article><p>' + more + '<a target="_blank" href="' + jsonData[i].id.replace("/en/univ/", "escuela?tipo=univ&id=").slice(0, -1) + '">' + jsonData[i].description.replace(more, "") + '</a>' + '</p></article></br>';
                                        //Departamentos
                                        output += '<div style="padding-top: 10px;">';
                                        for (j = 0; j < jsonData[i].faculties.collegeFacultyList.length; j++) {
                                            var array_split = jsonData[i].faculties.collegeFacultyList[j].facultyUrl.split("/");
                                            var id_univ = array_split["3"];
                                            var id_depart = array_split["4"];
                                            output += '<a class="button button-3d button-mini button-rounded button-aqua" target="_blank" href="' + "departamento?tipo=univ&id=" + id_univ + "&id_depart=" + id_depart + '">' + jsonData[i].faculties.collegeFacultyList[j].facultyName + '</a>';

                                        }
                                        output += '</div>';

                                        output += '</div>';
                                        output += '<div class="divider"><i class="icon-circle"></i></div>';
                                    }

                                    // Botones paginacion
                                    output += '<ul class = "pagination inline-block">';
                                    for (k = 1; k <= paginas; k++) {
                                        output += '<li id="pagination-li"><a class="loadSchoolDiv" href="#pag-estudios-' + k + '">' + k + '</a></li>';
                                    }
                                    output += '</ul>';

                                    output += '</div>';
                                }

                            } else {
                                output += "<p>No se ha encontrado ninguna universidad que encaje con la prefectura o el tipo de estudios indicado.</p>";
                            }

                            output += "</ul>";
                            mostrarEscuelas.append(output);
                            cargarEscuelas(1);
                            semaforo++;

                        } else {
                            output += "Error en la conexión con el servidor de búsquedas. Intentalo de nuevo mas tarde.";
                            mostrarEscuelas.append(output);
                        }
                    },
                    error: function(status) {
                        var output = "<ul>";
                        output += '<div class="alert alert-danger">';
                        output += '<i class="icon-remove-sign"></i><strong>Ha habido al intentar conectar con el servidor de datos. Error: ' + status;
                        output += '</div>';
                        output += "</ul>";
                        cargaEscuelas.hide();
                        mostrarEscuelas.append(output);
                    }

                });
                break;

            case "fp":
                $.ajax({
                    type: 'GET',
                    //http://www.jdecastroc.ovh:8081/fp/Tokyo?nameTech=
                    url: 'http://www.jdecastroc.ovh:8081/fp/' + prefectureSearch + '/',
                    data: {
                        nameTech: document.getElementById("nombreFp").value,
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

                            console.log(data);
                            mostrarEscuelas.empty(); //Refresh the div where the articles are stored
                            cargaEscuelas.hide();

                            var jsonData = data.collegeList; //parse data array from json
                            var output = "<ul>";

                            if (data.searchFound > 0) {

                                //TEMA PAGINACION
                                var elementosPagina = 5;
                                var paginas = Math.ceil(data.collegeList.length / elementosPagina);
                                var elementosRestantes = 0;
                                var elemento = 0;
                                var limite = 0;

                                for (p = 1; p <= paginas; p++) { //Numero de divs
                                    //alert("Pagina + " + p);

                                    output += '<div class="escuelas" id="pag-estudios-' + p + '" style="display: none;">';

                                    limite = 0;

                                    if ((data.collegeList.length - elemento) > elementosPagina) {
                                        elementosRestantes = elementosPagina;
                                    } else {
                                        elementosRestantes = data.collegeList.length - elemento;
                                    }

                                    for (i = elemento; limite < elementosRestantes; i++, elemento++, limite++) { //Elementos por pagina

                                        output += '<div class="col-md-10 blogShort">';
                                        output += '<h2>' + jsonData[i].name + ' - ' + jsonData[i].japaneseName + '</h2>';
                                        output += '<em>' + jsonData[i].prefecture + ' / ' + jsonData[i].type + '</em>';

                                        if (jsonData[i].guideUrl != "") {
                                            output += ' / <a target="_blank" href="' + jsonData[i].guideUrl + '">Guía universitaria</a>';
                                        }
                                        if (jsonData[i].imageUrl != "") {
                                            output += '<img class="pull-left img-responsive thumb img-thumbnail" width="230" height="186" style="border:0; padding-right: 10px;" alt="' + jsonData[i].name + '" src="' + jsonData[i].imageUrl + '">';
                                        }

                                        output += '<h4>' + jsonData[i].title + '</h4>';

                                        //Coge el substring de more... para enlazar con la universidad
                                        var more = jsonData[i].description.substring(jsonData[i].description.lastIndexOf("["), jsonData[i].description.lastIndexOf(";"));

                                        output += '<article><p>' + more + '<a target="_blank" href="' + jsonData[i].id.replace("/en/tech/", "escuela?tipo=tech&id=").slice(0, -1) + '">' + jsonData[i].description.replace(more, "") + '</a>' + '</p></article></br>';
                                        //Departamentos
                                        output += '<div style="padding-top: 10px;">';
                                        for (j = 0; j < jsonData[i].faculties.collegeFacultyList.length; j++) {
                                            var array = jsonData[i].faculties.collegeFacultyList[j].facultyUrl.replace("#", "").split("/");
                                            var id_fp = array[3];
                                            var id_depart = array[5];
                                            output += '<a class="button button-3d button-mini button-rounded button-aqua" target="_blank" href="' + "departamento?tipo=tech&id=" + id_fp + "&id_depart=" + id_depart + '">' + jsonData[i].faculties.collegeFacultyList[j].facultyName + '</a>';
                                        }
                                        output += '</div>';
                                        output += '</div>';
                                        output += '<div class="divider"><i class="icon-circle"></i></div>';
                                    }

                                    // Botones paginacion
                                    output += '<ul class = "pagination inline-block">';
                                    for (k = 1; k <= paginas; k++) {
                                        output += '<li id="pagination-li"><a class="loadSchoolDiv" href="#pag-estudios-' + k + '">' + k + '</a></li>';
                                    }
                                    output += '</ul>';

                                    output += '</div>';
                                }

                            } else {
                                output += "<p>No se ha encontrado ningun colegio de formación profesional que encaje con la prefectura o el tipo de estudios indicado.</p>";
                            }

                            output += "</ul>";
                            mostrarEscuelas.append(output);
                            cargarEscuelas(1);
                            semaforo++;

                        } else {
                            output += "Error en la conexión con el servidor de búsquedas. Intentalo de nuevo mas tarde.";
                            mostrarEscuelas.append(output);
                        }

                    },
                    error: function(status) {
                        var output = "<ul>";
                        output += '<div class="alert alert-danger">';
                        output += '<i class="icon-remove-sign"></i><strong>Ha habido al intentar conectar con el servidor de datos. Error: ' + status;
                        output += '</div>';
                        output += "</ul>";
                        cargaEscuelas.hide();
                        mostrarEscuelas.append(output);
                    }

                });
                break;

            case "posgrado":
                $.ajax({
                    type: 'GET',
                    //http://localhost:8080/posgrado/Tokyo, Kanagawa?nameGrad=&typeGrad=&typeCourse=&englishCourse=
                    url: 'http://www.jdecastroc.ovh:8081/posgrado/' + prefectureSearch + '/',
                    data: {
                        nameGrad: document.getElementById("nombrePosgrado").value,
                        typeGrad: document.getElementById("areaSelector").value,
                        typeCourse: '',
                        englishCourse: document.getElementById("idiomaSelector").value,
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

                            console.log(data);
                            mostrarEscuelas.empty(); //Refresh the div where the articles are stored
                            cargaEscuelas.hide();

                            var jsonData = data.collegeList; //parse data array from json
                            var output = "<ul>";

                            if (data.searchFound > 0) {

                                //TEMA PAGINACION
                                var elementosPagina = 5;
                                var paginas = Math.ceil(data.collegeList.length / elementosPagina);
                                var elementosRestantes = 0;
                                var elemento = 0;
                                var limite = 0;

                                for (p = 1; p <= paginas; p++) { //Numero de divs
                                    //alert("Pagina + " + p);

                                    output += '<div class="escuelas" id="pag-estudios-' + p + '" style="display: none;">';

                                    limite = 0;

                                    if ((data.collegeList.length - elemento) > elementosPagina) {
                                        elementosRestantes = elementosPagina;
                                    } else {
                                        elementosRestantes = data.collegeList.length - elemento;
                                    }

                                    for (i = elemento; limite < elementosRestantes; i++, elemento++, limite++) { //Elementos por pagina

                                        output += '<div class="col-md-10 blogShort">';
                                        output += '<h2>' + jsonData[i].name + ' - ' + jsonData[i].japaneseName + '</h2>';
                                        output += '<em>' + jsonData[i].prefecture + ' / ' + jsonData[i].type + '</em>';

                                        if (jsonData[i].guideUrl != "") {
                                            output += ' / <a target="_blank" href="' + jsonData[i].guideUrl + '">Guía universitaria</a>';
                                        }
                                        if (jsonData[i].imageUrl != "") {
                                            output += '<img class="pull-left img-responsive thumb img-thumbnail" width="230" height="186" style="border:0; padding-right: 10px;" alt="' + jsonData[i].name + '" src="' + jsonData[i].imageUrl + '">';
                                        }

                                        output += '<h4>' + jsonData[i].title + '</h4>';

                                        //Coge el substring de more... para enlazar con la universidad
                                        var more = jsonData[i].description.substring(jsonData[i].description.lastIndexOf("["), jsonData[i].description.lastIndexOf(";"));

                                        output += '<article><p>' + more + '<a target="_blank" href="' + jsonData[i].id.replace("/en/grad/", "escuela?tipo=grad&id=").slice(0, -1) + '">' + jsonData[i].description.replace(more, "") + '</a>' + '</p></article></br>';
                                        //Departamentos
                                        output += '<div style="padding-top: 10px;">';
                                        for (j = 0; j < jsonData[i].faculties.collegeFacultyList.length; j++) {
                                            var array_split = jsonData[i].faculties.collegeFacultyList[j].facultyUrl.split("/");
                                            var id_grad = array_split["3"];
                                            var id_depart = array_split["4"];
                                            output += '<a class="button button-3d button-mini button-rounded button-aqua" target="_blank" href="' + "departamento?tipo=grad&id=" + id_grad + "&id_depart=" + id_depart + '">' + jsonData[i].faculties.collegeFacultyList[j].facultyName + '</a>';
                                        }
                                        output += '</div>';
                                        output += '</div>';
                                        output += '<div class="divider"><i class="icon-circle"></i></div>';
                                    }

                                    // Botones paginacion
                                    output += '<ul class = "pagination inline-block">';
                                    for (k = 1; k <= paginas; k++) {
                                        output += '<li id="pagination-li"><a class="loadSchoolDiv" href="#pag-estudios-' + k + '">' + k + '</a></li>';
                                    }
                                    output += '</ul>';

                                    output += '</div>';
                                }

                            } else {
                                output += "<p>No se ha encontrado ningun colegio de posgrado que encaje con la prefectura o el tipo de estudios indicado.</p>";
                            }

                            output += "</ul>";
                            mostrarEscuelas.append(output);
                            cargarEscuelas(1);
                            semaforo++;

                        } else {
                            output += "Error en la conexión con el servidor de búsquedas. Intentalo de nuevo mas tarde.";
                            mostrarEscuelas.append(output);
                        }

                    },
                    error: function(status) {
                        var output = "<ul>";
                        output += '<div class="alert alert-danger">';
                        output += '<i class="icon-remove-sign"></i><strong>Ha habido al intentar conectar con el servidor de datos. Error: ' + status;
                        output += '</div>';
                        output += "</ul>";
                        cargaEscuelas.hide();
                        mostrarEscuelas.append(output);
                    }

                });
                break;

            case "japones":
                if ((document.getElementById('languageAreaSelector').value != null) && (document.getElementById('prefectureSelector').options[document.getElementById('prefectureSelector').selectedIndex].value == "Tokio")) {
                    prefectureSearch = document.getElementById('languageAreaSelector').value;
                } else {
                    prefectureSearch = document.getElementById('prefectureSelector').options[document.getElementById('prefectureSelector').selectedIndex].text.slice(3).trim();
                }
                $.ajax({
                    type: 'GET',
                    //http://localhost:8080/posgrado/Tokyo, Kanagawa?nameGrad=&typeGrad=&typeCourse=&englishCourse=
                    url: 'http://www.jdecastroc.ovh:8081/escuelasIdiomas/' + prefectureSearch,
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

                            console.log(data);
                            mostrarEscuelas.empty(); //Refresh the div where the articles are stored
                            cargaEscuelas.hide();

                            var jsonData = data.schoolList; //parse data array from json
                            var output = "<ul>";

                            if (data.schoolCounter > 0) {

                                //TEMA PAGINACION
                                var elementosPagina = 10;
                                var paginas = Math.ceil(data.schoolList.length / elementosPagina);
                                var elementosRestantes = 0;
                                var elemento = 0;
                                var limite = 0;

                                for (p = 1; p <= paginas; p++) { //Numero de divs
                                    //alert("Pagina + " + p);

                                    output += '<div class="escuelas" id="pag-estudios-' + p + '" style="display: none;">';

                                    limite = 0;

                                    if ((data.schoolList.length - elemento) > elementosPagina) {
                                        elementosRestantes = elementosPagina;
                                    } else {
                                        elementosRestantes = data.schoolList.length - elemento;
                                    }

                                    for (i = elemento; limite < elementosRestantes; i++, elemento++, limite++) { //Elementos por pagina

                                        output += '<div class="col-md-10 blogShort">';
                                        output += '<h4>' + jsonData[i].name + '</h4>';
                                        output += '<p>' + jsonData[i].address + '</p>';
                                        output += '<a target="_blank" href="escuela?tipo=japones&id=' + jsonData[i].id + '">Más información</a>';

                                        output += '</div>';
                                        output += '<div class="divider"><i class="icon-circle"></i></div>';
                                    }

                                    // Botones paginacion
                                    output += '<ul class = "pagination inline-block">';
                                    for (k = 1; k <= paginas; k++) {
                                        output += '<li id="pagination-li"><a class="loadSchoolDiv" href="#pag-estudios-' + k + '">' + k + '</a></li>';
                                    }
                                    output += '</ul>';

                                    output += '</div>';
                                }

                            } else {
                                output += "<p>No se ha encontrado ningun colegio de japonés que encaje con la prefectura o el tipo de estudios indicado.</p>";
                            }

                            output += "</ul>";
                            mostrarEscuelas.append(output);
                            cargarEscuelas(1);
                            semaforo++;

                        } else {
                            output += "Error en la conexión con el servidor de búsquedas. Intentalo de nuevo mas tarde.";
                            mostrarEscuelas.append(output);
                        }

                    },
                    error: function(status) {
                        var output = "<ul>";
                        output += '<div class="alert alert-danger">';
                        output += '<i class="icon-remove-sign"></i><strong>Ha habido al intentar conectar con el servidor de datos. Error: ' + status;
                        output += '</div>';
                        output += "</ul>";
                        cargaEscuelas.hide();
                        mostrarEscuelas.append(output);
                    }

                });
                break;

            default:
                var output = "<ul>";
                output += '<div class="alert alert-danger">';
                output += '<i class="icon-remove-sign"></i><strong>¡Oh vaya!</strong> Se te ha olvidado rellenar la información en los pasos previos.';
                output += '</div>';
                output += "</ul>";
                cargaEscuelas.hide();
                mostrarEscuelas.append(output);
                break;
        }

    }
});

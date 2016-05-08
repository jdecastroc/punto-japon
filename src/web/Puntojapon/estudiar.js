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
		//$('div.escuelas').hide();
		//mostrarEscuelas.show();
		//escuelas.fadeOut();
		//cargarEscuelas(1);
		
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
				document.getElementById("pag-estudios-" + i).setAttribute("style","display: none");
			}
			i++;
		}
		mostrarEscuelas.fadeIn('slow');
	}

	//Paginacion de escuelas
	$(document).on('click', '.loadSchoolDiv', function(){
    	cargarEscuelas($(this).text());
	});


	//Wikipedia get Info
	prefecturaSeleccion.change(function() {

		var playListURL = 'https://es.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&callback=?&rvparse=&titles=' + document.getElementById("prefectureSelector").value;

		$.getJSON(playListURL, function(data) {
			$.each(data.query.pages, function(i, item) {
				document.getElementById("tituloPrefectura").innerText = item.title;
				document.getElementById("descripcionPrefectura").innerText = item.extract;
				document.getElementById("wikipedia").innerText = 'https://es.wikipedia.org/wiki/' + document.getElementById("prefectureSelector").value;
			});
		});
		infoPrefectura.fadeIn('slow');
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
				break;

			case "empty":
				areaDefault.show();
				areaGrado.hide();
				areaPosgrado.hide();
				areaFp.hide();
				areaJapones.hide();
				break;

			default:
				areaDefault.show();
				areaGrado.hide();
				areaPosgrado.hide();
				areaFp.hide();
				areaJapones.hide();
				break;
		}
	});


	
	function loadColleges() {

		cargaEscuelas.show();
		semaforo--;
		var prefectureSearch = document.getElementById('prefectureSelector').options[document.getElementById('prefectureSelector').selectedIndex].text.slice(3).trim();
		
		//if (document.getElementById("prefectureSelector").value == "") {
		//	document.getElementById("studiesSelector").value = "";
		//}
			switch (document.getElementById("studiesSelector").value) {
				case "grado":
					$.ajax({
						type: 'GET',
						url: 'http://www.jdecastroc.ovh:8081/universidades/' + prefectureSearch + '/' + document.getElementById("areaSelector").value,
						data: {
							nameUni: '',
							typeUni: '',
							admisionMonth: '',
							deadLine: '',
							eju: '',
							engExam: '',
							admisionUni: ''
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

									for (p = 1; p <= paginas; p++){ //Numero de divs
										//alert("Pagina + " + p);

										output += '<div class="escuelas" id="pag-estudios-' + p + '" style="display: none;">';

										limite = 0;

										if ((data.collegeList.length - elemento) > elementosPagina ){
											elementosRestantes = elementosPagina ;
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
												output += '<img class="pull-left img-responsive thumb img-thumbnail" width="230" height="186" alt="' + jsonData[i].name +'" src="' + jsonData[i].imageUrl + '">';
											}
											
											output += '<h4>' + jsonData[i].title + '</h4>';

											//Coge el substring de more... para enlazar con la universidad
											var more = jsonData[i].description.substring(jsonData[i].description.lastIndexOf("["),jsonData[i].description.lastIndexOf(";"));
											
											output += '<article><p>' + more + '<a target="_blank" href="' + jsonData[i].description.replace(more,"") + '">' + jsonData[i].description.replace(more,"") + '</a>' + '</p></article></br>';
											//Departamentos
											for (j = 0; j < jsonData[i].faculties.collegeFacultyList.length; j++) {
												output += '<a class="button button-3d button-mini button-rounded button-aqua" target="_blank" href="' + jsonData[i].faculties.collegeFacultyList[j].facultyUrl + '">' + jsonData[i].faculties.collegeFacultyList[j].facultyName + '</a>';
											}
											output += '</div>';
											output += '<div class="divider"><i class="icon-circle"></i></div>';
										}

										// Botones paginacion
										output += '<ul class = "pagination inline-block">';
										for (k = 1; k <= paginas; k++) {
											 	output += '<li id="pagination-li"><a class="loadSchoolDiv" href="#pag-estudios-' + k +'">' + k + '</a></li>';
										}
										output += '</ul>';

										output += '</div>';
									}

								} else {
									output += "<p>No se ha encontrado ninguna universidad que encaje con la búsqueda</p>";
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
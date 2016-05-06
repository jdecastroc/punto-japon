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
		cargaEscuelas.show();
		if (semaforo > 0) {
			loadColleges();
		}
		mostrarEscuelas.show();
	});




	prefecturaSeleccion.change(function() {

		var playListURL = 'https://es.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&callback=?&rvparse=&titles=' + document.getElementById("prefectureSelector").value;
		//document.getElementById("tituloPrefectura").innerText = document.getElementById("prefectureSelector").value;

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
		semaforo--;
		var prefectureSearch = document.getElementById('prefectureSelector').options[document.getElementById('prefectureSelector').selectedIndex].text.slice(3).trim();
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
								for (i = 0; i < data.searchFound; i++) {

									output += 'id = ' + jsonData[i].id + '</br>';
									output += 'Japanese name = ' + jsonData[i].japaneseName + '</br>';
									output += 'Name = ' + jsonData[i].name + '</br>';
									output += 'Prefectura = ' + jsonData[i].prefecture + '</br>';
									output += 'Tipo = ' + jsonData[i].type + '</br>';
									output += 'Imagen = ' + jsonData[i].imageUrl + '</br>';
									output += 'Guia = ' + jsonData[i].guideUrl + '</br>';
									output += 'Titulo = ' + jsonData[i].title + '</br>';
									output += 'Descripcion = ' + jsonData[i].description + '</br>';


									//Departamentos
									for (j = 0; j < jsonData[i].faculties.collegeFacultyList.length; j++) {
										output += 'Facultad = ' + jsonData[i].faculties.collegeFacultyList[j].facultyName + '</br>';
										output += 'Enlace = ' + jsonData[i].faculties.collegeFacultyList[j].facultyUrl + '</br></br>';
									}

									output += '</br></br></br>'
								}
							} else {
								output += "<p>No se ha encontrado ninguna universidad que encaje con la búsqueda</p>";
							}

							output += "</ul>";
							mostrarEscuelas.append(output);
							semaforo++;

						} else {
							output += "Error en la conexión con el servidor de búsquedas. Intentalo de nuevo mas tarde.";
							mostrarEscuelas.append(output);
						}
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
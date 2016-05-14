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


$(document).ready(function() {

	var id = getUrlParameter('id');
	var tipo = getUrlParameter('tipo');



	switch(tipo) {
		case "univ":
			$("#cargando").show();
			$.ajax({
				type: 'GET',
				url: 'http://jdecastroc.ovh:8081/universidades/id/' + id,
				data: {
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
						var output = "";

						if (data.search == true) {
							output += '<div class="col-md-10 blogShort">';
							output += '<h2>' + data.name + ' - ' + data.japaneseName + '</h2>';
							output += '<em>' + data.prefecture + ' / ' + data.type + '</em>';
											
							if (data.guideUrl != "") {
								output += ' / <a target="_blank" href="http://www.jpss.jp' + data.guideUrl + '">Guía universitaria</a>';
							}
							if (data.imageUrl != "") {
								output += '<img class="pull-left img-responsive thumb img-thumbnail" width="230" height="186" style="border:0; padding-right: 10px;" alt="' + data.name +'" src="http://www.jpss.jp/' + data.imageUrl + '">';
							}

							output += '<h4>' + data.title + '</h4>';
							output += '<article><p>' + data.description + '</p></article></br>';
							//Departamentos
							output += '<div style="padding-top: 20px;">';
							for (j = 0; j < data.faculties.collegeFacultyList.length; j++) {
								output += '<a class="button button-3d button-mini button-rounded button-aqua" target="_blank" href="' + data.faculties.collegeFacultyList[j].facultyUrl + '">' + data.faculties.collegeFacultyList[j].facultyName + '</a>';
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
				url: 'http://jdecastroc.ovh:8081/posgrado/id/' + id,
				data: {
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
						var output = "";

						if (data.search == true) {
							output += '<div class="col-md-10 blogShort">';
							output += '<h2>' + data.name + ' - ' + data.japaneseName + '</h2>';
							output += '<em>' + data.prefecture + ' / ' + data.type + '</em>';
											
							if (data.guideUrl != "") {
								output += ' / <a target="_blank" href="http://www.jpss.jp' + data.guideUrl + '">Guía universitaria</a>';
							}
							if (data.imageUrl != "") {
								output += '<img class="pull-left img-responsive thumb img-thumbnail" width="230" height="186" style="border:0; padding-right: 10px;" alt="' + data.name +'" src="http://www.jpss.jp/' + data.imageUrl + '">';
							}

							output += '<h4>' + data.title + '</h4>';
							output += '<article><p>' + data.description + '</p></article></br>';
							//Departamentos
							output += '<div style="padding-top: 20px;">';
							for (j = 0; j < data.faculties.collegeFacultyList.length; j++) {
								output += '<a class="button button-3d button-mini button-rounded button-aqua" target="_blank" href="' + data.faculties.collegeFacultyList[j].facultyUrl + '">' + data.faculties.collegeFacultyList[j].facultyName + '</a>';
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
				url: 'http://jdecastroc.ovh:8081/fp/id/' + id,
				data: {
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
						var output = "";

						if (data.search == true) {
							output += '<div class="col-md-10 blogShort">';
							output += '<h2>' + data.name + ' - ' + data.japaneseName + '</h2>';
							output += '<em>' + data.prefecture + ' / ' + data.type + '</em>';
											
							if (data.guideUrl != "") {
								output += ' / <a target="_blank" href="http://www.jpss.jp' + data.guideUrl + '">Guía universitaria</a>';
							}
							if (data.imageUrl != "") {
								output += '<img class="pull-left img-responsive thumb img-thumbnail" width="230" height="186" style="border:0; padding-right: 10px;" alt="' + data.name +'" src="http://www.jpss.jp/' + data.imageUrl + '">';
							}

							output += '<h4>' + data.title + '</h4>';
							output += '<article><p>' + data.description + '</p></article></br>';
							//Departamentos
							output += '<div style="padding-top: 20px;">';
							for (j = 0; j < data.faculties.collegeFacultyList.length; j++) {
								output += '<a class="button button-3d button-mini button-rounded button-aqua" target="_blank" href="' + data.faculties.collegeFacultyList[j].facultyUrl + '">' + data.faculties.collegeFacultyList[j].facultyName + '</a>';
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
				url: 'http://jdecastroc.ovh:8081/escuelasIdiomas/id/' + id,
				data: {
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
						var output = "";

						if (data.search == true) {

							
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


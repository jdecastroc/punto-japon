$(document).ready(function () {
	
	var pasoTipoEstudios = $('#desContainer');

//Primera pagina
	var desContainer = $('#desContainer');
	var showFp = $('#fp');
	var showGrado = $('#grado');
	var showPosgrado = $('#posgrado');
	var showJapones = $('#lenguaJaponesa');
	
//Segunda pagina
	
	var infoPrefectura = $('#infoPrefectura');
	
	var nivelEstudios = $( "#studiesSelector");
	var prefecturaSeleccion = $( "#prefectureSelector");
	
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
	
	areaGrado.hide();
	areaPosgrado.hide();
	areaFp.hide();
	areaJapones.hide();
	areaDefault.show();
	
	prefecturaSeleccion.change(function() {

		var playListURL = 'https://es.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&callback=?&rvparse=&titles=' + document.getElementById("prefectureSelector").value;
		//document.getElementById("tituloPrefectura").innerText = document.getElementById("prefectureSelector").value;
		
		$.getJSON(playListURL ,function(data) {
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
});
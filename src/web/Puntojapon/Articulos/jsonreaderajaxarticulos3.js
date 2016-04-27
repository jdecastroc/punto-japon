$(document).ready(function () {
	
  $('#get-data').click(function () {
	  var showData = $('#show-data');

	  //Cargar articulos
		$.ajax({
		  type: 'GET',
		  url: 'http://www.jdecastroc.ovh:8081/articulos',
		  data: '', //Especifica los datos que se enviarán al servidor
		  async: true, //Cuidado con el true! esto es asíncrono puede generar problemas con otros fragmentos de código. Hace que el código se ejecute de manera concurrente
		  beforeSend: function (xhr) {
			if (xhr && xhr.overrideMimeType) {
			  xhr.overrideMimeType('application/json;charset=utf-8');
			}
		  },
		  dataType: 'json',
		  success: function (data, status) {
			
			//Do stuff with the JSON data
			if (status == "success") {
				
				  console.log(data);

				  // items hace referencia a un array de los campos que se obtienen de cada objeto. Item hace referencia a cada objeto.
				  var items = data.data.map(function (item) {
					return '<div class="testi-image"><a href="#"><img src="img/articulos/razi.jpg" alt="RaziEnJapon"></a></div>' + item.Title + '</li><li>' + item.PubDate + '</li><li>' + item.Author + '</li><li>' + item.Description + '</li><li>' + item.Link;
				  });

				  //Vaciamos el div donde se mostrarán los datos
				  showData.empty();

				  // Mientras que haya objetos en el array se volcará el contenido y AQUÍ SE DARÁ FORMATO
				  if (items.length) {
					var content = '<li>' + items.join('</li> </br> <li>') + '</li>';
					var list = $('<ul />').html(content);
					showData.append(list);
				  }
			  }
		  }
		});
		//----------Articulos cargados----------
		
		//Animacion de carga
		showData.text('Loading the JSON file.');
		
		
  });
  
  
});
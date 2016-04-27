$(document).ready(function () {
  $('#get-data').click(function () {
    var showData = $('#show-data');

	var url = 'http://www.jdecastroc.ovh:8081/articulos';
	
	//Obtenemos el json y cojemos la raiz. Data es el string JSON.
    $.getJSON(url, function (data, status) {
	  if (status == "success") {
		  console.log(data);

		  // items hace referencia a un array de los campos que se obtienen de cada objeto. Item hace referencia a cada objeto.
		  var items = data.data.map(function (item) {
			return item.Title + ': ' + item.PubDate + ': ' + item.Author + ': ' + item.Description + ': ' + item.Link;
		  });

		  //Vaciamos el div donde se mostrarán los datos
		  showData.empty();

		  // Mientras que haya objetos en el array se volcará el contenido y AQUÍ SE DARÁ FORMATO
		  if (items.length) {
			var content = '<li>' + items.join('</li><li>') + '</li>';
			var list = $('<ul />').html(content);
			showData.append(list);
		  }
	  }
    });

    showData.text('Loading the JSON file.');
  });
});
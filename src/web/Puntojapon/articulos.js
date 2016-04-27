$(document).ready(function () {
	
	  
	  var showData = $('#show-data');
	  
	  $('#moreArticles100').hide();

	  $('#show-data').fadeOut('slow', function(){
             loadArticles(5);
	  });

	   $('#moreArticles10').click(function () {
		   $('#show-data').fadeOut('slow', function(){
             loadArticles(10);
			});
		   $('#moreArticles10').hide();
		   $('#moreArticles100').show();
	   });
	   
	   $('#moreArticles100').click(function () {
		   $('#show-data').fadeOut('slow', function(){
             loadArticles(100);
			 $('#moreArticles100').hide();
			});
	   });

	   
	  //Cargar articulos
	  function loading() {
		//Animacion de carga
		showData.text('Cargando artículos');
	  }
	  
	 function loadArticles(showArticles) {
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

				  //showData.empty();


			var jsonData = data.data; //parse json
			var output = "<ul>";
			
			
	//		<h1>Title 2</h1>
    //                 <img src="http://www.kaczmarek-photo.com/wp-content/uploads/2012/06/DSC_4073-150x150.jpg" alt="post img" class="pull-left img-responsive thumb margin10 img-thumbnail">
     //                <article><p>
     //                    
     //                    </p></article>
     //                <a class="btn btn-blog pull-right marginBottom10" href="http://bootsnipp.com/user/snippets/2RoQ">READ MORE</a> 
	 
	 
	 
	 
	 
	 
			
			for (i = 0; i < showArticles; i++) {
				output+='<h3>' + jsonData[i].Title + '</h3>';
				output+='<div class="testi-image pull-left img-responsive"><a href="#"><img src="img/articulos/' + jsonData[i].Author +'.jpg" alt="' + jsonData[i].Author +'"></a></div>';
				output+=' <ul class="entry-meta clearfix"> <li><i class="icon-calendar3"></i>' + jsonData[i].PubDate + '</li> <li><a href="#"><i class="icon-user"></i>' + jsonData[i].Author +'</a></li></ul>';
				output+='<article><p> ' + jsonData[i].Description + '<p></article>';
				output+='<a class="btn btn-blog pull-right marginBottom10" href=" ' + jsonData[i].Link + '">Seguir leyendo</a>';
				
				output+='<div class="divider"><i class="icon-circle"></i></div>';//Separador de artículos
			}
			output+="</ul>";
			showData.append(output);

			}
		  }
		  
		});
		
		$('#show-data').fadeIn('slow');
	 }
		//----------Articulos cargados----------
		
});
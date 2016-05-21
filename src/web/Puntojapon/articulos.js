$(document).ready(function() {


    var showData = $('#show-data');
    var articulosCargados = 0;
    var loading = false;

    $('#moreArticles100').hide();

    $('#show-data').fadeOut('slow', function() {
        loadArticles(10);
    });

    $('#moreArticles10').click(function() {
        $('#show-data').fadeOut('slow', function() {
            loadArticles(52);
        });
        $('#moreArticles10').hide();
        $('#moreArticles100').show();
    });

    $('#moreArticles100').click(function() {
        $('#show-data').fadeOut('slow', function() {
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

                  //  showData.empty(); //Refresh the div where the articles are stored


                    var jsonData = data.data; //parse json
                    var output = "";
										if (articulosCargados < jsonData.length) {
										console.log("Cargados: " + articulosCargados + "Total: " + jsonData.length);
                    for (i = articulosCargados; i < showArticles; i++) {
                        articulosCargados++;
                        if (i % 2 == false) {
                            output += '<div class="col_half">';
                            output += '<h3>' + jsonData[i].Title + '</h3>';
                            output += '<div class="testi-image pull-left img-responsive"><a href="#"><img src="img/articulos/' + jsonData[i].Author + '.jpg" alt="' + jsonData[i].Author + '"></a></div>';

                            output += ' <ul class="entry-meta clearfix"> <li><i class="icon-calendar3"></i>' + jsonData[i].PubDate + '</li> <li><a href="#"><i class="icon-user"></i>' + jsonData[i].Author + '</a></li></ul>';
                            output += '<article><p> ' + jsonData[i].Description + '<p></article>';
                            output += '<a class="btn btn-blog pull-right marginBottom10" href=" ' + jsonData[i].Link + '">Seguir leyendo</a>';

                            //output+='<div class="divider"><i class="icon-circle"></i></div>';//Separador de artículos
                            output += '</div>';
                        } else {
                            output += '<div class="col_half col_last">';
                            output += '<h3>' + jsonData[i].Title + '</h3>';
                            output += '<div class="testi-image pull-left img-responsive"><a href="#"><img src="img/articulos/' + jsonData[i].Author + '.jpg" alt="' + jsonData[i].Author + '"></a></div>';

                            output += ' <ul class="entry-meta clearfix"> <li><i class="icon-calendar3"></i>' + jsonData[i].PubDate + '</li> <li><a href="#"><i class="icon-user"></i>' + jsonData[i].Author + '</a></li></ul>';
                            output += '<article><p> ' + jsonData[i].Description + '<p></article>';
                            output += '<a class="btn btn-blog pull-right marginBottom10" href=" ' + jsonData[i].Link + '">Seguir leyendo</a>';

                            //output+='<div class="divider"><i class="icon-circle"></i></div>';//Separador de artículos
                            output += '</div>';
                        }


                    }
/*                    output += "</ul>";*/
                    showData.append(output);
									}

                }
            }

        });
        $('#show-data').fadeIn('slow');
    }
    //----------Articulos cargados----------

    //header
    $('#header').hide();

    $(window).scroll(function() {

        var articleOffset = $("#show-data").offset().top;
        var articleOffsetBottom = $("#show-data").offset().top + $("#show-data").height();
        console.log(articleOffsetBottom);


        if ($(this).scrollTop() > articleOffset) {
            $('#header').fadeIn();
        } else {
            $('#header').fadeOut();
        }

        /*				if (($(this).scrollTop() + $(this).height() > articleOffsetBottom - 200) && ($(this).scrollTop() > articleOffset)) {
        					console.log("Scroll bottom =" + $(this).scrollTop() + $(this).height());
        					$('#show-data').fadeOut('slow', function() {
        						console.log("He entrado");
        							loadArticles(articulosCargados + 10);
        					});
        				}*/

        if (!loading && $(window).scrollTop() >= ($('#carga').offset().top + $('#carga').outerHeight() - window.innerHeight)) {
            loading = true;
						loadArticles(articulosCargados + 10);
						loading = false;
        }

    });
});

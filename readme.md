# Punto Japón #
 Proyecto de fin de grado del Grado en informática de la Universidad Europea
 Autor: Jorge de Castro
 Tutor: Enrique Puertas

# ¿Que es? #

Punto Japón es una aplicación móvil/web que acerca al usuario a una centralización de la información necesaria para vivir en Japón así como un acercamiento a los recursos producidos por la comunidad española interesada en Japón.

# Estructura del proyecto (MVC) 31/01/2016#

* src/main -> Contiene los crawlers y los objetos esenciales de la aplicación
* src/test -> Contiene los test referentes a la aplicación. Test de crawlers.
* src/view -> Contiene las vistas de la aplicación. La parte front-end.
* src/controller -> Contiene el controlador de la aplicación (REST)
* src/runnable -> Contiene el ejecutable de la aplicación backend.

# Modo de uso del servicio REST #
## Búsqueda de universidades ##

Armando la consulta:
http://localhost:8080/universidades/<Prefecturas>/<Tipo de grados>?nameUni=&typeUni=&admisionMonth=&deadLine=&eju=&engExam=&admisionUni=

Consulta de ejemplo: 
http://localhost:8080/universidades/Tokyo/Language?nameUni=&typeUni=&admisionMonth=&deadLine=&eju=&engExam=&admisionUni=

Parámetros:
<Prefecturas> (Path param) -> Se especifíca una lista con las prefecturas donde se quiere buscar la universidad.
La lista se puede encontrar [aquí](https://es.wikipedia.org/wiki/Anexo:Prefecturas_de_Jap%C3%B3n_por_superficie_y_poblaci%C3%B3n)

<Tipo de grados> (Path param) -> Se especifíca una lista con los tipos de grado.
La lista incluye los siguientes tipos de grado (case sensitive):
* Literature
* Language
* Law
* Economics
* Sociology
* International
* Health Sciences
* Medical
* Pharmacy
* Physical Science
* Engineering
* Agricultur
* Education
* Life
* Arts
* Integrated Science

nameUni (request param) -> Cadena con nombre de universidad a buscar

typeUni (request param) -> Lista donde se especifica el tipo de universidad:
* National
* Public
* Private
* jsfu (Japanese school of foreign university)

admisionMonth (request param) -> Lista donde se especifica los meses de admisión:
* januaryAdm
* marchAdm
* aprilAdm
* mayAdm
* septemberAdm
* octoberAdm

deadLine (request param) -> Lista donde se especifica el plazo límite:
* januaryApp
* februaryApp
* marchApp
* apri
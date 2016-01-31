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

```
#!java

http://localhost:8080/universidades/<Prefecturas>/<Tipo de grados>?nameUni=&typeUni=&admisionMonth=&deadLine=&eju=&engExam=&admisionUni=
```


Consulta de ejemplo: 

```
#!java

http://localhost:8080/universidades/Tokyo/Language?nameUni=&typeUni=&admisionMonth=&deadLine=&eju=&engExam=&admisionUni=
```


Parámetros:
**<Prefecturas>** (Path param) -> Se especifíca una lista con las prefecturas donde se quiere buscar la universidad.
La lista se puede encontrar [aquí](https://es.wikipedia.org/wiki/Anexo:Prefecturas_de_Jap%C3%B3n_por_superficie_y_poblaci%C3%B3n)

**<Tipo de grados>** (Path param) -> Se especifíca una lista con los tipos de grado.
La lista incluye los siguientes tipos de grado (case sensitive):

```
#!html

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
```


**nameUni** (request param) -> Cadena con nombre de universidad a buscar

**typeUni** (request param) -> Lista donde se especifica el tipo de universidad:

```
#!html

* National
* Public
* Private
* jsfu (Japanese school of foreign university)
```


**admisionMonth** (request param) -> Lista donde se especifica los meses de admisión:

```
#!html

* januaryAdm
...

```

**deadLine** (request param) -> Lista donde se especifica el plazo límite:

```
#!html

* januaryApp
* ...
```
**eju** (request param) -> Lista donde se especifíca si es necesario o no el examen de conocimientos previo ingreso (Examination for Japanese University Admission for International Student).

```
#!html

* ejuYes
* ejuNo
```

**engExam** (request param) -> Lista donde se especifica el tipo de examen de ingles requisito para el ingreso.

```
#!html

* engNotNecessary
* engNecessary
* engToeflUsed
* engOthers
* engToeflEtcUsed
```
**admisionUni** (request param) -> Lista donde se especifica desde donde se admite el ingreso a la universidad antes de entrar en Japón.

* appOutsideJap (aplicación desde fuera de Japón)
* appInsiedJap (aplicación desde dentro de Japón)

## Búsqueda de una universidad específica ##

Armando la consulta:

```
#!java

http://localhost:8080/universidades/id/<id>
```
Consulta de ejemplo: 

```
#!java

http://localhost:8080/universidades/id/234
```
Parámetros:
<id> (path param) -> id identificador de la universidad.

## Búsqueda escuelas de posgrado ##

Armando la consulta:

```
#!java

http://localhost:8080/posgrado/<Prefecturas>?nameGrad=&typeGrad=&typeCourse=&englishCourse=
```
Consulta de ejemplo: 

```
#!java
http://localhost:8080/posgrado/Tokyo, Kanagawa?nameGrad=&typeGrad=&typeCourse=&englishCourse=
```
Parámetros:
**<Prefecturas>** (Path param) -> Se especifíca una lista con las prefecturas donde se quiere buscar la universidad. También se admite "all".
La lista se puede encontrar [aquí](https://es.wikipedia.org/wiki/Anexo:Prefecturas_de_Jap%C3%B3n_por_superficie_y_poblaci%C3%B3n)

**nameGrad** -> Cadena para búsqueda por nombre de la escuela de posgrado
**typeGrad** -> Lista donde se especifica el tipo de posgrado que se quiere buscar:

```
#!html

*master
*doctoral
*researchGrad
*researchUnGrad (non-graduated)
*auditingGrad
*auditingUnGrad (non-graduated)
*profDegree
```
**typeCourse** -> Lista donde se especifica el tipo de escuela

```
#!html

*national
*public
*private
*jsfu
```
**englishCourse** -> Lista donde se especifica si las clases son en inglés o no

```
#!html

*englishYes
*englishNo
```
## Búsqueda escuelas profesionales ##

Armando la consulta:

```
#!java

http://localhost:8080/fp/<Prefecturas>?nameTech=
```
Consulta de ejemplo: 

```
#!java
http://localhost:8080/posgrado/Tokyo, Kanagawa?nameGrad=&typeGrad=&typeCourse=&englishCourse=
```
Parámetros:
**<Prefecturas>** (Path param) -> Se especifíca una lista con las prefecturas donde se quiere buscar la universidad. También se admite "all".
La lista se puede encontrar [aquí](https://es.wikipedia.org/wiki/Anexo:Prefecturas_de_Jap%C3%B3n_por_superficie_y_poblaci%C3%B3n)

**nameTech** -> Cadena para búsqueda por nombre de la escuela profesional

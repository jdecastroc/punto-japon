/**
 * Translator
 * @author jdecastroc
 * @version 2.0, 21 Feb 2016
 */
package com.puntojapon.colleges;

/**
 * Provides a function which translate the name of the faculties in a manual way
 * 
 * @deprecated not used from version 2.0. Maybe useful in the future.
 * @author jdecastroc
 */
public class Translator {

	/**
	 * Given a english string, evaluate it in order to translate it to spanish
	 * 
	 * @param textToTranslate
	 *            -> text wich is going to be evaluated in order to be
	 *            translated
	 * @return the text translated to spanish if the english provided string
	 *         match with the evaluated strings
	 */
	public static String translate(String textToTranslate) {

		// Literature
		if (textToTranslate.equals("Faculty of Letters"))
			textToTranslate = textToTranslate.replace("Faculty of Letters", "Facultad de letras");
		if (textToTranslate.equals("Faculty of Humanities"))
			textToTranslate = textToTranslate.replace("Faculty of Humanities", "Facultad de humanidades");
		if (textToTranslate.equals("Faculty of Law"))
			textToTranslate = textToTranslate.replace("Faculty of Law", "Facultad de Derecho");
		if (textToTranslate.equals("Faculty of Management"))
			textToTranslate = textToTranslate.replace("Faculty of Management", "Facultad de Gestión");
		// if (textToTranslate.equals("of "))
		// textToTranslate = textToTranslate.replace("of", "de");

		if (textToTranslate.equals("Art"))
			textToTranslate = textToTranslate.replace("Art", "Arte");
		if (textToTranslate.equals("Science"))
			textToTranslate = textToTranslate.replace("Science", "Ciencia");
		if (textToTranslate.equals("Humanities"))
			textToTranslate = textToTranslate.replace("Humanities", "Humanidades");
		if (textToTranslate.equals("Human"))
			textToTranslate = textToTranslate.replace("Human", "Humanidades");
		if (textToTranslate.equals("Society"))
			textToTranslate = textToTranslate.replace("Society", "Sociedad");
		if (textToTranslate.equals("Culture"))
			textToTranslate = textToTranslate.replace("Culture", "Cultura");
		if (textToTranslate.equals("Language"))
			textToTranslate = textToTranslate.replace("Language", "Lengua");
		if (textToTranslate.equals("Literature"))
			textToTranslate = textToTranslate.replace("Literature", "Literatura");
		if (textToTranslate.equals("literature"))
			textToTranslate = textToTranslate.replace("literature", "Literatura");
		if (textToTranslate.equals("Letters") || textToTranslate.equals("Faculty of Letters")) {
			textToTranslate = textToTranslate.replace("Faculty of Letters", "Letras");
			textToTranslate = textToTranslate.replace("Letters", "Letras");

		}
		if (textToTranslate.equals("Education"))
			textToTranslate = textToTranslate.replace("Education", "Educación");
		if (textToTranslate.equals("Law"))
			textToTranslate = textToTranslate.replace("Law", "Derecho");
		if (textToTranslate.equals("Communication"))
			textToTranslate = textToTranslate.replace("Communication", "Comunicación");
		if (textToTranslate.equals("School"))
			textToTranslate = textToTranslate.replace("School", "Colegio");
		if (textToTranslate.equals("Representation"))
			textToTranslate = textToTranslate.replace("Representation", "Representación");
		if (textToTranslate.equals("Theology"))
			textToTranslate = textToTranslate.replace("Theology", "Teología");
		if (textToTranslate.equals("Buddhism"))
			textToTranslate = textToTranslate.replace("Buddhism", "Budismo");
		if (textToTranslate.equals("Divinity"))
			textToTranslate = textToTranslate.replace("Divinity", "Estudios cristianos");
		if (textToTranslate.equals("Collaboration"))
			textToTranslate = textToTranslate.replace("Collaboration", "Colaboración");
		if (textToTranslate.equals("Faculty"))
			textToTranslate = textToTranslate.replace("Faculty", "Facultad");
		if (textToTranslate.equals("History"))
			textToTranslate = textToTranslate.replace("History", "Historia");
		if (textToTranslate.equals("Psychology"))
			textToTranslate = textToTranslate.replace("Psychology", "Psicología");
		if (textToTranslate.equals("Psychological"))
			textToTranslate = textToTranslate.replace("Psychological", "Psicología");
		if (textToTranslate.equals("Sociology"))
			textToTranslate = textToTranslate.replace("Sociology", "Sociología");
		if (textToTranslate.equals("Society"))
			textToTranslate = textToTranslate.replace("Society", "Sociología");
		if (textToTranslate.equals("Administration"))
			textToTranslate = textToTranslate.replace("Administration", "Administración");
		if (textToTranslate.equals("Tourism"))
			textToTranslate = textToTranslate.replace("Tourism", "Turismo");
		if (textToTranslate.equals("Tourism and Culture"))
			textToTranslate = textToTranslate.replace("Tourism and Culture", "Turismo y cultura");
		if (textToTranslate.equals("Music"))
			textToTranslate = textToTranslate.replace("Music", "Música");
		if (textToTranslate.equals("Economics"))
			textToTranslate = textToTranslate.replace("Economics", "Economía");
		if (textToTranslate.equals("Design"))
			textToTranslate = textToTranslate.replace("Design", "Diseño");
		if (textToTranslate.equals("Management"))
			textToTranslate = textToTranslate.replace("Management", "Gestión y dirección");
		if (textToTranslate.equals("Environment"))
			textToTranslate = textToTranslate.replace("Environment", "Entorno");

		if (textToTranslate.equals("Society and Collaboration"))
			textToTranslate = textToTranslate.replace("Society and Collaboration", " ciencias sociales y colaboración");
		if (textToTranslate.equals("Art and Design"))
			textToTranslate = textToTranslate.replace("Art and Design", "Arte y diseño");
		if (textToTranslate.equals("Literature, Arts and Cultural Studies"))
			textToTranslate = textToTranslate.replace("Literature, Arts and Cultural Studies",
					"Literatura, Arte y estudios Culturales");
		if (textToTranslate.equals("Literature and Social Sciences"))
			textToTranslate = textToTranslate.replace("Literature and Social Sciences",
					"Literatura y ciencias sociales");
		if (textToTranslate.equals("Law and Letters"))
			textToTranslate = textToTranslate.replace("Law and Letters", "Derecho y letras");
		if (textToTranslate.equals("Law and Literature"))
			textToTranslate = textToTranslate.replace("Law and Literature", "Derecho y literatura");
		if (textToTranslate.equals("Law, Economics and Humanities"))
			textToTranslate = textToTranslate.replace("Law, Economics and Humanities",
					"Derecho, economía y humanidades");
		if (textToTranslate.equals("Letters and Education"))
			textToTranslate = textToTranslate.replace("Letters and Education", "Educación y letras");
		if (textToTranslate.equals("Education and Human Sciences"))
			textToTranslate = textToTranslate.replace("Education and Human Sciences", "Educación y Ciencias Humanas");
		if (textToTranslate.equals("Education and Culture"))
			textToTranslate = textToTranslate.replace("Education and Culture", "Educación y Cultura");
		if (textToTranslate.equals("Education and Human Studies"))
			textToTranslate = textToTranslate.replace("Education and Human Studies", "Educación y Ciencias Humanas");
		if (textToTranslate.equals("Education, Art and Science"))
			textToTranslate = textToTranslate.replace("EEducation, Art and Science",
					"Educación, Arte y ciencias sociales");
		if (textToTranslate.equals("Education, Psychology and Human Studies"))
			textToTranslate = textToTranslate.replace("Education, Psychology and Human Studies",
					"Education, piscología y estudios humanos");
		if (textToTranslate.equals("Arts and Letters"))
			textToTranslate = textToTranslate.replace("Arts and letters", "Artes y letras");
		if (textToTranslate.equals("Arts and Sciences"))
			textToTranslate = textToTranslate.replace("Arts and Sciences", "Artes y ciencias");
		if (textToTranslate.equals("Social Policy"))
			textToTranslate = textToTranslate.replace("Social Policy", "Política social");
		if (textToTranslate.equals("Social Policy and Administration"))
			textToTranslate = textToTranslate.replace("Social Policy and Administration",
					"Política social y administración");
		if (textToTranslate.equals("Cultural Policy"))
			textToTranslate = textToTranslate.replace("Cultural Policy", "Política cultural");
		if (textToTranslate.equals("Cultural Policy and Management"))
			textToTranslate = textToTranslate.replace("Cultural Policy and Management", "Política cultural y gestión");
		if (textToTranslate.equals("Cultural and Creative Studies"))
			textToTranslate = textToTranslate.replace("Cultural and Creative Studies",
					"Estudios culturales y creativos");
		if (textToTranslate.equals("Cultural and Historical Studies"))
			textToTranslate = textToTranslate.replace("Cultural and Historical Studies",
					"Estudios culturales e históricos");
		if (textToTranslate.equals("Policy Studies"))
			textToTranslate = textToTranslate.replace("Policy Studies", "Estudios políticos");
		if (textToTranslate.equals("Regional Policy"))
			textToTranslate = textToTranslate.replace("Regional Policy", "Políticas regionales");
		if (textToTranslate.equals("Regional Studies"))
			textToTranslate = textToTranslate.replace("Regional Studies", "Estudios regionales");
		if (textToTranslate.equals("Regional Development"))
			textToTranslate = textToTranslate.replace("Regional Development", "Desarrollo regional");
		if (textToTranslate.equals("Regional Sciences"))
			textToTranslate = textToTranslate.replace("Regional Sciences", "Ciencias regionales");
		if (textToTranslate.equals("Historical Studies"))
			textToTranslate = textToTranslate.replace("Historical Studies", "Estudios de historia");
		if (textToTranslate.equals("Global and Inter-cultural Studies"))
			textToTranslate = textToTranslate.replace("Global and Inter-cultural Studies", "Estudios interculturales");
		if (textToTranslate.equals("Global Japanese Studies"))
			textToTranslate = textToTranslate.replace("Global Japanese Studies", "Estudios japoneses");
		if (textToTranslate.equals("Global Studies"))
			textToTranslate = textToTranslate.replace("Global Studies", "Estudios globales");
		if (textToTranslate.equals("Global Culture"))
			textToTranslate = textToTranslate.replace("Global Culture", "Cultura global");
		if (textToTranslate.equals("Global and Regional Studies"))
			textToTranslate = textToTranslate.replace("Global and Regional Studies", "Estudios globales y regionales");
		if (textToTranslate.equals("Global and Regional Culture"))
			textToTranslate = textToTranslate.replace("Global and Regional Culture", "Cultura global y regional");
		if (textToTranslate.equals("Global and Media Studies"))
			textToTranslate = textToTranslate.replace("Global and Media Studies", "Estudios globales y de los medios");
		if (textToTranslate.equals("Global and Community Studies"))
			textToTranslate = textToTranslate.replace("Global and Community Studies",
					"Estudios globales y de la comunicación");
		if (textToTranslate.equals("Intercultural Communication"))
			textToTranslate = textToTranslate.replace("Intercultural Communication", "Comunicación intercultural");
		if (textToTranslate.equals("Social Sciences"))
			textToTranslate = textToTranslate.replace("Social Sciencies", "Ciencias Sociales");
		if (textToTranslate.equals("Culture and Representation"))
			textToTranslate = textToTranslate.replace("Culture and Representation", "Cultura y representación");
		if (textToTranslate.equals("Intercultural Studies"))
			textToTranslate = textToTranslate.replace("Intercultural Studies", "Estudios interculturales");
		if (textToTranslate.equals("Interhuman Symbiotic Studies"))
			textToTranslate = textToTranslate.replace("Interhuman Symbiotic Studies",
					"Estudios interhumanos simbióticos");
		if (textToTranslate.equals("International Studies"))
			textToTranslate = textToTranslate.replace("International Studies", "Estudios internacionales");
		if (textToTranslate.equals("International Studies and Regional Development"))
			textToTranslate = textToTranslate.replace("International Studies and Regional Development",
					"Estudios internacionales y desarrollo regional");
		if (textToTranslate.equals("International Studies and Liberal Arts"))
			textToTranslate = textToTranslate.replace("International Studies and Liberal Arts",
					"Estudios internacionales y arte liberal");
		if (textToTranslate.equals("International Studies of Culture"))
			textToTranslate = textToTranslate.replace("International Studies of Culture",
					"Estudios internacionales de cultura");
		if (textToTranslate.equals("International Tourism"))
			textToTranslate = textToTranslate.replace("International Tourism", "Turismo Internacional");
		if (textToTranslate.equals("International Communication"))
			textToTranslate = textToTranslate.replace("International Communication", "Comunicación internacional");
		if (textToTranslate.equals("International Relations"))
			textToTranslate = textToTranslate.replace("International Relations", "Relaciones internacionales");
		if (textToTranslate.equals("International Liberal Studies"))
			textToTranslate = textToTranslate.replace("International Liberal Studies",
					"Estudios liberales internacionales");
		if (textToTranslate.equals("International Liberal Arts"))
			textToTranslate = textToTranslate.replace("International Liberal Arts", "Artes liberales internacionales");
		if (textToTranslate.equals("Urban Liberal Arts"))
			textToTranslate = textToTranslate.replace("Urban Liberal Arts", "Artes liberales urbanas");
		if (textToTranslate.equals("Urban Environmental Sciences"))
			textToTranslate = textToTranslate.replace("Urban Environmental Sciences",
					"Ciencias de los entornos urbanos");
		if (textToTranslate.equals("Shinto Cultural Studies"))
			textToTranslate = textToTranslate.replace("Shinto Cultural Studies", "Estudios culturales Shinto");
		if (textToTranslate.equals("Buddhist Studies"))
			textToTranslate = textToTranslate.replace("Buddhist Studies", "Estudios budistas");
		if (textToTranslate.equals("Fine Arts"))
			textToTranslate = textToTranslate.replace("Fine Arts", "Artes creativas");
		if (textToTranslate.equals("Modern Japanese Studies Program"))
			textToTranslate = textToTranslate.replace("Modern Japanese Studies Program",
					"Programa de estudios japoneses modernos");
		if (textToTranslate.equals("Japanese Studies"))
			textToTranslate = textToTranslate.replace("Japanese Studies", "Estudios japoneses");
		if (textToTranslate.equals("Cultural and Historical Studies"))
			textToTranslate = textToTranslate.replace("Cultural and Historical Studies",
					"Estudios de historia y cultura");
		if (textToTranslate.equals("Cultural Studies"))
			textToTranslate = textToTranslate.replace("Cultural Studies", "Estudios culturales");
		if (textToTranslate.equals("Culture, Media and Society"))
			textToTranslate = textToTranslate.replace("Culture, Media and Society", "Cultura, media y sociedad");
		if (textToTranslate.equals("Cultural and Creative Studies"))
			textToTranslate = textToTranslate.replace("Cultural and Creative Studies",
					"Estudios culturales y creativos");
		if (textToTranslate.equals("Language and Literature"))
			textToTranslate = textToTranslate.replace("Language and Literature", "Lengua y literatura");
		if (textToTranslate.equals("Language and Culture Studies"))
			textToTranslate = textToTranslate.replace("Language and Culture Studies", "Estudios de lengua y cultura");
		if (textToTranslate.equals("Foreign Languages"))
			textToTranslate = textToTranslate.replace("Foreign Languages", "Lenguas extranjeras");
		if (textToTranslate.equals("Foreign Language"))
			textToTranslate = textToTranslate.replace("Foreign Language", "Lengua extranjera");
		if (textToTranslate.equals("Foreign Studies"))
			textToTranslate = textToTranslate.replace("Foreign Studies", "Estudios extranjeros");
		if (textToTranslate.equals("Artistic Culture"))
			textToTranslate = textToTranslate.replace("Artistic Culture", "Cultura artística");
		if (textToTranslate.equals("Human Life Studies"))
			textToTranslate = textToTranslate.replace("Human Life Studies", "Estudios de la salud humana");
		if (textToTranslate.equals("Human Life and Environment"))
			textToTranslate = textToTranslate.replace("Human Life and Environment",
					"Estudios de la salud humana y su entorno");
		if (textToTranslate.equals("Human Communication"))
			textToTranslate = textToTranslate.replace("Human Communication", "Comunicación humana");
		if (textToTranslate.equals("Humanities and Culture"))
			textToTranslate = textToTranslate.replace("Humanities and Culture", "Humanidades y cultura");
		if (textToTranslate.equals("Humanities, Law and Economics"))
			textToTranslate = textToTranslate.replace("Humanities, Law and Economics",
					"Humanidades, Derecho y económicas");
		if (textToTranslate.equals("Humanities and Sciences"))
			textToTranslate = textToTranslate.replace("Humanities and Sciences", "Ciencias humanas y sociales");
		if (textToTranslate.equals("Humanities and Social Sciences"))
			textToTranslate = textToTranslate.replace("Humanities and Social Sciences", "Ciencias humanas y sociales");
		if (textToTranslate.equals("Humanities and Cultural Sciences"))
			textToTranslate = textToTranslate.replace("Humanities and Cultural Sciences",
					"Ciencias humanas y culturales");
		if (textToTranslate.equals("Humanities & Social Sciences"))
			textToTranslate = textToTranslate.replace("Humanities & Social Sciences", "Ciencias humanas y sociales");
		if (textToTranslate.equals("Human Life Science"))
			textToTranslate = textToTranslate.replace("Humana Life Science", "Ciencias humanas de la salud");
		if (textToTranslate.equals("Human and Social Sciences"))
			textToTranslate = textToTranslate.replace("Human and Social Sciences", "Ciencias humanas y sociales");
		if (textToTranslate.equals("Modern Japanese Studies Program"))
			textToTranslate = textToTranslate.replace("Modern Japanese Studies Program",
					"Programa de estudio de Japonés moderno");
		if (textToTranslate.equals("Human and Social Studies"))
			textToTranslate = textToTranslate.replace("Human and Social Studies", "Estudios humanos y sociales");
		if (textToTranslate.equals("Human Life"))
			textToTranslate = textToTranslate.replace("Humana Life", "Ciencias humanas de la salud");
		if (textToTranslate.equals("Human Culture and Science"))
			textToTranslate = textToTranslate.replace("Human Culture and Science", "Cultura humana y ciencia");
		if (textToTranslate.equals("Human Environment"))
			textToTranslate = textToTranslate.replace("Human Environment", "Entornos humanos");
		if (textToTranslate.equals("Human Environments"))
			textToTranslate = textToTranslate.replace("Human Environments", "Entornos humanos");
		if (textToTranslate.equals("Human Development"))
			textToTranslate = textToTranslate.replace("Human Development", "Desarrollo humano");
		if (textToTranslate.equals("Human Development and Education"))
			textToTranslate = textToTranslate.replace("Human Development and Education",
					"Desarrollo humano y educación");
		if (textToTranslate.equals("Human Development and Culture"))
			textToTranslate = textToTranslate.replace("Human Development and Culture", "Desarrollo humano y cultura");
		if (textToTranslate.equals("Human  Development"))
			textToTranslate = textToTranslate.replace("Human  Development", "Desarrollo humano");
		if (textToTranslate.equals("Human Studies"))
			textToTranslate = textToTranslate.replace("Human Studies", "Humanidades");
		if (textToTranslate.equals("Human Health Sciences"))
			textToTranslate = textToTranslate.replace("Human Health Sciences", "Ciencias de la salud");
		if (textToTranslate.equals("Human Health and Welfare"))
			textToTranslate = textToTranslate.replace("Human Health and Welfare", "Ciencias de la salud y bienestar");
		if (textToTranslate.equals("Health and Welfare"))
			textToTranslate = textToTranslate.replace("Health and Welfare", "Ciencias de la salud y bienestar");
		if (textToTranslate.equals("Human Society"))
			textToTranslate = textToTranslate.replace("Human Society", "Sociedad Humana");
		if (textToTranslate.equals("Human and Culture Sciences"))
			textToTranslate = textToTranslate.replace("Human and Culture Sciences", "Ciencias humanas y culturales");
		if (textToTranslate.equals("Global Humanities"))
			textToTranslate = textToTranslate.replace("Global Humanities", "Humanidades (Global)");
		if (textToTranslate.equals("Global Humanities and Social Sciences"))
			textToTranslate = textToTranslate.replace("Global Humanities and Social Sciences",
					"Humanidades (Global) y ciencias sociales");
		if (textToTranslate.equals("Global and Inter-cultural Studies"))
			textToTranslate = textToTranslate.replace("Global and Inter-cultural Studies",
					"Estudios globales y interculturales");
		if (textToTranslate.equals("Global and Regional Studies"))
			textToTranslate = textToTranslate.replace("Global and Regional Studies", "Estudios globales y regionales");
		if (textToTranslate.equals("Global Communication"))
			textToTranslate = textToTranslate.replace("Global Communication", "Comunicación global");
		if (textToTranslate.equals("Global Communications"))
			textToTranslate = textToTranslate.replace("Global Communications", "Comunicaciones globales");
		if (textToTranslate.equals("Human science"))
			textToTranslate = textToTranslate.replace("Human science", "Ciencias humanas");
		if (textToTranslate.equals("Human Sciences"))
			textToTranslate = textToTranslate.replace("Human Sciences", "Ciencias humanas");
		if (textToTranslate.equals("Human Science"))
			textToTranslate = textToTranslate.replace("Human Science", "Ciencias humanas");
		if (textToTranslate.equals("Human Relation"))
			textToTranslate = textToTranslate.replace("Human Relation", "Relaciones humanas");
		if (textToTranslate.equals("Human Relations"))
			textToTranslate = textToTranslate.replace("Human Relations", "Relaciones humanas");
		if (textToTranslate.equals("Human Care Studies"))
			textToTranslate = textToTranslate.replace("Human Care Studies", "Estudios sobre el cuidado humano");
		if (textToTranslate.equals("Comprehensive Human Science"))
			textToTranslate = textToTranslate.replace("Comprehensive Human Science",
					"Comprensión de las Ciencias humanas");
		if (textToTranslate.equals("International Human Studies"))
			textToTranslate = textToTranslate.replace("International Human Studies",
					"Estudios internacionales de humanidades");
		if (textToTranslate.equals("Integrated Arts and Sciences"))
			textToTranslate = textToTranslate.replace("Integrated Arts and Sciences",
					"Estudios de arte y ciencias sociales integrados");
		if (textToTranslate.equals("Integrated Arts and Social Sciences"))
			textToTranslate = textToTranslate.replace("Integrated Arts and Social Sciences",
					"Estudios de arte y ciencias sociales integrados");
		if (textToTranslate.equals("Integrated Human Studies"))
			textToTranslate = textToTranslate.replace("Integrated Human Studies",
					"Estudios humanos integrados (Arte, Humanidades, Ciencia)");
		if (textToTranslate.equals("Integrated Human and Social Welfare"))
			textToTranslate = textToTranslate.replace("Integrated Human and Social Welfare",
					"Estudios humanos integrados y bienestar social");
		if (textToTranslate.equals("Contemporary Psychology"))
			textToTranslate = textToTranslate.replace("Contemporary Psychology", "Piscología contemporanea");
		if (textToTranslate.equals("Contemporary Cultures"))
			textToTranslate = textToTranslate.replace("Contemporary Cultures", "Culturas contemporáneas");
		if (textToTranslate.equals("Contemporary Culture"))
			textToTranslate = textToTranslate.replace("Contemporary Culture", "Cultura contemporánea");
		if (textToTranslate.equals("Contemporary Humanities"))
			textToTranslate = textToTranslate.replace("Contemporary Humanities", "Humanidades contemporáneas");
		if (textToTranslate.equals("Contemporary Social Studies"))
			textToTranslate = textToTranslate.replace("Contemporary Social Studies",
					"Estudios sociales contemporáneos");
		if (textToTranslate.equals("Contemporary Sociology"))
			textToTranslate = textToTranslate.replace("Contemporary Sociology", "Estudios sociales contemporáneos");
		if (textToTranslate.equals("World Englishes"))
			textToTranslate = textToTranslate.replace("World Englishes", "Inglés global");
		if (textToTranslate.equals("Psychological and Physiacal Science"))
			textToTranslate = textToTranslate.replace("Psychological and Physiacal Science",
					"Ciencias psicológicas y físicas");
		if (textToTranslate.equals("Clinical Education"))
			textToTranslate = textToTranslate.replace("Clinical Education", "Educación clínica");
		if (textToTranslate.equals("Life Science"))
			textToTranslate = textToTranslate.replace("Life Science", "Ciencias de la salud");
		if (textToTranslate.equals("Social Welfare"))
			textToTranslate = textToTranslate.replace("Social Welfare", "Seguridad Social");
		if (textToTranslate.equals("Psychology Welfare"))
			textToTranslate = textToTranslate.replace("Psychology Welfare", "Psicología del bienestar");
		if (textToTranslate.equals("Comprehensive Welfare"))
			textToTranslate = textToTranslate.replace("Comprehensive Welfare", "Comprensión del bienestar");
		if (textToTranslate.equals("Social Relations"))
			textToTranslate = textToTranslate.replace("Social Welfare", "Relaciones sociales");
		if (textToTranslate.equals("Social work studies"))
			textToTranslate = textToTranslate.replace("Social work studies", "Estudios sobre el trabajo social");
		if (textToTranslate.equals("Social work"))
			textToTranslate = textToTranslate.replace("Social work", "Estudios sobre el trabajo social");
		if (textToTranslate.equals("Social and Management Studies"))
			textToTranslate = textToTranslate.replace("Social and Management Studies",
					"Estudios sociales y de gestión");
		if (textToTranslate.equals("Applied Sociology"))
			textToTranslate = textToTranslate.replace("Applied Sociology", "Sociología aplicada");
		if (textToTranslate.equals("Applied Psychology"))
			textToTranslate = textToTranslate.replace("Applied Psychology", "Psicología aplicada");
		if (textToTranslate.equals("Biospher-Geospher Science"))
			textToTranslate = textToTranslate.replace("Biospher-Geospher Science", "Ciencias de la biosfera-geosfera");
		if (textToTranslate.equals("Health Science"))
			textToTranslate = textToTranslate.replace("Health Science", "Ciencias de la salud");
		if (textToTranslate.equals("Health Sciences"))
			textToTranslate = textToTranslate.replace("Health Sciences", "Ciencias de la salud");
		if (textToTranslate.equals("Medical Science"))
			textToTranslate = textToTranslate.replace("Medical Science", "Ciencias médicas");
		if (textToTranslate.equals("Medical Science and Welfare"))
			textToTranslate = textToTranslate.replace("Medical Science and Welfare",
					"Ciencias médicas y del bienestar");
		if (textToTranslate.equals("Health and Medical Science"))
			textToTranslate = textToTranslate.replace("Health and Medical Science", "Ciencias médicas y de la salud");
		if (textToTranslate.equals("Health and Medical Sciences"))
			textToTranslate = textToTranslate.replace("Health and Medical Sciences", "Ciencias médicas y de la salud");
		if (textToTranslate.equals("Child Study"))
			textToTranslate = textToTranslate.replace("Child Study", "Educación infantil");
		if (textToTranslate.equals("Child Psychology"))
			textToTranslate = textToTranslate.replace("Child Psychology", "Psicología infantíl");
		if (textToTranslate.equals("Child Development"))
			textToTranslate = textToTranslate.replace("Child Development", "Desarrollo infantíl");
		if (textToTranslate.equals("School Education"))
			textToTranslate = textToTranslate.replace("School Education", "Educación primaria");
		if (textToTranslate.equals("Household Arts"))
			textToTranslate = textToTranslate.replace("Household Arts", "Estudios infantiles");
		if (textToTranslate.equals("Modern Culture"))
			textToTranslate = textToTranslate.replace("Modern Culture", "Culturas modernas");
		if (textToTranslate.equals("Modern Communication Studies"))
			textToTranslate = textToTranslate.replace("Modern Communication Studies",
					"Estudios sobre la comunicación moderna");
		if (textToTranslate.equals("Communication and Culture"))
			textToTranslate = textToTranslate.replace("Communication and Culture", "Comunicación y cultura");
		if (textToTranslate.equals("Information Enviroment"))
			textToTranslate = textToTranslate.replace("Information Enviroment", "Entornos informáticos");
		if (textToTranslate.equals("Business Administration"))
			textToTranslate = textToTranslate.replace("Business Administration", "Administración de empresas");
		if (textToTranslate.equals("Psychological Science"))
			textToTranslate = textToTranslate.replace("Psychological Science", "Ciencias psicológicas");
		if (textToTranslate.equals("Future Design"))
			textToTranslate = textToTranslate.replace("Future Design", "Diseño del futuro");
		if (textToTranslate.equals("International College"))
			textToTranslate = textToTranslate.replace("International College", "Facultad internacional");
		if (textToTranslate.equals("Geo-Environmental Science"))
			textToTranslate = textToTranslate.replace("Geo-Environmental Science", "Ciencia de las geoentornos");
		if (textToTranslate.equals("Representational Studies"))
			textToTranslate = textToTranslate.replace("Representational Studies", "Estudios de representación");
		if (textToTranslate.equals("Liberal Arts"))
			textToTranslate = textToTranslate.replace("Liberal Arts", "Artes liberales");
		if (textToTranslate.equals("Liberal Arts and Sciences"))
			textToTranslate = textToTranslate.replace("Liberal Arts and Sciences", "Artes liberales y ciencias");
		if (textToTranslate.equals("Informatics and Human Communication"))
			textToTranslate = textToTranslate.replace("Informatics and Human Communication",
					"Informática y comunicación humana");

		// Language
		if (textToTranslate.equals("Business"))
			textToTranslate = textToTranslate.replace("Business", "Estudios de negocios");
		if (textToTranslate.equals("Language and Cultures"))
			textToTranslate = textToTranslate.replace("Language and Cultures", "Lengua y culturas");
		if (textToTranslate.equals("Hospitality & Tourism Management"))
			textToTranslate = textToTranslate.replace("Hospitality & Tourism Management",
					"Gestión turística y hospitalidad");
		if (textToTranslate.equals("Center for Japanese Language and Culture"))
			textToTranslate = textToTranslate.replace("Center for Japanese Language and Culture",
					"Facultad de lengua y cultura japonesa");
		if (textToTranslate.equals("Global Culture and Communication"))
			textToTranslate = textToTranslate.replace("Global Culture and Communication",
					"Cultura global y comunicación");
		if (textToTranslate.equals("Japanese Language Program"))
			textToTranslate = textToTranslate.replace("Japanese Language Program", "Programa de lengua japonesa");
		if (textToTranslate.equals("Global Business"))
			textToTranslate = textToTranslate.replace("Global Business", "Administración de empresas (global)");
		if (textToTranslate.equals("Language Communication"))
			textToTranslate = textToTranslate.replace("Language Communication", "Lenguaje y comunicación");
		if (textToTranslate.equals("Tourism and Business Management"))
			textToTranslate = textToTranslate.replace("Tourism and Business Management",
					"Dirección de empresas y turismo");
		if (textToTranslate.equals("Business Management"))
			textToTranslate = textToTranslate.replace("Business Management", "Dirección de empresas");
		if (textToTranslate.equals("English, IT and Management"))
			textToTranslate = textToTranslate.replace("English, IT and Management",
					"Inglés, IT y Dirección de empresas");
		if (textToTranslate.equals("Global and Interdisciplinary Studies"))
			textToTranslate = textToTranslate.replace("Global and Interdisciplinary Studies",
					"Estudios globales e interdisciplinares");
		if (textToTranslate.equals("Cross-Cultural Studies"))
			textToTranslate = textToTranslate.replace("Cross-Cultural Studies", "Estudios de culturas cruzadas");
		if (textToTranslate.equals("Japanese Language Program for Foreign Students"))
			textToTranslate = textToTranslate.replace("Japanese Language Program for Foreign Students",
					"Programa de lengua japonesa para extranjeros");
		if (textToTranslate.equals("International Cultural Relations"))
			textToTranslate = textToTranslate.replace("International Cultural Relations",
					"Relaciones culturales internacionales");
		if (textToTranslate.equals("Media and Communication"))
			textToTranslate = textToTranslate.replace("Media and Communitacion", "Medios y comunicaciones");
		if (textToTranslate.equals("Global Business"))
			textToTranslate = textToTranslate.replace("Global Business", "Negocios Globales");
		if (textToTranslate.equals("International, English"))
			textToTranslate = textToTranslate.replace("International, English", "Inglés internacional");
		if (textToTranslate.equals("Emphasis Human Education"))
			textToTranslate = textToTranslate.replace("Emphasis Human Education", "Humanidades");
		if (textToTranslate.equals("International Studies and Business"))
			textToTranslate = textToTranslate.replace("International Studies and Business",
					"Estudios internacionales y dirección de empresas");
		if (textToTranslate.equals("Cultural Development"))
			textToTranslate = textToTranslate.replace("Cultural Development", "Desarrollo Cultural");
		if (textToTranslate.equals("Human Cultures"))
			textToTranslate = textToTranslate.replace("Human Cultures", "Estudio de culturas");
		if (textToTranslate.equals("Modern Life"))
			textToTranslate = textToTranslate.replace("Modern Life", "Facultad de vida moderna");
		if (textToTranslate.equals("Digital Communications"))
			textToTranslate = textToTranslate.replace("Digital Communications", "Facultad de Comunicaciones digitales");
		if (textToTranslate.equals("International and Area Studies"))
			textToTranslate = textToTranslate.replace("International and Area Studies",
					"Estudios internacionales y de area");
		if (textToTranslate.equals("Applied International Studies"))
			textToTranslate = textToTranslate.replace("Applied International Studies",
					"Estudios internacionales aplicados");
		if (textToTranslate.equals("Contemporary International Studies"))
			textToTranslate = textToTranslate.replace("Contemporary International Studies",
					"Estudios internacionales contemporaneos");
		if (textToTranslate.equals("International Welfare Development"))
			textToTranslate = textToTranslate.replace("International Welfare Development",
					"Estudios para el desarrollo del bienestar internacional");
		if (textToTranslate.equals("International Career Development"))
			textToTranslate = textToTranslate.replace("International Career Development",
					"Estudios para el desarrollo de la carrera profesional internacional");
		if (textToTranslate.equals("Future Learning"))
			textToTranslate = textToTranslate.replace("Future Learning", "Aprendizaje del futuro");
		if (textToTranslate.equals("Faculty of International Studies"))
			textToTranslate = textToTranslate.replace("Faculty of International Studies",
					"Facultad de estudios internacionales");
		if (textToTranslate.equals("Economics and Business Administration"))
			textToTranslate = textToTranslate.replace("Economics and Business Administration",
					"Económicas y administración de empresas");
		if (textToTranslate.equals("Economics and Business Management"))
			textToTranslate = textToTranslate.replace("Economics and Business Management",
					"Económicas y dirección de empresas");

		// Law
		if (textToTranslate.equals("Law and Politics"))
			textToTranslate = textToTranslate.replace("Law and Politics", "Derecho y política");
		if (textToTranslate.equals("International Polítics, Economics and Communication"))
			textToTranslate = textToTranslate.replace("International Polítics, Economics and Communication",
					"Políticas internacionales, economía y comunicación");
		if (textToTranslate.equals("Political Science and Economics"))
			textToTranslate = textToTranslate.replace("Political Science and Economics",
					"Ciencias políticas y economía");
		if (textToTranslate.equals("Business and Public Policies"))
			textToTranslate = textToTranslate.replace("Business and Public Policies", "Empresa y políticas públicas");
		if (textToTranslate.equals("Policy Science"))
			textToTranslate = textToTranslate.replace("Policy Science", "Ciencias políticas");
		if (textToTranslate.equals("Management and Economics"))
			textToTranslate = textToTranslate.replace("Management and Economics", "Dirección de empresas y economía");
		if (textToTranslate.equals("Management and Law"))
			textToTranslate = textToTranslate.replace("Management and Law", "Dirección de empresas y derecho");
		if (textToTranslate.equals("Intellectual Property"))
			textToTranslate = textToTranslate.replace("Intellectual Property", "Propiedad Intelectual");
		if (textToTranslate.equals("Managerial Culture"))
			textToTranslate = textToTranslate.replace("Managerial Culture", "Cultura Directiva");
		if (textToTranslate.equals("Contemporary Policy"))
			textToTranslate = textToTranslate.replace("Contemporary Policy", "Políticas contemporáneas");
		if (textToTranslate.equals("Political Science & Economics"))
			textToTranslate = textToTranslate.replace("Political Science & Economics",
					"Ciencias políticas y económicas");
		if (textToTranslate.equals("Law, Politics and Economics"))
			textToTranslate = textToTranslate.replace("Law, Politics and Economics", "Derecho, políticas y económicas");
		if (textToTranslate.equals("Social and International Studies"))
			textToTranslate = textToTranslate.replace("Social and International Studies",
					"Estudios sociales e internacionales");
		if (textToTranslate.equals("Community Development"))
			textToTranslate = textToTranslate.replace("Community Development", "Desarrollo comunitario");
		if (textToTranslate.equals("International Politics and Economics"))
			textToTranslate = textToTranslate.replace("International Politics and Economics",
					"Política internacional y economía");
		if (textToTranslate.equals("Administration and Social Sciences"))
			textToTranslate = textToTranslate.replace("Administration and Social Sciences",
					"Administración y ciencias sociales");
		if (textToTranslate.equals("Healthcare Management"))
			textToTranslate = textToTranslate.replace("Healthcare Management", "Gestión sanitaria");
		if (textToTranslate.equals("Jurisprudence"))
			textToTranslate = textToTranslate.replace("Jurisprudence", "Facultad de jurisprudencia");
		if (textToTranslate.equals("Glocal Policy Management and Communications"))
			textToTranslate = textToTranslate.replace("Glocal Policy Management and Communications",
					"Gestión política global y comunicaciones");
		if (textToTranslate.equals("International College of Arts and Science"))
			textToTranslate = textToTranslate.replace("International College of Arts and Science",
					"Colegio internacional de artes y ciencias");
		if (textToTranslate.equals("Policy Management"))
			textToTranslate = textToTranslate.replace("Policy Management", "Gestión política");
		if (textToTranslate.equals("Commerce"))
			textToTranslate = textToTranslate.replace("Commerce", "Comercio");

		// Economics, Management, Commerce
		if (textToTranslate.equals("Health and Sport Science"))
			textToTranslate = textToTranslate.replace("Health and Sport Science", "Salud y ciencias del deporte");
		if (textToTranslate.equals("International Politics, Economics and Communication"))
			textToTranslate = textToTranslate.replace("International Politics, Economics and Communication",
					"Política internacional, económicas y comunicaciones");
		if (textToTranslate.equals("Real Estate Sciences"))
			textToTranslate = textToTranslate.replace("Real Estate Sciences", "Ciencias reales del estado");
		if (textToTranslate.equals("Urban Life Studies"))
			textToTranslate = textToTranslate.replace("Urban Life Studies", "Ciencias de la vida urbana");
		if (textToTranslate.equals("Business and Commerce"))
			textToTranslate = textToTranslate.replace("Business and Commerce", "Empresa y comercio");
		if (textToTranslate.equals("Contemporary Management"))
			textToTranslate = textToTranslate.replace("Contemporary Management", "Dirección contemporanea");
		if (textToTranslate.equals("Modern Chinese Studies"))
			textToTranslate = textToTranslate.replace("Modern Chinese Studies", "Estudios de chino moderno");
		if (textToTranslate.equals("Business Administration Education"))
			textToTranslate = textToTranslate.replace("Business Administration Education",
					"Educación en dirección de empresas");
		if (textToTranslate.equals("Financial Economy"))
			textToTranslate = textToTranslate.replace("Financial Economy", "Economía financiera");
		if (textToTranslate.equals("Sustainable System Sciences"))
			textToTranslate = textToTranslate.replace("Sustainable System Sciences",
					"Ciencias de sistemas sostenibles");
		if (textToTranslate.equals("Commerce and Economics"))
			textToTranslate = textToTranslate.replace("Commerce and Economics", "Comercio y economía");
		if (textToTranslate.equals("Economics, Management and Information Science"))
			textToTranslate = textToTranslate.replace("Economics, Management and Information Science",
					"Economía, Gestion de empresas y ciencias de la información");
		if (textToTranslate.equals("Business Innovation"))
			textToTranslate = textToTranslate.replace("Business Innovation", "Innovación de empresas");
		if (textToTranslate.equals("Human Welfare Studies"))
			textToTranslate = textToTranslate.replace("Human Welfare Studies", "Estudios sobre el bienestar humano");
		if (textToTranslate.equals("Contemporary Business"))
			textToTranslate = textToTranslate.replace("Contemporary Business", "Negocios Contemporáneos");
		if (textToTranslate.equals("Economic Information"))
			textToTranslate = textToTranslate.replace("Economic Information", "Información económica");
		if (textToTranslate.equals("Social Design & Management"))
			textToTranslate = textToTranslate.replace("Social Design & Management", "Diseño social y gestión");
		if (textToTranslate.equals("Humanities and Social Sciences (Tentative)"))
			textToTranslate = textToTranslate.replace("Humanities and Social Sciences (Tentative)",
					"Humanidades y ciencias sociales");
		if (textToTranslate.equals("Social Information"))
			textToTranslate = textToTranslate.replace("Social Information", "Información social");
		if (textToTranslate.equals("Sports Human"))
			textToTranslate = textToTranslate.replace("Sports Human", "Facultad de deportes");
		if (textToTranslate.equals("Information Studies"))
			textToTranslate = textToTranslate.replace("Information Studies", "Estudios de la información");
		if (textToTranslate.equals("Community Studies"))
			textToTranslate = textToTranslate.replace("Community Studies", "Estudios comunitarios");
		if (textToTranslate.equals("Management Information"))
			textToTranslate = textToTranslate.replace("Management Information", "Gestión de la información");
		if (textToTranslate.equals("Service Management"))
			textToTranslate = textToTranslate.replace("Service Management", "Gestión de servicios");
		if (textToTranslate.equals("Social Systems Science"))
			textToTranslate = textToTranslate.replace("Social Systems Science", "Ciencia de sistemas sociales");
		if (textToTranslate.equals("Survice Innovation"))
			textToTranslate = textToTranslate.replace("Survice Innovation", "Departamento de innovación");
		if (textToTranslate.equals("Life Design"))
			textToTranslate = textToTranslate.replace("Life Design", "Diseño de vida");
		if (textToTranslate.equals("Comprehensive Management"))
			textToTranslate = textToTranslate.replace("Comprehensive Management", "Gestión comprensiva");
		if (textToTranslate.equals("Art and Design"))
			textToTranslate = textToTranslate.replace("Art and Design", "Arte y diseño");
		if (textToTranslate.equals("Business, Marketing and Distribution"))
			textToTranslate = textToTranslate.replace("Business, Marketing and Distribution",
					"Negocios, marketing y distribución");
		if (textToTranslate.equals("Economics & Management"))
			textToTranslate = textToTranslate.replace("Economics & Management", "Economía y gestión");
		if (textToTranslate.equals("Environmental information business"))
			textToTranslate = textToTranslate.replace("Environmental information business",
					"Negocios de información ambiental");
		if (textToTranslate.equals("Health and Nutrition"))
			textToTranslate = textToTranslate.replace("Health and Nutrition", "Salud y nutrición");
		if (textToTranslate.equals("Health Sciences"))
			textToTranslate = textToTranslate.replace("Health Sciences", "Ciencias de la salud");
		if (textToTranslate.equals("Business Administeration"))
			textToTranslate = textToTranslate.replace("Business Administeration", "Administración de empresas");
		if (textToTranslate.equals("Home Economics"))
			textToTranslate = textToTranslate.replace("Home Economics", "Economía del hogar");
		if (textToTranslate.equals("Pharmaceutical"))
			textToTranslate = textToTranslate.replace("Pharmaceutical", "Estudios farmacéuticos");
		if (textToTranslate.equals("Economic Informatics"))
			textToTranslate = textToTranslate.replace("Economic Informatics", "Economía e informática");
		if (textToTranslate.equals("Commerce and Management"))
			textToTranslate = textToTranslate.replace("Commerce and Management", "Comercio y gestión");
		if (textToTranslate.equals("Econoinformatics"))
			textToTranslate = textToTranslate.replace("Econoinformatics", "Economía e informática");
		if (textToTranslate.equals("Information design and sociology"))
			textToTranslate = textToTranslate.replace("Information design and sociology",
					"Diseño de la información y sociología");
		if (textToTranslate.equals("Health Services Management"))
			textToTranslate = textToTranslate.replace("Health Services Management", "Gestión de servicios sanitarios");
		if (textToTranslate.equals("Commercial Science"))
			textToTranslate = textToTranslate.replace("Commercial Science", "Ciencias comerciales");
		if (textToTranslate.equals("Economic Sciences"))
			textToTranslate = textToTranslate.replace("Economic Sciences", "Ciencias económicas");
		if (textToTranslate.equals("Social and Environment Studies"))
			textToTranslate = textToTranslate.replace("Social and Environment Studies",
					"Estudios sociales y del entorno");
		if (textToTranslate.equals("International Business Management"))
			textToTranslate = textToTranslate.replace("International Business Management",
					"Dirección de empresas internacionales");
		if (textToTranslate.equals("Project Design"))
			textToTranslate = textToTranslate.replace("Project Design", "Diseño de proyectos");
		if (textToTranslate.equals("Sport and Health Science"))
			textToTranslate = textToTranslate.replace("Sport and Health Science", "Deportes y ciencias de la salud");
		if (textToTranslate.equals("Tourism Sciences and Industrial Management"))
			textToTranslate = textToTranslate.replace("Tourism Sciences and Industrial Management",
					"Turismo y gestión industrial");
		if (textToTranslate.equals("Distribution and Logistics Systems"))
			textToTranslate = textToTranslate.replace("Distribution and Logistics Systems",
					"Distribución y sistemas logísticos");
		//
		if (textToTranslate.equals("Performing and Visual Arts"))
			textToTranslate = textToTranslate.replace("Performing and Visual Arts", "Ejecución y artes visuales");
		if (textToTranslate.equals("Performing and Visual Arts"))
			textToTranslate = textToTranslate.replace("Performing and Visual Arts", "Ejecución y artes visuales");
		if (textToTranslate.equals("System Design"))
			textToTranslate = textToTranslate.replace("System Design", "Diseño de sistemas");
		if (textToTranslate.equals("Medical Pharmaceutical Science"))
			textToTranslate = textToTranslate.replace("Medical Pharmaceutical Science",
					"Ciencias sanitarias farmacéuticas");
		if (textToTranslate.equals("Media and Information Resources"))
			textToTranslate = textToTranslate.replace("Media and Information Resources",
					"Medios y recursos de información");
		if (textToTranslate.equals("Humanity and Environment"))
			textToTranslate = textToTranslate.replace("Humanity and Environment", "Humanidad y medioambiente");
		if (textToTranslate.equals("Lifelong Learning and Career Studies"))
			textToTranslate = textToTranslate.replace("Lifelong Learning and Career Studies",
					"Aprendizaje vital y estudios de carrera");
		if (textToTranslate.equals("Engineering and Design"))
			textToTranslate = textToTranslate.replace("Engineering and Design", "Ingeniería y diseño");
		if (textToTranslate.equals("Bioscience and Applied Chemistry"))
			textToTranslate = textToTranslate.replace("Bioscience and Applied Chemistry",
					"Biociencia y química aplicada");
		if (textToTranslate.equals("Computer and Information Sciences"))
			textToTranslate = textToTranslate.replace("Computer and Information Sciences",
					"Ciencias de la computación y de la información");
		if (textToTranslate.equals("Fundamental Science and Engineering"))
			textToTranslate = textToTranslate.replace("Fundamental Science and Engineering",
					"Ciencias fundamentales e ingeniería");
		if (textToTranslate.equals("Creative Science and Engineering"))
			textToTranslate = textToTranslate.replace("Creative Science and Engineering",
					"Ciencias creativas e ingeniería");
		if (textToTranslate.equals("Advanced Science and Engineering"))
			textToTranslate = textToTranslate.replace("Creative Science and Engineering",
					"Ciencias creativas e ingeniería");
		if (textToTranslate.equals("Medicine"))
			textToTranslate = textToTranslate.replace("Medicine", "Medicina");
		if (textToTranslate.equals("Pharma-Science"))
			textToTranslate = textToTranslate.replace("Pharma-Science", "Ciencia parafarmacéutica");
		if (textToTranslate.equals("Medical Technology"))
			textToTranslate = textToTranslate.replace("Medical Technology", "Tecnología médica");
		if (textToTranslate.equals("Fukuoka Medical Technology"))
			textToTranslate = textToTranslate.replace("Fukuoka Medical Technology", "Tecnología médica (Fukuoka)");
		if (textToTranslate.equals("Human Life and Environmental Sciences"))
			textToTranslate = textToTranslate.replace("Human Life and Environmental Sciences",
					"Vida humana y ciencias del medioambiente");
		if (textToTranslate.equals("Community and Human Services"))
			textToTranslate = textToTranslate.replace("Community and Human Services",
					"Servicios humanos y comunitarios");
		if (textToTranslate.equals("Sociology & Social Work"))
			textToTranslate = textToTranslate.replace("Sociology & Social Work", "Sociología y trabajo social");
		if (textToTranslate.equals("Knowledge Engineering"))
			textToTranslate = textToTranslate.replace("Knowledge Engineering", "Ingeniería de conocimientos");
		if (textToTranslate.equals("Regional Development Studies"))
			textToTranslate = textToTranslate.replace("Regional Development Studies", "Estudios de desarollo regional");
		if (textToTranslate.equals("Life Sciences"))
			textToTranslate = textToTranslate.replace("Life Sciences", "Ciencias vitales");
		if (textToTranslate.equals("Human Life Design"))
			textToTranslate = textToTranslate.replace("Human Life Design", "Diseño de vida humana");
		if (textToTranslate.equals("Information Science and Arts"))
			textToTranslate = textToTranslate.replace("Information Science and Arts",
					"Ciencias de la información y arte");
		if (textToTranslate.equals("Food Science"))
			textToTranslate = textToTranslate.replace("Food Science", "Ciencias alimenticias");
		if (textToTranslate.equals("Engineering Science"))
			textToTranslate = textToTranslate.replace("Engineering Science", "Ingeniería y ciencias");
		if (textToTranslate.equals("Environmental and Urban Engineering"))
			textToTranslate = textToTranslate.replace("Environmental and Urban Engineering",
					"Ingeniería urbana y medioambiental");
		if (textToTranslate.equals("Chemistry Materials and Bioengineering"))
			textToTranslate = textToTranslate.replace("Chemistry Materials and Bioengineering",
					"Materiales químicos y bioingeniería");
		if (textToTranslate.equals("Safety Science"))
			textToTranslate = textToTranslate.replace("Safety Science", "Ciencias de la seguridad");
		if (textToTranslate.equals("Health and Well-being"))
			textToTranslate = textToTranslate.replace("Health and Well-being", "Salud y bienestar");
		if (textToTranslate.equals("Dentistry"))
			textToTranslate = textToTranslate.replace("Dentistry", "Odontología");
		if (textToTranslate.equals("Languages and Cultures"))
			textToTranslate = textToTranslate.replace("Languages and Cultures", "Lenguas y culturas");
		if (textToTranslate.equals("Social-Human Environmentology"))
			textToTranslate = textToTranslate.replace("Social-Human Environmentology", "Entorno Social-humano");
		if (textToTranslate.equals("Sports & Health Science"))
			textToTranslate = textToTranslate.replace("Sports & Health Science", "Ciencias de la salud y el deporte");
		if (textToTranslate.equals("Social Informatics"))
			textToTranslate = textToTranslate.replace("Social Informatics", "Informatica y sociedad");
		if (textToTranslate.equals("Human  Informatics"))
			textToTranslate = textToTranslate.replace("Human  Informatics", "Humanidades e informática");
		if (textToTranslate.equals("Creation and Representation"))
			textToTranslate = textToTranslate.replace("Creation and Representation", "Creación y representación");
		if (textToTranslate.equals("Sciences (FGL)"))
			textToTranslate = textToTranslate.replace("Sciences (FGL)", "Ciencias (Programa global de liderazgo)");
		if (textToTranslate.equals("Engineering (FGL)"))
			textToTranslate = textToTranslate.replace("Sciences (FGL)", "Ingeniería (Programa global de liderazgo)");
		if (textToTranslate.equals("Agriculture (FGL)"))
			textToTranslate = textToTranslate.replace("Agriculture (FGL)",
					"Agricultura (Programa global de liderazgo)");
		if (textToTranslate.equals("Pharmacy and Pharmaceutical Sciences"))
			textToTranslate = textToTranslate.replace("Pharmacy and Pharmaceutical Sciences",
					"Farmacia y ciencias farmacéuticas");
		if (textToTranslate.equals("International College (Undergraduate English Courses)"))
			textToTranslate = textToTranslate.replace("International College (Undergraduate English Courses)",
					"Colegio internacional (Cursos de inglés)");
		if (textToTranslate.equals("Pharmaceutical Sciences"))
			textToTranslate = textToTranslate.replace("Pharmaceutical Sciences", "Ciencias farmacéuticas");
		if (textToTranslate.equals("Management and Administration"))
			textToTranslate = textToTranslate.replace("Management and Administration", "Dirección y administración");
		if (textToTranslate.equals("Science and Technology"))
			textToTranslate = textToTranslate.replace("Science and Technology", "Ciencia y tecnología");
		if (textToTranslate.equals("Fashion Science"))
			textToTranslate = textToTranslate.replace("Fashion Science", "Moda");
		if (textToTranslate.equals("Agriculture"))
			textToTranslate = textToTranslate.replace("Agriculture", "Agricultura");
		if (textToTranslate.equals("The Japanese Language Course"))
			textToTranslate = textToTranslate.replace("The Japanese Language Course", "Curso de lengua japonesa");

		if (textToTranslate.equals("Industrial Administraion"))
			textToTranslate = textToTranslate.replace("Industrial Administraion", "Administración industrial");
		if (textToTranslate.equals("Medical Informatics"))
			textToTranslate = textToTranslate.replace("Medical Informatics", "Informática sanitaria");
		if (textToTranslate.equals("Applied Information Technology"))
			textToTranslate = textToTranslate.replace("Applied Information Technology",
					"Tecnologías de la información aplicadas");
		if (textToTranslate.equals("Computer Science"))
			textToTranslate = textToTranslate.replace("Computer Science", "Ciencias de la computación");
		if (textToTranslate.equals("Business and Informatis"))
			textToTranslate = textToTranslate.replace("Business and Informatis", "Negocios e informática");
		if (textToTranslate.equals("Informatics and Engineering"))
			textToTranslate = textToTranslate.replace("Informatics and Engineering", "Informática e ingeniería");
		if (textToTranslate.equals("Science and Engineering"))
			textToTranslate = textToTranslate.replace("Science and Engineering", "Ciencias e ingeniería");
		if (textToTranslate.equals("Business and Informatics"))
			textToTranslate = textToTranslate.replace("Business and Informatics", "Negocios e informática");
		if (textToTranslate.equals("Business Administration and Information Science"))
			textToTranslate = textToTranslate.replace("Business Administration and Information Science",
					"Administración de empresas y ciencias de la información");
		if (textToTranslate.equals("Informatics  /  Department of Computer Science and Art"))
			textToTranslate = textToTranslate.replace("Informatics  /  Department of Computer Science and Art",
					"Informática y arte");
		if (textToTranslate.equals("Network and Information"))
			textToTranslate = textToTranslate.replace("Network and Information", "Redes e información");
		if (textToTranslate.equals("Administration and Informatics"))
			textToTranslate = textToTranslate.replace("Administration and Informatics", "Administración e informática");
		if (textToTranslate.equals("Science and Industrial Technology"))
			textToTranslate = textToTranslate.replace("Science and Industrial Technology",
					"Ciencia y tecnología industrial");
		if (textToTranslate.equals("Humanity-Oriented Science and Engineering"))
			textToTranslate = textToTranslate.replace("Humanity-Oriented Science and Engineering",
					"Ciencia orientada a la humanidad y ingeniería");
		if (textToTranslate.equals("Industry and Information Science"))
			textToTranslate = textToTranslate.replace("Industry and Information Science",
					"Industria y ciencias de la información");
		if (textToTranslate.equals("Management and Information Science"))
			textToTranslate = textToTranslate.replace("Management and Information Science",
					"Administración y ciencias de la información");
		if (textToTranslate.equals("Management and Information Systems"))
			textToTranslate = textToTranslate.replace("Management and Information Systems",
					"Administración y sistemas de información");
		if (textToTranslate.equals("Management and Information Sciences"))
			textToTranslate = textToTranslate.replace("Management and Information Sciences",
					"Administración y ciencias de la información");
		if (textToTranslate.equals("Management & Information Sciences"))
			textToTranslate = textToTranslate.replace("Management & Information Sciences",
					"Administración y ciencias de la información");
		if (textToTranslate.equals("Informatics"))
			textToTranslate = textToTranslate.replace("Informatics", "Informática");
		if (textToTranslate.equals("Engineering"))
			textToTranslate = textToTranslate.replace("Engineering", "Facultad de Ingeniería");

		return textToTranslate;
	}

}

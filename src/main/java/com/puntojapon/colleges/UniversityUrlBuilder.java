/**
 * Universities Url Builder
 * @author jdecastroc
 * @version 2.0, 21 Feb 2016
 *
 */
package com.puntojapon.colleges;

/**
 * Provides the url Builder for the Universities. It builds the url which is
 * going to be crawled following the url specifications of the crawling page
 * 
 * @author jdecastroc
 */
public class UniversityUrlBuilder extends UrlBuilder {

	// University subjects
	private final String literatureUrl = "101-102-103-104-105-106-107-111";
	private final String languageUrl = "151-160-166";
	private final String lawUrl = "201-202";
	private final String economManagCommercUrl = "221-222-223";
	private final String sociologyUrl = "241-242-243-244";
	private final String internacionalStudUrl = "261";
	private final String nursingHealtUrl = "651-652-653-654";
	private final String medicalDentalUrl = "601";
	private final String pharmacyUrl = "621";
	private final String physicalScienceUrl = "501-502-503-504-505-506-507";
	private final String engineeringUrl = "551-552-553-554-555-556-557-558-559-560-561-563-564-566";
	private final String agriFishUrl = "701-702-703-704-705-707-708-709";
	private final String educationUrl = "301-315-317";
	private final String lifeScienceUrl = "401-402-403-404-405";
	private final String artsUrl = "451-452-453-458";
	private final String integSciencUrl = "751-752-753";

	// Type of university
	private final String nationalUni = "&u%5But%5D%5B%5D=1";
	private final String publicUni = "&u%5But%5D%5B%5D=2";
	private final String privateUni = "&u%5But%5D%5B%5D=3";
	private final String jsfuUni = "&u%5But%5D%5B%5D=4";

	// Month of admission
	private final String januaryAdm = "&u%5Bsm%5D%5B%5D=1";
	private final String marchAdm = "&u%5Bsm%5D%5B%5D=3";
	private final String aprilAdm = "&u%5Bsm%5D%5B%5D=4";
	private final String mayAdm = "&u%5Bsm%5D%5B%5D=5";
	private final String septemberAdm = "&u%5Bsm%5D%5B%5D=9";
	private final String octoberAdm = "&u%5Bsm%5D%5B%5D=10";

	// Application deadline
	private final String januaryApp = "&u%5Bem%5D%5B%5D=1";
	private final String februaryApp = "&u%5Bem%5D%5B%5D=2";
	private final String marchApp = "&u%5Bem%5D%5B%5D=3";
	private final String aprilApp = "&u%5Bem%5D%5B%5D=4";
	private final String mayApp = "&u%5Bem%5D%5B%5D=5";
	private final String juneApp = "&u%5Bem%5D%5B%5D=6";
	private final String julyApp = "&u%5Bem%5D%5B%5D=7";
	private final String augustApp = "&u%5Bem%5D%5B%5D=8";
	private final String septemberApp = "&u%5Bem%5D%5B%5D=9";
	private final String octoberApp = "&u%5Bem%5D%5B%5D=10";
	private final String novemberApp = "&u%5Bem%5D%5B%5D=11";
	private final String decemberApp = "&u%5Bem%5D%5B%5D=12";

	// EJU Examen
	private final String ejuYes = "&u%5Bej%5D%5B%5D=1";
	private final String ejuNo = "&u%5Bej%5D%5B%5D=2";

	// English examination
	private final String engNotNecessary = "&u%5Ben%5D%5B%5D=1";
	private final String engNecessary = "&u%5Ben%5D%5B%5D=2";
	private final String engToeflUsed = "&u%5Ben%5D%5B%5D=3";
	private final String engOthers = "&u%5Ben%5D%5B%5D=4";
	private final String engToeflEtcUsed = "&u%5Ben%5D%5B%5D=5";

	// Admission to the university before entering Japan
	private final String appOutsideJap = "&u%5Baf%5D%5B%5D=1";
	private final String appInsideJap = "&u%5Baf%5D%5B%5D=2";

	// Object variables
	private String[] typeStudiesList;

	private String typeUniUrl = "";
	private String admissionMonthUrl = "";
	private String deadLineUrl = "";
	private String ejuUrl = "";
	private String engExamUrl = "";
	private String admissionUniUrl = "";
	private String urlTypeStudies = "";
	private String nameUni = "";

	/**
	 * Mainly class constructor. Build the search information in the urlBuilder
	 * using the proper functions to build all the url based on the information
	 * provided by the client search.
	 * 
	 * @param nameUni
	 *            -> name of the university provided by the client to search
	 * @param prefecture
	 *            -> list of prefectures to search in
	 * @param typeStudiesList
	 *            -> list of the type of studies to evaluate in the search
	 * @param typeUni
	 *            -> list of type of Uni provided by the client
	 * @param admissionMonth
	 *            -> list of the admission months provided
	 * @param deadLine
	 *            -> list of the deadLine date provided
	 * @param eju
	 *            -> list of the eju parameter provided
	 * @param engExam
	 *            -> list of the engExam parameter provided
	 * @param admisionUni
	 *            -> list of admisionUni months provided by the client.
	 */
	public UniversityUrlBuilder(String nameUni, String[] prefecture, String[] typeStudiesList, String[] typeUni,
			String[] admissionMonth, String[] deadLine, String[] eju, String[] engExam, String[] admisionUni) {
		// Build URL's
		setPrefecture(prefecture);
		setTypeStudiesList(typeStudiesList);

		// prefecture.length > 0 && !prefecture[0].equals("all")
		if (prefecture.length > 0 && !prefecture[0].equals("all"))
			setUrlPrefecture(buildPrefectureUrl(getPrefecturesList(), prefecture));
		if (typeStudiesList.length > 0)
			setUrlTypeStudies(buildStudiesUrl(typeStudiesList));
		if (typeUni.length > 0) // If many type uni
			setTypeUniUrl(buildTypeUniUrl(typeUni));
		if (admissionMonth.length > 0)
			setAdmissionMonthUrl(buildAdmissionMonthUrl(admissionMonth));
		if (deadLine.length > 0)
			setDeadLineUrl(buildDeadLineUrl(deadLine));
		if (eju.length > 0)
			setEjuUrl(buildEjuExamUrl(eju));
		if (engExam.length > 0)
			setEngExamUrl(buildEngExamUrl(engExam));
		if (admisionUni.length > 0)
			setAdmissionUniUrl(buildAdmissionUniUrl(engExam));
		if (typeStudiesList.length > 0)
			setUrlPrefecture(buildPrefectureUrl(getPrefecturesList(), prefecture));
		if (!nameUni.isEmpty())
			setNameUni(nameUni);

	}

	/**
	 * Build the final url which is going to be used by the crawler
	 * 
	 * @param prefecture
	 *            -> prefecture list provided by the client which is going to be
	 *            compared with the prefectures real list in order to check
	 *            errors
	 * @return the url which is going the be used by the crawler as a string
	 */
	@Override
	public String getSearchUrl(String[] prefecture) {
		if (prefecture.length > 0 && !prefecture[0].equals("all")) { // If many
																		// prefectures
																		// choosen

			setUrl("http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=" + getNameUni() + "&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
					+ getTypeUniUrl() + getAdmissionMonthUrl() + getDeadLineUrl() + getEjuUrl() + getEngExamUrl()
					+ getAdmissionUniUrl() + "&u%5Bac%5D=&a%5Bpf%5D=" + getUrlPrefecture()
					+ "&search.x=177&search.y=11&search=search");

			System.out.println("urlPrefecture -> " + urlPrefecture);

			if (getTypeStudiesList().length > 0) { // If many prefectures
													// choosen and many studies
													// choosen
				setUrl("http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=" + getNameUni()
						+ "&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=" + getTypeUniUrl() + getAdmissionMonthUrl()
						+ getDeadLineUrl() + getEjuUrl() + getEngExamUrl() + getAdmissionUniUrl() + getUrlTypeStudies()
						+ "&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search");

				System.out.println("urlStudies -> " + getUrlTypeStudies());
			}
		}
		if (getPrefecture()[0].equals("all")) { // If no prefecture choosen
			setUrl("http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=" + getNameUni() + "&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
					+ getTypeUniUrl() + getAdmissionMonthUrl() + getDeadLineUrl() + getEjuUrl() + getEngExamUrl()
					+ getAdmissionUniUrl() + "&u%5Bac%5D=&a%5Bpf%5D=" + urlPrefecture
					+ "&search.x=177&search.y=11&search=search");

			setPrefecture(getPrefecturesList());

			if (typeStudiesList.length > 0) { // If many studies choosen
				setUrl("http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=" + getNameUni()
						+ "&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=" + getTypeUniUrl() + getAdmissionMonthUrl()
						+ getDeadLineUrl() + getEjuUrl() + getEngExamUrl() + getAdmissionUniUrl() + getUrlTypeStudies()
						+ "&a%5Bpf%5D=" + getUrlPrefecture() + "&search.x=177&search.y=11&search=search");

				System.out.println("urlStudies -> " + getUrlTypeStudies());
			}
		}
		return getUrl();
	}

	/**
	 * @return the name of the uni provided by the client
	 */
	private String getNameUni() {
		return nameUni;
	}

	/**
	 * @param nameUni
	 *            -> set the name of the uni which the urlBuilder is going to
	 *            use
	 */
	private void setNameUni(String nameUni) {
		this.nameUni = nameUni;
	}

	/**
	 * @return the list of type of studies provided
	 */
	public String[] getTypeStudiesList() {
		return typeStudiesList;
	}

	/**
	 * @param typeStudiesList
	 *            -> set the typeStudies string which is going to be used in the
	 *            build of the final url
	 */
	private void setTypeStudiesList(String[] typeStudiesList) {
		this.typeStudiesList = typeStudiesList;
	}

	/**
	 * @return the TypeStudies url string build
	 */
	public String getUrlTypeStudies() {
		return urlTypeStudies;
	}

	/**
	 * @param urlTypeStudies
	 *            -> set the typeStudies url build
	 */
	private void setUrlTypeStudies(String urlTypeStudies) {
		this.urlTypeStudies = urlTypeStudies;
	}

	/**
	 * @return the typeUni url build
	 */
	public String getTypeUniUrl() {
		return typeUniUrl;
	}

	/**
	 * @return the admissionMonth ulr build
	 */
	public String getAdmissionMonthUrl() {
		return admissionMonthUrl;
	}

	/**
	 * @return the deadLine url build
	 */
	public String getDeadLineUrl() {
		return deadLineUrl;
	}

	/**
	 * @return the ejuUrl build
	 */
	public String getEjuUrl() {
		return ejuUrl;
	}

	/**
	 * @return the engExamUrl build
	 */
	public String getEngExamUrl() {
		return engExamUrl;
	}

	/**
	 * @return the admissionUni url build
	 */
	public String getAdmissionUniUrl() {
		return admissionUniUrl;
	}

	/**
	 * @param typeUniUrl
	 *            -> set the typUni url build
	 */
	private void setTypeUniUrl(String typeUniUrl) {
		this.typeUniUrl = typeUniUrl;
	}

	/**
	 * @param admisionMonthUrl
	 *            -> set the admissionMonth url build
	 */
	private void setAdmissionMonthUrl(String admisionMonthUrl) {
		this.admissionMonthUrl = admisionMonthUrl;
	}

	/**
	 * @param deadLineUrl
	 *            -> set the deadLine url build
	 */
	private void setDeadLineUrl(String deadLineUrl) {
		this.deadLineUrl = deadLineUrl;
	}

	/**
	 * @param ejuUrl
	 *            -> set the eju url build
	 */
	private void setEjuUrl(String ejuUrl) {
		this.ejuUrl = ejuUrl;
	}

	/**
	 * @param engExamUrl
	 *            -> set the enExam url build
	 */
	private void setEngExamUrl(String engExamUrl) {
		this.engExamUrl = engExamUrl;
	}

	/**
	 * @param admissionUniUrl
	 *            -> set the admissionUni url build
	 */
	private void setAdmissionUniUrl(String admisionUniUrl) {
		this.admissionUniUrl = admisionUniUrl;
	}

	/**
	 * It build the part the final url related to the typeStudies given a
	 * typeStudies list provided by the client
	 * 
	 * @param typeStudiesList
	 *            -> type of studies list provided by the client
	 * @return a string used to build the final url which is going to be used
	 *         for crawl the search
	 */
	private String buildStudiesUrl(String[] typeStudiesList) {
		String urlStudies = "";
		String text = "";
		for (String i : typeStudiesList) {
			if (i.contains("Literature"))
				text = literatureUrl;
			if (i.contains("Language"))
				text = languageUrl;
			if (i.contains("Law"))
				text = lawUrl;
			if (i.contains("Economics"))
				text = economManagCommercUrl;
			if (i.contains("Sociology"))
				text = sociologyUrl;
			if (i.contains("International"))
				text = internacionalStudUrl;
			if (i.contains("Health Sciences"))
				text = nursingHealtUrl;
			if (i.contains("Medical"))
				text = medicalDentalUrl;
			if (i.contains("Pharmacy"))
				text = pharmacyUrl;
			if (i.contains("Physical Science"))
				text = physicalScienceUrl;
			if (i.contains("Engineering"))
				text = engineeringUrl;
			if (i.contains("Agricultur")) // TODO Take care
				text = agriFishUrl;
			if (i.contains("Education"))
				text = educationUrl;
			if (i.contains("Life"))
				text = lifeScienceUrl;
			if (i.contains("Arts"))
				text = artsUrl;
			if (i.contains("Integrated Science"))
				text = integSciencUrl;

			urlStudies = urlStudies + text + "-";
		}
		urlStudies = urlStudies.substring(0, urlStudies.length() - 1);
		return "&u%5Bac%5D=" + urlStudies;
	}

	/**
	 * It build the part the final url related to the admission months given a
	 * admisionUni list provided by the client
	 * 
	 * @param admisionUni
	 *            -> admission months list provided by the client
	 * @return a string used to build the final url which is going to be used
	 *         for crawl the search
	 */
	private String buildAdmissionUniUrl(String[] admisionUni) {
		String admisionUniUrl = "";
		String text = "";
		for (String i : admisionUni) {
			if (i.equals("appOutsideJap"))
				text = appOutsideJap;
			if (i.equals("appInsideJap"))
				text = appInsideJap;
			admisionUniUrl = admisionUniUrl + text;
		}
		return admisionUniUrl;
	}

	/**
	 * It build the part the final url related to the english exam given a
	 * engExam list with the search parameters provided by the client
	 * 
	 * @param engExam
	 *            -> engExam related parameters list provided by the client
	 * @return a string used to build the final url which is going to be used
	 *         for crawl the search
	 */
	private String buildEngExamUrl(String[] engExam) {
		String engExamUrl = "";
		String text = "";
		for (String i : engExam) {
			if (i.equals("engNotNecessary"))
				text = engNotNecessary;
			if (i.equals("engNecessary"))
				text = engNecessary;
			if (i.equals("engToeflUsed"))
				text = engToeflUsed;
			if (i.equals("engOthers"))
				text = engOthers;
			if (i.equals("engToeflEtcUsed"))
				text = engToeflEtcUsed;
			engExamUrl = engExamUrl + text;
		}
		return engExamUrl;
	}

	/**
	 * It build the part the final url related to the eju exam given a ejuExam
	 * list with the search parameters provided by the client
	 * 
	 * @param engExam
	 *            -> ejuExam related parameters list provided by the client
	 * @return a string used to build the final url which is going to be used
	 *         for crawl the search
	 */
	private String buildEjuExamUrl(String[] eju) {
		String ejuUrl = "";
		String text = "";
		for (String i : eju) {
			if (i.equals("ejuYes"))
				text = ejuYes;
			if (i.equals("ejuNo"))
				text = ejuNo;
			ejuUrl = ejuUrl + text;
		}
		return ejuUrl;
	}

	/**
	 * It build the part the final url related to the dead linea given a
	 * deadLine list with the search parameters provided by the client
	 * 
	 * @param deadLine
	 *            -> deadLine related parameters list provided by the client
	 * @return a string used to build the final url which is going to be used
	 *         for crawl the search
	 */
	private String buildDeadLineUrl(String[] deadLine) {
		String deadLineUrl = "";
		String text = "";
		for (String i : deadLine) {
			if (i.equals("januaryApp"))
				text = januaryApp;
			if (i.equals("februaryApp"))
				text = februaryApp;
			if (i.equals("marchApp"))
				text = marchApp;
			if (i.equals("aprilApp"))
				text = aprilApp;
			if (i.equals("mayApp"))
				text = mayApp;
			if (i.equals("juneApp"))
				text = juneApp;
			if (i.equals("julyApp"))
				text = julyApp;
			if (i.equals("augustApp"))
				text = augustApp;
			if (i.equals("septemberApp"))
				text = septemberApp;
			if (i.equals("octoberApp"))
				text = octoberApp;
			if (i.equals("novemberApp"))
				text = novemberApp;
			if (i.equals("decemberApp"))
				text = decemberApp;
			deadLineUrl = deadLineUrl + text;
		}
		return deadLineUrl;
	}

	/**
	 * It build the part the final url related to the admission months of the
	 * university given a admissionMonths list with the search parameters
	 * provided by the client
	 * 
	 * @param admissionMonth
	 *            -> admission Month related parameters list provided by the
	 *            client
	 * @return a string used to build the final url which is going to be used
	 *         for crawl the search
	 */
	private String buildAdmissionMonthUrl(String[] admissionMonth) {
		String admissionMonthUrl = "";
		String text = "";
		for (String i : admissionMonth) {
			if (i.equals("januaryAdm"))
				text = januaryAdm;
			if (i.equals("marchAdm"))
				text = marchAdm;
			if (i.equals("aprilAdm"))
				text = aprilAdm;
			if (i.equals("mayAdm"))
				text = mayAdm;
			if (i.equals("septemberAdm"))
				text = septemberAdm;
			if (i.equals("octoberAdm"))
				text = octoberAdm;
			admissionMonthUrl = admissionMonthUrl + text;
		}
		return admissionMonthUrl;
	}

	/**
	 * It build the part the final url related to the typeUni given a typeUni
	 * list with the search parameters provided by the client
	 * 
	 * @param typeUni
	 *            -> typeUni related parameters list provided by the client
	 * @return a string used to build the final url which is going to be used
	 *         for crawl the search
	 */
	private String buildTypeUniUrl(String[] typeUni) {
		String typeUniUrl = "";
		String text = "";
		for (String i : typeUni) {
			if (i.equals("national"))
				text = nationalUni;
			if (i.equals("public"))
				text = publicUni;
			if (i.equals("private"))
				text = privateUni;
			if (i.equals("jsfu"))
				text = jsfuUni;
			typeUniUrl = typeUniUrl + text;
		}
		return typeUniUrl;
	}
}

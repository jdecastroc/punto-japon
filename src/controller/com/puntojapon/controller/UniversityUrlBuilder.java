package com.puntojapon.controller;

public class UniversityUrlBuilder {
	// Prefectures in crawler numerical order
	private final String[] prefecturesList = { "Hokkaido", "Aomori", "Iwate", "Miyagi", "Akita", "Yamagata",
			"Fukushima", "Ibaraki", "Tochigi", "Gunma", "Saitama", "Chiba", "Tokyo", "Kanagawa", "Niigata", "Toyama",
			"Ishikawa", "Fukui", "Yamanashi", "Nagano", "Gifu", "Shizuoka", "Aichi", "Mie", "Shiga", "Kyoto", "Osaka",
			"Hyogo", "Nara", "Wakayama", "Tottori", "Shimane", "Okayama", "Hiroshima", "Yamaguchi", "Tokushima",
			"Kagawa", "Ehime", "Kochi", "Fukuoka", "Saga", "Nagasaki", "Kumamoto", "Oita", "Miyazaki", "Kagoshima",
			"Okinawa" };

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
	private String url = "";
	private String[] prefecture;
	private String[] typeStudiesList;

	private String typeUniUrl = "";
	private String admisionMonthUrl = "";
	private String deadLineUrl = "";
	private String ejuUrl = "";
	private String engExamUrl = "";
	private String admisionUniUrl = "";
	private String urlPrefecture = "";
	private String urlTypeStudies = "";
	private String nameUni = "";

	public UniversityUrlBuilder(String nameUni, String[] prefecture, String[] typeStudiesList, String[] typeUni, String[] admisionMonth,
			String[] deadLine, String[] eju, String[] engExam, String[] admisionUni) {
		// Build URL's
		setPrefecture(prefecture);
		setTypeStudiesList(typeStudiesList);

		if (prefecture.length > 0 && !prefecture[0].equals("all"))
			setUrlPrefecture(buildPrefectureUrl(prefecturesList, prefecture));
		if (typeStudiesList.length > 0)
			setUrlTypeStudies(buildStudiesUrl(typeStudiesList));
		if (typeUni.length > 0) // If many type uni
			setTypeUniUrl(buildTypeUniUrl(typeUni));
		if (admisionMonth.length > 0)
			setAdmisionMonthUrl(buildAdmisionMonthUrl(admisionMonth));
		if (deadLine.length > 0)
			setDeadLineUrl(buildDeadLineUrl(deadLine));
		if (eju.length > 0)
			setEjuUrl(buildEjuExamUrl(eju));
		if (engExam.length > 0)
			setEngExamUrl(buildEngExamUrl(engExam));
		if (admisionUni.length > 0)
			setAdmisionUniUrl(buildAdmisionUniUrl(engExam));
		if (typeStudiesList.length > 0)
			setUrlPrefecture(buildPrefectureUrl(prefecturesList, prefecture));
		if (!nameUni.isEmpty())
			setNameUni(nameUni);
			
	}

	public String getSearchUrl(String[] prefecture) {
		if (prefecture.length > 0 && !prefecture[0].equals("all")) { // If many
																		// prefectures
																		// choosen

			setUrl("http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=" + getNameUni() +"&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=" + getTypeUniUrl()
					+ getAdmisionMonthUrl() + getDeadLineUrl() + getEjuUrl() + getEngExamUrl() + getAdmisionUniUrl()
					+ "&u%5Bac%5D=&a%5Bpf%5D=" + getUrlPrefecture() + "&search.x=177&search.y=11&search=search");

			System.out.println("urlPrefecture -> " + urlPrefecture);

			if (getTypeStudiesList().length > 0) { // If many prefectures
													// choosen and many studies
													// choosen
				setUrl("http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D="+ getNameUni() + "&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
						+ getTypeUniUrl() + getAdmisionMonthUrl() + getDeadLineUrl() + getEjuUrl() + getEngExamUrl()
						+ getAdmisionUniUrl() + getUrlTypeStudies() + "&a%5Bpf%5D=" + urlPrefecture
						+ "&search.x=177&search.y=11&search=search");

				System.out.println("urlStudies -> " + getUrlTypeStudies());
			}
		}
		if (getPrefecture()[0].equals("all")) { // If no prefecture choosen
			setUrl("http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=" + getNameUni() + "&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=" + getTypeUniUrl()
					+ getAdmisionMonthUrl() + getDeadLineUrl() + getEjuUrl() + getEngExamUrl() + getAdmisionUniUrl()
					+ "&u%5Bac%5D=&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search");

			setPrefecture(prefecturesList);

			if (typeStudiesList.length > 0) { // If many studies choosen
				setUrl("http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=" + getNameUni() + "&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
						+ getTypeUniUrl() + getAdmisionMonthUrl() + getDeadLineUrl() + getEjuUrl() + getEngExamUrl()
						+ getAdmisionUniUrl() + getUrlTypeStudies() + "&a%5Bpf%5D=" + getUrlPrefecture()
						+ "&search.x=177&search.y=11&search=search");

				System.out.println("urlStudies -> " + getUrlTypeStudies());
			}
		}
		return getUrl();
	}

	private String getNameUni() {
		return nameUni;
	}

	private void setNameUni(String nameUni) {
		this.nameUni = nameUni;
	}

	public String[] getPrefecture() {
		return prefecture;
	}

	public String[] getTypeStudiesList() {
		return typeStudiesList;
	}

	private void setPrefecture(String[] prefecture) {
		this.prefecture = prefecture;
	}

	private void setTypeStudiesList(String[] typeStudiesList) {
		this.typeStudiesList = typeStudiesList;
	}

	public String getUrlTypeStudies() {
		return urlTypeStudies;
	}

	private void setUrlTypeStudies(String urlTypeStudies) {
		this.urlTypeStudies = urlTypeStudies;
	}

	public String getUrl() {
		return url;
	}

	public String getTypeUniUrl() {
		return typeUniUrl;
	}

	public String getAdmisionMonthUrl() {
		return admisionMonthUrl;
	}

	public String getDeadLineUrl() {
		return deadLineUrl;
	}

	public String getEjuUrl() {
		return ejuUrl;
	}

	public String getEngExamUrl() {
		return engExamUrl;
	}

	public String getAdmisionUniUrl() {
		return admisionUniUrl;
	}

	public String getUrlPrefecture() {
		return urlPrefecture;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	private void setTypeUniUrl(String typeUniUrl) {
		this.typeUniUrl = typeUniUrl;
	}

	private void setAdmisionMonthUrl(String admisionMonthUrl) {
		this.admisionMonthUrl = admisionMonthUrl;
	}

	private void setDeadLineUrl(String deadLineUrl) {
		this.deadLineUrl = deadLineUrl;
	}

	private void setEjuUrl(String ejuUrl) {
		this.ejuUrl = ejuUrl;
	}

	private void setEngExamUrl(String engExamUrl) {
		this.engExamUrl = engExamUrl;
	}

	private void setAdmisionUniUrl(String admisionUniUrl) {
		this.admisionUniUrl = admisionUniUrl;
	}

	private void setUrlPrefecture(String urlPrefecture) {
		this.urlPrefecture = urlPrefecture;
	}

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

	private String buildPrefectureUrl(String[] prefecturesList, String[] prefecture) {
		String urlPrefectures = "";
		for (int i = 0; i < prefecturesList.length; i++) {
			for (int k = 0; k < prefecture.length; k++) {
				if (prefecture[k].contains(prefecturesList[i])) {
					int m = i + 1;
					urlPrefectures = urlPrefectures + m + "-";
				}
			}
		}
		urlPrefectures = urlPrefectures.substring(0, urlPrefectures.length() - 1);
		return urlPrefectures;
	}

	private String buildAdmisionUniUrl(String[] admisionUni) {
		String admisionUniUrl = "";
		String text = "";
		for (String i : admisionUni) {
			if (i.equals("appOutsideJap"))
				text = appOutsideJap;
			if (i.equals("appInsideJap"))
				text = appInsideJap;
			if (i.equals("engToeflUsed"))
				text = engToeflUsed;
			if (i.equals("engOthers"))
				text = engOthers;
			if (i.equals("engToeflEtcUsed"))
				text = engToeflEtcUsed;
			admisionUniUrl = admisionUniUrl + text;
		}
		return admisionUniUrl;
	}

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

	private String buildAdmisionMonthUrl(String[] admisionMonth) {
		String admisionMonthUrl = "";
		String text = "";
		for (String i : admisionMonth) {
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
			admisionMonthUrl = admisionMonthUrl + text;
		}
		return admisionMonthUrl;
	}

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

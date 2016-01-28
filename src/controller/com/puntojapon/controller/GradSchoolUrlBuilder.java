package com.puntojapon.controller;

public class GradSchoolUrlBuilder {

	// Prefectures in crawler numerical order
	private final String[] prefecturesList = { "Hokkaido", "Aomori", "Iwate", "Miyagi", "Akita", "Yamagata",
			"Fukushima", "Ibaraki", "Tochigi", "Gunma", "Saitama", "Chiba", "Tokyo", "Kanagawa", "Niigata", "Toyama",
			"Ishikawa", "Fukui", "Yamanashi", "Nagano", "Gifu", "Shizuoka", "Aichi", "Mie", "Shiga", "Kyoto", "Osaka",
			"Hyogo", "Nara", "Wakayama", "Tottori", "Shimane", "Okayama", "Hiroshima", "Yamaguchi", "Tokushima",
			"Kagawa", "Ehime", "Kochi", "Fukuoka", "Saga", "Nagasaki", "Kumamoto", "Oita", "Miyazaki", "Kagoshima",
			"Okinawa" };

	// Type of Study
	private final String nationalGrad = "&g%5Bgt%5D%5B%5D=1";
	private final String publicGrad = "&g%5Bgt%5D%5B%5D=2";
	private final String privateGrad = "&g%5Bgt%5D%5B%5D=3";
	private final String jsfuGrad = "&g%5Bgt%5D%5B%5D=4";

	// Type of course
	private final String master = "&g%5Bcu%5D%5B%5D=1";
	private final String doctoral = "&g%5Bcu%5D%5B%5D=2";
	private final String researchGrad = "&g%5Bcu%5D%5B%5D=3";
	private final String researchUnGrad = "&g%5Bcu%5D%5B%5D=4";
	private final String auditingGrad = "&g%5Bcu%5D%5B%5D=5";
	private final String auditingUnGrad = "&g%5Bcu%5D%5B%5D=6";
	private final String profDegree = "&g%5Bcu%5D%5B%5D=7";

	// Lessons held in english
	private final String englishYes = "&g%5Bet%5D%5B%5D=4";
	private final String englishNo = "&g%5Bet%5D%5B%5D=5";

	// Object variables
	private String url = "";
	private String[] prefecture;

	private String typeGradUrl = "";
	private String typeCourseUrl = "";
	private String englishCourseUrl = "";
	private String urlPrefecture = "";
	private String nameGrad = "";
	
	public GradSchoolUrlBuilder(String nameGrad, String[] prefecture, String[] typeGrad, String[] typeCourse, String[] englishCourse) {
		// Build URL's
		setPrefecture(prefecture);

		if (prefecture.length > 0 && !prefecture[0].equals("all"))
			setUrlPrefecture(buildPrefectureUrl(prefecturesList, prefecture));
		if (typeGrad.length > 0) // If many type grad
			setTypeGradUrl(buildTypeGradUrl(typeGrad));
		if (typeCourse.length > 0)
			setTypeCourseUrl(buildTypeCourseUrl(typeCourse));
		if (englishCourse.length > 0)
			setEnglishCourseUrl(buildEnglishCourseUrl(englishCourse));
		if (!nameGrad.isEmpty())
			setNameGrad(nameGrad);
	}
	

	public String getSearchUrl(String[] prefecture) {
		
		if (prefecture.length > 0 && !prefecture[0].equals("all")) { // If many
																		// prefectures
																		// choosen

			setUrl("http://www.jpss.jp/en/search/?tb=2&a%5Bnm%5D=" + getNameGrad()
					+ "&a%5Bfw%5D=&g%5Brs%5D=&g%5Bmj%5D=&" + getTypeGradUrl() + getTypeCourseUrl()
					+ getEnglishCourseUrl() + "&a%5Bpf%5D=" + getUrlPrefecture()
					+ "&search.x=119&search.y=16&search=search");

			System.out.println("urlPrefecture -> " + urlPrefecture);

		}
		if (getPrefecture()[0].equals("all")) { // If no prefecture choosen
			setUrl("http://www.jpss.jp/en/search/?tb=2&a%5Bnm%5D=" + getNameGrad()
					+ "&a%5Bfw%5D=&g%5Brs%5D=&g%5Bmj%5D=&" + getTypeGradUrl() + getTypeCourseUrl()
					+ getEnglishCourseUrl() + "&a%5Bpf%5D=&search.x=119&search.y=16&search=search");

			setPrefecture(prefecturesList);

		}
		return getUrl();
	}

	private String getUrl() {
		return url;
	}

	private String[] getPrefecture() {
		return prefecture;
	}

	private String getTypeGradUrl() {
		return typeGradUrl;
	}

	private String getTypeCourseUrl() {
		return typeCourseUrl;
	}

	private String getEnglishCourseUrl() {
		return englishCourseUrl;
	}

	private String getUrlPrefecture() {
		return urlPrefecture;
	}

	private String getNameGrad() {
		return nameGrad;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	private void setPrefecture(String[] prefecture) {
		this.prefecture = prefecture;
	}

	private void setTypeGradUrl(String typeGradUrl) {
		this.typeGradUrl = typeGradUrl;
	}

	private void setTypeCourseUrl(String typeCourseUrl) {
		this.typeCourseUrl = typeCourseUrl;
	}

	private void setEnglishCourseUrl(String englishCourseUrl) {
		this.englishCourseUrl = englishCourseUrl;
	}

	private void setUrlPrefecture(String urlPrefecture) {
		this.urlPrefecture = urlPrefecture;
	}

	private void setNameGrad(String nameGrad) {
		this.nameGrad = nameGrad;
	}

	private String buildTypeCourseUrl(String[] typeCourse) {
		String typeCourseUrl = "";
		String text = "";
		for (String i : typeCourse) {
			if (i.equals("master"))
				text = master;
			if (i.equals("doctoral"))
				text = doctoral;
			if (i.equals("researchGrad"))
				text = researchGrad;
			if (i.equals("researchUnGrad"))
				text = researchUnGrad;
			if (i.equals("auditingGrad"))
				text = auditingGrad;
			if (i.equals("auditingUnGrad"))
				text = auditingUnGrad;
			if (i.equals("profDegree"))
				text = profDegree;
			typeCourseUrl = typeCourseUrl + text;
		}
		return typeCourseUrl;
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
		if (!urlPrefectures.equals(""))
			urlPrefectures = urlPrefectures.substring(0, urlPrefectures.length() - 1);
		return urlPrefectures;
	}

	private String buildEnglishCourseUrl(String[] englishCourse) {
		String englishCourseUrl = "";
		String text = "";
		for (String i : englishCourse) {
			if (i.equals("englishYes"))
				text = englishYes;
			if (i.equals("englishNo"))
				text = englishNo;
			englishCourseUrl = englishCourseUrl + text;
		}
		return englishCourseUrl;
	}

	private String buildTypeGradUrl(String[] typeGrad) {
		String typeGradUrl = "";
		String text = "";
		for (String i : typeGrad) {
			if (i.equals("national"))
				text = nationalGrad;
			if (i.equals("public"))
				text = publicGrad;
			if (i.equals("private"))
				text = privateGrad;
			if (i.equals("jsfu"))
				text = jsfuGrad;
			typeGradUrl = typeGradUrl + text;
		}
		return typeGradUrl;
	}

}

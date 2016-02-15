package com.puntojapon.colleges;

public class GradSchoolUrlBuilder extends UrlBuilder {

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

	private String typeGradUrl = "";
	private String typeCourseUrl = "";
	private String englishCourseUrl = "";
	private String nameGrad = "";

	public GradSchoolUrlBuilder(String nameGrad, String[] prefecture, String[] typeGrad, String[] typeCourse,
			String[] englishCourse) {
		// Build URL's
		setPrefecture(prefecture);

		if (prefecture.length > 0 && !prefecture[0].equals("all"))
			setUrlPrefecture(buildPrefectureUrl(getPrefecturesList(), prefecture));
		if (typeGrad.length > 0) // If many type grad
			setTypeGradUrl(buildTypeGradUrl(typeGrad));
		if (typeCourse.length > 0)
			setTypeCourseUrl(buildTypeCourseUrl(typeCourse));
		if (englishCourse.length > 0)
			setEnglishCourseUrl(buildEnglishCourseUrl(englishCourse));
		if (!nameGrad.isEmpty())
			setNameGrad(nameGrad);
	}

	@Override
	public String getSearchUrl(String[] prefecture) {

		if (prefecture.length > 0 && !prefecture[0].equals("all")) { // If many
																		// prefectures
																		// choosen

			setUrl("http://www.jpss.jp/en/search/?tb=2&a%5Bnm%5D=" + getNameGrad()
					+ "&a%5Bfw%5D=&g%5Brs%5D=&g%5Bmj%5D=&" + getTypeGradUrl() + getTypeCourseUrl()
					+ getEnglishCourseUrl() + "&a%5Bpf%5D=" + getUrlPrefecture()
					+ "&search.x=119&search.y=16&search=search");

			System.out.println("urlPrefecture -> " + getUrlPrefecture());

		}
		if (getPrefecture()[0].equals("all")) { // If no prefecture choosen
			setUrl("http://www.jpss.jp/en/search/?tb=2&a%5Bnm%5D=" + getNameGrad()
					+ "&a%5Bfw%5D=&g%5Brs%5D=&g%5Bmj%5D=&" + getTypeGradUrl() + getTypeCourseUrl()
					+ getEnglishCourseUrl() + "&a%5Bpf%5D=&search.x=119&search.y=16&search=search");

			setPrefecture(getPrefecturesList());

		}
		return getUrl();
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

	private String getNameGrad() {
		return nameGrad;
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

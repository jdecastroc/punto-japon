package com.puntojapon.colleges;

public class TechSchoolUrlBuilder extends UrlBuilder {

	private String nameTech = "";

	public TechSchoolUrlBuilder(String nameTech, String[] prefecture) {
		// Build URL's
		setPrefecture(prefecture);

		if (prefecture.length > 0 && !prefecture[0].equals("all"))
			setUrlPrefecture(buildPrefectureUrl(getPrefecturesList(), prefecture));
		if (!nameTech.isEmpty())
			setNameTech(nameTech);
	}

	@Override
	public String getSearchUrl(String[] prefecture) {

		if (getPrefecture()[0].equals("all")) { // If no prefecture choosen
			setUrl("http://www.jpss.jp/en/search/?tb=3&a%5Bnm%5D=" + getNameTech()
					+ "&a%5Bfw%5D=&a%5Bpf%5D=&search.x=0&search.y=0&search=search");
		} else {
			setUrl("http://www.jpss.jp/en/search/?tb=3&a%5Bnm%5D=" + getNameTech() + "&a%5Bfw%5D=&a%5Bpf%5D="
					+ getUrlPrefecture() + "&search.x=0&search.y=0&search=search");
		}
		return getUrl();
	}

	private String getNameTech() {
		return nameTech;
	}

	private void setNameTech(String nameTech) {
		this.nameTech = nameTech;
	}

}

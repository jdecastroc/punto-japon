package com.puntojapon.colleges;

public abstract class UrlBuilder {
	
	// Prefectures in crawler numerical order
	protected static final String[] PREFECTURES_LIST = { "Hokkaido", "Aomori", "Iwate", "Miyagi", "Akita", "Yamagata",
			"Fukushima", "Ibaraki", "Tochigi", "Gunma", "Saitama", "Chiba", "Tokyo", "Kanagawa", "Niigata", "Toyama",
			"Ishikawa", "Fukui", "Yamanashi", "Nagano", "Gifu", "Shizuoka", "Aichi", "Mie", "Shiga", "Kyoto", "Osaka",
			"Hyogo", "Nara", "Wakayama", "Tottori", "Shimane", "Okayama", "Hiroshima", "Yamaguchi", "Tokushima",
			"Kagawa", "Ehime", "Kochi", "Fukuoka", "Saga", "Nagasaki", "Kumamoto", "Oita", "Miyazaki", "Kagoshima",
			"Okinawa" };
	
	protected String url = "";
	protected String[] prefecture;
	protected String urlPrefecture = "";
	
	protected String[] getPrefecturesList(){
		return UrlBuilder.PREFECTURES_LIST;
	}
	
	protected String getUrl() {
		return url;
	}

	protected String[] getPrefecture() {
		return prefecture;
	}
	
	protected String getUrlPrefecture() {
		return urlPrefecture;
	}
	
	protected void setUrlPrefecture(String urlPrefecture) {
		this.urlPrefecture = urlPrefecture;
	}
	
	protected void setUrl(String url) {
		this.url = url;
	}

	protected void setPrefecture(String[] prefecture) {
		this.prefecture = prefecture;
	}
	
	public abstract String getSearchUrl(String[] prefecture);
	
	protected String buildPrefectureUrl(String[] prefecturesList, String[] prefecture) {
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

}

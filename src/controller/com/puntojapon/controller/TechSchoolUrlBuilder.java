package com.puntojapon.controller;

public class TechSchoolUrlBuilder {
	
	// Prefectures in crawler numerical order
		private final String[] prefecturesList = { "Hokkaido", "Aomori", "Iwate", "Miyagi", "Akita", "Yamagata",
				"Fukushima", "Ibaraki", "Tochigi", "Gunma", "Saitama", "Chiba", "Tokyo", "Kanagawa", "Niigata", "Toyama",
				"Ishikawa", "Fukui", "Yamanashi", "Nagano", "Gifu", "Shizuoka", "Aichi", "Mie", "Shiga", "Kyoto", "Osaka",
				"Hyogo", "Nara", "Wakayama", "Tottori", "Shimane", "Okayama", "Hiroshima", "Yamaguchi", "Tokushima",
				"Kagawa", "Ehime", "Kochi", "Fukuoka", "Saga", "Nagasaki", "Kumamoto", "Oita", "Miyazaki", "Kagoshima",
				"Okinawa" };
		
		private String url = "";
		private String[] prefecture;
		private String nameTech= "";
		private String urlPrefecture = "";
		
		public TechSchoolUrlBuilder(String nameTech, String[] prefecture) {
			// Build URL's
			setPrefecture(prefecture);

			if (prefecture.length > 0 && !prefecture[0].equals("all"))
				setUrlPrefecture(buildPrefectureUrl(prefecturesList, prefecture));
			if (!nameTech.isEmpty())
				setNameTech(nameTech);
		}
		
		public String getSearchUrl(String[] prefecture) {
			
			//http://www.jpss.jp/en/search/?tb=3&a%5Bnm%5D=tokyo&a%5Bfw%5D=&a%5Bpf%5D=&search.x=0&search.y=0&search=search
			setUrl("http://www.jpss.jp/en/search/?tb=3&a%5Bnm%5D=" + getNameTech() + "&a%5Bfw%5D=&a%5Bpf%5D=&search.x=0&search.y=0&search=search");
			return getUrl();
		}

		private String getUrl() {
			return url;
		}

		private String[] getPrefecture() {
			return prefecture;
		}

		private String getNameTech() {
			return nameTech;
		}

		private String getUrlPrefecture() {
			return urlPrefecture;
		}

		private void setUrl(String url) {
			this.url = url;
		}

		private void setPrefecture(String[] prefecture) {
			this.prefecture = prefecture;
		}

		private void setNameTech(String nameTech) {
			this.nameTech = nameTech;
		}

		private void setUrlPrefecture(String urlPrefecture) {
			this.urlPrefecture = urlPrefecture;
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

}

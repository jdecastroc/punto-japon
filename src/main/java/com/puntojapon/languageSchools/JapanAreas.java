package com.puntojapon.languageSchools;

public class JapanAreas {
	
	public static String areaTranslator (String englishArea) {
		String japaneseArea = "";
		
		switch(englishArea) {
		
		//Hokkaido . Tohoku
		case "hokkaido": japaneseArea = "北海道"; break;
		case "aomori": japaneseArea = "青森"; break;
		case "iwate": japaneseArea = "岩手"; break;
		case "miyagi": japaneseArea = "宮城"; break;
		case "akita": japaneseArea = "秋田"; break;
		case "yamagata": japaneseArea = "山形"; break;
		case "fukusima": japaneseArea = "福島"; break;
		
		//Kanto . Koshin-Etsu
		case "ibaraki": japaneseArea = "茨城"; break;
		case "tochigi": japaneseArea = "栃木"; break;
		case "gunma": japaneseArea = "群馬"; break;
		case "saitama": japaneseArea = "埼玉"; break;
		case "chiba": japaneseArea = "千葉"; break;
		case "kanagawa": japaneseArea = "神奈川"; break;
		case "yamanashi": japaneseArea = "山梨"; break;
		case "nagano": japaneseArea = "長野"; break;
		case "niigata": japaneseArea = "新潟"; break;
		
		//All the Tokyo area
		case "tokyo": japaneseArea = "東京都"; break;
		
		//Tokyo quarters
		case "chiyoda": japaneseArea = "東京都千代田"; break;
		case "chuo": japaneseArea = "東京都中央"; break;
		case "minato": japaneseArea = "東京都港"; break;
		case "shinjuku": japaneseArea = "東京都新宿"; break;
		case "bunkyo": japaneseArea = "東京都文京"; break;
		case "taito": japaneseArea = "東京都台東"; break;
		case "sumida": japaneseArea = "東京都墨田"; break;
		case "koto": japaneseArea = "東京都江東"; break;
		case "shinagawa": japaneseArea = "東京都品川"; break;
		case "meguro": japaneseArea = "東京都目黒"; break;
		case "ota": japaneseArea = "東京都大田"; break;
		case "setagaya": japaneseArea = "東京都世田谷"; break;
		case "shibuya": japaneseArea = "東京都渋谷"; break;
		case "nakano": japaneseArea = "東京都中野"; break;
		case "suginami": japaneseArea = "東京都杉並"; break;
		case "toshima": japaneseArea = "東京都豊島"; break;
		case "kita": japaneseArea = "東京都北"; break;
		case "arakawa": japaneseArea = "東京都荒川"; break;
		case "itabashi": japaneseArea = "東京都板橋"; break;
		case "nerima": japaneseArea = "東京都練馬"; break;
		case "adachi": japaneseArea = "東京都足立"; break;
		case "katsushika": japaneseArea = "東京都葛飾"; break;
		case "edogawa": japaneseArea = "東京都江戸川"; break;
		case "mitaka": japaneseArea = "東京都三鷹市"; break;
		case "musashino": japaneseArea = "東京都武蔵野市"; break;
		case "hachioji": japaneseArea = "東京都八王子市"; break;
		case "hino": japaneseArea = "東京都日野市"; break;
		case "kiyose": japaneseArea = "東京都清瀬市"; break;
		case "fussa": japaneseArea = "東京都福生市"; break;
		
		//Tokai . Hokuriku	
		case "toyama": japaneseArea = "富山"; break;
		case "ishikawa": japaneseArea = "石川"; break;
		case "fukui": japaneseArea = "福井"; break;
		case "gifu": japaneseArea = "岐阜"; break;
		case "shizuoka": japaneseArea = "静岡"; break;
		case "aichi": japaneseArea = "愛知"; break;
		case "mie": japaneseArea = "三重"; break;
		
		//Kinki
		case "shiga": japaneseArea = "滋賀"; break;
		case "kyoto": japaneseArea = "京都"; break;
		case "osaka": japaneseArea = "大阪"; break;
		case "hyogo": japaneseArea = "兵庫"; break;
		case "nara": japaneseArea = "奈良"; break;
		case "wakayama": japaneseArea = "和歌山"; break;

		//Chugoku . Shikoku		
		case "tottori": japaneseArea = "鳥取"; break;
		case "shimane": japaneseArea = "島根"; break;
		case "okayama": japaneseArea = "岡山"; break;
		case "hiroshima": japaneseArea = "広島"; break;
		case "yamaguchi": japaneseArea = "山口"; break;
		case "tokushima": japaneseArea = "徳島"; break;
		case "kagawa": japaneseArea = "香川"; break;
		case "ehime": japaneseArea = "愛媛"; break;
		case "kochi": japaneseArea = "高知"; break;

		//Kyousyu . Okinawa
		case "fukuoka": japaneseArea = "福岡"; break;
		case "saga": japaneseArea = "佐賀"; break;
		case "nagasaki": japaneseArea = "長崎"; break;
		case "kumamoto": japaneseArea = "熊本"; break;
		case "oita": japaneseArea = "大分"; break;
		case "miyazaki": japaneseArea = "宮崎"; break;
		case "kagoshima": japaneseArea = "鹿児島"; break;
		case "okinawa": japaneseArea = "沖縄"; break;
		
		}
		
		return japaneseArea;
	}

}

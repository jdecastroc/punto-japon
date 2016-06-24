package com.puntojapon.live;

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.puntojapon.common.RandomUserAgent;

public class HousesCrawler {

	private static final String[] PREFECTURES_LIST = { "hokkaido", "aomori", "iwate", "miyagi", "akita", "yamagata",
			"fukushima", "ibaraki", "tochigi", "gunma", "saitama", "chiba", "tokyo", "kanagawa", "niigata", "toyama",
			"ishikawa", "fukui", "yamanashi", "nagano", "gifu", "shizuoka", "aichi", "mie", "shiga", "kyoto", "osaka",
			"hyogo", "nara", "wakayama", "tottori", "shimane", "okayama", "hiroshima", "yamaguchi", "tokushima",
			"kagawa", "ehime", "kochi", "fukuoka", "saga", "nagasaki", "kumamoto", "oita", "miyazaki", "kagoshima",
			"okinawa" };

	public String getHouses(int page, String prefectureS, String minPriceS, String maxPriceS, String minSizeS,
			String minLayoutS, String distanceStationS, String buildingTypeS, String buildingAgeS,
			String lowInitialCostsS, String noGuarantorS, String noKeyMoneyS, String petsNegotiableS, String noDepositS,
			String shortTermS, String noAgencyFeeS, String furnishedS, String internetS, String wifiS,
			String creditCardPaymentS) throws IOException {

		String jsonHouses = "";

		// Jobs list
		HouseList houseList = new HouseList();
		houseList.setSearchType("Houses search");
		houseList.setPage(page);
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		if (Arrays.asList(PREFECTURES_LIST).contains(prefectureS.trim().toLowerCase()) && page >= 0) {

			// Info of each house
			String buildingType = "";
			String place = "";
			String rent = "";
			String size = "";
			String deposit = "";
			String keyMoney = "";
			String floor = "";
			String maintenanceFee = "";
			String nearestStation = "";
			String agent = "";
			String imageUrl = "";
			String map = "";

			// BUILD URL

			if (!minPriceS.equals(""))
				minPriceS = "&min_price=" + minPriceS;
			if (!maxPriceS.equals(""))
				maxPriceS = "&max_price=" + maxPriceS;
			if (!minSizeS.equals(""))
				minSizeS = "&min_meter=" + minSizeS;
			if (!minLayoutS.equals(""))
				minLayoutS = "&rooms=" + minLayoutS;
			if (!distanceStationS.equals(""))
				distanceStationS = "&distance_station=" + distanceStationS;
			if (!buildingTypeS.equals(""))
				buildingTypeS = "&building_type=" + buildingTypeS;
			if (!buildingAgeS.equals(""))
				buildingAgeS = "&building_age=" + buildingAgeS;
			if (!lowInitialCostsS.equals(""))
				lowInitialCostsS = "&low_initial_costs=" + lowInitialCostsS;
			if (!noGuarantorS.equals(""))
				noGuarantorS = "&no_guarantor=" + noGuarantorS;
			if (!noKeyMoneyS.equals(""))
				noKeyMoneyS = "&no_key_money=" + noKeyMoneyS;
			if (!petsNegotiableS.equals(""))
				petsNegotiableS = "&pets=" + petsNegotiableS;
			if (!noDepositS.equals(""))
				noDepositS = "&no_deposit=" + noDepositS;
			if (!shortTermS.equals(""))
				shortTermS = "&short_term_ok=" + shortTermS;
			if (!noAgencyFeeS.equals(""))
				noAgencyFeeS = "&no_agency_fee=" + noAgencyFeeS;
			if (!furnishedS.equals(""))
				furnishedS = "&furnished=" + furnishedS;
			if (!internetS.equals(""))
				internetS = "&internet=" + internetS;
			if (!wifiS.equals(""))
				wifiS = "&wifi=" + wifiS;
			if (!creditCardPaymentS.equals(""))
				creditCardPaymentS = "&credit_card=" + creditCardPaymentS;

			String url = "https://www.realestate.co.jp/en/rent/listing?page=" + page + "&prefecture=" + prefectureS
					+ minPriceS + maxPriceS + minSizeS + minLayoutS + distanceStationS + buildingTypeS + buildingAgeS
					+ lowInitialCostsS + noGuarantorS + noKeyMoneyS + petsNegotiableS + noDepositS + shortTermS
					+ noAgencyFeeS + furnishedS + internetS + wifiS + creditCardPaymentS;

			System.out.println("Voy a -> " + url);

			Document document = Jsoup.connect(url).userAgent(RandomUserAgent.getRandomUserAgent())
					.timeout((int) Math.random() * 5).get();

			Elements paginationObjects = document.select("li.pagination-next > a");

			// Check whether there is a next page
			for (Element paginationObject : paginationObjects) {
				if (paginationObject.attr("data-target") != null) {
					houseList.setHasNextPage(true);
				} else {
					houseList.setHasNextPage(false);
				}
			}

			Elements propertySource = document.select("div.property-listing");

			for (Element property : propertySource) {

				// Building type
				if (property
						.select("div.listing-body >  div.listing-right-col > div.listing-item.listing-title > a > span.text-semi-strong")
						.first() != null) {
					buildingType = property
							.select("div.listing-body >  div.listing-right-col > div.listing-item.listing-title > a > span.text-semi-strong")
							.first().text().trim();
				}

				// Place
				if (property.select("div.listing-body >  div.listing-right-col > div.listing-item.listing-title > span")
						.attr("itemprop", "address").first() != null) {
					place = property
							.select("div.listing-body >  div.listing-right-col > div.listing-item.listing-title > span")
							.attr("itemprop", "address").first().text().trim();
				}

				// Rent
				if (property.select("div.listing-body >  div.listing-right-col > div.listing-item").get(1) != null) {
					rent = property.select("div.listing-body >  div.listing-right-col > div.listing-item").get(1).text()
							.trim();
				}

				// Size
				if (property.select("div.listing-body >  div.listing-right-col > div.listing-item").get(2) != null) {
					size = property.select("div.listing-body >  div.listing-right-col > div.listing-item").get(2).text()
							.trim();
				}

				// Deposit
				if (property.select("div.listing-body >  div.listing-info >  div.listing-right-col > div.listing-item")
						.get(0) != null) {
					deposit = property
							.select("div.listing-body >  div.listing-info >  div.listing-right-col > div.listing-item")
							.get(0).text().trim();
				}

				// Key money
				if (property.select("div.listing-body >  div.listing-info >  div.listing-right-col > div.listing-item")
						.get(1).select("span").get(1) != null) {
					keyMoney = property
							.select("div.listing-body >  div.listing-info >  div.listing-right-col > div.listing-item")
							.get(1).select("span").get(1).text().trim();
				}

				// Floor
				if (property.select("div.listing-body >  div.listing-info >  div.listing-right-col > div.listing-item")
						.get(2) != null) {
					floor = property
							.select("div.listing-body >  div.listing-info >  div.listing-right-col > div.listing-item")
							.get(2).text().trim();
				}

				// Maintenance Fee
				if (property.select("div.listing-body >  div.listing-info >  div.listing-right-col > div.listing-item")
						.get(3) != null) {
					maintenanceFee = property
							.select("div.listing-body >  div.listing-info >  div.listing-right-col > div.listing-item")
							.get(3).text().trim();
				}

				// Nearest Station
				if (property.select("div.listing-body >  div.listing-info >  div.listing-right-col > div.listing-item")
						.get(4) != null) {
					nearestStation = property
							.select("div.listing-body >  div.listing-info >  div.listing-right-col > div.listing-item")
							.get(4).select("span").get(1).text().trim();
					nearestStation += "  " + property
							.select("div.listing-body >  div.listing-info >  div.listing-right-col > div.listing-item")
							.get(4).select("small").get(0).text().trim();
				}

				// Agent
				if (property.select(
						"div.listing-body >  div.listing-info >  div.listing-left-col > div.listing-logo > a > span.text-xsmall > small") != null) {
					agent = property
							.select("div.listing-body >  div.listing-info >  div.listing-left-col > div.listing-logo > a > span.text-xsmall > small")
							.text().trim();
				}

				// Image URL LIZAR MANUEL BECERRA

				if (property.select("div.listing-body > div.listing-left-col > a > img") != null) {
					imageUrl = "https://www.realestate.co.jp"
							+ property.select("div.listing-body > div.listing-left-col > a > img").attr("src");
				}

				// TODO Map
				if (property.select("div.rej-map-container") != null) {
					map = property.select("div.rej-map-container").attr("data-address");
				}

				houseList.addHouse(new House(buildingType, place, rent, size, deposit, keyMoney, floor, maintenanceFee,
						nearestStation, agent, imageUrl, map));
				houseList.setSearchFound(houseList.getSearchFound() + 1);

			}
		} else {
			houseList.setSearchState(false);
		}

		if (houseList.getSearchFound() > 0) {
			houseList.setSearchState(true);
		} else {
			houseList.setSearchState(false);
		}
		jsonHouses = gson.toJson(houseList);
		return jsonHouses;

	}

}

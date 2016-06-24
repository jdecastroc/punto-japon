package com.puntojapon.live;

public class House {

	private String buildingType;
	private String place;
	private String rent;
	private String size;
	private String deposit;
	private String keyMoney;
	private String floor;
	private String maintenanceFee;
	private String nearestStation;
	private String agent;
	private String imageUrl;
	private String map;

	public House(String buildingType, String place, String rent, String size, String deposit, String keyMoney, String floor,
			String maintenanceFee, String nearestStation, String agent, String imageUrl, String map) {
		
		setBuildingType(buildingType);
		setPlace(place);
		setRent(rent);
		setSize(size);
		setDeposit(deposit);
		setKeyMoney(keyMoney);
		setFloor(floor);
		setMaintenanceFee(maintenanceFee);
		setNearestStation(nearestStation);
		setAgent(agent);
		setImageUrl(imageUrl);
		setMap(map);

	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public void setKeyMoney(String keyMoney) {
		this.keyMoney = keyMoney;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public void setMaintenanceFee(String maintenanceFee) {
		this.maintenanceFee = maintenanceFee;
	}

	public void setNearestStation(String nearestStation) {
		this.nearestStation = nearestStation;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setMap(String map) {
		this.map = map;
	}

}

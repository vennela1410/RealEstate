package com.softwareverification.realestate.models.request;

public class SearchRequest {

	private String houseOwner;
	private String houseType;
	private String city;
	private String state;
	private int countofTimesHouseSold;
	private String boughtType;
	private String condition1;
	private String priceScale;
	private int floors;
	private int bedRoomCount;
	private String totalbaths;
	private String availabilityStatus;
	private String areaCovered;
	private String builtYear;
	private String agentID;

	private String zipCode;

	private String priceScaleTo;
	public String getAgentID() {
		return agentID;
	}
	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}
	public String getHouseOwner() {
		return houseOwner;
	}
	public void setHouseOwner(String houseOwner) {
		this.houseOwner = houseOwner;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getCountofTimesHouseSold() {
		return countofTimesHouseSold;
	}
	public void setCountofTimesHouseSold(int countofTimesHouseSold) {
		this.countofTimesHouseSold = countofTimesHouseSold;
	}
	public String getBoughtType() {
		return boughtType;
	}
	public void setBoughtType(String boughtType) {
		this.boughtType = boughtType;
	}
	public String getCondition1() {
		return condition1;
	}
	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}
	public String getPriceScale() {
		return priceScale;
	}
	public void setPriceScale(String priceScale) {
		this.priceScale = priceScale;
	}
	public int getFloors() {
		return floors;
	}
	public void setFloors(int floors) {
		this.floors = floors;
	}
	public int getBedRoomCount() {
		return bedRoomCount;
	}
	public void setBedRoomCount(int bedRoomCount) {
		this.bedRoomCount = bedRoomCount;
	}
	public String getTotalbaths() {
		return totalbaths;
	}
	public void setTotalbaths(String totalbaths) {
		this.totalbaths = totalbaths;
	}
	public String getAvailabilityStatus() {
		return availabilityStatus;
	}
	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	public String getAreaCovered() {
		return areaCovered;
	}
	public void setAreaCovered(String areaCovered) {
		this.areaCovered = areaCovered;
	}
	public String getBuiltYear() {
		return builtYear;
	}
	public void setBuiltYear(String builtYear) {
		this.builtYear = builtYear;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPriceScaleTo() {
		return priceScaleTo;
	}

	public void setPriceScaleTo(String priceScaleTo) {
		this.priceScaleTo = priceScaleTo;
	}
}

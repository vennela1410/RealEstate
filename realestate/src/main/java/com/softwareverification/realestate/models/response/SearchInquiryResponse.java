package com.softwareverification.realestate.models.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SearchInquiryResponse {

	private String houseOwner;
	private int houseId;
	private String houseType;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String county;
	private String floors;
	private String bathrooms;
	private String bedrooms;
	private String landSize;
	private String yearConstructed;
	private String price;
	private String floorSpace;
	private String status;
	private String ownerFirstName;
	private String ownerLastName;
	private String applianceName;
	private String makeYear;
	private String agentFirstName;
	private String agentLastName;
	private String totalCommission;
	
	public String getHouseOwner() {
		return houseOwner;
	}
	public void setHouseOwner(String houseOwner) {
		this.houseOwner = houseOwner;
	}
	public int getHouseId() {
		return houseId;
	}
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getFloors() {
		return floors;
	}
	public void setFloors(String floors) {
		this.floors = floors;
	}
	public String getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(String bathrooms) {
		this.bathrooms = bathrooms;
	}
	public String getLandSize() {
		return landSize;
	}
	public void setLandSize(String landSize) {
		this.landSize = landSize;
	}
	public String getYearConstructed() {
		return yearConstructed;
	}
	public void setYearConstructed(String yearConstructed) {
		this.yearConstructed = yearConstructed;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getFloorSpace() {
		return floorSpace;
	}
	public void setFloorSpace(String floorSpace) {
		this.floorSpace = floorSpace;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOwnerFirstName() {
		return ownerFirstName;
	}
	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}
	public String getOwnerLastName() {
		return ownerLastName;
	}
	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}
	public String getApplianceName() {
		return applianceName;
	}
	public void setApplianceName(String applianceName) {
		this.applianceName = applianceName;
	}
	public String getMakeYear() {
		return makeYear;
	}
	public void setMakeYear(String makeYear) {
		this.makeYear = makeYear;
	}
	public String getAgentFirstName() {
		return agentFirstName;
	}
	public void setAgentFirstName(String agentFirstName) {
		this.agentFirstName = agentFirstName;
	}
	public String getAgentLastName() {
		return agentLastName;
	}
	public void setAgentLastName(String agentLastName) {
		this.agentLastName = agentLastName;
	}
	public String getTotalCommission() {
		return totalCommission;
	}
	public void setTotalCommission(String totalCommission) {
		this.totalCommission = totalCommission;
	}
	public String getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(String bedrooms) {
		this.bedrooms = bedrooms;
	}
	
	
	
}

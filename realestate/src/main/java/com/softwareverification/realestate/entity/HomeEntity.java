package com.softwareverification.realestate.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="home")
@Data
public class HomeEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088165970403536920L;

	@Id 
	@Column(name="HomeID")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id; 

	@Column(name = "owner")
	private String houseOwner;
	
	@Column(name = "Floorspace")
	private String floorSpace;
	
	@Column(name = "rate")
	private String houseRate;
	
	@Column(name = "Floors")
	private int floors;
	
	@Column(name = "Bathrooms")
	private String totalbaths;
	
	@Column(name = "Bedrooms")
	private int bedRoomCount;
	
	@Column(name = "Landsize")
	private String areaCovered;
	
	@Column(name = "Yearconstructed")
	private int builtYear;
	
	@Column(name = "Hometype")
	private String houseType;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "availabilitystatus")
	private String availabilityStatus;
	
	@Column(name = "AgentID")
	private int agentId;
	
	@Column(name = "Address_id")
	private int addressId;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name = "Address_id")
	private AddressEntity address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHouseOwner() {
		return houseOwner;
	}

	public void setHouseOwner(String houseOwner) {
		this.houseOwner = houseOwner;
	}

	public String getFloorSpace() {
		return floorSpace;
	}

	public void setFloorSpace(String floorSpace) {
		this.floorSpace = floorSpace;
	}

	public String getHouseRate() {
		return houseRate;
	}

	public void setHouseRate(String houseRate) {
		this.houseRate = houseRate;
	}

	public int getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}

	public String getTotalbaths() {
		return totalbaths;
	}

	public void setTotalbaths(String totalbaths) {
		this.totalbaths = totalbaths;
	}

	public int getBedRoomCount() {
		return bedRoomCount;
	}

	public void setBedRoomCount(int bedRoomCount) {
		this.bedRoomCount = bedRoomCount;
	}

	public String getAreaCovered() {
		return areaCovered;
	}

	public void setAreaCovered(String areaCovered) {
		this.areaCovered = areaCovered;
	}

	public int getBuiltYear() {
		return builtYear;
	}

	public void setBuiltYear(int builtYear) {
		this.builtYear = builtYear;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
}

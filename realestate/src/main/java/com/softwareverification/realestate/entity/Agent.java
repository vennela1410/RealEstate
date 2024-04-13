package com.softwareverification.realestate.entity;



import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="agent")
@Data
public class Agent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1908015663832035719L;

	@Id 
	@Column(name="AgentID")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id; 

	@Column(name = "AgentFName")
	private String agentFirstName;
	
	@Column(name = "AgentLName")
	private String agentLastName;
	
	@Column(name = "address")
	private String agentAddress;
	
	@Column(name = "contact")
	private String agentContact;
	
	@Column(name = "email")
	private String agentEmail;
	
	@Column(name = "zip")
	private int zipCode;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "State")
	private String state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getAgentAddress() {
		return agentAddress;
	}

	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
	}

	public String getAgentContact() {
		return agentContact;
	}

	public void setAgentContact(String agentContact) {
		this.agentContact = agentContact;
	}

	public String getAgentEmail() {
		return agentEmail;
	}

	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
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
	
	
}

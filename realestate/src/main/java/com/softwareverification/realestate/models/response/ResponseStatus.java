package com.softwareverification.realestate.models.response;

import java.util.List;

import com.softwareverification.realestate.entity.HomeEntity;
import com.softwareverification.realestate.entity.HomeOwnerEntity;

import lombok.Data;

@Data
public class ResponseStatus {
	
	public String message;
	public int statusCode;
	public int id;
	public List<SearchInquiryResponse> obj;
	public HomeEntity homeDetails;
	public HomeOwnerEntity homeOwnerDetails;
	public Object object;
	
	public ResponseStatus(String message, int statusCode, int id) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.id = id;
	}
	
	public ResponseStatus(String message, int statusCode, int id, List<SearchInquiryResponse> obj) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.id = id;
		this.obj = obj;
	}

	public ResponseStatus(String message, int statusCode, int id, Object obj) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.id = id;
		this.object = obj;
	}
	
	public ResponseStatus(String message, int statusCode, int id, HomeEntity homeDetails) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.id = id;
		this.homeDetails = homeDetails;
	}
	
	public ResponseStatus(String message, int statusCode, int id, HomeOwnerEntity homeOwnerDetails) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.id = id;
		this.homeOwnerDetails = homeOwnerDetails;
	}

}

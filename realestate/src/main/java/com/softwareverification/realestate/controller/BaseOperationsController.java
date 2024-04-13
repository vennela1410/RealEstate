package com.softwareverification.realestate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softwareverification.realestate.entity.Agent;
import com.softwareverification.realestate.entity.HomeEntity;
import com.softwareverification.realestate.entity.HomeOwnerEntity;
import com.softwareverification.realestate.models.request.SearchRequest;
import com.softwareverification.realestate.models.response.ResponseStatus;
import com.softwareverification.realestate.models.response.SearchInquiryResponse;
import com.softwareverification.realestate.service.CommnOperationsService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class BaseOperationsController {

	@Autowired CommnOperationsService commOpsServ;
	
	@PostMapping(value = "/realEstate/addNewAgent", consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStatus> addNewUser(@RequestBody Agent agent) {
		 
		try {
			Agent newAgent = commOpsServ.createAgent(agent);
			if(newAgent.getId() != 0 ) {
				return new ResponseEntity<>(new ResponseStatus("Success",200,newAgent.getId()),HttpStatus.OK);
			}
			throw new Exception("Error occured while creating agent");
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/realEstate/saveHomeDetails", consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStatus> saveorUpdateHome(@RequestBody HomeEntity home) {
		 
		try {
			HomeEntity savedResp = commOpsServ.saveorUpdateHome(home);
			if(savedResp.getId() != 0 ) {
				return new ResponseEntity<>(new ResponseStatus("Success",200,savedResp.getId()),HttpStatus.OK);
			}
			throw new Exception("Error occured while creating agent");
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/realEstate/deleteHomeDetails", consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStatus> deleteHomeDetails(@RequestBody HomeEntity home) {
		 
		try {
			boolean savedResp = commOpsServ.deleteHomeDetails(home);
			if(savedResp) {
				return new ResponseEntity<>(new ResponseStatus("Success",200,0),HttpStatus.OK);
			}
			throw new Exception("Error occured while creating agent");
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/realEstate/saveHomeOwners", consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStatus> saveorUpdateHomeOwners(@RequestBody HomeOwnerEntity homeOwner) {
		 
		try {
			HomeOwnerEntity savedResp = commOpsServ.saveorUpdateHomeOwners(homeOwner);
			if(savedResp.getId() != 0 ) {
				return new ResponseEntity<>(new ResponseStatus("Success",200,savedResp.getId()),HttpStatus.OK);
			}
			throw new Exception("Error occured while creating agent");
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/realEstate/searchRequest", consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStatus> search(@RequestBody SearchRequest request) {
		 
		try {
			List<SearchInquiryResponse> obj = commOpsServ.searchInquiry(request);
			return new ResponseEntity<>(new ResponseStatus("Success",200,0,obj),HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/realEstate/fetchHomeDetails")
	public ResponseEntity<ResponseStatus> fetchHomeDetailsbyId(@RequestParam(name = "homeId") int homeId) {
		try {
			HomeEntity homeresp = commOpsServ.fetchHomeDetails(homeId);
			return new ResponseEntity<>(new ResponseStatus("Success",200,0,homeresp),HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}

	@GetMapping(value = "/realEstate/findAgent")
	public ResponseEntity<ResponseStatus> findAgent(@RequestParam(name = "fName") String fName,
													@RequestParam(name = "lName") String lName) {
		try {
			List<Agent> agents = commOpsServ.findAgent(fName, lName);
			return new ResponseEntity<>(new ResponseStatus("Success",200,0,agents),HttpStatus.OK);

		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/realEstate/fetchHomeOwnerDetails")
	public ResponseEntity<ResponseStatus> fetchHomeOwnerDetailsbyId(@RequestParam(name = "ownerId") int ownerId) {
		try {
			HomeOwnerEntity homeresp = commOpsServ.fetchHomeOwnerDetails(ownerId);
			return new ResponseEntity<>(new ResponseStatus("Success",200,0,homeresp),HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}
	
}


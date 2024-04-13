package com.softwareverification.realestate.service;

import java.util.List;

import com.softwareverification.realestate.entity.Agent;
import com.softwareverification.realestate.entity.HomeEntity;
import com.softwareverification.realestate.entity.HomeOwnerEntity;
import com.softwareverification.realestate.models.request.SearchRequest;
import com.softwareverification.realestate.models.response.SearchInquiryResponse;

public interface CommnOperationsService {

	public Agent createAgent(Agent agent) throws Exception;

	public List<Agent> findAgent(String fName, String lName) throws Exception;

	public List<SearchInquiryResponse> searchInquiry(SearchRequest request) throws Exception;

	public HomeEntity saveorUpdateHome(HomeEntity home) throws Exception;
	
	public HomeEntity fetchHomeDetails(int homeID) throws Exception;

	public HomeOwnerEntity saveorUpdateHomeOwners(HomeOwnerEntity homeOwner) throws Exception;

	public boolean deleteHomeDetails(HomeEntity home) throws Exception;

	public HomeOwnerEntity fetchHomeOwnerDetails(int ownerId) throws Exception;
}

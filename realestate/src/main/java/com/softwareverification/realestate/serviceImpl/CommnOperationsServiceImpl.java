package com.softwareverification.realestate.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.softwareverification.realestate.entity.AddressEntity;
import com.softwareverification.realestate.entity.Agent;
import com.softwareverification.realestate.entity.HomeEntity;
import com.softwareverification.realestate.entity.HomeOwnerEntity;
import com.softwareverification.realestate.models.request.SearchRequest;
import com.softwareverification.realestate.models.response.SearchInquiryResponse;
import com.softwareverification.realestate.repository.AgentRepository;
import com.softwareverification.realestate.repository.HomeRepository;
import com.softwareverification.realestate.service.CommnOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.softwareverification.realestate.repository.AddressRepository;
import com.softwareverification.realestate.repository.HomeOwnersRepository;

@Service
public class CommnOperationsServiceImpl implements CommnOperationsService {

	@Autowired
	AgentRepository agentRepo;

	@Autowired
	HomeRepository homeRepo;
	
	@Autowired
	HomeOwnersRepository homeOwnersRepo;

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	JdbcTemplate template;

	@Override
	public Agent createAgent(Agent agent) throws Exception {
		// TODO Auto-generated method stub
		try {
			return agentRepo.save(agent);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error occured while creating new Agent");
		}

	}

    @Override
    public List<Agent> findAgent(String fName, String lName) throws Exception {
		List<Agent> agents = null;
		// search for agents based on respective input search criteria
        if(!ObjectUtils.isEmpty(fName) && !ObjectUtils.isEmpty(lName)){
			agents = this.agentRepo.findAgent(fName.toLowerCase() ,lName.toLowerCase());
		}else if(!ObjectUtils.isEmpty(fName)){
			agents = this.agentRepo.findAgentbyFirstName(fName.toLowerCase());
		}else if(!ObjectUtils.isEmpty(lName)){
			agents = this.agentRepo.findAgentbyLastName(lName.toLowerCase());
		}else{
			throw new Exception("Agent first name or last name mandatory for search");
		}
		return agents;
    }

    @SuppressWarnings("deprecation")
	@Override
	public List<SearchInquiryResponse> searchInquiry(SearchRequest request) throws Exception {
		// TODO Auto-generated method stub
		try {

			if (!ObjectUtils.isEmpty(request)) {
				// fetch listing that ara available based on search condition
				String queryStr = "Select distinct home.HomeID as houseId, home.owner as houseOwner,home.FloorSpace as floorSpace, home.Floors as floors, "
						+ "home.Bedrooms as bedrooms, home.Bathrooms as bathrooms, home.LandSize as landSize,\r\n"
						+ "					home.YearConstructed, home.HomeType as houseType,\r\n"
						+ "					address.address as address, address.city as city, address.county as county, address.zip as zipCode,address.state,"
						+ " home.rate as price \r\n"
						+ "					From home, address \r\n"
						+ "					Where home.Address_id = address.Address_id  and UPPER(home.availabilityStatus) = 'AVAILABLE' ";

				List<String> params = new ArrayList<>();

				// add condition for filter house type
				if (!StringUtils.isEmpty(request.getHouseType())) {
					params.add(request.getHouseType().toLowerCase());
					queryStr = queryStr + "and lower(home.HomeType) = ? ";
				}

				// add condition for filter city
				if (!StringUtils.isEmpty(request.getCity())) {
					params.add(request.getCity().toLowerCase());
					queryStr = queryStr + "and lower(address.city) = ? ";
				}

				// add condition for filter state
				if (!StringUtils.isEmpty(request.getState())) {
					params.add(request.getState().toLowerCase());
					queryStr = queryStr + "and lower(address.state) = ? ";
				}

				// add condition for filter no of floors
				if (!StringUtils.isEmpty(request.getFloors()) && 0 != request.getFloors()) {
					params.add(String.valueOf(request.getFloors()));
					queryStr = queryStr + "and home.Floors = ? ";
				}

				// add condition for filter no of bedrooms
				if (!StringUtils.isEmpty(request.getBedRoomCount()) && 0 != request.getBedRoomCount()) {
					params.add(String.valueOf(request.getBedRoomCount()));
					queryStr = queryStr + "and home.Bedrooms = ? ";
				}

				// add condition for filter no of baths
				if (!StringUtils.isEmpty(request.getTotalbaths())) {
					params.add(request.getTotalbaths());
					queryStr = queryStr + "and home.Bathrooms = ? ";
				}

				// add condition for filter zipCode / postal code
				if (!StringUtils.isEmpty(request.getZipCode())) {
					params.add(request.getZipCode());
					queryStr = queryStr + "and address.zip = ? ";
				}

				// add condition for filter min price / rate
				if (!StringUtils.isEmpty(request.getPriceScale())) {
					params.add(request.getPriceScale());
					queryStr = queryStr + "and home.rate >= ? ";
				}

				// add condition for filter max price / rate
				if (!StringUtils.isEmpty(request.getPriceScaleTo())) {
					params.add(request.getPriceScaleTo());
					queryStr = queryStr + "and home.rate <= ? ";
				}

				queryStr = queryStr + ";";

				List<SearchInquiryResponse> resp = template.query(queryStr, params.toArray(),
						new BeanPropertyRowMapper<SearchInquiryResponse>(SearchInquiryResponse.class));
				return resp;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public HomeEntity saveorUpdateHome(HomeEntity home) throws Exception {
		// TODO Auto-generated method stub

		try {
			AddressEntity addressEnt = addressRepo.save(home.getAddress());
			home.setAddress(addressEnt);
			home.setAddressId(addressEnt.getId());
			return homeRepo.save(home);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error occured while creating new Agent");
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public HomeEntity fetchHomeDetails(int homeID) throws Exception {
		// TODO Auto-generated method stub
		HomeEntity entity = null;
		try {
			Optional<HomeEntity> homeResp = homeRepo.findById(homeID);
			if (homeResp.isPresent()) {
				entity = homeResp.get();
				Optional<AddressEntity> address = addressRepo.findById(entity.getAddressId());
				entity.setAddress(address.isPresent() ? address.get() : null);
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error occured while creating new Agent");
		}

	}

	@Override
	public HomeOwnerEntity saveorUpdateHomeOwners(HomeOwnerEntity homeOwner) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			AddressEntity addressEnt = addressRepo.save(homeOwner.getAddress());
			homeOwner.setAddress(addressEnt);
			homeOwner.setAddressId(addressEnt.getId());
			return homeOwnersRepo.save(homeOwner);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error occured while creating new Agent");
		}
	}

	@Override
	public boolean deleteHomeDetails(HomeEntity home) throws Exception {
		// TODO Auto-generated method stub
		try {
			homeRepo.deleteById(home.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error occured while creating new Agent");
		}
	}

	@Override
	public HomeOwnerEntity fetchHomeOwnerDetails(int ownerId) throws Exception {

		// TODO Auto-generated method stub
		HomeOwnerEntity entity = null;
		try {
			Optional<HomeOwnerEntity> homeResp = homeOwnersRepo.findById(ownerId);
			if (homeResp.isPresent()) {
				entity = homeResp.get();
				Optional<AddressEntity> address = addressRepo.findById(entity.getAddressId());
				entity.setAddress(address.isPresent() ? address.get() : null);
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error occured while creating new Agent");
		}

	
	}

}

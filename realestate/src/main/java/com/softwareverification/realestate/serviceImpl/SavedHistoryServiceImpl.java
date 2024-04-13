package com.softwareverification.realestate.serviceImpl;

import com.softwareverification.realestate.entity.SavedHistory;
import com.softwareverification.realestate.models.response.SearchInquiryResponse;
import com.softwareverification.realestate.repository.SavedHistoryRepository;
import com.softwareverification.realestate.service.SavedHistoryService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SavedHistoryServiceImpl implements SavedHistoryService {

    @Autowired
    JdbcTemplate template;

    @Autowired
    SavedHistoryRepository repo;
    @Override
    public List<SearchInquiryResponse> fetchSavedHistory(String userId) throws Exception {
        try {
            // fetching users saved history based on the userId
            if (!StringUtils.isEmpty(userId)) {
                String queryStr = "Select distinct home.HomeID as houseId, home.owner as houseOwner,home.FloorSpace as floorSpace, home.Floors as floors, "
                        + "home.Bedrooms as bedrooms, home.Bathrooms as bathrooms, home.LandSize as landSize,\r\n"
                        + "					home.YearConstructed, home.HomeType as houseType,\r\n"
                        + "					address.address as address, address.city as city, address.county as county, address.zip as zipCode,address.state,"
                        + " home.rate as price \r\n"
                        + "					From home, address, SavedHistory \r\n"
                        + "					Where home.Address_id = address.Address_id and SavedHistory.property_id = home.HomeID and lower(SavedHistory.user_id) = ?";

                List<String> params = new ArrayList<>();
                params.add(userId.toLowerCase());
                queryStr = queryStr + ";";

                List<SearchInquiryResponse> resp = template.query(queryStr, params.toArray(),
                        new BeanPropertyRowMapper<SearchInquiryResponse>(SearchInquiryResponse.class));
                return resp;
            }else{
                throw new Exception("UserId can't be empty");
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String saveFavourites(SavedHistory request) throws Exception {
        try{
            if(request!= null){
                repo.save(request);
            }else{
                throw new Exception("Request is invalid");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e.getLocalizedMessage());
        }
        return "Success";
    }

    @Override
    public String updateFavourites(SavedHistory request) throws Exception {
        try{
            if(request!= null){
                repo.delete(request);
            }else{
                throw new Exception("Request is invalid");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e.getLocalizedMessage());
        }
        return "Success";
    }
}

package com.softwareverification.realestate.controller;

import com.softwareverification.realestate.entity.Agent;
import com.softwareverification.realestate.entity.SavedHistory;
import com.softwareverification.realestate.models.response.ResponseStatus;
import com.softwareverification.realestate.models.response.SearchInquiryResponse;
import com.softwareverification.realestate.service.SavedHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SavedHistoryController {

    @Autowired
    SavedHistoryService serv;

    @GetMapping(value = "/realEstate/saveHistory")
    public ResponseEntity<ResponseStatus> savedHistory(@RequestParam(name = "userId") String userId) {
        try {
            List<SearchInquiryResponse> resp = serv.fetchSavedHistory(userId);
            return new ResponseEntity<>(new ResponseStatus("Success",200,0,resp), HttpStatus.OK);

        }catch(Exception e) {
            return new ResponseEntity<>(new ResponseStatus(e.getLocalizedMessage(),500,0),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/realEstate/saveFavourites")
    public ResponseEntity<ResponseStatus> saveFavourites(@RequestBody SavedHistory request) {
        try {
            String resp = serv.saveFavourites(request);
            return new ResponseEntity<>(new ResponseStatus(resp,200,0), HttpStatus.OK);

        }catch(Exception e) {
            return new ResponseEntity<>(new ResponseStatus(e.getLocalizedMessage(),500,0),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/realEstate/updateFavourites")
    public ResponseEntity<ResponseStatus> updateFavourites(@RequestBody SavedHistory request) {
        try {
            String resp = serv.updateFavourites(request);
            return new ResponseEntity<>(new ResponseStatus(resp,200,0), HttpStatus.OK);

        }catch(Exception e) {
            return new ResponseEntity<>(new ResponseStatus(e.getLocalizedMessage(),500,0),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

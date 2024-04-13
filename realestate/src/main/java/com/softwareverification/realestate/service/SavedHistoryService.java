package com.softwareverification.realestate.service;

import com.softwareverification.realestate.entity.SavedHistory;
import com.softwareverification.realestate.models.response.SearchInquiryResponse;

import java.util.List;

public interface SavedHistoryService {

    public List<SearchInquiryResponse> fetchSavedHistory(String userId) throws Exception;

    String saveFavourites(SavedHistory request) throws Exception;

    String updateFavourites(SavedHistory request) throws Exception;
}

package com.softwareverification.realestate.service;

import com.softwareverification.realestate.entity.SavedHistory;
import com.softwareverification.realestate.models.response.SearchInquiryResponse;
import com.softwareverification.realestate.repository.SavedHistoryRepository;
import com.softwareverification.realestate.serviceImpl.SavedHistoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class SavedHistoryServiceTest {

    @InjectMocks
    SavedHistoryServiceImpl serv;

    @Mock
    SavedHistoryRepository repo;

    @Mock
    JdbcTemplate template;

    List<SearchInquiryResponse> resp = new ArrayList<>();

    SavedHistory history;
    @BeforeEach
    void setUp(){
        SearchInquiryResponse response = new SearchInquiryResponse();
        response.setHouseType("Appartment");
        response.setHouseId(1);
        response.setBathrooms("2");
        response.setState("Michigan");
        response.setCity("Farmington Hills");
        response.setZipCode("48335");
        response.setAddress("anywhere jhbkj");
        response.setFloors("2");
        response.setMakeYear("2000");
        resp.add(response);

        history = new SavedHistory();
        history.setUserId("test 123");
        history.setPropertyId(3);
    }
    @Test
    void fetchSavedHistory_test() throws Exception {
        Mockito.when(template.query(anyString(), ArgumentMatchers.<Object[]> any(),
                any(BeanPropertyRowMapper.class))).thenReturn(resp);
        List<SearchInquiryResponse> resp1  = serv.fetchSavedHistory("testId");
        assertNotNull(resp1);
        assertEquals(1, resp1.size());
    }

    @Test
    void fetchSavedHistory_invalid_userId_test() throws Exception {
        Exception exception = assertThrows(Exception.class, () ->
                serv.fetchSavedHistory(""));
        assertEquals("UserId can't be empty", exception.getMessage());
    }

    @Test
    void fetchSavedHistory_Database_error_test() throws Exception {
        Mockito.when(template.query(anyString(), ArgumentMatchers.<Object[]> any(),
                any(BeanPropertyRowMapper.class))).thenThrow(RuntimeException.class);
        Exception exception = assertThrows(Exception.class, () ->
                serv.fetchSavedHistory("testId"));
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void saveFavourites_test() throws Exception {
        Mockito.when(repo.save(any(SavedHistory.class))).thenReturn(history);
        String resp = serv.saveFavourites(history);
        assertNotNull(resp);
        assertEquals("Success", resp);
    }

    @Test
    void saveFavourites_test_failure() throws Exception {
        Mockito.when(repo.save(any(SavedHistory.class))).thenThrow(RuntimeException.class);
        Exception exception = assertThrows(Exception.class, () ->
                serv.saveFavourites(history));
        assertTrue(exception instanceof Exception);
    }

    @Test
    void saveFavourites_invalid_request() throws Exception {
        Exception exception = assertThrows(Exception.class, () ->
                serv.saveFavourites(null));
        assertEquals("Request is invalid", exception.getMessage());
    }

    @Test
    void updateFavourites_invalid_request() throws Exception {
        Exception exception = assertThrows(Exception.class, () ->
                serv.updateFavourites(null));
        assertEquals("Request is invalid", exception.getMessage());
    }

}

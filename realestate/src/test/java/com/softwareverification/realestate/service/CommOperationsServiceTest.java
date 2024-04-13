package com.softwareverification.realestate.service;

import com.softwareverification.realestate.entity.AddressEntity;
import com.softwareverification.realestate.entity.Agent;
import com.softwareverification.realestate.entity.HomeEntity;
import com.softwareverification.realestate.entity.SavedHistory;
import com.softwareverification.realestate.models.request.SearchRequest;
import com.softwareverification.realestate.models.response.SearchInquiryResponse;
import com.softwareverification.realestate.repository.AddressRepository;
import com.softwareverification.realestate.repository.AgentRepository;
import com.softwareverification.realestate.repository.HomeRepository;
import com.softwareverification.realestate.serviceImpl.CommnOperationsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ws.soap.addressing.server.annotation.Address;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class CommOperationsServiceTest {

    @InjectMocks
    CommnOperationsServiceImpl serv;

    @Mock
    AgentRepository agentRepo;

    @Mock
    AddressRepository addressRepo;

    @Mock
    HomeRepository homeRepository;

    @Mock
    JdbcTemplate template;

    List<SearchInquiryResponse> resp = new ArrayList<>();

    HomeEntity home = new HomeEntity();
    AddressEntity ent = new AddressEntity();

    @BeforeEach
    void setUp(){
        SearchInquiryResponse response = new SearchInquiryResponse();
        response.setHouseType("Appartment");
        response.setHouseId(1);
        response.setBathrooms("2");
        response.setBedrooms("2");
        response.setState("Michigan");
        response.setCity("Novi");
        response.setZipCode("48335");
        response.setAddress("anywhere jhbkj");
        response.setFloors("2");
        response.setMakeYear("2000");
        resp.add(response);

        ent.setAddress("oak street ");
        ent.setCity("South Lyon");
        ent.setId(4);
        ent.setPostalCode(502235);
        ent.setState("MI");
        home.setAddress(ent);
        home.setAddressId(4);
        home.setHouseType("Mansion");
        home.setBuiltYear(2021);
        home.setHouseRate("554000");
        home.setFloorSpace("5546");
        home.setAreaCovered("10000 sqFt");
        home.setAvailabilityStatus("Available");
        home.setDescription("Nice warm and cozy place");
    }
    @Test
    void findAgentbyFirstName() throws Exception {
        List<Agent> agents = new ArrayList<>();
        Agent agent = new Agent();
        agent.setAgentFirstName("TestAgent");
        agent.setAgentLastName("TestNme");
        agent.setAgentEmail("dummyemail@email.com");
        agents.add(agent);
        Mockito.when(agentRepo.findAgentbyFirstName(any(String.class))).thenReturn(agents);
        List<Agent> resp = serv.findAgent("TestAgent","");
        assertNotNull(resp);
        assertEquals("TestAgent", resp.get(0).getAgentFirstName());
        assertEquals("TestNme", resp.get(0).getAgentLastName());
    }

    @Test
    void findAgentbyLastName() throws Exception {
        List<Agent> agents = new ArrayList<>();
        Agent agent = new Agent();
        agent.setAgentFirstName("TestAgent");
        agent.setAgentLastName("TestNme");
        agent.setAgentEmail("dummyemail@email.com");
        agents.add(agent);
        Mockito.when(agentRepo.findAgentbyLastName(any(String.class))).thenReturn(agents);
        List<Agent> resp = serv.findAgent("","TestNme");
        assertNotNull(resp);
        assertEquals("TestAgent", resp.get(0).getAgentFirstName());
        assertEquals("TestNme", resp.get(0).getAgentLastName());
    }

    @Test
    void findAgentbyFirstandLastName() throws Exception {
        List<Agent> agents = new ArrayList<>();
        Agent agent = new Agent();
        agent.setAgentFirstName("TestAgent");
        agent.setAgentLastName("TestNme");
        agent.setAgentEmail("dummyemail@email.com");
        agents.add(agent);
        Mockito.when(agentRepo.findAgent(any(String.class), any(String.class))).thenReturn(agents);
        List<Agent> resp = serv.findAgent("TestAgent","TestNme");
        assertNotNull(resp);
        assertEquals("TestAgent", resp.get(0).getAgentFirstName());
        assertEquals("TestNme", resp.get(0).getAgentLastName());
    }

    @Test
    void findAgentbyFirstandLastName_invalid() throws Exception {
        Exception exception = assertThrows(Exception.class, () ->
                serv.findAgent("",""));
        assertEquals("Agent first name or last name mandatory for search", exception.getMessage());
    }

    @Test
    void searchInquiry_test() throws Exception {
        SearchRequest req = new SearchRequest();
        req.setCity("Novi");
        Mockito.when(template.query(anyString(), ArgumentMatchers.<Object[]> any(),
                any(BeanPropertyRowMapper.class))).thenReturn(resp);
        List<SearchInquiryResponse> res = serv.searchInquiry(req);
        assertNotNull(res);
        assertEquals(1,res.size());
        assertEquals("Novi", resp.get(0).getCity());

    }

    @Test
    void searchInquiry_test_withMultipleFilters() throws Exception {
        SearchRequest req = new SearchRequest();
        req.setCity("Novi");
        req.setFloors(2);
        req.setBedRoomCount(2);
        req.setZipCode("48335");

        Mockito.when(template.query(anyString(), ArgumentMatchers.<Object[]> any(),
                any(BeanPropertyRowMapper.class))).thenReturn(resp);
        List<SearchInquiryResponse> res = serv.searchInquiry(req);
        assertNotNull(res);
        assertEquals(1,res.size());
        assertEquals("Novi", resp.get(0).getCity());
        assertEquals("48335", resp.get(0).getZipCode());
        assertEquals("2", resp.get(0).getBedrooms());

    }

    @Test
    void searchInquiry_Database_error_test() throws Exception {
        Mockito.when(template.query(anyString(), ArgumentMatchers.<Object[]> any(),
                any(BeanPropertyRowMapper.class))).thenThrow(RuntimeException.class);
        Exception exception = assertThrows(Exception.class, () ->
                serv.searchInquiry(new SearchRequest()));
        assertTrue(exception instanceof Exception);
    }

    @Test
    void saveorUpdateHome_test() throws Exception {
        Mockito.when(addressRepo.save(any(AddressEntity.class))).thenReturn(ent);
        Mockito.when(homeRepository.save(any(HomeEntity.class))).thenReturn(home);
        HomeEntity entity = serv.saveorUpdateHome(home);
        assertNotNull(entity);
        assertEquals(2021, entity.getBuiltYear());
        assertEquals("Mansion", entity.getHouseType());
        assertEquals(4, entity.getAddressId());
    }

    @Test
    void saveorUpdateHome_Database_error_test() throws Exception {
        Mockito.when(addressRepo.save(any(AddressEntity.class))).thenThrow(RuntimeException.class);
        Exception exception = assertThrows(Exception.class, () ->
                serv.saveorUpdateHome(home));
        assertTrue(exception instanceof Exception);
    }

}

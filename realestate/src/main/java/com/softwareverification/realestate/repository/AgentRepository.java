package com.softwareverification.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softwareverification.realestate.entity.Agent;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface AgentRepository extends JpaRepository<Agent, Integer>{

    @Query(value = "SELECT u FROM Agent u where LOWER(u.agentFirstName) like %?1%")
    List<Agent> findAgentbyFirstName(String firstName);

    @Query(value = "SELECT u FROM Agent u where LOWER(u.agentLastName) like %?1%")
    List<Agent> findAgentbyLastName(String lastNme);

    @Query(value = "SELECT u FROM Agent u where Lower(u.agentFirstName) like %?1% and LOWER(u.agentLastName) like %?2%" )
    List<Agent> findAgent(String firstName, String lastNme);
}

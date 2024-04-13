package com.softwareverification.realestate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="savedhistory")
@Data
public class SavedHistory {

    @Id
    @Column(name="history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historyId;


    @Column(name="user_id")
    private String userId;

    @Column(name = "property_id")
    private int propertyId;
}

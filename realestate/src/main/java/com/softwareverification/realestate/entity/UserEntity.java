package com.softwareverification.realestate.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="Users")
@Data
public class UserEntity {

    private static final long serialVersionUID = -1908015663832035719L;

    @Id
    @Column(name="user_id")
    private String userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "lst_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "password")
    private String password;
}

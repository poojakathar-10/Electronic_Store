package com.Lcwd.ElectronicStore.Electronic_Store1.Entity;

import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;


import javax.persistence.*;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Users")
public class User {

    @Id
    private String userId;

    @Column(name = "user_name")
    private String name;

    @Column(name ="user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "User_gender")
    private String gender;

    @Column(length = 1000)
    private String about;

    @Column(name="user_image_name")
    private String imageName;
}

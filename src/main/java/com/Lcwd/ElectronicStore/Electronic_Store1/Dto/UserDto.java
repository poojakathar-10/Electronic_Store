package com.Lcwd.ElectronicStore.Electronic_Store1.Dto;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @Id
    private String userId;
    @Size(min = 3, max = 15, message = "invalid_name")
    private String name;

    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)[a-z]{2,5}$")
    @Email(message = "Invalid email address")
    @NotBlank(message = "email address required!!")
    private String email;

    @NotBlank(message = "Password is required")
    private  String password;


    @Size(min = 3, max = 6, message = "invalid user gender")
    private String gender;

    @NotBlank(message = "Write something about yourself")
    private String about;

    //pattern
    //custom validator

    private String imageName;
}

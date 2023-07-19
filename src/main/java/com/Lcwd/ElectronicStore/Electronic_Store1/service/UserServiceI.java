package com.Lcwd.ElectronicStore.Electronic_Store1.service;

import com.Lcwd.ElectronicStore.Electronic_Store1.Dto.UserDto;

import java.util.List;

public interface UserServiceI {

    //create
   UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto, String userId);
    //delete

    //delete
    void deleteUser(String userId);

    //getAllUser
    List<UserDto> getAllUser();

    //get user by id
   UserDto getUserById(String userId);

    //get user by email;
    UserDto getUserByEmail(String email);

    // search
   List<UserDto> searchUser(String Keywords);
}

package com.Lcwd.ElectronicStore.Electronic_Store1.service.impl;

import com.Lcwd.ElectronicStore.Electronic_Store1.Dto.UserDto;
import com.Lcwd.ElectronicStore.Electronic_Store1.Entity.User;
import com.Lcwd.ElectronicStore.Electronic_Store1.controller.UserController;
import com.Lcwd.ElectronicStore.Electronic_Store1.exception.ResourceNotFoundException;
import com.Lcwd.ElectronicStore.Electronic_Store1.payloads.AppConstants;
import com.Lcwd.ElectronicStore.Electronic_Store1.repository.UserRepositoryI;
import com.Lcwd.ElectronicStore.Electronic_Store1.service.UserServiceI;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServiceI {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepositoryI userRepository;

    @Autowired
    private ModelMapper mapper;

    public UserDto createUser(UserDto userDto) {
        logger.info("Creating new user: {}", userDto);
        //generate userId in string format
        String userId = java.util.UUID.randomUUID().toString();
        userDto.setUserId(userId);
        //dto to entity
        User user = mapper.map(userDto, User.class);
        User saveUser = userRepository.save(user);
        //dto to entity
        UserDto newDto = mapper.map(user, UserDto.class);
        logger.info("User Created Sucessfully", newDto);
        return newDto;
    }



    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        logger.info("User Updating with id:- {}", userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
        //save data
        User updatedUser = userRepository.save(user);
        UserDto updatedDto = mapper.map(updatedUser, UserDto.class);
        logger.info("User Updated Sucessfully...!!!", updatedDto);
        return updatedDto;
    }

    @Override
    public void deleteUser(String userId) {
        logger.info("Deliting User...!!", userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.DELETE_USER));
        logger.info("User Deleted Sucessfully..!!", user);

        userRepository.delete(user);


    }

    @Override
    public List<UserDto> getAllUser() {
        logger.info("Retrieving AlL User...!");
        List<User> userList = userRepository.findAll();
        List<UserDto> dtoList = userList.stream().map((user) -> entityToDto(user)).collect(Collectors.toList());
        logger.info("Sucessfully Retrieve All Users..!!", dtoList);
        return dtoList;
    }

    @Override
    public UserDto getUserById(String userId) {
        logger.info("Geting User By Id..!!", userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND + userId));
        UserDto userDto = entityToDto(user);
        logger.info("Get User Ny Id...!!", userDto);
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        logger.info("Get User By Email..!!", email);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND));
        logger.info("Geting User By Email", user);
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String Keyword) {
        logger.info("Search Usre By Keyword..!!", Keyword);
        List<User> users = userRepository.findByNameContaining(Keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        logger.info("Searched User By Keyword..!!", dtoList);
        return dtoList;
    }

    private User dtoToEntity(UserDto userDto) {
       /* User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .gender(userDto.getGender())
                .about(userDto.getAbout())
                .imageName(userDto.getImageName())
                .build();*/
        return mapper.map(userDto,User.class);
    }

    public UserDto entityToDto(User saveUser) {
      /*  UserDto.builder()
                .userId(saveUser.getUserId())
                .name(saveUser.getName())
                .email(saveUser.getEmail())
                .password(saveUser.getPassword())
                .gender(saveUser.getGender())
                .about(saveUser.getAbout())
                .imageName(saveUser.getImageName())
                .build();*/
        return mapper.map(saveUser, UserDto.class);
    }
}

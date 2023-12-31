package com.Lcwd.ElectronicStore.Electronic_Store1.controller;

import com.Lcwd.ElectronicStore.Electronic_Store1.Dto.UserDto;
import com.Lcwd.ElectronicStore.Electronic_Store1.payloads.ApiResponseMessage;
import com.Lcwd.ElectronicStore.Electronic_Store1.service.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
        private static final Logger logger = LoggerFactory.getLogger(UserController.class);

        @Autowired
        private UserServiceI userService;

        /**
         * @Author pooja kathar
         * @param userDto
         * @return
         */
        //create

        /**
         * @param userDto
         * @return
         * @Author pooja Kathar
         */
        @PostMapping("/createUser")
        public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
            logger.info("Creating new user: {}", userDto);
            UserDto createdUser = userService.createUser(userDto);
            logger.info("User created successfully: {}", createdUser);

            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }

        //update

        /**
         * @param userId
         * @param userDto
         * @return
         * @Author pooja kathar
         */
        @PutMapping("/{userId}")
        public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId, @RequestBody UserDto userDto) {
            logger.info("User Update with Id :- { }", userId);
            UserDto updatedUserDto = userService.updateUser(userDto, userId);
            logger.info("User Updated Sucessfully...!!!", updatedUserDto);

            return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
        }

        //delete

        /**
         * @param userId
         * @return
         * @throws IOException
         * @Author pooja kathar
         */
        @DeleteMapping("/{userId}")
        public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId) {
            logger.info("Deleteting user with ID:- {}", userId);
            userService.deleteUser(userId);
            ApiResponseMessage message = ApiResponseMessage.builder().message("User is deleted successfully !!").success(true).status(HttpStatus.OK).build();

            logger.info("User delete Sucessfully..!!!", userId);
            return new ResponseEntity<>(message, HttpStatus.OK);

        }

        //get all

        /**
         * @param
         * @param
         * @return
         * @Author pooja kathar
         */
        @GetMapping("/allusers")
        public ResponseEntity<List<UserDto>> getAllUsers() {
            logger.info("Retrieving all users...!!!");
            List<UserDto> allUsers = userService.getAllUser();
            logger.info("Successfully retrieved all users...!!!");
            return new ResponseEntity<List<UserDto>>(allUsers, HttpStatus.OK);
        }

        //get Single


        /**
         * @param userId
         * @return
         * @Author pooja kathar
         */
        @GetMapping("/{userId}")
        public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
            logger.info("Retrieving user with ID: {}", userId);
            UserDto userById = userService.getUserById(userId);
            logger.info("Successfully retrieved user: {}", userById);
            return new ResponseEntity<>(userById, HttpStatus.OK);
        }

        //get by email

        /**
         * @param email
         * @return
         * @Author pooja kathar
         */
        @GetMapping("/email/{email}")
        public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
            logger.info("Retrieving user by email: {}", email);
            UserDto userByEmail = userService.getUserByEmail(email);
            logger.info("Successfully retrieved user: {}", userByEmail);
            return new ResponseEntity<>(userByEmail, HttpStatus.OK);
        }

        //Search user

        /**
         * @param keywords
         * @return
         * @Author pooja kathar
         */
        @GetMapping("/search/{keywords}")
        public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords) {
            logger.info("Searching for users containing keyword: {}", keywords);
            List<UserDto> userDtos = userService.searchUser(keywords);
            logger.info("Successfully retrieved users matching the search...!!!");
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        }


    }


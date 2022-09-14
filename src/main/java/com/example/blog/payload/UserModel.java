package com.example.blog.payload;

import com.example.blog.entity.User;
import com.example.blog.entity.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long id;

    private String fullName;

    private String username;

    private String password;

    private Roles roles = Roles.USER;

    private String bio;

    private List<UserModel> followers = new ArrayList<>();

    private List<UserModel> follows = new ArrayList<>();

    private List<UserModel> friends = new ArrayList<>();

    private Integer followersCount;

    private Integer followsCount;

    private Integer friendsCount;
}

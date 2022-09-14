package com.example.blog.entity;

import com.example.blog.entity.enums.ReactionType;
import com.example.blog.entity.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String recoverEmail;

    private String password;

    private String bio;

    @Enumerated(value = EnumType.STRING)
    private Roles roles;

    @ManyToOne
    private Attachment attachment;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Reaction> reactions;
    @Transient
    private List<User> followers = new ArrayList<>();

    @Transient
    private List<User> follows = new ArrayList<>();
    @Transient
    private List<User> friends = new ArrayList<>();

    @Transient
    private Integer followersCount;

    @Transient
    private Integer followsCount;

    @Transient
    private Integer friendsCount;

    /********************************************/

    private Boolean accountNotExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private Boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(roles);
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNotExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public Integer getFollowersCount() {
        return this.followers.size();
    }

    public Integer getFollowsCount() {
        return this.follows.size();
    }

    public Integer getFriendsCount() {
        return this.friends.size();
    }
}
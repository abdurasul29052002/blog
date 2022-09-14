package com.example.blog.service;

import com.example.blog.component.JwtProvider;
import com.example.blog.entity.User;
import com.example.blog.mapper.UserMapper;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.UserModel;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.template.GenericService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Super;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends GenericService<User, UserModel, UserRepository, UserMapper> {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public AuthService(UserMapper mapper, UserRepository repository) {
        super(mapper, repository);
    }

    public UserModel register(UserModel userModel){
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return save(userModel);
    }

    public ApiResponse login(UserModel userModel) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userModel.getUsername(),
                userModel.getPassword()
        ));
        User user = userRepository.findByUsername(userModel.getUsername()).orElseThrow();
        String token = jwtProvider.generateToken(user.getUsername(), user.getRoles());
        return new ApiResponse("Login successfuly",true,token);
    }
}

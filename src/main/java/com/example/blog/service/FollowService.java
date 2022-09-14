package com.example.blog.service;

import com.example.blog.entity.Follow;
import com.example.blog.entity.User;
import com.example.blog.payload.ApiResponse;
import com.example.blog.repository.FollowRepository;
import com.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public ApiResponse follow(Long toUserId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Follow> optionalFollow1 = followRepository.findByToUserIdAndFromUserId(toUserId, user.getId());
        Optional<Follow> optionalFollow2 = followRepository.findByToUserIdAndFromUserId(user.getId(), toUserId);
        Follow follow;
        if (optionalFollow1.isEmpty()){
            if (optionalFollow2.isEmpty()){
                follow = new Follow(null, user, userRepository.findById(toUserId).orElseThrow(), true, false);
            }else {
                follow = optionalFollow2.get();
                follow.setToUserAnswered(true);
            }
        }else {
            follow = optionalFollow1.get();
            follow.setFromUserAnswered(true);
        }
        followRepository.save(follow);
        return new ApiResponse("Followed",true);
    }

    public ApiResponse unFollow(Long userId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Follow follow;
        Optional<Follow> optionalFollow1 = followRepository.findByToUserIdAndFromUserId(userId, user.getId());
        Optional<Follow> optionalFollow2 = followRepository.findByToUserIdAndFromUserId(user.getId(), userId);
        if (optionalFollow1.isEmpty()){
            follow = optionalFollow2.orElseThrow();
            follow.setToUserAnswered(false);
        }else {
            follow=optionalFollow1.get();
            follow.setFromUserAnswered(false);
        }
        if (!follow.isFromUserAnswered() && !follow.isToUserAnswered()){
            followRepository.deleteById(follow.getId());
        }else {
            followRepository.save(follow);
        }
        return new ApiResponse("Deleted", true);
    }
}

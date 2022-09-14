package com.example.blog.component;

import com.example.blog.entity.Follow;
import com.example.blog.entity.User;
import com.example.blog.repository.FollowRepository;
import com.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDataLoader implements Runnable{

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Override
    public void run() {
        /*User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> followers = new ArrayList<>();
        List<User> follows = new ArrayList<>();
        List<User> friends = new ArrayList<>();
        /******************** Find Followers ******************************/

        /*for (Follow follow : followRepository.findAllByAnsweredAndFromUserId(false, user.getId())) {
            followers.add(userRepository.findById(follow.getToUser().getId()).orElseThrow());
        }

        for (Follow follow : followRepository.findAllByAnsweredAndToUserId(false, user.getId())) {
            followers.add(userRepository.findById(follow.getFromUser().getId()).orElseThrow());
        }

        user.setFollowers(followers);

        /******************* Find Follows ***************************/
        /*for (Follow follow : followRepository.findAllByAnsweredAndToUserId(false, user.getId())) {
            follows.add(userRepository.findById(follow.getFromUser().getId()).orElseThrow());
        }

        for (Follow follow : followRepository.findAllByAnsweredAndFromUserId(false, user.getId())) {
            follows.add(userRepository.findById(follow.getToUser().getId()).orElseThrow());
        }

        user.setFollows(follows);
        /******************** Find Friends *****************************/

        /*for (Follow follow : followRepository.findAllByAnsweredAndFromUserId(true, user.getId())) {
            friends.add(userRepository.findById(follow.getToUser().getId()).orElseThrow());
        }

        for (Follow follow : followRepository.findAllByAnsweredAndToUserId(true, user.getId())) {
            friends.add(userRepository.findById(follow.getFromUser().getId()).orElseThrow());
        }

        user.setFriends(friends);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                user,null,user.getAuthorities()
        ));
        */

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /*********************** Find Followers ****************************************/
        List<User> followers = new ArrayList<>();
        followers.addAll(followRepository.findAllDataByFromUserIdAndTwoBooleans(user.getId(),true,false));
        followers.addAll(followRepository.findAllDataByToUserIdAndTwoBooleans(user.getId(),false,true));
        user.setFollowers(followers);
        /************************ Find Follows ******************************************/
        List<User> follows = new ArrayList<>();
        follows.addAll(followRepository.findAllDataByFromUserIdAndTwoBooleans(user.getId(), false,true));
        follows.addAll(followRepository.findAllDataByToUserIdAndTwoBooleans(user.getId(), true, false));
        user.setFollows(follows);
        /************************* Find Friends *******************************************/
        List<User> friends = new ArrayList<>();
        friends.addAll(followRepository.findAllDataByFromUserIdAndTwoBooleans(user.getId(), true, true));
        friends.addAll(followRepository.findAllDataByToUserIdAndTwoBooleans(user.getId(), true,true));
        user.setFriends(friends);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                user,null,user.getAuthorities()
        ));
    }
}

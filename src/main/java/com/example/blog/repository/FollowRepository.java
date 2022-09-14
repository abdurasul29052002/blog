package com.example.blog.repository;

import com.example.blog.entity.Follow;
import com.example.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
//
//    @Query(value = "select u from users u join Follow f on u.id=f.toUser.id where f.toUser.id=:user_id")
//    List<User> findAllFollowerByUserId(Long user_id);
//
//    @Query(value = "select u from users u join Follow f on u.id=f.toUser.id where f.answered=true or f.fromUser.id=:user_id")
//    List<User> findAllFollowsByToUserId(Long user_id);
//
//    @Query(value = "select u from users u join Follow f on u.id=f.fromUser.id where f.answered=true or f.toUser.id=:user_id")
//    List<User> findAllFollowsByFromUserId(Long user_id);
//
////    @Query(value = "select u from users u where u.id=(select f.fromUser.id from Follow f where f.answered=true and f.fromUser.id=:user_id) or u.id=(select f.toUser.id from Follow  f where f.answered=true and f.toUser.id=:user_id)")
//    @Query(value = "select u from users u join Follow f on ")
//    List<User> findAllFriendsByUserId(Long user_id);
//
    Optional<Follow> findByToUserIdAndFromUserId(Long toUser_id, Long fromUser_id);
////
    Boolean existsByToUserIdAndFromUserId(Long toUser_id, Long fromUser_id);
//
//    List<Follow> findAllByAnsweredAndToUserId(boolean answered, Long toUser_id);
//
//    List<Follow> findAllByAnsweredAndFromUserId(boolean answered, Long fromUser_id);

    @Query(value = "select u from users u join Follow f on u.id=f.fromUser.id where f.toUser.id=:userId and f.fromUserAnswered=:fromUserAnswered and f.toUserAnswered=:toUserAnswered")
    List<User> findAllDataByToUserIdAndTwoBooleans(Long userId, boolean toUserAnswered, boolean fromUserAnswered);

    @Query(value = "select u from users u join Follow f on u.id=f.toUser.id where f.fromUser.id=:userId and f.fromUserAnswered=:fromUserAnswered and f.toUserAnswered=:toUserAnswered")
    List<User> findAllDataByFromUserIdAndTwoBooleans(Long userId, boolean toUserAnswered, boolean fromUserAnswered);

//    @Query(value = "select u from users u join Follow f on u.id=f.toUser.id where f.fromUser.id=:user_id and f.fromUserAnswered=false and f.toUserAnswered=true")
//    List<User> findByFollowersByFromUserId(Long user_id);
//
//    @Query(value = "select u from users u join Follow f on u.id=f.fromUser.id where f.toUser.id=:user_id and f.fromUserAnswered=true and f.toUserAnswered=false")
//    List<User> findByFollowersByToUserId(Long user_id);
//
//    @Query(value = "select u from users u join Follow f on u.id=f.toUser.id where f.fromUser.id=:user_id and f.fromUserAnswered=false and f.toUserAnswered=true")
//    List<User> findAllFollowsByFromUserId(Long user_id);
//
//    @Query(value = "select u from users u join Follow f on u.id=f.fromUser.id where f.toUser.id=:user_id and f.fromUserAnswered=true and f.toUserAnswered=false")
//    List<User> findAllFollowsByToUserId(Long user_id);
//
//    @Query(value = "select u from users u join Follow f on u.id=f.toUser.id where f.fromUser.id=:user_id and f.fromUserAnswered=true and f.toUserAnswered=true")
//    List<User> findAllFriendsByFromUserId(Long user_id);
}

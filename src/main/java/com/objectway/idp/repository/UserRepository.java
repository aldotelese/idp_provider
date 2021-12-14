package com.objectway.idp.repository;

import com.objectway.idp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);
    List<User> findAll();
    long count();

    @Query("{'tokens' : ?0}")
    User findUserByToken(String token);
}

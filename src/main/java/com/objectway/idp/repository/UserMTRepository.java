package com.objectway.idp.repository;

import com.objectway.idp.model.User;
import com.objectway.idp.model.UserMT;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


// repo of all Users
@Repository
public interface UserMTRepository extends MongoRepository<UserMT, String> {
    UserMT findUserByUsername(String username);
    @Query("{'tokens' : ?0}")
    UserMT findUserByToken(String token);

}

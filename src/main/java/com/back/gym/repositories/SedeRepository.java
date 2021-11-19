package com.back.gym.repositories;

import com.back.gym.models.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepository extends MongoRepository<SedeModel,String>{
    
}
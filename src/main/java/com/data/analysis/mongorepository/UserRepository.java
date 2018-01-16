package com.data.analysis.mongorepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.data.analysis.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
	
    public User findById(String id);
    
    public User findByFirstName(String firstName);
    
    public User findByLastName(String lastName);
    
    public void deleteByFirstName(String firstName);
}

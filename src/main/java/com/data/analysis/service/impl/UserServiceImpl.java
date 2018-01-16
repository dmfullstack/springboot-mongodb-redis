package com.data.analysis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.data.analysis.entity.User;
import com.data.analysis.mongorepository.UserRepository;
import com.data.analysis.service.UserService;
import com.data.analysis.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public UserServiceImpl() {
    }
    
	@Override
    @Cacheable(value = "user_cache_data_haskey", keyGenerator = "customKeyGenerator")
    public User findUserById(String id) {
    		log.info("findUserById query from db, id: {}", id);
        return userRepository.findById(id);
    }

    @Override
    @CachePut(value = "user", key = "'user'.concat(#user.id.toString())")
    public void update(User user) {
        mongoTemplate.insert(user);
    }

	@Override
	public void save(String firtname, String lastname) {
		User user = new User();
		user.setFirstName(firtname);
		user.setLastName(lastname);
		userRepository.save(user);
	}

	@Override
	@Cacheable(value = "user", key = "#firstName")
	public User findByFirstName(String firstName) {
        log.info("first name is: {}", firstName);
		return userRepository.findByFirstName(firstName);
	}

	@Override
	@Cacheable(value = "user", key = "#lastName")
	public User findByLastName(String lastName) {
		User user = userRepository.findByLastName(lastName);
		return user;
	}

	@Override
	public List<User> fetchAll() {
		return mongoTemplate.findAll(User.class);
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}
    
}

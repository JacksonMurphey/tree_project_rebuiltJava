package com.jmurphey.authentication.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jmurphey.authentication.models.Tree;
import com.jmurphey.authentication.models.User;



@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
	
	List<User> findAll();
	
	List<User> findAllByTreesVisited(Tree tree);
	
}

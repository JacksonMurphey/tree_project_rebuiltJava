package com.jmurphey.authentication.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jmurphey.authentication.models.Tree;
import com.jmurphey.authentication.models.User;



@Repository
public interface TreeRepository extends CrudRepository<Tree, Long>{
	
	List<Tree> findAll();
	
	List<Tree> findAllByPlanter(User planter);
}

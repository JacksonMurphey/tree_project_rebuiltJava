package com.jmurphey.authentication.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmurphey.authentication.models.Tree;
import com.jmurphey.authentication.models.User;
import com.jmurphey.authentication.repositories.UserRepository;



@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	
	
// --- Register user and hash their Password ---
	
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepo.save(user);
	}
	
	
// --- Authenticate the user ---
	
	public boolean authenticateUser(String email, String password) {
		
		User user = userRepo.findByEmail(email);
		
		if(user == null) {
			return false;
		} else {
			if(BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
		
	}
	
	
// --- Find user by email ---
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
// --- Find user by id ---
	
	public User findUserById(Long id) {
		return this.userRepo.findById(id).orElse(null);
	}
	
// --- Find all users ---
	
	public List<User> allUsers(){
		return userRepo.findAll();
	}
	

	// --- Add/Create Relationship ---
	
	public void addToTreesVisited(User user, Tree tree) {
		List<Tree> visitedTrees = user.getTreesVisited();
		visitedTrees.add(tree);
		userRepo.save(user);
	}
	
	// -- Find All By Tree Visited
	
	public List<User> getVisitorsofTree(Tree tree){
		return userRepo.findAllByTreesVisited(tree);
	}

	
// --- Create/Save user ---
	
	public User createUser(User user) {
		return userRepo.save(user);
	}
	
// --- Update User ---   (This would only be necessary if we wanted to update name/email/password)
	
	public User updateUser(User user) {
		return userRepo.save(user);
	}
	
// --- Delete User by Id ---
	
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
	
}

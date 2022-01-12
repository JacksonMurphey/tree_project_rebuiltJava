package com.jmurphey.authentication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmurphey.authentication.models.Tree;
import com.jmurphey.authentication.models.User;
import com.jmurphey.authentication.repositories.TreeRepository;

@Service
public class TreeService {

	@Autowired
	private TreeRepository treeRepo;
	
	
	
	
	
// --- Find Tree by Id ---
	
	public Tree getTreeById(Long id) {
		return treeRepo.findById(id).orElse(null);
	}
	
// --- Find All Trees / Find all by User ---
	
	public List<Tree> allTrees(){
		return treeRepo.findAll();
	}
	
	public List<Tree> getAllTreesByPlanter(User planter){
		return treeRepo.findAllByPlanter(planter);
	}
	
	
	// --- Add/Create Relationship ---
	
	public void addToUsersVisited(User user, Tree tree) {
		List<User> visitedUsers = tree.getUsersVisited();
		visitedUsers.add(user);
		treeRepo.save(tree);
	}
	
	// --- Remove Relationship ---
	
	public void removeUserVisit(User user, Tree tree) {
		tree.getUsersVisited().remove(user);
		treeRepo.save(tree);
	}
	
	
	
// --- Create/Save Tree ---
	
	public Tree createTree(Tree tree) {
		return treeRepo.save(tree);
	}
	
// --- Update Tree ---
	
	public Tree updateTree(Tree tree) {
		return treeRepo.save(tree);
	}
	
// --- Delete Tree ---
	
	public void deleteTree(Long id) {
		treeRepo.deleteById(id);
	}
	
	
}

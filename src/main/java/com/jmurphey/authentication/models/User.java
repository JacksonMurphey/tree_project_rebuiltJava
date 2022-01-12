package com.jmurphey.authentication.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email
	@NotBlank(message="Must be a valid Email")
	private String email;
	
	@Size(min=5, message="Password must be greater than 5 characters")
	private String password;
	
	@NotBlank(message = "Must input your first name")
	private String firstName;
	
	@NotBlank(message = "Must input your last name")
	private String lastName; 
	

	
	@Transient
	private String passwordConfirmation;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	
// --- Joining Tables ---
	
	@OneToMany(mappedBy = "planter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Tree> trees;
	
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "visitors",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "tree_id")
			)
	private List<Tree> treesVisited;
	
	
// --- Constructor ---	
	
	public User() {}

// --- Getters And Setters ---
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<Tree> getTrees() {
		return trees;
	}

	public void setTrees(List<Tree> trees) {
		this.trees = trees;
	}

	public List<Tree> getTreesVisited() {
		return treesVisited;
	}

	public void setTreesVisited(List<Tree> treesVisited) {
		this.treesVisited = treesVisited;
	}

	@PrePersist
	protected void onCreate() {	
		this.createdAt = new Date(); 
	}
		
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}

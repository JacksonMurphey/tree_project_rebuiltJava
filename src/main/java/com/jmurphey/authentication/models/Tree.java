package com.jmurphey.authentication.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "trees")
public class Tree {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Please provide the name of the Species")
	private String species;
	
	@NotBlank(message = "Please provide a location")
	private String location;
	
	@NotBlank(message = "Please provide the reason for planting")
	private String reason;
	
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date plantDate;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
// --- Joining Tables ---
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User planter;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "visitors",
			joinColumns = @JoinColumn(name = "tree_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> usersVisited;
	
	
// --- Constructor ---
	
	public Tree() {}

// --- Getters And Setters ---

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getSpecies() {
		return species;
	}


	public void setSpecies(String species) {
		this.species = species;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public Date getPlantDate() {
		return plantDate;
	}


	public void setPlantDate(Date plantDate) {
		this.plantDate = plantDate;
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


	public User getPlanter() {
		return planter;
	}


	public void setPlanter(User planter) {
		this.planter = planter;
	}


	public List<User> getUsersVisited() {
		return usersVisited;
	}


	public void setUsersVisited(List<User> usersVisited) {
		this.usersVisited = usersVisited;
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

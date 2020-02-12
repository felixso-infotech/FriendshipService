package com.felixso_infotech.domain.graph;



import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;


@NodeEntity
public class RegisteredUser {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    private String firstName;
   
    private String lastName;
    
    @Index(unique = true)
    private String userId;
    

       
    public RegisteredUser() {
		
	}

	@Override
	public String toString() {
		return "RegisteredUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userId=" + userId
				+ "]";
	}	
	
	
   

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public RegisteredUser firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public RegisteredUser lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public RegisteredUser userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
	
    
    
    
    
    
    
	/*
	 * @Relationship(type = "FRIEND_OF", direction = Relationship.DIRECTION) private
	 * List<RegisteredUser> friends = new ArrayList<>();
	 * 
	 * @Relationship(type = "WELLWISHER_OF", direction = Relationship.INCOMING)
	 * private List<RegisteredUser> wellwishers = new ArrayList<>();
	 * 
	 * @Relationship(type = "WELLWISHING_OF", direction = Relationship.OUTGOING)
	 * private List<RegisteredUser> wellwishingList = new ArrayList<>();
	 * 
	 * 
	 * 
	 * public List<RegisteredUser> getWellwishers() { return wellwishers; }
	 * 
	 * public void setWellwishers(List<RegisteredUser> wellwishers) {
	 * this.wellwishers = wellwishers; }
	 * 
	 * public List<RegisteredUser> getWellwishingList() { return wellwishingList; }
	 * 
	 * public void setWellwishingList(List<RegisteredUser> wellwishingList) {
	 * this.wellwishingList = wellwishingList; }
	 * 
	 * public void setFriends(List<RegisteredUser> friends) { this.friends =
	 * friends; }
	 * 
	 * public List<RegisteredUser> getFriends() { return friends; }
	 * 
	 */

}

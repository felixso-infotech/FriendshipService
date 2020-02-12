package com.felixso_infotech.domain.graph;


import java.util.HashMap;
import java.util.Map;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class WellwisherAndRelationship {
	
	
    private RegisteredUser wellWishers;
    
    private String wellWisherRel;
    
    private Map<String,Boolean> wellWisherProperties = new HashMap<String,Boolean>();
    
    private String friendRel;
    
    private Map<String,Boolean> friendProperties = new HashMap<String,Boolean>();
       
    
	public String getFriendRel() {
		return friendRel;
	}


	public void setFriendRel(String friendRel) {
		this.friendRel = friendRel;
	}


	public Map<String, Boolean> getFriendProperties() {
		return friendProperties;
	}


	public void setFriendProperties(Map<String, Boolean> friendProperties) {
		this.friendProperties = friendProperties;
	}


	public String getWellWisherRel() {
		return wellWisherRel;
	}

	
	public Map<String, Boolean> getWellWisherProperties() {
		return wellWisherProperties;
	}


	public void setWellWisherProperties(Map<String, Boolean> wellWisherProperties) {
		this.wellWisherProperties = wellWisherProperties;
	}


	public void setWellWisherRel(String wellWisherRel) {
		this.wellWisherRel = wellWisherRel;
	}


	/*
	 * public String getFriendRel() { return friendRel; }
	 * 
	 * public void setFriendRel(String friendRel) { this.friendRel = friendRel; }
	 */
	

	public RegisteredUser getWellWishers() {
		return wellWishers;
	}

	public void setWellWishers(RegisteredUser wellWishers) {
		this.wellWishers = wellWishers;
	}

	
    

}

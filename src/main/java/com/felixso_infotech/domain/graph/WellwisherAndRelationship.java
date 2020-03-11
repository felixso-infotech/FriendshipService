package com.felixso_infotech.domain.graph;


import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class WellwisherAndRelationship {
	
	
    private RegisteredUser wellWisher;
        
    private String relationshipType;
    
   
	public RegisteredUser getWellWisher() {
		return wellWisher;
	}

	public void setWellWisher(RegisteredUser wellWisher) {
		this.wellWisher = wellWisher;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	
    
    //private Map<String,Boolean> wellWisherProperties = new HashMap<String,Boolean>();
    
    //private String friendRel;
       
    //private Map<String,Boolean> friendProperties = new HashMap<String,Boolean>();
    
    

	
	 
	

	
	
    

}

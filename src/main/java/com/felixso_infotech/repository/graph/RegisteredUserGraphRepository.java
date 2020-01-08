package com.felixso_infotech.repository.graph;


import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.felixso_infotech.domain.graph.RegisteredUser;


@Repository
public interface RegisteredUserGraphRepository extends Neo4jRepository<RegisteredUser, String> {
	
	
	/**
	 * Find registeredUser by registered user id
	 *
	 * @param userId the registered user id
	 * @return registered users
	 */
	RegisteredUser findByUserId(@Param("userId") String userId);	
	
		
	/**
	 * Create a well wisher relationship.
	 *
	 * @param userId       the registered user id
	 * @param wellWisherId the registered user id
	 * @return the well wisher registered user
	 */
	@Query("match (u:RegisteredUser{userId:" + "{userId}" + "}),(w:RegisteredUser{userId:" + "{wellWisherId}"
			+ "}) create (u)-[:WELLWISHER_OF]->(w) MERGE (u)-[p:WELLWISHER_OF]->(w) \r\n" + 
			"ON CREATE SET p.alreadyExisted=false \r\n" + 
			"ON MATCH SET p.alreadyExisted=true \r\n" + 
			"RETURN p.alreadyExisted;")
	Boolean createWellWisher(@Param("userId") String userId, @Param("wellWisherId") String wellWisherId);
	
	
	/**
	 * Create a well wishing relationship.
	 *
	 * @param userId       the registered user id
	 * @param wellWishingId the registered user id
	 * @return the well wisher registered user
	 */
	@Query("match (u:RegisteredUser{userId:" + "{userId}" + "}),(w:RegisteredUser{userId:" + "{wellWisherId}"
			+ "}) create (u)-[:WELLWISHING_OF]->(w)  MERGE (u)-[p:WELLWISHING_OF]->(w) \r\n" +  
			"ON CREATE SET p.alreadyExisted=false \r\n" + 
			"ON MATCH SET p.alreadyExisted=true \r\n" +  
			"RETURN p.alreadyExisted;")
	Boolean createWellWishing(@Param("userId") String userId, @Param("wellWisherId") String wellWisherId);
	
	
	/**
	 * Find all well wishers by registered user id
	 *
	 * @param userId the registered user id
	 * @return list of well wisher registered users
	 */
	@Query("MATCH (u:RegisteredUser{userId:" + " {userId} "
			+ "}),(u)-[r:WELLWISHER_OF]-(wellwishers) RETURN wellwishers;")
	List<RegisteredUser> findAllWellWishersByUserId(@Param("userId") String userId);
	
	
	/**
	 * Find all well wishing by registered user id
	 *
	 * @param userId the registered user id
	 * @return list of well wishing registered users
	 */
	@Query("MATCH (u:RegisteredUser{userId:" + " {userId} "
			+ "}),(u)-[r:WELLWISHING_OF]-(wellwishing) RETURN wellwishing;")
	List<RegisteredUser> findAllWellWishingByUserId(@Param("userId") String userId);
	
	
	/**
	 * get count well wishers by registered user id
	 *
	 * @param userId the registered user id
	 * @return count of well wishers registered users
	 */	
	@Query("MATCH (u:RegisteredUser{userId:" + " {userId}"
			+ "}),(u)-[r:WELLWISHER_OF]-(wellwishers) RETURN count(*) ")
	Long countOfWellWishersByUserId(@Param("userId") String userId);

	
	/**
	 * get count well wishing by registered user id
	 *
	 * @param userId the registered user id
	 * @return count of well wishing registered users
	 */
	@Query("MATCH (u:RegisteredUser{userId:" + " {userId}"
			+ "}),(u)-[r:WELLWISHING_OF]-(wellwishing) RETURN count(*) ")
	Long countOfWellWishingByUserId(@Param("userId") String userId);
	
	
	/**
	 * Find exist registeredUser by registered user id
	 *
	 * @param userId the registered user id
	 * @return registered users
	 */
	@Query("MATCH (p:RegisteredUser) WHERE exists(p.userId) RETURN p.name,p.userId")
	RegisteredUser getRegisteredUserIsExist(@Param("userId") String userId);
	
	/**
	 * save registeredUser by registered user 
	 *
	 * @param registereduser the registered user
	 * @return registered users
	 */
	@Query("CREATE (r:RegisteredUser {firstName: " + "{firstName}" + ", userId:"+"{userId}" + "}) return r;")
	RegisteredUser saveNewUser(@Param("firstName") String firstName,@Param("userId") String userId);
	
	
	/**
	 * save registeredUser by registered user 
	 *
	 * @param registereduser the registered user
	 * @return registered users
	 */
	@Query("CREATE (r:RegisteredUser {firstName:\"sanila\", userId:\"sanila\"})return r;")
	RegisteredUser saveNew(RegisteredUser registeredUser);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

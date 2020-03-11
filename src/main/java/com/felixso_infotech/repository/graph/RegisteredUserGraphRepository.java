package com.felixso_infotech.repository.graph;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.felixso_infotech.domain.graph.RegisteredUser;
import com.felixso_infotech.domain.graph.WellwisherAndRelationship;

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
	 * @return Boolean value if create relationship
	 */
	@Query("match (u:RegisteredUser{userId:" + "{userId}" + "}),(w:RegisteredUser{userId:" + "{wellWisherId}"
			+ "}) create (u)-[:WELLWISHER_OF]->(w) MERGE (u)-[p:WELLWISHER_OF]->(w) \r\n"
			+ "ON CREATE SET p.alreadyExisted=false \r\n" + "ON MATCH SET p.alreadyExisted=true \r\n"
			+ "RETURN p.alreadyExisted;")
	Boolean createWellWisher(@Param("userId") String userId, @Param("wellWisherId") String wellWisherId);

	/**
	 * Create a FRIEND relationship.
	 *
	 * @param userId the registered user id
	 * @param userId the registered user id
	 * @return Boolean value if create relationship
	 */
	@Query("match (u:RegisteredUser{userId:" + "{userId}" + "}),(w:RegisteredUser{userId:" + "{userId2}"
			+ "}) create (u)-[:FRIEND_OF]->(w)  MERGE (u)-[p:FRIEND_OF]->(w) \r\n"
			+ "ON CREATE SET p.alreadyExisted=false \r\n" + "ON MATCH SET p.alreadyExisted=true \r\n"
			+ "RETURN p.alreadyExisted;")
	Boolean createFriend(@Param("userId") String userId, @Param("userId2") String userId2);
	
	/**
	 * Delete a WELLWISHER relationship.
	 *
	 * @param userId the registered user id
	 * @param wellWisherId the registered user id
	 * @return the Boolean value if exist or not
	 */
	@Query("MATCH (u:RegisteredUser{userId:" + "{currentUserId}" + "})-[r:WELLWISHER_OF]->(w:RegisteredUser{userId:" + "{wellWisherId}" + "}) delete r RETURN NOT EXISTS((u)-[:WELLWISHER_OF]->(w))")
	Boolean unFollow(@Param("currentUserId") String currentUserId,@Param("wellWisherId") String wellWisherId);

	
	/**
	 * Delete a FRIEND relationship.
	 *
	 * @param userId the registered user id
	 * @param friendId the registered user id
	 * @return the Boolean value if exist or not
	 */
	@Query("MATCH (u:RegisteredUser{userId:" + "{currentUserId}" + "})-[r:FRIEND_OF]-(w:RegisteredUser{userId:" + "{friendId}" + "}) delete r RETURN NOT EXISTS((u)-[:FRIEND_OF]-(w))")
	Boolean unFriend(@Param("currentUserId") String currentUserId,@Param("friendId") String friendId);
	

	/**
	 * Check a well wisher relationship.
	 *
	 * @param userId        the registered user id
	 * @param wellWishingId the registered user id
	 * @return Boolean value if relationship exist or not
	 */
	@Query("MATCH (u:RegisteredUser{userId:" + "{userId}" + "}),(w:RegisteredUser{userId:" + "{wellWisherId}"
			+ "}) RETURN EXISTS( (u)-[:WELLWISHER_OF]->(w) )")
	Boolean checkRegisteredUserIsFollowed(@Param("userId") String userId, @Param("wellWisherId") String wellWisherId);
	
	/**
	 * Check a Friend relationship.
	 *
	 * @param userId        the registered user id
	 * @param friend the registered user id
	 * @return Boolean value if relationship exist or not
	 */
	@Query("MATCH (u:RegisteredUser{userId:" + "{userId1}" + "}),(w:RegisteredUser{userId:" + "{userId2}"
			+ "}) RETURN EXISTS( (u)-[:FRIEND_OF]-(w) )")
	Boolean checkRegisteredUserIsFriend(@Param("userId1") String userId1, @Param("userId2") String userId2);

	/**
	 * Check a wellwisher or Friend relationship.
	 *
	 * @param currentUserId    the registered user id
	 * @param registeredUserId the registered user id
	 * @return Boolean value if relationship exist or not
	 */
	@Query("MATCH(p:RegisteredUser {userId: {currentUserId}}),(b:RegisteredUser {userId: {registeredUserId}}) RETURN EXISTS ((p)-[:WELLWISHER_OF]->(b)) OR EXISTS((p)-[:FRIEND_OF]-(b))")
	Boolean checkRegisteredUserIsFollowedOrIsFriend(@Param("currentUserId") String currentUserId,@Param("registeredUserId") String registeredUserId);

	/**
	 * Find all well wishers by registered user id
	 *
	 * @param userId the registered user id
	 * 
	 * @return list of well wisher registered users
	 */
	
	@Query("MATCH (u:RegisteredUser{userId:" + " {userId}"
			+ "})<-[r:WELLWISHER_OF|FRIEND_OF]-(wellwisher) WITH wellwisher, CASE EXISTS((:RegisteredUser{userId:"
			+ " {userId}"
			+ "})-[:FRIEND_OF]-(wellwisher)) WHEN true THEN \"FRIEND_OF\" ELSE \"WELLWISHER_OF\" END as relationshipType RETURN wellwisher,relationshipType;")
	List<WellwisherAndRelationship> findAllWellWishersByUserId(@Param("userId") String userId);

	/**
	 * Find all well wishing by registered user id
	 *
	 * @param userId the registered user id
	 * @return list of well wishing registered users
	 */
	@Query("MATCH (u:RegisteredUser{userId:" + " {userId}"
			+ "})-[r:WELLWISHER_OF|FRIEND_OF]->(wellwisher) WITH wellwisher, CASE EXISTS((:RegisteredUser{userId:"
			+ " {userId}"
			+ "})-[:FRIEND_OF]-(wellwisher)) WHEN true THEN \"FRIEND_OF\" ELSE \"WELLWISHER_OF\" END as relationshipType RETURN wellwisher,relationshipType;")
	List<WellwisherAndRelationship> findAllWellWishingByUserId(@Param("userId") String userId);

	/**
	 * get count well wishers by registered user id
	 *
	 * @param userId the registered user id
	 * @return count of well wishers registered users
	 */
	@Query("MATCH (u:RegisteredUser)<-[r:WELLWISHER_OF|FRIEND_OF]-(wellwishers) Where u.userId = {userId} RETURN count(*)")
	Long countOfWellWishersByUserId(@Param("userId") String userId);

	/**
	 * get count well wishing by registered user id
	 *
	 * @param userId the registered user id
	 * @return count of well wishing registered users
	 */
	@Query("MATCH (u:RegisteredUser)-[r:WELLWISHER_OF|FRIEND_OF]->(wellwishers) Where u.userId = {userId} RETURN count(*)")
	Long countOfWellWishingByUserId(@Param("userId") String userId);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// testing............................................................................................................................

	/**
	 * Create a well wishing relationship.
	 *
	 * @param userId        the registered user id
	 * @param wellWishingId the registered user id
	 * @return the well wisher registered user
	 */
	/*
	 * @Query("match (u:RegisteredUser{userId:" + "{userId}" +
	 * "}),(w:RegisteredUser{userId:" + "{wellWishingId}" +
	 * "}) create (u)-[:WELLWISHING_OF]->(w)  MERGE (u)-[p:WELLWISHING_OF]->(w) \r\n"
	 * + "ON CREATE SET p.alreadyExisted=false \r\n" +
	 * "ON MATCH SET p.alreadyExisted=true \r\n" + "RETURN p.alreadyExisted;")
	 * Boolean createWellWishing(@Param("userId") String
	 * userId, @Param("wellWishingId") String wellWishingId);
	 * 
	 * 
	 * @Query("MATCH (u:RegisteredUser{userId:" + " {userId}" +
	 * "})<-[r:WELLWISHER_OF]-(wellwishers) WITH wellwishers, CASE EXISTS((:RegisteredUser{userId:"
	 * + " {userId}" +
	 * "})<-[:FRIEND_OF]-(wellwishers)) WHEN true THEN \"FRIEND_OF\" ELSE \"WELLWISHER_OF\" END as wellWisherRel RETURN wellwishers,wellWisherRel;"
	 * ) List<WellwisherAndRelationship>
	 * findAllWellWishersWithFriendsByUserId(@Param("userId") String userId);
	 * 
	 * 
	 *//**
		 * Find exist registeredUser by registered user id
		 *
		 * @param userId the registered user id
		 * @return registered users
		 */
	/*
	 * @Query("MATCH (u:RegisteredUser) WHERE exists (u.userId = {userId}) RETURN u"
	 * ) RegisteredUser getRegisteredUserIsExist(@Param("userId") String userId);
	 * 
	 * 
	 * 
	 *//**
		 * Check a well wishing relationship.
		 *
		 * @param userId        the registered user id
		 * @param wellWishingId the registered user id
		 * @return the well wishing registered user
		 *//*
			 * @Query("MATCH (u:RegisteredUser{userId:" + "{userId}"+
			 * "}),(w:RegisteredUser{userId:" + "{wellWisherId}"+
			 * "}) RETURN EXISTS( (u)-[:WELLWISHER_OF]->(w) )") Boolean
			 * checkRegisteredUserIsFollowing(@Param("userId") String
			 * userId, @Param("wellWishingId") String wellWisherId);
			 * 
			 */

	// MATCH (u:RegisteredUser)<-[r:WELLWISHER_OF]-(wellwishers) where
		// u.userId="prasad" RETURN wellwishers,r,u

	/**
	 * save registeredUser by registered user
	 *
	 * @param registereduser the registered user
	 * @return registered users
	 */
	/*
	 * @Query("CREATE (r:RegisteredUser {firstName: " + "{firstName}" +
	 * ", userId:"+"{userId}" + "}) return r;") RegisteredUser
	 * saveNewUser(@Param("firstName") String firstName,@Param("userId") String
	 * userId);
	 * 
	 * 
	 *//**
		 * save registeredUser by registered user
		 *
		 * @param registereduser the registered user
		 * @return registered users
		 *//*
			 * @Query("CREATE (r:RegisteredUser {firstName:\"sanila\", userId:\"sanila\"})return r;"
			 * ) RegisteredUser saveNew(RegisteredUser registeredUser);
			 */

}

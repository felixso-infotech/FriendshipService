package com.felixsoinfotech.friendship.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.felixsoinfotech.friendship.domain.RegisteredUser;

@RepositoryRestResource(collectionResourceRel = "friends", path = "friends")
public interface RegisteredUserRepository extends Neo4jRepository<RegisteredUser, String> {

	@Override
	RegisteredUser save(RegisteredUser entity);

	List<RegisteredUser> findByName(@Param("name") String name);

	@Query("MATCH (u:RegisteredUser{userId:" + " {userId} " + "}),(u)-[r:FRIEND_OF]-(friends) RETURN friends;")
	List<RegisteredUser> findAllFriends(@Param("userId") String userId);

	// method to make friend or accept friend request

	@Query("MATCH (u:RegisteredUser{userId:" + " {userId} " + "}),(f:RegisteredUser{userId:" + " {friendId} "
			+ "}) create (u)-[:FRIEND_OF]->(f) RETURN u;")
	RegisteredUser acceptFriendRequest(@Param("userId") String userId, @Param("friendId") String friendId);

	// method to unfriend an existing friend

	@Query("match (u:RegisteredUser{userId:" + "{userId}" + "}),(f:RegisteredUser{userId:" + "{friendId}"
			+ "}),(u)-[r:FRIEND_OF]-(f) delete r;")
	RegisteredUser unfriend(@Param("userId") String userId, @Param("friendId") String friendId);

	// method to make an friend request relation between friends
	@Query("match (u:RegisteredUser{userId:" + "{userId}" + "}),(f:RegisteredUser{userId:" + "{friendId}"
			+ "}) create (u)-[:FRIEND_REQUEST]->(f) return f")
	RegisteredUser createAnFriendRequest(@Param("userId") String userId, @Param("friendId") String friendId);

	// get all friend requests get for this user
	@Query("match (u:RegisteredUser{userId:" + "{ userId }"
			+ "}),(u)<-[:FRIEND_REQUEST]-(requesters) return requesters;")
	List<RegisteredUser> findAllFriendRequests(@Param("userId") String userId);

	// method to cancel friend requests
	@Query("match (u:RegisteredUser{userId:" + "{userId}" + "}),(f:RegisteredUser{userId:" + "{friendId}"
			+ "}),(u)-[r:FRIEND_REQUEST]-(f) delete r;")
	RegisteredUser cancelFriendRequest(@Param("userId") String userId, @Param("friendId") String friendId);

	// get all users with the given name from the friend list
	@Query("match (u:RegisteredUser{userId:" + "{ userId }" + "}),(friends:RegisteredUser{name:" + "{ name }"
			+ "}),(u)-[:FRIEND_OF]-(friends) return friends limit 25;")
	List<RegisteredUser> findAllFriendsWithName(@Param("userId") String userId, @Param("name") String name);

	// get all users with the given name from the friendrequest list
	@Query("match (u:RegisteredUser{userId:" + "{ userId }" + "}),(friendRequests:RegisteredUser{name:" + "{ name }"
			+ "}),(u)<-[:FRIEND_REQUEST]-(friendRequests) return friendRequests limit 25;")
	List<RegisteredUser> findAllFriendRequestsWithName(@Param("userId") String userId, @Param("name") String name);

	// get all users except people in friend list or friend request list by name
	@Query("MATCH (b:RegisteredUser{name:" + "{name}" + "}),(a:RegisteredUser{userId:" + "{userId}"
			+ "}) WHERE NOT (a)-[:FRIEND_OF]-(b) and  not (a)-[:FRIEND_REQUEST]-(b) and  b.userId <> " + "{userId}"
			+ " return b limit 30;")
	List<RegisteredUser> findAllUsersExceptFriendRequesOrFriends(@Param("userId") String userId,
			@Param("name") String name);

	// -----------------------------------------------
	// method to create well wishers
	@Query("match (u:RegisteredUser{userId:" + "{userId}" + "}),(w:RegisteredUser{userId:" + "{friendId}"
			+ "}) create (u)-[:WELLWISHER_OF]->(w) return w;")
	RegisteredUser createWellWisher(@Param("userId") String userId, @Param("friendId") String friendId);

	// To find well wishers of registered user by userId
	@Query("MATCH (u:RegisteredUser{userId:" + " {userId} "
			+ "}),(u)-[r:WELLWISHER_OF]-(wellwishers) RETURN wellwishers;")
	List<RegisteredUser> findAllWellWishersByUserId(@Param("userId") String userId);

	// To find well wishers of registered user by userId
	RegisteredUser findByUserId(@Param("userId") String userId);

	// To find mutaul well wishers of two registered user
	@Query("MATCH (u1:RegisteredUser {userId:" + "{userId1}"
			+ "})-[:WELLWISHER_OF]-(ww:RegisteredUser)-[:WELLWISHER_OF]-(u2:RegisteredUser {userId:" + "{userId2}"
			+ "}) RETURN  ww")
	List<RegisteredUser> findMutualFriends(@Param("userId1") String userId1, @Param("userId2") String userId2);

}

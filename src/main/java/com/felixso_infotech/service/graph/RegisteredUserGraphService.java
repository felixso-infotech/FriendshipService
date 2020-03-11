package com.felixso_infotech.service.graph;


import java.util.List;

import com.felixso_infotech.domain.graph.RegisteredUser;
import com.felixso_infotech.domain.graph.WellwisherAndRelationship;

public interface RegisteredUserGraphService {

	/**
	 * Create a well wisher or friend relationship.
	 *
	 * @param currentUser the registered user id
	 * @param registeredUser the registered user id
	 * @return the String value
	 */
	public String createWellWisherOrFriend(RegisteredUser currentUser, RegisteredUser registeredUser);
	
	/**
	 * Delete a Wellwisher Or Friend relationship.
	 *
	 * @param currentUserId the registered user id
	 * @param registeredUserId the registered user id
	 * @return the String value
	 */
	public String unFollowOrUnFriend(String currentUserId, String registeredUserId);

	/**
	 * Find all well wishers by registered user id
	 *
	 * @param userId the registered user id
	 * @return list of well wisher registered users
	 */
	public List<WellwisherAndRelationship> findAllWellWishersByUserId(String userId);

	/**
	 * Find all well wishing by registered user id
	 *	
	 * @param userId the registered user id
	 * @return list of well wishing registered users
	 */
	public List<WellwisherAndRelationship> findAllWellWishingByUserId(String userId);

	/**
	 * get count well wishers by registered user id
	 *
	 * @param userId the registered user id
	 * @return count of well wishers registered users
	 */
	Long countOfWellWishersByUserId(String userId);

	/**
	 * get count well wishing by registered user id
	 *
	 * @param userId the registered user id
	 * @return count of well wishing registered users
	 */
	Long countOfWellWishingByUserId(String userId);

	/**
	 * Check a well wisher relationship.
	 *
	 * @param userId        the registered user id
	 * @param wellWishingId the registered user id
	 * @return Boolean value if relationship exist or not
	 */
	public Boolean checkRegisteredUserIsFollowed(String userId, String wellWisherId);

	/**
	 * Check a Friend relationship.
	 *
	 * @param userId        the registered user id
	 * @param friend the registered user id
	 * @return Boolean value if relationship exist or not
	 */
	public Boolean checkRegisteredUserIsFriend(String userId, String friendId);

	/**
	 * Check a wellwisher or Friend relationship.
	 *
	 * @param currentUserId    the registered user id
	 * @param registeredUserId the registered user id
	 * @return Boolean value if relationship exist or not
	 */
	public Boolean checkRegisteredUserIsFollowedOrIsFriend(String currentUserId, String registeredUserId);

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// testing............................................................................................................................

	/*
	*//**
		 * Create a well wisher relationship.
		 *
		 * @param userId       the registered user id
		 * @param wellWisherId the registered user id
		 * @return the well wisher registered user
		 */
	/*
	 * public String createWellWisherAndWellWishing(RegisteredUser
	 * currentUser,RegisteredUser registeredUser);
	 * 
	 * public Boolean createFriend(String cureentUserId,String friendId);
	 * 
	 * public List<WellwisherAndRelationship>
	 * findAllWellWishersWithFriendsByUserId(String userId);
	 * 
	 * public RegisteredUser createRegisteredUser(RegisteredUser registeredUser);
	 * 
	 * RegisteredUser getRegisteredUserIsExist(String userId);
	 * 
	 *//**
		 * Check a well wishing relationship.
		 *
		 * @param userId        the registered user id
		 * @param wellWishingId the registered user id
		 * @return the well wishing registered user
		 *//*
			 * public Boolean checkRegisteredUserIsFollowing(String userId, String
			 * wellWishingId);
			 */

}

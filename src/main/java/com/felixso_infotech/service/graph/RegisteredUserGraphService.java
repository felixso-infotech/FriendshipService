package com.felixso_infotech.service.graph;

import java.util.List;


import com.felixso_infotech.domain.graph.RegisteredUser;
import com.felixso_infotech.domain.graph.WellwisherAndRelationship;

public interface RegisteredUserGraphService {
	
	/**
	 * Create a well wisher relationship.
	 *
	 * @param userId       the registered user id
	 * @param wellWisherId the registered user id
	 * @return the well wisher registered user
	 */
	public String createWellWisherAndWellWishing(RegisteredUser currentUser,RegisteredUser registeredUser);
		
	/**
	 * Find all well wishers by registered user id
	 *
	 * @param userId the registered user id
	 * @return list of well wisher registered users
	 */
	public List<RegisteredUser> findAllWellWishersByUserId(String userId);
	
	public WellwisherAndRelationship findAllWellWishersWithFriendsByUserId(String userId);
	
	/**
	 * Find all well wishing by registered user id
	 *
	 * @param userId the registered user id
	 * @return list of well wisher registered users
	 */
	public List<RegisteredUser> findAllWellWishingByUserId(String userId);
	
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

    RegisteredUser getRegisteredUserIsExist(String userId);
    
    /**
	 * Check a well wishing relationship.
	 *
	 * @param userId        the registered user id
	 * @param wellWishingId the registered user id
	 * @return the well wishing registered user
	 */
	public Boolean checkRegisteredUserIsFollowing(String userId, String wellWishingId);
	
	/**
	 * Check a well wisher relationship.
	 *
	 * @param userId        the registered user id
	 * @param wellWishingId the registered user id
	 * @return the well wisher registered user
	 */
	public Boolean checkRegisteredUserIsFollowed(String userId, String wellWisherId);


	/**
	 * Check a Friend relationship.
	 *
	 * @param currentUserId the registered user id
	 * @param registeredUserId the registered user id
	 * @return the FRIEND_OF registered user
	 */
	public Boolean checkRegisteredUsersAreFriends(String currentUserId,String registeredUserId);
	

	

}

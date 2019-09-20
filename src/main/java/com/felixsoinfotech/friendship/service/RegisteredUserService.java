package com.felixsoinfotech.friendship.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.felixsoinfotech.friendship.domain.RegisteredUser;

public interface RegisteredUserService {

	public RegisteredUser create(RegisteredUser user);

	public List<RegisteredUser> findByName(String name);

	public List<RegisteredUser> findFriends(String userId);

	public RegisteredUser acceptFriendRequest(String userId, String friendId);

	public RegisteredUser unfriend(String userId, String friendId);

	public Boolean delete(RegisteredUser user);

	public RegisteredUser createFriendRequest(String userId, String friendId);

	public List<RegisteredUser> findAllFriendRequests(String userId);

	public RegisteredUser cancelFriendRequest(String userId, String friendId);

	public List<RegisteredUser> findFriendRequestByname(String userId, String name);

	public List<RegisteredUser> findFriendsByname(String userId, String name);

	public List<RegisteredUser> findOthersByname(String userId, String name);

	/**
	 * Create a well wisher relationship.
	 *
	 * @param userId       the registered user id
	 * @param wellWisherId the registered user id
	 * @return the well wisher registered user
	 */
	public RegisteredUser createWellWisher(String userId, String wellWisherId);

	/**
	 * Find all well wishers by registered user id
	 *
	 * @param userId the registered user id
	 * @return list of well wisher registered users
	 */
	public List<RegisteredUser> findAllWellWishersByUserId(@PathVariable String userId);

	/**
	 * Find a registered user.
	 *
	 * @param userId the registered user id
	 * @return the well wisher registered user
	 */
	public RegisteredUser findByUserId(String userId);

	/**
	 * Find mutual well wishers
	 *
	 * @param userId1 the registered user id
	 * @param userId2 the registered user id
	 * @return list of well wisher registered userss
	 */
	public List<RegisteredUser> findMutualWellWishers(String userId1, String userId2);

}

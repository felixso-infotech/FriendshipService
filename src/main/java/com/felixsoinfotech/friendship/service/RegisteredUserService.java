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

	public RegisteredUser createWellWisher(String userId, String friendId);

	public List<RegisteredUser> findAllWellWishersByUserId(@PathVariable String userId);

	public RegisteredUser findByUserId(String userId);

	public List<RegisteredUser> findMutualFriends(String userId1, String userId2);

}

package com.felixsoinfotech.friendship.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felixsoinfotech.friendship.domain.RegisteredUser;
import com.felixsoinfotech.friendship.repository.RegisteredUserRepository;
import com.felixsoinfotech.friendship.service.RegisteredUserService;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserService {

	private final Logger log = LoggerFactory.getLogger(RegisteredUserServiceImpl.class);
	@Autowired
	private RegisteredUserRepository userRepo;

	// create an new user
	@Override
	public RegisteredUser create(RegisteredUser user) {

		return userRepo.save(user);
	}

	@Override
	public List<RegisteredUser> findByName(String name) {
		return userRepo.findByName(name);
	}

	@Override
	public List<RegisteredUser> findFriends(String userId) {

		return userRepo.findAllFriends(userId);
	}

	@Override
	public RegisteredUser acceptFriendRequest(String userId, String friendId) {

		RegisteredUser user = userRepo.acceptFriendRequest(userId, friendId);
		cancelFriendRequest(userId, friendId);
		return user;
	}

	@Override
	public RegisteredUser unfriend(String userId, String friendId) {

		return userRepo.unfriend(userId, friendId);
	}

	@Override
	public Boolean delete(RegisteredUser user) {

		Boolean result = true;
		try {
			userRepo.delete(user);
		} catch (Exception e) {
			log.error("error while removing user " + e);
			result = false;
		}
		return result;

	}

	@Override
	public RegisteredUser createFriendRequest(String userId, String friendId) {

		return userRepo.createAnFriendRequest(userId, friendId);
	}

	@Override
	public List<RegisteredUser> findAllFriendRequests(String userId) {

		return userRepo.findAllFriendRequests(userId);
	}

	@Override
	public RegisteredUser cancelFriendRequest(String userId, String friendId) {

		return userRepo.cancelFriendRequest(userId, friendId);
	}

	@Override
	public List<RegisteredUser> findFriendRequestByname(String userId, String name) {

		return userRepo.findAllFriendRequestsWithName(userId, name);
	}

	@Override
	public List<RegisteredUser> findFriendsByname(String userId, String name) {

		return userRepo.findAllFriendsWithName(userId, name);
	}

	@Override
	public List<RegisteredUser> findOthersByname(String userId, String name) {

		return userRepo.findAllUsersExceptFriendRequesOrFriends(userId, name);
	}

	@Override
	public RegisteredUser createWellWisher(String userId, String friendId) {

		return userRepo.createWellWisher(userId, friendId);
	}

	@Override
	public List<RegisteredUser> findAllWellWishersByUserId(String userId) {
		// TODO Auto-generated method stub
		return userRepo.findAllWellWishersByUserId(userId);
	}

	@Override
	public RegisteredUser findByUserId(String userId) {
		// TODO Auto-generated method stub
		return userRepo.findByUserId(userId);
	}

	@Override
	public List<RegisteredUser> findMutualFriends(String userId1, String userId2) {
		// TODO Auto-generated method stub
		return userRepo.findMutualFriends(userId1, userId2);
	}

}

package com.felixsoinfotech.friendship.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felixsoinfotech.friendship.domain.RegisteredUser;
import com.felixsoinfotech.friendship.service.RegisteredUserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/apis")
public class RegisteredUserController {

	private final Logger log = LoggerFactory.getLogger(RegisteredUserController.class);
	@Autowired
	private RegisteredUserService userService;

	@PostMapping("/registeredUser")
	public RegisteredUser createUser(@RequestBody RegisteredUser user) {
		log.debug(">>>>>>>>>>>>>>>>user " + user.getName());
		userService.create(user);
		return user;

	}

	@GetMapping("/registeredUser/{name}")
	public List<RegisteredUser> findUserByName(@PathVariable String name) {

		log.debug(">>>>>>>>>>>>>>>>username " + name);

		return userService.findByName(name);

	}

	@GetMapping("/registeredUser/{userId}/friends")
	public List<RegisteredUser> findFriends(@PathVariable String userId) {
		log.debug(">>>>>>>>>>>>>>>>userId " + userId);
		return userService.findFriends(userId);
	}

	@PostMapping("/registeredUser/{userId}/accept/{friendId}")
	public RegisteredUser acceptFriendRequest(@PathVariable String userId, @PathVariable String friendId) {
		log.debug("request to accept friend request from userId:" + userId + " friendId:" + friendId);
		return userService.acceptFriendRequest(userId, friendId);
	}

	@DeleteMapping("/registeredUser/{userId}/unfriendUser/{friendId}")
	public RegisteredUser unfriend(@PathVariable String userId, @PathVariable String friendId) {
		log.debug("request to unfriend userId:" + userId + " friendId:" + friendId);
		return userService.unfriend(userId, friendId);
	}

	@DeleteMapping("/registeredUser")
	public Boolean delete(@RequestBody RegisteredUser user) {
		log.debug("request to delete  user :" + user);
		return userService.delete(user);
	}

	@PostMapping("/createFriendRequest/registeredUser/{userId}/friend/{friendId}")
	public void friendRequest(@PathVariable String userId, @PathVariable String friendId) {
		log.debug("request to sent friend request  userId:" + userId + " friendId:" + friendId);
		userService.createFriendRequest(userId, friendId);

	}

	@GetMapping("/friendRequest/registeredUser/{userId}")
	public List<RegisteredUser> findAllFriendRequests(@PathVariable String userId) {

		log.debug("request to get all friendrequests of userId:" + userId);

		return userService.findAllFriendRequests(userId);

	}

	@DeleteMapping("/registeredUser/{userId}/cancelFriendRequest/{friendId}")
	public RegisteredUser cancelFriendRequest(@PathVariable String userId, @PathVariable String friendId) {
		log.debug("request to unfriend userId:" + userId + " friendId:" + friendId);
		return userService.cancelFriendRequest(userId, friendId);
	}

	@GetMapping("/registeredUsersByName/{name}")
	public List<RegisteredUser> findByUserName(@PathVariable String name) {
		log.debug("requets to find all user with name " + name);
		return userService.findByName(name);

	}

	@GetMapping("/{userId}/friendRequestByName/{name}")
	public List<RegisteredUser> findFriendRequestByName(@PathVariable String userId, @PathVariable String name) {
		log.debug("requets to find all friendRequest with name " + name);
		return userService.findFriendRequestByname(userId, name);

	}

	@GetMapping("/{userId}/friendsByName/{name}")
	public List<RegisteredUser> findFriendsByName(@PathVariable String userId, @PathVariable String name) {
		log.debug(">>>>>>>>>>>>requets to find all friendRequest with name " + name);
		return userService.findFriendsByname(userId, name);

	}

	// rest method to find people that are not in friendlist or friend request list
	@GetMapping("/{userId}/othersByName/{name}")
	public List<RegisteredUser> findOthersByname(@PathVariable String userId, @PathVariable String name) {
		log.debug("requets to find all friendRequest with name " + name);
		return userService.findOthersByname(userId, name);
	}

//------------------------------------------------------------------
	// To create well wisher of a registered user
	@PostMapping("/createWellWisher/registeredUser/{userId}/{wellWisherId}")
	public RegisteredUser createWellWisher(@PathVariable String userId, @PathVariable String wellWisherId) {
		return userService.createWellWisher(userId, wellWisherId);
	}

	// To find well wishers of registered user by userId
	@GetMapping("/registeredUser/wellWisher/{userId}")
	public List<RegisteredUser> findAllWellWishersByUserId(@PathVariable String userId) {
		return userService.findAllWellWishersByUserId(userId);
	}

	// To find well wishers of registered user by userId
	@GetMapping("/registeredUserByUserId/{userId}")
	public RegisteredUser findByUserId(@PathVariable String userId) {
		return userService.findByUserId(userId);
	}

	// To find mutaul well wishers of two registered user
	@GetMapping("/registeredUserMutaulWellWisher/{userId1}/{userId2}")
	public List<RegisteredUser> findMutualFriends(@PathVariable String userId1, @PathVariable String userId2) {
		return userService.findMutualFriends(userId1, userId2);
	}
}

package com.felixso_infotech.graph.web.rest;


import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.felixso_infotech.domain.graph.RegisteredUserModel;
import com.felixso_infotech.domain.graph.WellwisherAndRelationship;
import com.felixso_infotech.service.graph.RegisteredUserGraphService;


/**
 * REST controller for managing
 * {@link com.felixso_infotech.domain.RegisteredUser}.
 */
@RestController
@RequestMapping("/api/graph")
public class RegisteredUserGraphResource {

	private final Logger log = LoggerFactory.getLogger(RegisteredUserGraphResource.class);

	private RegisteredUserGraphService registeredUserGraphService;

	public RegisteredUserGraphResource(RegisteredUserGraphService registeredUserGraphService) {
		this.registeredUserGraphService = registeredUserGraphService;
	}

	/**
     * POST /create-wellwisher-friend/registeredUser/ : create WellWisher Or Friend.
     *
     * @param RegisteredUserModel the RegisteredUserModel to create.
     * @return the String value
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
	@PostMapping("/create-wellwisher-friend/registeredUser/")
	public String createWellWisherOrFriend(@RequestBody RegisteredUserModel registeredUserModel)throws URISyntaxException {
		
		log.debug("request to create welwisher-wellwishing  currentuser:{} registeredUser:{}",registeredUserModel.getCurrentUser(),registeredUserModel.getRegisteredUser());

		return registeredUserGraphService.createWellWisherOrFriend(registeredUserModel.getCurrentUser(),registeredUserModel.getRegisteredUser());
	}
	
	/**
     * POST /unFollow-unFriend/registeredUser/{currentUserId}/{registeredUserId} : unFollow Or UnFriend.
     *
     * @param currentUserId the registered user id
     * @param registeredUserId the registered user id
     * 
     * @return the String value
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
	@PostMapping("/unFollow-unFriend/registeredUser/{currentUserId}/{registeredUserId}")
	public String unFollowOrUnFriend(@PathVariable String currentUserId,@PathVariable String registeredUserId)throws URISyntaxException {
		
		log.debug("request to create welwisher-wellwishing  currentuserId:{} registeredUserId:{}",currentUserId,registeredUserId);

		return registeredUserGraphService.unFollowOrUnFriend(currentUserId,registeredUserId);
	}
	
	/**
	 * GET /registeredUser/wellWisher/{userId} : get all well wishers by user id
	 * @param userId the registered user id
	 * @return list of wellwisher registered users
	 */
	@GetMapping("/registeredUser/well-Wishers/{userId}")
	public List<WellwisherAndRelationship> findAllWellWishersByUserId(@PathVariable String userId) {
		
		log.debug("request to get All WellWishers By UserId:{} ",userId);
		
		return registeredUserGraphService.findAllWellWishersByUserId(userId);
	}

	/**
	 * GET /registeredUser/wellWishing/{userId} : get all well wishers by user id
	*	
	 * @param userId the registered user id
	 * @return list of wellwishing registered users
	 */
	@GetMapping("/registeredUser/well-Wishing/{userId}")
	public List<WellwisherAndRelationship> findAllWellWishingByUserId(@PathVariable String userId) {
		
		log.debug("request to get All WellWishing By UserId:{} ",userId);
		
		return registeredUserGraphService.findAllWellWishingByUserId(userId);
	}

	/**
	 * GET /registeredUser/wellWisher/{userId} : get count well wishers by user id
	 * 
	 * @param userId the registered user id
	 * @return count of wellwishers registered users
	 */
	@GetMapping("/registeredUser/wellWishers-count/{userId}")
	public Long countOfWellWishersByUserId(@PathVariable String userId) {
		
		log.debug("request to get count WellWishers By UserId:{} ",userId);
		
		return registeredUserGraphService.countOfWellWishersByUserId(userId);
	}

	/**
	 * GET /registeredUser/wellWishing/{userId} : get count well wishing by user id
	 *
	 * @param userId the registered user id
	 * @return count of well wishing registered users
	 */
	@GetMapping("/registeredUser/wellWishing-count/{userId}")
	public Long countOfWellWishingByUserId(@PathVariable String userId) {
		
		log.debug("request to get count WellWishing By UserId:{} ",userId);
		
		return registeredUserGraphService.countOfWellWishingByUserId(userId);
	}

	/**
	 * GET /registeredUser/is-Followed/{userId}/{wellWisherId} : check RegisteredUser Is Followed by user id
	 *
	 * @param userId        the registered user id
	 * @param wellWisherId the registered user id
	 * @return Boolean value if relationship exist or not
	 */
	@GetMapping("/registeredUser/is-Followed/{userId}/{wellWisherId}")
	public Boolean checkRegisteredUserIsFollowed(@PathVariable String userId, @PathVariable String wellWisherId) {
		
		log.debug("request to check whether a user is WellWisher or not By UserId:{} wellWisherId:{}",userId,wellWisherId);	
		
		return registeredUserGraphService.checkRegisteredUserIsFollowed(userId, wellWisherId);
	}
	
	/**
	 * GET /registeredUser/is-Friend/{userId}/{friendId} : check RegisteredUser Is Friend
	 *
	 * @param userId        the registered user id
	 * @param friend the registered user id
	 * @return Boolean value if relationship exist or not
	 */
	@GetMapping("/registeredUser/is-Friend/{userId}/{friendId}")
	public Boolean checkRegisteredUserIsFriend(String userId, String friendId) {
		
		log.debug("request to check whether a user is friend or not By UserId:{} friendId:{}",userId,friendId);	
		
		return registeredUserGraphService.checkRegisteredUserIsFriend(userId, friendId);
	}


	/**
	 * GET /registeredUser/isFollowed-isFriend/{currentUserId}/{registeredUserId} : check RegisteredUser Is Followed Or Is Friend
	 *
	 * @param currentUserId the registered user id
	 * @param registeredUserId the registered user id
	 * @return Boolean value if relationship exist or not
	 */
	@GetMapping("/registeredUser/isFollowed-isFriend/{currentUserId}/{registeredUserId}")
	public Boolean checkRegisteredUserIsFollowedOrIsFriend(@PathVariable String currentUserId,@PathVariable String registeredUserId) {
		
		log.debug("request to check whether a user is wellwisher or friend or not By UserId:{} friendId:{}",currentUserId,registeredUserId);	
		
		return registeredUserGraphService.checkRegisteredUserIsFollowedOrIsFriend(currentUserId, registeredUserId);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// testing...........................................................

	/*
	 * 
	 * 
	 * @GetMapping("/registeredUser/well-Wishers-friends/{userId}") public
	 * List<WellwisherAndRelationship>
	 * findAllWellWishersWithFriendsByUserId(@PathVariable String userId) {
	 * 
	 * return
	 * registeredUserGraphService.findAllWellWishersWithFriendsByUserId(userId);
	 * 
	 * 
	 * }
	 * 
	 *//**
		 * POST /createWellWisher-wellWishing/registeredUser/ create well
		 * wisher-wellwishing relationship
		 *
		 * @param registeredUserModel the registeredUserModel.
		 *
		 */
	/*
	 * @PostMapping("/createWellWisher-wellWishing/registeredUser/") public String
	 * createWellWisherAndWellWishing(@RequestBody RegisteredUserModel
	 * registeredUserModel) throws URISyntaxException {
	 * 
	 * log.debug("request to create welwisher-wellwishing  currentuser:" +
	 * registeredUserModel.getCurrentUser() + " registeredUser:" +
	 * registeredUserModel.getRegisteredUser());
	 * 
	 * return
	 * registeredUserGraphService.createWellWisherAndWellWishing(registeredUserModel
	 * .getCurrentUser(), registeredUserModel.getRegisteredUser());
	 * 
	 * }
	 * 
	 *//**
		 * GET /registeredUser/wellWishing/{userId} : get all well wishers by user id
		 *
		 * @param userId the registered user id
		 */
	/*
	 * @GetMapping("/registeredUser/is-exist/{userId}") public RegisteredUser
	 * getRegisteredUserIsExist(@PathVariable String userId) { return
	 * registeredUserGraphService.getRegisteredUserIsExist(userId); }
	 *//**
		 * Check a well wishing relationship.
		 *
		 * @param userId        the registered user id
		 * @param wellWishingId the registered user id
		 * @return the well wishing registered user
		 *//*
			 * @GetMapping("/registeredUser/Is-Following/{userId}/{wellWishingId}") public
			 * Boolean checkRegisteredUserIsFollowing(@PathVariable String
			 * userId, @PathVariable String wellWishingId) { return
			 * registeredUserGraphService.checkRegisteredUserIsFollowing(userId,
			 * wellWishingId); }
			 * 
			 * 
			 * @PostMapping("/create-registeredUser/") public ResponseEntity<RegisteredUser>
			 * createRegisteredUser(@RequestBody RegisteredUser registeredUser)throws
			 * URISyntaxException {
			 * log.debug("**************************************request to create new user:"
			 * + registeredUser);
			 * 
			 * RegisteredUser result =
			 * registeredUserGraphService.createRegisteredUser(registeredUser);
			 * 
			 * return ResponseEntity.created(new URI("/api/registered-users/" +
			 * result.getId()))
			 * .headers(HeaderUtil.createEntityCreationAlert(applicationName, true,
			 * ENTITY_NAME, result.getId().toString())) .body(result); }
			 * 
			 * @PostMapping("/create-friend/registeredUser/") public Boolean
			 * createFriend(@RequestBody RegisteredUserModel registeredUserModel)throws
			 * URISyntaxException { log.debug("request to create friend currentuser:" +
			 * registeredUserModel.getCurrentUser() + " registeredUser:" +
			 * registeredUserModel.getRegisteredUser());
			 * 
			 * return
			 * registeredUserGraphService.createFriend(registeredUserModel.getCurrentUser().
			 * getUserId(),registeredUserModel.getRegisteredUser().getUserId()); }
			 */

}

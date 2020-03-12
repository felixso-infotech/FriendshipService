package com.felixso_infotech.graph.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felixso_infotech.domain.graph.RegisteredUser;
import com.felixso_infotech.domain.graph.WellwisherAndRelationship;
import com.felixso_infotech.repository.graph.RegisteredUserGraphRepository;
import com.felixso_infotech.service.graph.RegisteredUserGraphService;

@Service
@Transactional("graphTransactionManager")
public class RegisteredUserGraphServiceImpl implements RegisteredUserGraphService {

	private final Logger log = LoggerFactory.getLogger(RegisteredUserGraphServiceImpl.class);

	private RegisteredUserGraphRepository registeredUserGraphRepository;

	public RegisteredUserGraphServiceImpl(RegisteredUserGraphRepository registeredUserGraphRepository) {
		this.registeredUserGraphRepository = registeredUserGraphRepository;
	}

	/**
	 * Create a well wisher or friend relationship.
	 *
	 * @param currentUser the registered user id
	 * @param registeredUser the registered user id
	 * @return the String value
	 */
	@Override
	public String createWellWisherOrFriend(RegisteredUser currentUser, RegisteredUser registeredUser) {
		log.debug("request to create welwisher-wellwishing  currentuser:{} registeredUser:{}", currentUser,registeredUser);

		RegisteredUser currentUser1 = registeredUserGraphRepository.findByUserId(currentUser.getUserId());

		// System.out.println("******************************findByUserId method : \t
		// "+currentUser1);

		if (currentUser1 == null)
			currentUser1 = registeredUserGraphRepository.save(currentUser);

		RegisteredUser registeredUser1 = registeredUserGraphRepository.findByUserId(registeredUser.getUserId());

		// System.out.println("******************************findByUserId method : \t
		// "+registeredUser1);

		if (registeredUser1 == null)
			registeredUser1 = registeredUserGraphRepository.save(registeredUser);

		//System.out.println("******************************************************************************************************************"+registeredUserGraphRepository.checkRegisteredUserIsFollowedOrIsFriend(currentUser1.getUserId(),registeredUser1.getUserId()));
		
		if (!(registeredUserGraphRepository.checkRegisteredUserIsFollowedOrIsFriend(currentUser1.getUserId(),registeredUser1.getUserId()))) {
			if (registeredUserGraphRepository.checkRegisteredUserIsFollowed(registeredUser1.getUserId(),currentUser1.getUserId()))
				registeredUserGraphRepository.createFriend(currentUser1.getUserId(), registeredUser1.getUserId());
			else
				registeredUserGraphRepository.createWellWisher(currentUser1.getUserId(), registeredUser1.getUserId());

			return "Sucess";
		}

		else
			return "Relationship already exist";

	}
	
	/**
	 * Delete a Wellwisher Or Friend relationship.
	 *
	 * @param currentUserId the registered user id
	 * @param registeredUserId the registered user id
	 * @return the String value
	 */
	@Override
	public String unFollowOrUnFriend(String currentUserId, String registeredUserId)
	{
		log.debug("request to unFollowOrUnFriend currentuserId:{} registeredUserId:{}", currentUserId,registeredUserId);
		
		RegisteredUser currentUser1 = registeredUserGraphRepository.findByUserId(currentUserId);
		
		RegisteredUser registeredUser1 = registeredUserGraphRepository.findByUserId(registeredUserId);

		if (currentUser1 != null && registeredUser1 != null)
		{
			if((registeredUserGraphRepository.checkRegisteredUserIsFollowed(currentUser1.getUserId(),registeredUser1.getUserId())) && (registeredUserGraphRepository.checkRegisteredUserIsFriend(currentUser1.getUserId(),registeredUser1.getUserId())))
			{
				//if(registeredUserGraphRepository.unFriend(currentUser1.getUserId(),registeredUser1.getUserId()) && registeredUserGraphRepository.unFollow(currentUser1.getUserId(),registeredUser1.getUserId()))
				
				if(registeredUserGraphRepository.unFollow(currentUser1.getUserId(),registeredUser1.getUserId()) && registeredUserGraphRepository.unFriend(currentUser1.getUserId(),registeredUser1.getUserId()))
				  {				
					registeredUserGraphRepository.createWellWisher(registeredUser1.getUserId(), currentUser1.getUserId());
					return "Sucess 1st";
				  }
				else
					return "something wrong";				

			}	
			
			else if(registeredUserGraphRepository.checkRegisteredUserIsFollowed(currentUser1.getUserId(),registeredUser1.getUserId()))
			{
				registeredUserGraphRepository.unFollow(currentUser1.getUserId(),registeredUser1.getUserId());
				return "Sucess 2nd";
			}
			
			else if(registeredUserGraphRepository.checkRegisteredUserIsFriend(currentUser1.getUserId(),registeredUser1.getUserId()))
			{
				registeredUserGraphRepository.unFriend(currentUser1.getUserId(), registeredUser1.getUserId());
				return "Sucess 3rd";
			}
			
			else
			{
				return "relationship already delete";
			}
			
			
		}
		
		else 
			
	       return "The users and relationships not exist";
				
	}

	/**
	 * Check a well wisher relationship.
	 *
	 * @param userId        the registered user id
	 * @param wellWishingId the registered user id
	 * @return Boolean value if relationship exist or not
	 */
	@Override
	public Boolean checkRegisteredUserIsFollowed(String userId, String wellWisherId) {
		log.debug("get checkRegisteredUserIsFollowed:" + userId + " " + wellWisherId);
		return registeredUserGraphRepository.checkRegisteredUserIsFollowed(userId, wellWisherId);
	}
	
	/**
	 * Check a Friend relationship.
	 *
	 * @param userId        the registered user id
	 * @param friend the registered user id
	 * @return Boolean value if relationship exist or not
	 */
	@Override
	public Boolean checkRegisteredUserIsFriend(String userId, String friendId) {
		log.debug("get checkRegisteredUserIsFriend:" + userId + " " + friendId);
		return registeredUserGraphRepository.checkRegisteredUserIsFriend(userId, friendId);
	}

	/**
	 * Check a wellwisher or Friend relationship.
	 *
	 * @param currentUserId    the registered user id
	 * @param registeredUserId the registered user id
	 * @return Boolean value if relationship exist or not
	 */
	@Override
	public Boolean checkRegisteredUserIsFollowedOrIsFriend(String currentUserId, String registeredUserId) {
		log.debug("get checkRegisteredUserIsFollowedOrIsFriend:" + currentUserId + " " + registeredUserId);
		return registeredUserGraphRepository.checkRegisteredUserIsFollowedOrIsFriend(currentUserId, registeredUserId);
	}

	/**
	 * Find all well wishers by registered user id
	 *
	 * @param userId the registered user id
	 * @return list of well wisher registered users
	 */
	@Override
	public List<WellwisherAndRelationship> findAllWellWishersByUserId(String userId) {

		log.debug("getall welwishers:" + userId);
		return registeredUserGraphRepository.findAllWellWishersByUserId(userId);
	}

	/**
	 * Find all well wishing by registered user id
	 *
	 * @param userId the registered user id
	 * @return list of well wishing registered users
	 */

	@Override
	public List<WellwisherAndRelationship> findAllWellWishingByUserId(String userId) {

		log.debug("getall welwishing:" + userId);
		return registeredUserGraphRepository.findAllWellWishingByUserId(userId);
	}

	/**
	 * get count well wishers by registered user id
	 *
	 * @param userId the registered user id
	 * @return count of well wishers registered users
	 */

	@Override
	public Long countOfWellWishersByUserId(String userId) {

		log.debug("get count welwishers:" + userId);
		return registeredUserGraphRepository.countOfWellWishersByUserId(userId);
	}

	/**
	 * get count well wishing by registered user id
	 *
	 * @param userId the registered user id
	 * @return count of well wishing registered users
	 */
	@Override
	public Long countOfWellWishingByUserId(String userId) {

		log.debug("get count welwishing:" + userId);
		return registeredUserGraphRepository.countOfWellWishingByUserId(userId);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
	 * @Override public String createWellWisherAndWellWishing(RegisteredUser
	 * currentUser,RegisteredUser registeredUser) {
	 * 
	 * log.debug("request to create welwisher-wellwishing  currentuser:" +
	 * currentUser + " registeredUser:" + registeredUser);
	 * 
	 * RegisteredUser currentUser1 = registeredUserGraphRepository.findByUserId(
	 * currentUser.getUserId());
	 * 
	 * System.out.println("******************************findByUserId method : \t "
	 * +currentUser1);
	 * 
	 * if(currentUser1 == null)
	 * currentUser1=registeredUserGraphRepository.save(currentUser);
	 * 
	 * 
	 * RegisteredUser registeredUser1 =
	 * registeredUserGraphRepository.findByUserId(registeredUser.getUserId());
	 * 
	 * //
	 * System.out.println("******************************findByUserId method : \t "
	 * +registeredUser1);
	 * 
	 * if(registeredUser1 == null)
	 * registeredUser1=registeredUserGraphRepository.save(registeredUser);
	 * 
	 * Boolean isWelwishing = false; Boolean isWelwishingBack = false; Boolean
	 * isFriends = false; Boolean isFriendsBack = false;
	 * 
	 * Boolean isWelwisher =
	 * registeredUserGraphRepository.checkRegisteredUserIsFollowed(currentUser1.
	 * getUserId(),registeredUser1.getUserId()); if(isWelwisher == false)
	 * isWelwisher =
	 * registeredUserGraphRepository.createWellWisher(currentUser1.getUserId(),
	 * registeredUser1.getUserId());
	 * 
	 * if(isWelwisher == true) {
	 * isWelwishing=registeredUserGraphRepository.checkRegisteredUserIsFollowing(
	 * currentUser1.getUserId(),registeredUser1.getUserId()); if(isWelwishing ==
	 * false) isWelwishing =
	 * registeredUserGraphRepository.createWellWishing(currentUser1.getUserId(),
	 * registeredUser1.getUserId());
	 * 
	 * 
	 * if(isWelwishing == true) isWelwishingBack =
	 * registeredUserGraphRepository.checkRegisteredUserIsFollowing(registeredUser1.
	 * getUserId(), currentUser1.getUserId());
	 * 
	 * 
	 * if(isWelwishing == true && isWelwishingBack == true) {
	 * //isFriends=registeredUserGraphRepository.checkRegisteredUserIsFriend(
	 * currentUser1.getUserId(),registeredUser1.getUserId()); if(isFriends == false)
	 * //registeredUserGraphRepository.createWellwishingsAsFriend(currentUser1.
	 * getUserId(),registeredUser1.getUserId());
	 * 
	 * if(isFriends == true) { // isFriendsBack =
	 * registeredUserGraphRepository.createWellwishingsAsFriend(registeredUser1.
	 * getUserId(),currentUser1.getUserId()); if(isFriends == true && isFriendsBack
	 * == true)
	 * System.out.println(currentUser1.getUserId()+" and "+registeredUser1.getUserId
	 * ()+"*********Become friends********\t\n"); } }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * if(isWelwisher && isWelwishing) return "Sucess"; else return "fail";
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * @Override public List<WellwisherAndRelationship>
	 * findAllWellWishersWithFriendsByUserId(String userId) {
	 * 
	 * log.debug("getall welwishers with friends:" + userId );
	 * 
	 * //System.out.println("******************************"+
	 * registeredUserGraphRepository.findAllWellWishersWithFriendsByUserId(userId)+
	 * "**************************");
	 * 
	 * //List<RegisteredUser> regUser =
	 * registeredUserGraphRepository.findAllWellWishersWithFriendsByUserId(userId);
	 * 
	 * for(RegisteredUser registeredUser : regUser) {
	 * 
	 * }
	 * 
	 * 
	 * return
	 * registeredUserGraphRepository.findAllWellWishersWithFriendsByUserId(userId);
	 * }
	 * 
	 * 
	 * 
	 * @Override public RegisteredUser getRegisteredUserIsExist(String userId) {
	 * log.debug("get registeredUser IsExist:" + userId ); return
	 * registeredUserGraphRepository.getRegisteredUserIsExist(userId); }
	 * 
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
			 * @Override public Boolean checkRegisteredUserIsFollowing(String userId, String
			 * wellWishingId) { log.debug("get checkRegisteredUserIsFollowed:" + userId +
			 * " "+wellWishingId); return
			 * registeredUserGraphRepository.checkRegisteredUserIsFollowing(userId,
			 * wellWishingId); }
			 * 
			 * 
			 * 
			 * public Boolean createFriend(String currentUserId,String friendId) { Boolean
			 * isWelwisher =
			 * registeredUserGraphRepository.checkRegisteredUserIsFollowed(currentUserId,
			 * friendId);
			 * 
			 * Boolean isFriend=registeredUserGraphRepository.
			 * checkRegisteredUserIsFollowedOrIsFriend(currentUserId,friendId);
			 * 
			 * if(isFriend == false && isWelwisher==true) isFriend =
			 * registeredUserGraphRepository.createFriend(currentUserId,friendId);
			 * 
			 * return isFriend; }
			 * 
			 * 
			 * 
			 * @Override public RegisteredUser createRegisteredUser(RegisteredUser
			 * registeredUser) { RegisteredUser newUser =
			 * registeredUserGraphRepository.findByUserId(registeredUser.getUserId());
			 * 
			 * //System.out.
			 * println("******************************findByUserId method : \t "+newUser);
			 * 
			 * if(newUser == null) return
			 * registeredUserGraphRepository.save(registeredUser); else return
			 * registeredUser;
			 * 
			 * }
			 * 
			 * 
			 * 
			 */

}
		
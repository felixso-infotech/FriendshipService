package com.felixso_infotech.graph.service.impl;



import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felixso_infotech.domain.graph.RegisteredUser;
import com.felixso_infotech.repository.graph.RegisteredUserGraphRepository;
import com.felixso_infotech.service.graph.RegisteredUserGraphService;

@Service
@Transactional("graphTransactionManager")
public class RegisteredUserGraphServiceImpl implements RegisteredUserGraphService {

	private final Logger log = LoggerFactory.getLogger(RegisteredUserGraphServiceImpl.class);
	
	private RegisteredUserGraphRepository registeredUserGraphRepository;
	
	public RegisteredUserGraphServiceImpl(RegisteredUserGraphRepository registeredUserGraphRepository)
	{
		this.registeredUserGraphRepository = registeredUserGraphRepository;
	}
	
	/**
	 * Create a well wisher relationship.
	 *
	 * @param userId       the registered user id
	 * @param wellWisherId the registered user id
	 * @return the well wisher registered user
	 */
	@Override
	public String createWellWisherAndWellWishing(RegisteredUser currentUser,RegisteredUser registeredUser) {
		
		log.debug("request to create welwisher-wellwishing  currentuser:" + currentUser + " registeredUser:" + registeredUser);
		
		RegisteredUser currentUser1 = registeredUserGraphRepository.findByUserId( currentUser.getUserId());
		
		System.out.println("******************************findByUserId method : \t "+currentUser1);
		 
		if(currentUser1 == null)
			currentUser1=registeredUserGraphRepository.save(currentUser);
				
				
	    RegisteredUser	registeredUser1 = registeredUserGraphRepository.findByUserId(registeredUser.getUserId());
	    
	   // System.out.println("******************************findByUserId method : \t "+registeredUser1);
	    
	    if(registeredUser1 == null)
	    	registeredUser1=registeredUserGraphRepository.save(registeredUser);
	    
	    Boolean isWelwishing = false;	
	    Boolean isWelwishingBack = false;
	    Boolean isFriends = false;
	    Boolean isFriendsBack = false;
	    
        Boolean isWelwisher = registeredUserGraphRepository.checkRegisteredUserIsFollowed(currentUser1.getUserId(),registeredUser1.getUserId());
         if(isWelwisher == false)
             isWelwisher = registeredUserGraphRepository.createWellWisher(currentUser1.getUserId(),registeredUser1.getUserId());
        	    
	      if(isWelwisher == true)
	      {
	    	  isWelwishing=registeredUserGraphRepository.checkRegisteredUserIsFollowing(currentUser1.getUserId(),registeredUser1.getUserId());
	    	  if(isWelwishing == false)
	    	        isWelwishing = registeredUserGraphRepository.createWellWishing(currentUser1.getUserId(),registeredUser1.getUserId());
	    	  
	    	  
	    	  if(isWelwishing == true)
	    	     isWelwishingBack = registeredUserGraphRepository.checkRegisteredUserIsFollowing(registeredUser1.getUserId(), currentUser1.getUserId());
	    	        
	    	  
	    	     if(isWelwishing == true && isWelwishingBack == true)
	    	     {
	    	    	 isFriends=registeredUserGraphRepository.checkRegisteredUsersAreFriends(currentUser1.getUserId(),registeredUser1.getUserId());
	    	    	 if(isFriends == false)
	    	    	    registeredUserGraphRepository.createWellwishingsAsFriend(currentUser1.getUserId(),registeredUser1.getUserId());
	    	    	 
	    	    	 if(isFriends == true)
	    	    	 {
	    	    	   isFriendsBack = registeredUserGraphRepository.createWellwishingsAsFriend(registeredUser1.getUserId(),currentUser1.getUserId());
	    	    	   if(isFriends == true && isFriendsBack == true)
	    	    	       System.out.println(currentUser1.getUserId()+" and "+registeredUser1.getUserId()+"*********Become friends********\t\n");
	    	    	 }  
	    	     }
	    	  
	      } 	 
	    
         
	    
	    if(isWelwisher && isWelwishing)
		    return "Sucess";
	    else
	    	return "fail";
		
	}

	/**
	 * Find all well wishers by registered user id
	 *
	 * @param userId the registered user id
	 * @return list of well wisher registered users
	 */
	@Override
	public List<RegisteredUser> findAllWellWishersByUserId(String userId) {
		
		log.debug("getall welwishers:" + userId );
		return registeredUserGraphRepository.findAllWellWishersByUserId(userId);
	}
	
	/**
	 * Find all well wishing by registered user id
	 *
	 * @param userId the registered user id
	 * @return list of well wishing registered users
	 */

	@Override
	public List<RegisteredUser> findAllWellWishingByUserId(String userId) {
		
		log.debug("getall welwishing:" + userId );
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
		
		log.debug("get count welwishers:" + userId );
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
		
		log.debug("get count welwishing:" + userId );
		return registeredUserGraphRepository.countOfWellWishingByUserId(userId);
	}

	@Override
	public RegisteredUser getRegisteredUserIsExist(String userId) {
		log.debug("get registeredUser IsExist:" + userId );
		return registeredUserGraphRepository.getRegisteredUserIsExist(userId);
	}
	
	/**
	 * Check a well wishing relationship.
	 *
	 * @param userId        the registered user id
	 * @param wellWishingId the registered user id
	 * @return the well wishing registered user
	 */
	@Override
	public Boolean checkRegisteredUserIsFollowing(String userId, String wellWishingId) {
		log.debug("get checkRegisteredUserIsFollowed:" + userId + " "+wellWishingId);
		return registeredUserGraphRepository.checkRegisteredUserIsFollowing(userId, wellWishingId);
	}
	
	/**
	 * Check a well wishing relationship.
	 *
	 * @param userId        the registered user id
	 * @param wellWishingId the registered user id
	 * @return the well wishing registered user
	 */
	@Override
	public Boolean checkRegisteredUserIsFollowed(String userId, String wellWisherId) {
		log.debug("get checkRegisteredUserIsFollowed:" + userId + " "+wellWisherId);
		return registeredUserGraphRepository.checkRegisteredUserIsFollowed(userId, wellWisherId);
	}

	/**
	 * Check a Friend relationship.
	 *
	 * @param registeredUserOneUserId the registered user id
	 * @param registeredUserTwoUserId the registered user id
	 * @return the FRIEND_OF registered user
	 */
	@Override
	public Boolean checkRegisteredUsersAreFriends(String registeredUserOneUserId, String registeredUserTwoUserId) {
		log.debug("get checkRegisteredUserIsFollowed:" + registeredUserOneUserId + " "+registeredUserTwoUserId);
		return registeredUserGraphRepository.checkRegisteredUsersAreFriends(registeredUserOneUserId, registeredUserTwoUserId);
	}
	
	
}

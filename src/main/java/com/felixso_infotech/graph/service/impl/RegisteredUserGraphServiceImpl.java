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
	public RegisteredUser createWellWisherAndWellWishing(RegisteredUser currentUser,RegisteredUser registeredUser) {
		
		//log.debug("request to create welwisher-wellwishing  currentuser:" + currentUser + " registeredUser:" + registeredUser);
		
		RegisteredUser currentUser1 = registeredUserGraphRepository.findByUserId( currentUser.getUserId());
		if(currentUser1 != null)
			currentUser1=registeredUserGraphRepository.save(currentUser1);
				
	    RegisteredUser	registeredUser1 = registeredUserGraphRepository.findByUserId(registeredUser.getUserId());
	    if(registeredUser1 != null)
	    	registeredUser1=registeredUserGraphRepository.save(registeredUser1);
	    
	    //registeredUserGraphRepository.createWellWishing(currentUser.getUserId(),registeredUser.getUserId());

	    return registeredUserGraphRepository.createWellWisher(currentUser.getUserId(),registeredUser.getUserId());
		
		
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
	
	
}

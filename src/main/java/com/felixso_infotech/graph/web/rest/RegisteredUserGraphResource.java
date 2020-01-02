package com.felixso_infotech.graph.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felixso_infotech.domain.graph.RegisteredUser;
import com.felixso_infotech.domain.graph.RegisteredUserModel;
import com.felixso_infotech.service.graph.RegisteredUserGraphService;

import io.github.jhipster.web.util.HeaderUtil;


/**
 * REST controller for managing {@link com.felixso_infotech.domain.RegisteredUser}.
 */
@RestController
@RequestMapping("/api/graph")
public class RegisteredUserGraphResource {
	
	private final Logger log = LoggerFactory.getLogger(RegisteredUserGraphResource.class);
	
	private static final String ENTITY_NAME = "jhipsterMysqlNeo4JSampleRegisteredUser";
	
	@Value("${jhipster.clientApp.name}")
    private String applicationName;

    private RegisteredUserGraphService registeredUserGraphService;

    public RegisteredUserGraphResource(RegisteredUserGraphService registeredUserGraphService) {
        this.registeredUserGraphService = registeredUserGraphService;
    }
    
    /**
	 * POST /createWellWisher-wellWishing/registeredUser/ create well
	 * wisher-wellwishing relationship
	 *
	 * @param registeredUserModel the registeredUserModel.
	 *
	 */
	@PostMapping("/createWellWisher-wellWishing/registeredUser/")
	public ResponseEntity<RegisteredUser> createWellWisherAndWellWishing(@RequestBody RegisteredUserModel registeredUserModel) throws URISyntaxException {
		
		log.debug("request to create welwisher-wellwishing  currentuser:" + registeredUserModel.getCurrentUser() + " registeredUser:" + registeredUserModel.getRegisteredUser());
		
		RegisteredUser result = registeredUserGraphService.createWellWisherAndWellWishing(registeredUserModel.getCurrentUser(), registeredUserModel.getRegisteredUser());
		
		return ResponseEntity.created(new URI("/api/registered-users/"))
	            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,"result"))
	            .body(result);
	}

	/**
	 * GET /registeredUser/wellWisher/{userId} : get all well wishers by user id
	 *
	 * @param userId the registered user id
	 */
	@GetMapping("/registeredUser/well-Wishers/{userId}")
	public List<RegisteredUser> findAllWellWishersByUserId(@PathVariable String userId) {
		return registeredUserGraphService.findAllWellWishersByUserId(userId);
	}
	
	/**
	 * GET /registeredUser/wellWishing/{userId} : get all well wishers by user id
	 *
	 * @param userId the registered user id
	 */
	@GetMapping("/registeredUser/well-Wishing/{userId}")
	public List<RegisteredUser> findAllWellWishingByUserId(@PathVariable String userId) {
		return registeredUserGraphService.findAllWellWishingByUserId(userId);
	}
	
	/**
	 * GET /registeredUser/wellWisher/{userId} : get count well wishers by user id
	 *
	 * @param userId the registered user id
	 */
	@GetMapping("/registeredUser/wellWishers-count/{userId}")	
	public Long countOfWellWishersByUserId(@PathVariable String userId) {
		return registeredUserGraphService.countOfWellWishersByUserId(userId);
	}
	
	/**
	 * GET /registeredUser/wellWishing/{userId} : get count well wishing by user id
	 *
	 * @param userId the registered user id
	 */
	@GetMapping("/registeredUser/wellWishing-count/{userId}")
	public Long countOfWellWishingByUserId(@PathVariable String userId) {
		return registeredUserGraphService.countOfWellWishingByUserId(userId);
	}
	
}

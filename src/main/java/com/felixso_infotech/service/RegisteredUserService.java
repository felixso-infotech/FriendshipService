package com.felixso_infotech.service;


import com.felixso_infotech.service.dto.RegisteredUserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.felixso_infotech.domain.RegisteredUser}.
 */
public interface RegisteredUserService {
	
	

    /**
     * Save a registeredUser.
     *
     * @param registeredUserDTO the entity to save.
     * @return the persisted entity.
     */
    RegisteredUserDTO save(RegisteredUserDTO registeredUserDTO);

    /**
     * Get all the registeredUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RegisteredUserDTO> findAll(Pageable pageable);


    /**
     * Get the "id" registeredUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RegisteredUserDTO> findOne(Long id);

    /**
     * Delete the "id" registeredUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

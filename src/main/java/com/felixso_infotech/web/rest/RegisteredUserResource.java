package com.felixso_infotech.web.rest;

import com.felixso_infotech.service.RegisteredUserService;
import com.felixso_infotech.web.rest.errors.BadRequestAlertException;
import com.felixso_infotech.service.dto.RegisteredUserDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.felixso_infotech.domain.RegisteredUser}.
 */

public class RegisteredUserResource {

    private final Logger log = LoggerFactory.getLogger(RegisteredUserResource.class);

    private static final String ENTITY_NAME = "FriendshipServiceRegisteredUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RegisteredUserService registeredUserService;

    public RegisteredUserResource(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    /**
     * {@code POST  /registered-users} : Create a new registeredUser.
     *
     * @param registeredUserDTO the registeredUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new registeredUserDTO, or with status {@code 400 (Bad Request)} if the registeredUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/registered-users")
    public ResponseEntity<RegisteredUserDTO> createRegisteredUser(@Valid @RequestBody RegisteredUserDTO registeredUserDTO) throws URISyntaxException {
        log.debug("REST request to save RegisteredUser : {}", registeredUserDTO);
        if (registeredUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new registeredUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RegisteredUserDTO result = registeredUserService.save(registeredUserDTO);
        return ResponseEntity.created(new URI("/api/registered-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /registered-users} : Updates an existing registeredUser.
     *
     * @param registeredUserDTO the registeredUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated registeredUserDTO,
     * or with status {@code 400 (Bad Request)} if the registeredUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the registeredUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/registered-users")
    public ResponseEntity<RegisteredUserDTO> updateRegisteredUser(@Valid @RequestBody RegisteredUserDTO registeredUserDTO) throws URISyntaxException {
        log.debug("REST request to update RegisteredUser : {}", registeredUserDTO);
        if (registeredUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RegisteredUserDTO result = registeredUserService.save(registeredUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, registeredUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /registered-users} : get all the registeredUsers.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of registeredUsers in body.
     */
    @GetMapping("/registered-users")
    public ResponseEntity<List<RegisteredUserDTO>> getAllRegisteredUsers(Pageable pageable) {
        log.debug("REST request to get a page of RegisteredUsers");
        Page<RegisteredUserDTO> page = registeredUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /registered-users/:id} : get the "id" registeredUser.
     *
     * @param id the id of the registeredUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the registeredUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/registered-users/{id}")
    public ResponseEntity<RegisteredUserDTO> getRegisteredUser(@PathVariable Long id) {
        log.debug("REST request to get RegisteredUser : {}", id);
        Optional<RegisteredUserDTO> registeredUserDTO = registeredUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(registeredUserDTO);
    }

    /**
     * {@code DELETE  /registered-users/:id} : delete the "id" registeredUser.
     *
     * @param id the id of the registeredUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/registered-users/{id}")
    public ResponseEntity<Void> deleteRegisteredUser(@PathVariable Long id) {
        log.debug("REST request to delete RegisteredUser : {}", id);
        registeredUserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

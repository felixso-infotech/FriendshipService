package com.felixso_infotech.service.mapper;

import com.felixso_infotech.domain.*;
import com.felixso_infotech.service.dto.RegisteredUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RegisteredUser} and its DTO {@link RegisteredUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RegisteredUserMapper extends EntityMapper<RegisteredUserDTO, RegisteredUser> {



    default RegisteredUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setId(id);
        return registeredUser;
    }
}

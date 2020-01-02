package com.felixso_infotech.service.dto;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.felixso_infotech.domain.RegisteredUser} entity.
 */
@ApiModel(description = "RegisteredUser entity.\n@author Sarangibalu")
public class RegisteredUserDTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    
    private String userId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RegisteredUserDTO registeredUserDTO = (RegisteredUserDTO) o;
        if (registeredUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), registeredUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RegisteredUserDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }
}

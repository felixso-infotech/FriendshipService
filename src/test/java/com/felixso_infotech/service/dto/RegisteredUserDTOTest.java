package com.felixso_infotech.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.felixso_infotech.web.rest.TestUtil;

public class RegisteredUserDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegisteredUserDTO.class);
        RegisteredUserDTO registeredUserDTO1 = new RegisteredUserDTO();
        registeredUserDTO1.setId(1L);
        RegisteredUserDTO registeredUserDTO2 = new RegisteredUserDTO();
        assertThat(registeredUserDTO1).isNotEqualTo(registeredUserDTO2);
        registeredUserDTO2.setId(registeredUserDTO1.getId());
        assertThat(registeredUserDTO1).isEqualTo(registeredUserDTO2);
        registeredUserDTO2.setId(2L);
        assertThat(registeredUserDTO1).isNotEqualTo(registeredUserDTO2);
        registeredUserDTO1.setId(null);
        assertThat(registeredUserDTO1).isNotEqualTo(registeredUserDTO2);
    }
}

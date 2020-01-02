package com.felixso_infotech.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.felixso_infotech.web.rest.TestUtil;

public class RegisteredUserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegisteredUser.class);
        RegisteredUser registeredUser1 = new RegisteredUser();
        registeredUser1.setId(1L);
        RegisteredUser registeredUser2 = new RegisteredUser();
        registeredUser2.setId(registeredUser1.getId());
        assertThat(registeredUser1).isEqualTo(registeredUser2);
        registeredUser2.setId(2L);
        assertThat(registeredUser1).isNotEqualTo(registeredUser2);
        registeredUser1.setId(null);
        assertThat(registeredUser1).isNotEqualTo(registeredUser2);
    }
}

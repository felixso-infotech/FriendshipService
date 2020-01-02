package com.felixso_infotech.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class RegisteredUserMapperTest {

    private RegisteredUserMapper registeredUserMapper;

    @BeforeEach
    public void setUp() {
        registeredUserMapper = new RegisteredUserMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(registeredUserMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(registeredUserMapper.fromId(null)).isNull();
    }
}

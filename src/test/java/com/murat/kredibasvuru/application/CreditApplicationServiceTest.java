package com.murat.kredibasvuru.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreditApplicationServiceTest {

    private FakeNotificationSender fakeSender;
    private CreditApplicationService service;

    @BeforeEach
    void setUp() {
        fakeSender = new FakeNotificationSender();
        service = new CreditApplicationService(fakeSender);
    }

    @Test
    void shouldSendNotification_whenApplicationIsValid() {
        //when
        UUID applicationId = service.submit("Ahmet Yılmaz", new BigDecimal(50000));

        //then
        assertThat(applicationId).isNotNull();
        assertThat(fakeSender.getSendMessages()).hasSize(1);
    }

    @Test
    void shouldThrowException_whenAmountIsNegative() {
        //then
        assertThatThrownBy(() -> service.submit("Ahmet Yılmaz", new BigDecimal(-1))).isInstanceOf(IllegalArgumentException.class);
        assertThat(fakeSender.getSendMessages()).hasSize(0);
    }

    @Test
    void shouldThrowException_whenAmountIsZero() {
        assertThatThrownBy(() -> service.submit("Ahmet Yılmaz", new BigDecimal(0))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldThrowException_whenAmountIsNull() {
        assertThatThrownBy(() -> service.submit("Ahmet Yılmaz", null)).isInstanceOf(IllegalArgumentException.class);
        assertThat(fakeSender.getSendMessages()).isEmpty();
    }

    @Test
    void shouldThrowException_whenCustomerNameIsEmpty() {
        //then
        assertThatThrownBy(() -> service.submit(" ", new BigDecimal(1))).isInstanceOf(IllegalArgumentException.class);
        assertThat(fakeSender.getSendMessages()).isEmpty();
    }

    @Test
    void shouldThrowException_whenCustomerNameIsNull() {
        //then
        assertThatThrownBy(() -> service.submit(null, new BigDecimal(1))).isInstanceOf(IllegalArgumentException.class);
        assertThat(fakeSender.getSendMessages()).isEmpty();
    }
}

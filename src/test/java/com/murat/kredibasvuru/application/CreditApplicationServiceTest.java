package com.murat.kredibasvuru.application;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreditApplicationServiceTest {

    @Test
    void shouldSendNotification_whenApplicationIsValid() {
        //given
        FakeNotificationSender fakeSender = new FakeNotificationSender();
        CreditApplicationService service = new CreditApplicationService(fakeSender);

        //when
        UUID applicationId = service.submit("Ahmet Yılmaz", new BigDecimal(50000));

        //then
        assertThat(applicationId).isNotNull();
        assertThat(fakeSender.getSendMessages()).hasSize(1);
    }

    @Test
    void shouldThrowException_whenPriceIsNegative() {
        //given
        FakeNotificationSender fakeSender = new FakeNotificationSender();
        CreditApplicationService service = new CreditApplicationService(fakeSender);

        //then
        assertThatThrownBy(() -> service.submit("Ahmet Yılmaz", new BigDecimal(-1))).isInstanceOf(IllegalArgumentException.class);
        assertThat(fakeSender.getSendMessages()).hasSize(0);
    }

    @Test
    void shouldThrowException_whenCustomerNameIsEmpty() {
        //given
        FakeNotificationSender fakeSender = new FakeNotificationSender();
        CreditApplicationService service = new CreditApplicationService(fakeSender);

        //then
        assertThatThrownBy(() -> service.submit(" ", new BigDecimal(1)));
        assertThat(fakeSender.getSendMessages()).hasSize(0);
    }
}

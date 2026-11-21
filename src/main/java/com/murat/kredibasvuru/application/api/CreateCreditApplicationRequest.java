package com.murat.kredibasvuru.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateCreditApplicationRequest(@NotBlank  String customerName, @Positive BigDecimal amount) {

}

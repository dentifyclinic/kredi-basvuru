package com.murat.kredibasvuru.application.api;

import java.math.BigDecimal;

public record CreateCreditApplicationRequest(String customerName, BigDecimal amount) {

}

package com.murat.kredibasvuru.application.api;

import com.murat.kredibasvuru.application.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/credit-applications")
@RequiredArgsConstructor
public class CreditApplicationController {

    private final CreditApplicationService creditApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditApplicationResponse createApplication(@RequestBody CreateCreditApplicationRequest request) {
        UUID applicationId = creditApplicationService.submit(request.customerName(), request.amount());
        return new CreditApplicationResponse(applicationId);
    }
}

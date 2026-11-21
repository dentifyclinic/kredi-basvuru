package com.murat.kredibasvuru.application.api;

import com.murat.kredibasvuru.application.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credit-applications")
@RequiredArgsConstructor
public class CreditApplicationController {

    public final CreditApplicationService creditApplicationService;

    @PostMapping
    public CreditApplicationResponse post(@RequestBody CreateCreditApplicationRequest creditApplicationRequest) {
        return new CreditApplicationResponse(creditApplicationService.submit(creditApplicationRequest.customerName(), creditApplicationRequest.amount()));
    }
}

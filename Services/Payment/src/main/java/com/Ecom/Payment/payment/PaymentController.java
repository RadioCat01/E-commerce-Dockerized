package com.Ecom.Payment.payment;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.core.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
@Slf4j
public class PaymentController {

    private final PaymentService service;
    public PaymentRequest incomingReq;
    /*@PostMapping
    public ResponseEntity<Integer> createPayment(
            @RequestBody @Valid PaymentRequest request
    ){
        return ResponseEntity.ok(service.createPayment(request));
    } */

    @PostMapping("/payment/create")
    public RedirectView createPayment(
            @RequestBody PaymentRequest request
    ) throws PayPalRESTException {
        try {
            String cancelUrl = "http://localhost:8053/api/v1/payments/payment/cancel";
            String successUrl = "http://localhost:8053/api/v1/payments/payment/success";

            Payment payment = service.createPayment(
                    request.amount().toBigInteger().doubleValue(),
                    "USD",
                    "paypal",
                    "sale",
                    "Payment Description",
                    cancelUrl,
                    successUrl
            );
            incomingReq = request;
            for(Links links: payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    log.info("Redirecting to: " + links.getHref());
                    return new RedirectView(links.getHref());
                }
            }

        } catch (com.paypal.base.rest.PayPalRESTException e) {
            log.error("error :", e);

        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/payment/success")
    public RedirectView paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
            ){
        try{
            Payment payment = service.executePayment(paymentId,payerId);
            if(payment.getState().equals("approved")){
                service.sendEmail(incomingReq);
                return new RedirectView("http://localhost:4200/landing");
            }
        } catch (com.paypal.base.rest.PayPalRESTException e) {
            throw new RuntimeException(e);
        }
        return new RedirectView("http://localhost:4200/landing");
    }

    @GetMapping("/payment/cancel")
    public String paymentCancel(){
        return "paymentCancel";
    }

    @GetMapping("/payment/error")
    public String paymentError(){
        return "paymentError";
    }


}

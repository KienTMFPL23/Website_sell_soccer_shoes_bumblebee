//package com.example.Website_sell_soccer_shoes_bumblebee.controller;
//
//import com.example.Website_sell_soccer_shoes_bumblebee.config.PaypalPaymentIntent;
//import com.example.Website_sell_soccer_shoes_bumblebee.config.PaypalPaymentMethod;
//import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
//import com.example.Website_sell_soccer_shoes_bumblebee.service.PayPalService;
//import com.paypal.api.payments.Links;
//import com.paypal.api.payments.Payment;
//import com.paypal.base.rest.PayPalRESTException;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class PayPalController {
//
//    @Autowired
//    private PayPalService service;
//
//    public static final String SUCCESS_URL = "pay/success";
//    public static final String CANCEL_URL = "pay/cancel";
//
//    @GetMapping("/")
//    public String home() {
//        return "/paypal/paypal";
//    }
//
//    @PostMapping("/pay")
//    public String pay (HttpServletRequest request, @RequestParam("price") double price ) {
//        String cancelUrl = "http://localhost:8080/" + CANCEL_URL;
//        String successUrl = "http://localhost:8080/" + SUCCESS_URL;
//        try {
//            Payment payment = service.createPayment(
//                    price,
//                    "VND",
//                    PaypalPaymentMethod.paypal,
//                    PaypalPaymentIntent.sale,
//                    "payment description",
//                    cancelUrl,
//                    successUrl);
//            for (Links links : payment.getLinks()) {
//                if (links.getRel().equals("approval_url")) {
//                    return "redirect:" + links.getHref();
//                }
//            }
//        } catch (PayPalRESTException e) {
//            e.printStackTrace();
//        }
//        return "redirect:/";
//    }
//
//    @GetMapping(CANCEL_URL)
//    public String cancelPay() {
//        return "/paypal/cancel";
//    }
//
//    @GetMapping(SUCCESS_URL)
//    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
//        try {
//            Payment payment = service.executePayment(paymentId, payerId);
//            System.out.println(payment.toJSON());
//            if (payment.getState().equals("approved")) {
//                return "/paypal/success";
//            }
//        } catch (PayPalRESTException e) {
//            System.out.println(e.getMessage());
//        }
//        return "redirect:/";
//    }
//}

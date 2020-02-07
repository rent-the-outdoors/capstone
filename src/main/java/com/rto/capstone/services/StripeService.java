package com.rto.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

    @Service
    public class StripeService {

        @Value("${STRIPE_SECRET_KEY}")
        private String API_SECRET_KEY;

        @Autowired
        public StripeService() {
            //find a way to hide this
            //put in config, do config.getApiKey() or something
            Stripe.apiKey = "sk_test_fcaJ2PuAc7DCFWwIMFTyH9kf004p4PEHU8";
        }

        public Charge chargeNewCard(String token, double amount) throws Exception {
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount",(int)(amount * 100));
            chargeParams.put("currency", "USD");
            chargeParams.put("source", token);
            return Charge.create(chargeParams);
        }
    }






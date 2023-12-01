package com.psl.ecommerceapp.ECommerceApp.utils;

import java.util.Random;

public class OTPGenerator {
    public static String generateOTP() {
        // Define the length of the OTP (e.g., 6 digits)
        int otpLength = 6;
        StringBuilder otp = new StringBuilder();

        // Use a random number generator to generate each digit
        Random random = new Random();
        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10)); // Generates a random digit between 0 and 9
        }

        return otp.toString();
    }
}

package kz.edu.astanait.diplomawork.service.serviceInterface.utils;

import kz.edu.astanait.diplomawork.model.user.User;

import javax.mail.MessagingException;

public interface EmailService {
    void sendVerificationPinCode(User user, int pinCode) throws MessagingException;
    void sendMessage(String email, String text) throws MessagingException;
}

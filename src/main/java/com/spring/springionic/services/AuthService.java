package com.spring.springionic.services;

import java.util.Random;

import com.spring.springionic.domain.Client;
import com.spring.springionic.repositories.ClientRepository;
import com.spring.springionic.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private EmailService emailService;

    private Random random = new Random();
    
    public void sendNewPassword(String email){
        Client client = clientRepository.findByEmail(email);
        if(client == null){
            throw new ObjectNotFoundException("Email not found.");
        }
        String newPass = newPassword();
        client.setPassword(encoder.encode(newPass));
        
        clientRepository.save(client);

        emailService.sendNewPasswordEmail(client, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for(int i=0; i<vet.length;i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {

        int opt = random.nextInt(3);

        if (opt == 0) {
            return (char) (random.nextInt(10)+48);
        } else if (opt == 1){
            return (char) (random.nextInt(26)+65);
        } else {
            return (char) (random.nextInt(26)+97);
        }
    }
}

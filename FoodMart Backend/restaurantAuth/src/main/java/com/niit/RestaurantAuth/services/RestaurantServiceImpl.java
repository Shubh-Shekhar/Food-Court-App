package com.niit.RestaurantAuth.services;

import com.niit.RestaurantAuth.exception.EmailAlreadyRegistered;
import com.niit.RestaurantAuth.exception.InvalidCredentialsException;
import com.niit.RestaurantAuth.models.RestaurantOwner;
import com.niit.RestaurantAuth.models.RestaurantOwnerDTO;
import com.niit.RestaurantAuth.models.proxy.OwnerProxy;
import com.niit.RestaurantAuth.rabbitMQ.MailProducer;
import com.niit.RestaurantAuth.repository.RestaurantOwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantAuthService {


    @Autowired
    private RestaurantAuthService restaurantAuthService;

    @Autowired
    private RestaurantOwnerRepo restaurantOwnerRepo;

    @Autowired
    private OwnerProxy ownerProxy;

    @Autowired
    private MailProducer mailProducer;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String receiver, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("shubhamshekhar725@gmail.com");
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);
        javaMailSender.send(simpleMailMessage);
        System.out.println("Mail sent...");
    }


    @Override
    public RestaurantOwner signUpOwner(RestaurantOwner restaurantOwner) throws EmailAlreadyRegistered {
        if (restaurantOwnerRepo.findById(restaurantOwner.getEmailId()).isPresent()) throw new EmailAlreadyRegistered();
        RestaurantOwnerDTO restaurantOwnerDTO = new RestaurantOwnerDTO(restaurantOwner.getEmailId(), restaurantOwner.getOwnerName());
        ResponseEntity<?> response = ownerProxy.sendDataToService(restaurantOwnerDTO);
        sendMail(restaurantOwner.getEmailId(), "RESTAURANT OWNER REGISTRATION SUCCESSFUL",
                "Hi !!! " + restaurantOwner.getOwnerName() + "Welcome to FoodZone ...");

        return restaurantOwnerRepo.save(restaurantOwner);
    }

    @Override
    public RestaurantOwner restaurantOwnerLogin(RestaurantOwner restaurantOwner) throws InvalidCredentialsException {
        RestaurantOwner restaurantOwner1 = restaurantOwnerRepo.findByEmailIdAndPassword(restaurantOwner.getEmailId(), restaurantOwner.getPassword());
        if (restaurantOwner1 == null) throw new InvalidCredentialsException();
        else return restaurantOwner1;

    }

}

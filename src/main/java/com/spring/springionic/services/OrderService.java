package com.spring.springionic.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import com.spring.springionic.domain.AppOrder;
import com.spring.springionic.domain.BilletPayment;
import com.spring.springionic.domain.ItemOrder;
import com.spring.springionic.domain.enums.PaymentStatus;
import com.spring.springionic.repositories.ItemOrderRepository;
import com.spring.springionic.repositories.OrderRepository;
import com.spring.springionic.repositories.PaymentRepository;
import com.spring.springionic.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private BilletService billetService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ItemOrderRepository itemOrderRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmailService emailService;

    public AppOrder find(Integer id){
       Optional<AppOrder> obj = repo.findById(id);
       return obj.orElseThrow(() -> 
       new ObjectNotFoundException("Object not found! Id:"+id+", Tipo: "+ AppOrder.class.getName()));
    }

    @Transactional
    public @Valid AppOrder insert(AppOrder obj) {
        obj.setId(null);
        obj.setMoment(new Date());
        obj.setClient(clientService.find(obj.getClient().getId()));
        obj.getPayment().setStatus(PaymentStatus.PENDING);
        obj.getPayment().setOrder(obj);

        if(obj.getPayment() instanceof BilletPayment){
            BilletPayment pay = (BilletPayment) obj.getPayment();
            billetService.fillBilletPayment(pay, obj.getMoment());
        }

        obj = repo.save(obj);
        paymentRepository.save(obj.getPayment());

        for(ItemOrder io : obj.getItems()){
            io.setDiscount(0.0);
            io.setProduct(productService.find(io.getProduct().getId()));
            io.setPrice(io.getProduct().getPrice());
            io.setOrder(obj);
        }

        itemOrderRepository.saveAll(obj.getItems());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;
    }
}

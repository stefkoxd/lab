package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Order;
import mk.ukim.finki.lab.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order placeOrder(String balloonColor, String clientName, String address) {
        return new Order(balloonColor, clientName, address);
    }
}

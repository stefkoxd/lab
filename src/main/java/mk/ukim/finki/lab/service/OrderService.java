package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Order;

public interface OrderService {
    Order placeOrder(String balloonColor, String clientName, String address);
}

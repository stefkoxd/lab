package mk.ukim.finki.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shop_orders")
public class Order {
    private String balloonColor;
    private String balloonSize;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    public Order(String balloonColor) {
        this.balloonColor = balloonColor;
    }
    public Order(){}
}

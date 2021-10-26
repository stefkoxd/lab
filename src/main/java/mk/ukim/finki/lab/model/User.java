package mk.ukim.finki.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shop_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToMany
    private List<ShoppingCart> carts;
    public User(){}
}

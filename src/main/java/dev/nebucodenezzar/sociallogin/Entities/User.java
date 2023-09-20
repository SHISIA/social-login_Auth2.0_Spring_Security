package dev.nebucodenezzar.sociallogin.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
public class User {
    @Id
    private int user_id;
    private String username;
    private String password;
    private int enabled;
    @Enumerated(EnumType.STRING)
    private Provider provider;
}
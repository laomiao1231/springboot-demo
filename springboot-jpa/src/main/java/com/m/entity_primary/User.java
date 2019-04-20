package com.m.entity_primary;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Component
@Entity
@Table(name = "demo")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}

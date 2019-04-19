package com.m.entity_secondary;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Component
@Entity
@Table(name = "demo")
public class Demo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    public Demo() {
    }

    public Demo(String name) {
        this.name = name;
    }
}

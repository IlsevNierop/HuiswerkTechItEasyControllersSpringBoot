package com.example.huiswerktechiteasycontrollerspringboot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "remote_controllers")
public class RemoteController {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private double price;
    private int originalStock;

    @OneToOne(mappedBy = "remoteController")
    private Television television;


}

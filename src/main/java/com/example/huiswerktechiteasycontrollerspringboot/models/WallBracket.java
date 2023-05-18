package com.example.huiswerktechiteasycontrollerspringboot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="wall_brackets")
public class WallBracket {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String size;
    private Boolean adjustable;
    private String name;
    private double price;


}

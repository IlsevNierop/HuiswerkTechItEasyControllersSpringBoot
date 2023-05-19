package com.example.huiswerktechiteasycontrollerspringboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
@Table(name = "televisions")
@Getter
@Setter
public class Television {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String brand;
    private String name;
    private double price;
    private double availableSize;
    private double refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private int originalStock;
    private int sold;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("television")
    private RemoteController remoteController;

    @OneToMany (mappedBy = "television")
    @JsonIgnore
    private List<CIModule> ciModules;


}

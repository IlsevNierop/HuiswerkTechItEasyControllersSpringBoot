package com.example.huiswerktechiteasycontrollerspringboot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

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

    public Television() {
    }

    public Television(Long id, String type, String brand, String name, double price, double availableSize, double refreshRate, String screenType, String screenQuality, boolean smartTv, boolean voiceControl, boolean hdr, boolean bluetooth, boolean ambiLight, int originalStock, int sold) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.availableSize = availableSize;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
        this.screenQuality = screenQuality;
        this.smartTv = smartTv;
        this.voiceControl = voiceControl;
        this.hdr = hdr;
        this.bluetooth = bluetooth;
        this.ambiLight = ambiLight;
        this.originalStock = originalStock;
        this.sold = sold;
    }
}

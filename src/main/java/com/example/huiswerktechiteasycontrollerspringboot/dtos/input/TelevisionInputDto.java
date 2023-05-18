package com.example.huiswerktechiteasycontrollerspringboot.dtos.input;

import com.example.huiswerktechiteasycontrollerspringboot.models.RemoteController;
import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


public class TelevisionInputDto {


    public String type;
    @NotBlank(message = "Brand can't be blank")
    public String brand;
    @Size(max=20,message = "Name cannot have more characters than 20.")
    public String name;

    @PositiveOrZero(message="Price needs to be zero or higher, can't be negative")
    public double price;
    @PositiveOrZero(message="Size needs to be zero or higher, can't be negative")
    public double availableSize;
    public double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;

    @PositiveOrZero(message="Stock needs to be zero or higher, can't be negative")
    public int originalStock;
    @PositiveOrZero(message="Sold needs to be zero or higher, can't be negative")
    public int sold;


    public Long remoteControllerId;



}

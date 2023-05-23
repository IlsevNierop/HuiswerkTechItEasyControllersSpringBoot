package com.example.huiswerktechiteasycontrollerspringboot.dtos.output;

import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import com.example.huiswerktechiteasycontrollerspringboot.repositories.TelevisionRepository;

public class CIModuleOutputDto {

    public Long id;
    public String name;
    public String type;

    public double price;

    public Television television;
}

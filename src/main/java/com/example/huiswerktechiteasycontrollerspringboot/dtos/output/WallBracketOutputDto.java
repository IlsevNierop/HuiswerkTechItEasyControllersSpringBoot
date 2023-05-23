package com.example.huiswerktechiteasycontrollerspringboot.dtos.output;

import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import com.example.huiswerktechiteasycontrollerspringboot.models.WallBracket;

import java.util.List;

public class WallBracketOutputDto {

    public Long id;
    public String size;
    public Boolean adjustable;
    public String name;
    public double price;
    public List<Television> televisions;
}

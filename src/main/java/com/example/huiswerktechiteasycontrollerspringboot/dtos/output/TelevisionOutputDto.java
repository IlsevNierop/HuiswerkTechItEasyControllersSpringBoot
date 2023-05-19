package com.example.huiswerktechiteasycontrollerspringboot.dtos.output;

import com.example.huiswerktechiteasycontrollerspringboot.models.CIModule;
import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import com.example.huiswerktechiteasycontrollerspringboot.models.WallBracket;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class TelevisionOutputDto {

    public Long id;
    public String type;
    public String brand;
    public String name;
    public double price;
    public double availableSize;
    public double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;
    public int originalStock;
    public int sold;

    public String remoteControllerName;

    public List<CIModule> ciModules;

    public List<WallBracket> wallBrackets;

}

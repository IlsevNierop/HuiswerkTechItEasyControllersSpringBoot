package com.example.huiswerktechiteasycontrollerspringboot.dtos.output;

import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import org.springframework.beans.BeanUtils;

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

    public void copyProperties(Television target){
        BeanUtils.copyProperties(this, target);
    }
}

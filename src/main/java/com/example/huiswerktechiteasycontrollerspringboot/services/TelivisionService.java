package com.example.huiswerktechiteasycontrollerspringboot.services;

import com.example.huiswerktechiteasycontrollerspringboot.dtos.TelevisionOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import com.example.huiswerktechiteasycontrollerspringboot.repositories.TelevisionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelivisionService {

    private final TelevisionRepository televisionRepository;

    public TelivisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public List<TelevisionOutputDto> getAllTelevisions (){
        ArrayList<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();
        List<Television> televisions = televisionRepository.findAll();
        for (Television t: televisions) {
            TelevisionOutputDto televisionOutputDto = new TelevisionOutputDto();
            televisionOutputDto.copyProperties(t);
            televisionOutputDtos.add(televisionOutputDto);
        }
        return televisionOutputDtos;
    }


}

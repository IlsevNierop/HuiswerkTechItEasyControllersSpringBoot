package com.example.huiswerktechiteasycontrollerspringboot.services;

import com.example.huiswerktechiteasycontrollerspringboot.dtos.input.TelevisionInputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.input.WallBracketInputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.output.TelevisionOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.output.WallBracketOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.exceptions.RecordNotFoundException;
import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import com.example.huiswerktechiteasycontrollerspringboot.models.WallBracket;
import com.example.huiswerktechiteasycontrollerspringboot.repositories.WallBracketRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class WallBracketService {
    private final WallBracketRepository wallBracketRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }

    public ArrayList<WallBracketOutputDto> getAllWallBrackets() {
        ArrayList<WallBracketOutputDto> wallBracketOutputDtos = new ArrayList<>();
        List<WallBracket> wallBrackets = wallBracketRepository.findAll();
        if (wallBrackets.isEmpty()) {
            throw new RecordNotFoundException("There are currently no wallbrackets in the database");
        }
        for (WallBracket w : wallBrackets) {
            WallBracketOutputDto wallBracketOutputDto = transferWallBracketModelToOutputDto(w);
            wallBracketOutputDtos.add(wallBracketOutputDto);
        }
        return wallBracketOutputDtos;
    }

    public WallBracketOutputDto createWallBracket(WallBracketInputDto wallBracketInputDto){
        WallBracket wallBracket = transferInputDtoToWallBracketModel(wallBracketInputDto);
        wallBracketRepository.save(wallBracket);
        return transferWallBracketModelToOutputDto(wallBracket);
    }


    public WallBracket transferInputDtoToWallBracketModel(WallBracketInputDto wallBracketInputDto){
        WallBracket wallBracket = new WallBracket();
        wallBracket.setSize(wallBracketInputDto.size);
        wallBracket.setAdjustable(wallBracketInputDto.adjustable);
        wallBracket.setName(wallBracketInputDto.name);
        wallBracket.setPrice(wallBracketInputDto.price);

        return wallBracket;
    }
    public WallBracketOutputDto transferWallBracketModelToOutputDto(WallBracket wallBracket){
        WallBracketOutputDto wallBracketOutputDto = new WallBracketOutputDto();
        wallBracketOutputDto.id = wallBracket.getId();
        wallBracketOutputDto.size = wallBracket.getSize();
        wallBracketOutputDto.adjustable = wallBracket.getAdjustable();
        wallBracketOutputDto.name = wallBracket.getName();
        wallBracketOutputDto.price = wallBracket.getPrice();

        return wallBracketOutputDto;

    }
}

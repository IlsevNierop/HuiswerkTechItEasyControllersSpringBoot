package com.example.huiswerktechiteasycontrollerspringboot.services;

import com.example.huiswerktechiteasycontrollerspringboot.dtos.input.CIModuleInputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.input.WallBracketInputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.output.CIModuleOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.output.WallBracketOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.exceptions.RecordNotFoundException;
import com.example.huiswerktechiteasycontrollerspringboot.models.CIModule;
import com.example.huiswerktechiteasycontrollerspringboot.models.WallBracket;
import com.example.huiswerktechiteasycontrollerspringboot.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CIModuleService {

    private final CIModuleRepository ciModuleRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }

    public ArrayList<CIModuleOutputDto> getAllCIModules() {
        ArrayList<CIModuleOutputDto> ciModuleOutputDtos = new ArrayList<>();
        List<CIModule> ciModules = ciModuleRepository.findAll();
        if (ciModules.isEmpty()) {
            throw new RecordNotFoundException("There are currently no CI-Modules in the database");
        }
        for (CIModule c : ciModules) {
            CIModuleOutputDto ciModuleOutputDto = transferCIModuleModelToOutputDto(c);
            ciModuleOutputDtos.add(ciModuleOutputDto);
        }
        return ciModuleOutputDtos;
    }

    public CIModuleOutputDto createCIModule(CIModuleInputDto ciModuleInputDto){
        CIModule ciModule = transferInputDtoToCIModuleModel(ciModuleInputDto);
        ciModuleRepository.save(ciModule);
        return transferCIModuleModelToOutputDto(ciModule);
    }


    public CIModule transferInputDtoToCIModuleModel(CIModuleInputDto ciModuleInputDto){
        CIModule ciModule = new CIModule();
        ciModule.setName(ciModuleInputDto.name);
        ciModule.setType(ciModuleInputDto.type);
        ciModule.setPrice(ciModuleInputDto.price);
        return ciModule;
    }
    public CIModuleOutputDto transferCIModuleModelToOutputDto(CIModule ciModule){
        CIModuleOutputDto ciModuleOutputDto = new CIModuleOutputDto();
        ciModuleOutputDto.id = ciModule.getId();
        ciModuleOutputDto.name = ciModule.getName();
        ciModuleOutputDto.type = ciModule.getType();
        ciModuleOutputDto.price = ciModule.getPrice();

        return ciModuleOutputDto;

    }
}

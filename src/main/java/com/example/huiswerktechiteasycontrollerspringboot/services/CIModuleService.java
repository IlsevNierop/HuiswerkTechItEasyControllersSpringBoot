package com.example.huiswerktechiteasycontrollerspringboot.services;

import com.example.huiswerktechiteasycontrollerspringboot.dtos.input.CIModuleInputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.output.CIModuleOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.exceptions.RecordNotFoundException;
import com.example.huiswerktechiteasycontrollerspringboot.models.CIModule;
import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import com.example.huiswerktechiteasycontrollerspringboot.repositories.CIModuleRepository;
import com.example.huiswerktechiteasycontrollerspringboot.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CIModuleService {

    private final CIModuleRepository ciModuleRepository;

    private final TelevisionRepository televisionRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository, TelevisionRepository televisionRepository) {
        this.ciModuleRepository = ciModuleRepository;
        this.televisionRepository = televisionRepository;
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

    public CIModuleOutputDto createCIModule(CIModuleInputDto ciModuleInputDto) throws RecordNotFoundException {
        CIModule ciModule = transferInputDtoToCIModuleModel(ciModuleInputDto);
        ciModuleRepository.save(ciModule);
        return transferCIModuleModelToOutputDto(ciModule);
    }

    public CIModuleOutputDto assignTelevisionToCIModule(Long id, Long televisionId) throws RecordNotFoundException {
        CIModule ciModule = ciModuleRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Ci-module with id " + id + " doesn't exist."));
        Television television = televisionRepository.findById(televisionId).orElseThrow(() -> new RecordNotFoundException("Television with id " + televisionId + " doesn't exist."));
        ciModule.setTelevision(television);
        ciModuleRepository.save(ciModule);

        return transferCIModuleModelToOutputDto(ciModule);
    }


    public CIModule transferInputDtoToCIModuleModel(CIModuleInputDto ciModuleInputDto) {
        CIModule ciModule = new CIModule();
        if (ciModuleInputDto.televisionId != null) {
            Television television = televisionRepository.findById(ciModuleInputDto.televisionId).orElseThrow(() -> new RecordNotFoundException("Television with id " + ciModuleInputDto.televisionId + " doesn't exist"));
            ciModule.setTelevision(television);
        }

        ciModule.setName(ciModuleInputDto.name);
        ciModule.setType(ciModuleInputDto.type);
        ciModule.setPrice(ciModuleInputDto.price);
        return ciModule;
    }

    public CIModuleOutputDto transferCIModuleModelToOutputDto(CIModule ciModule) {
        CIModuleOutputDto ciModuleOutputDto = new CIModuleOutputDto();
        ciModuleOutputDto.id = ciModule.getId();
        ciModuleOutputDto.name = ciModule.getName();
        ciModuleOutputDto.type = ciModule.getType();
        ciModuleOutputDto.price = ciModule.getPrice();
        if (ciModule.getTelevision() != null) {
            ciModuleOutputDto.television = ciModule.getTelevision();
        }

        return ciModuleOutputDto;

    }
}

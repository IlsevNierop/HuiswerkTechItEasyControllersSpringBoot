package com.example.huiswerktechiteasycontrollerspringboot.services;

import com.example.huiswerktechiteasycontrollerspringboot.dtos.input.RemoteControllerInputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.input.WallBracketInputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.output.RemoteControllerOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.output.WallBracketOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.exceptions.RecordNotFoundException;
import com.example.huiswerktechiteasycontrollerspringboot.models.RemoteController;
import com.example.huiswerktechiteasycontrollerspringboot.models.WallBracket;
import com.example.huiswerktechiteasycontrollerspringboot.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public ArrayList<RemoteControllerOutputDto> getAllRemoteControllers() {
        ArrayList<RemoteControllerOutputDto> remoteControllerOutputDtos = new ArrayList<>();
        List<RemoteController> remoteControllers = remoteControllerRepository.findAll();
        if (remoteControllers.isEmpty()) {
            throw new RecordNotFoundException("There are currently no remote controllers in the database");
        }
        for (RemoteController r : remoteControllers) {
            RemoteControllerOutputDto remoteControllerOutputDto = transferRemoteControllerModelToOutputDto(r);
            remoteControllerOutputDtos.add(remoteControllerOutputDto);
        }
        return remoteControllerOutputDtos;
    }

    public RemoteControllerOutputDto createRemoteController(RemoteControllerInputDto remoteControllerInputDto){
        RemoteController remoteController = transferInputDtoToRemoteControllerModel(remoteControllerInputDto);
        remoteControllerRepository.save(remoteController);
        return transferRemoteControllerModelToOutputDto(remoteController);
    }




    public RemoteController transferInputDtoToRemoteControllerModel(RemoteControllerInputDto remoteControllerInputDto){
        RemoteController remoteController = new RemoteController();
        remoteController.setCompatibleWith(remoteControllerInputDto.compatibleWith);
        remoteController.setBatteryType(remoteControllerInputDto.batteryType);
        remoteController.setName(remoteControllerInputDto.name);
        remoteController.setBrand(remoteControllerInputDto.brand);
        remoteController.setPrice(remoteControllerInputDto.price);
        remoteController.setOriginalStock(remoteControllerInputDto.originalStock);

        return remoteController;

    }
    public RemoteControllerOutputDto transferRemoteControllerModelToOutputDto(RemoteController remoteController){
        RemoteControllerOutputDto remoteControllerOutputDto = new RemoteControllerOutputDto();
        remoteControllerOutputDto.id = remoteController.getId();
        remoteControllerOutputDto.compatibleWith = remoteController.getCompatibleWith();
        remoteControllerOutputDto.batteryType = remoteController.getBatteryType();
        remoteControllerOutputDto.name = remoteController.getName();
        remoteControllerOutputDto.brand = remoteController.getBrand();
        remoteControllerOutputDto.price = remoteController.getPrice();
        remoteControllerOutputDto.originalStock = remoteController.getOriginalStock();

        return remoteControllerOutputDto;


    }
}

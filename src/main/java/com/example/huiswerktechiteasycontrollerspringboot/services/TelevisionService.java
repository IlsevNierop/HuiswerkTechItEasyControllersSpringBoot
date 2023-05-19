package com.example.huiswerktechiteasycontrollerspringboot.services;

import com.example.huiswerktechiteasycontrollerspringboot.dtos.input.TelevisionInputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.output.TelevisionOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.exceptions.RecordNotFoundException;
import com.example.huiswerktechiteasycontrollerspringboot.models.RemoteController;
import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import com.example.huiswerktechiteasycontrollerspringboot.repositories.RemoteControllerRepository;
import com.example.huiswerktechiteasycontrollerspringboot.repositories.TelevisionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;

    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public List<TelevisionOutputDto> getAllTelevisions() throws RecordNotFoundException{
        ArrayList<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();
        List<Television> televisions = televisionRepository.findAll();
        if (televisions.isEmpty()) {
            throw new RecordNotFoundException("There are currently no televisions in the database");
        }
        for (Television t : televisions) {
            TelevisionOutputDto televisionOutputDto = transferTelevisionModelToOutputDto(t);
            televisionOutputDtos.add(televisionOutputDto);
        }
        return televisionOutputDtos;
    }

    public List<TelevisionOutputDto> getAllTelevisionsOrderedByBrand(){
        ArrayList<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();
        List<Television> televisions = televisionRepository.findAllByOrderByBrand();
        if (televisions.isEmpty()) {
            throw new RecordNotFoundException("There are currently no televisions in the database");
        }
        for (Television t : televisions) {
            TelevisionOutputDto televisionOutputDto = transferTelevisionModelToOutputDto(t);
            televisionOutputDtos.add(televisionOutputDto);
        }
        return televisionOutputDtos;
    }
    public List<TelevisionOutputDto> getAllTelevisionsOrderedByPrice(){
        ArrayList<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();
        List<Television> televisions = televisionRepository.findAllByOrderByPrice();
        if (televisions.isEmpty()) {
            throw new RecordNotFoundException("There are currently no televisions in the database");
        }
        for (Television t : televisions) {
            TelevisionOutputDto televisionOutputDto = transferTelevisionModelToOutputDto(t);
            televisionOutputDtos.add(televisionOutputDto);
        }
        return televisionOutputDtos;
    }

    public TelevisionOutputDto getTelevisionById (Long id) throws RecordNotFoundException {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id " + id + " doesn't exist"));
        TelevisionOutputDto televisionOutputDto = transferTelevisionModelToOutputDto(television);
        return televisionOutputDto;

    }
    public List<TelevisionOutputDto> getTelevisionByBrand (String brand) {
        ArrayList<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();
        List<Television> televisions = televisionRepository.findByBrandIgnoreCase(brand);
        if (televisions.isEmpty()) {
            throw new RecordNotFoundException("There are currently no televisions in the database with brand " + brand);
        }
        for (Television t : televisions) {
            TelevisionOutputDto televisionOutputDto = transferTelevisionModelToOutputDto(t);
            televisionOutputDtos.add(televisionOutputDto);
        }
        return televisionOutputDtos;
    }


    public TelevisionOutputDto createTelevision(TelevisionInputDto televisionInputDto) {
        Television television = transferInputDtoToTelevisionModel(televisionInputDto);
        televisionRepository.save((television));
        TelevisionOutputDto televisionOutputDto = transferTelevisionModelToOutputDto(television);
        return televisionOutputDto;
    }

    public TelevisionOutputDto assignRemoteControllerToTelevision(Long id,Long remoteControllerId) {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id " + id + " doesn't exist"));
        RemoteController remoteController = remoteControllerRepository.findById(remoteControllerId).orElseThrow(() -> new RecordNotFoundException("RemoteController with id " + remoteControllerId + " doesn't exist"));
        television.setRemoteController(remoteController);
        televisionRepository.save(television);

        return transferTelevisionModelToOutputDto(television);
    }

    public TelevisionOutputDto updateTelevision(Long id, TelevisionInputDto televisionInputDto)  throws RecordNotFoundException {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id " + id + " doesn't exist"));
        // als de variabelen niet ingevuld worden in client side, dan worden deze variabelen gevuld met de volgende waardes: 0, 0.0, null, of false (=default boolean) (afhankelijk van type variabele). Daarom eerst checken of de variabelen wel meegegeven worden in de body.
        //Bij de inputDto wordt momenteel een aantal variabelen gecheckt op notBank etc, maar sommige niet. Die kunnen voor null vervangen worden zonder onderstaande checks.
        if (televisionInputDto.type != null) {
            television.setType(televisionInputDto.type);
        }
        if (televisionInputDto.brand != null) {
            television.setBrand(televisionInputDto.brand);
        }
        if (televisionInputDto.name != null) {
            television.setName(televisionInputDto.name);
        }
        if (televisionInputDto.price != 0.0) {
            television.setPrice(televisionInputDto.price);
        }
        if (televisionInputDto.availableSize != 0.0) {
            television.setAvailableSize(televisionInputDto.availableSize);
        }
        if (televisionInputDto.refreshRate != 0.0) {
            television.setRefreshRate(televisionInputDto.refreshRate);
        }
        if (televisionInputDto.screenType != null) {
            television.setScreenType(televisionInputDto.screenType);
        }
        if (televisionInputDto.screenQuality != null) {
            television.setScreenQuality(televisionInputDto.screenQuality);
        }
        // Door in de klasse niet boolean met kleine letter (=primitieve variabele die alleen true of false kan teruggeven), maar Boolean met hoofdletter (die kan ook null zijn) te gebruiken, kan je checken of die in de body zit.
        if (televisionInputDto.smartTv != null) {
            television.setSmartTv(televisionInputDto.smartTv);
        }
        if (televisionInputDto.voiceControl != null) {
            television.setVoiceControl(televisionInputDto.voiceControl);
        }
        if (televisionInputDto.hdr != null) {
            television.setHdr(televisionInputDto.hdr);
        }
        if (televisionInputDto.bluetooth != null) {
            television.setBluetooth(televisionInputDto.bluetooth);
        }
        if (televisionInputDto.ambiLight != null) {
            television.setAmbiLight(televisionInputDto.ambiLight);
        }
        if (televisionInputDto.originalStock != 0) {
            television.setOriginalStock(televisionInputDto.originalStock);
        }
        if (televisionInputDto.sold != 0) {
            television.setSold(televisionInputDto.sold);
        }
        televisionRepository.save(television);

        return transferTelevisionModelToOutputDto(television);
    }

    public String deleteTelevision (Long id) throws RecordNotFoundException {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id " + id + " doesn't exist"));

        televisionRepository.delete(television);

        return "Television successfully removed";
    }





    public Television transferInputDtoToTelevisionModel(TelevisionInputDto televisionInputDto) throws RecordNotFoundException {
        Television television = new Television();
        if (televisionInputDto.remoteControllerId != null) {
            RemoteController remoteController = remoteControllerRepository.findById(televisionInputDto.remoteControllerId).orElseThrow(() -> new RecordNotFoundException("RemoteController with id " + televisionInputDto.remoteControllerId + " doesn't exist"));
            television.setRemoteController(remoteController);
        }

        television.setType(televisionInputDto.type);
        television.setBrand(televisionInputDto.brand);
        television.setName(televisionInputDto.name);
        television.setPrice(televisionInputDto.price);
        television.setAvailableSize(televisionInputDto.availableSize);
        television.setRefreshRate(televisionInputDto.refreshRate);
        television.setScreenType(televisionInputDto.screenType);
        television.setScreenQuality(televisionInputDto.screenQuality);
        television.setSmartTv(televisionInputDto.smartTv);
        television.setVoiceControl(televisionInputDto.voiceControl);
        television.setHdr(televisionInputDto.hdr);
        television.setBluetooth(televisionInputDto.bluetooth);
        television.setAmbiLight(televisionInputDto.ambiLight);
        television.setOriginalStock(televisionInputDto.originalStock);
        television.setSold(televisionInputDto.sold);


        return television;
    }
    public TelevisionOutputDto transferTelevisionModelToOutputDto(Television television){
        TelevisionOutputDto televisionOutputDto = new TelevisionOutputDto();
        televisionOutputDto.id = television.getId();
        televisionOutputDto.type = television.getType();
        televisionOutputDto.brand = television.getBrand();
        televisionOutputDto.name = television.getName();
        televisionOutputDto.price = television.getPrice();
        televisionOutputDto.availableSize = television.getAvailableSize();
        televisionOutputDto.refreshRate = television.getRefreshRate();
        televisionOutputDto.screenType = television.getScreenType();
        televisionOutputDto.screenQuality = television.getScreenQuality();
        televisionOutputDto.smartTv = television.getSmartTv();
        televisionOutputDto.voiceControl = television.getVoiceControl();
        televisionOutputDto.hdr = television.getHdr();
        televisionOutputDto.bluetooth = television.getBluetooth();
        televisionOutputDto.ambiLight = television.getAmbiLight();
        televisionOutputDto.originalStock = television.getOriginalStock();
        televisionOutputDto.sold = television.getSold();
        if (television.getRemoteController() != null) {
            televisionOutputDto.remoteControllerName = television.getRemoteController().getName();
        }
        if (television.getCiModules() != null) {
            televisionOutputDto.ciModules = television.getCiModules();
        }

        return televisionOutputDto;

    }


}

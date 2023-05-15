package com.example.huiswerktechiteasycontrollerspringboot.controllers;

import com.example.huiswerktechiteasycontrollerspringboot.dtos.TelevisionOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import com.example.huiswerktechiteasycontrollerspringboot.services.TelivisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelivisionService televisionService;

    public TelevisionController(TelivisionService televisionService) {
        this.televisionService = televisionService;
    }


    @GetMapping
    public ResponseEntity<List<TelevisionOutputDto>> getAllTelevisions() {

        return new ResponseEntity<>(televisionService.getAllTelevisions(), HttpStatus.OK);
    }

//    @GetMapping("/orderedbybrand")
//    public ResponseEntity<List<Television>> getAllTelevisionsOrderedByBrand() {
//
//        return ResponseEntity.ok(repos.findAllByOrderByBrand());
//    }
//
//    @GetMapping("/orderedbyprice")
//    public ResponseEntity<List<Television>> getAllTelevisionsOrderedByPrice() {
//
//        return ResponseEntity.ok(repos.findAllByOrderByPrice());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Television> getTelevisionById(@PathVariable Long id) throws RecordNotFoundException {
//        Optional<Television> optionalTV = repos.findById(id);
//        if (optionalTV.isEmpty()) {
//            throw new RecordNotFoundException("The television with id " + id + " doesn't exist.");
//        }
//        Television television = optionalTV.get();
//        return new ResponseEntity<>(television, HttpStatus.OK);
//    }
//
//
//    //omdat er met een lijst gewerkt wordt, wordt er altijd een lijst teruggegeven, die kan leeg zijn. Hier kan niet met optional gewerkt worden.
//    @GetMapping("/brand")
//    public ResponseEntity<List<Television>> getTelevisionByBrand(@RequestParam String brand) throws RecordNotFoundException {
//        if (repos.findByBrandIgnoreCase(brand).isEmpty()) {
//            throw new RecordNotFoundException("There are no televisions from the brand " + brand);
//        }
//        return new ResponseEntity<>(repos.findByBrandIgnoreCase(brand), HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<Television> createTelevision(@Valid @RequestBody Television t) {
//        repos.save(t);
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + t.getId()).toUriString());
//        return ResponseEntity.created(uri).body(t);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Television> updateTelevision(@PathVariable Long id, @Valid @RequestBody Television t) throws RecordNotFoundException {
//        Optional<Television> optionalTV = repos.findById(id);
//        if (optionalTV.isEmpty()) {
//            throw new RecordNotFoundException("The television with id " + id + " doesn't exist.");
//        }
//        Television television = optionalTV.get();
//        // als de variabelen niet ingevuld worden in postman, dan worden deze variabelen gevuld met de volgende waardes: 0, 0.0, null, of false (=default boolean) (afhankelijk van type variabele). Daarom eerst checken of de variabelen wel meegegeven worden in de body.
//        if (t.getBrand() != null) {
//            television.setBrand(t.getBrand());
//        }
//        if (t.getType() != null) {
//            television.setType(t.getType());
//        }
//        if (t.getName() != null) {
//            television.setName(t.getName());
//        }
//        if (t.getPrice() != 0.0) {
//            television.setPrice(t.getPrice());
//        }
//        if (t.getAvailableSize() != 0.0) {
//            television.setAvailableSize(t.getAvailableSize());
//        }
//        if (t.getRefreshRate() != 0.0) {
//            television.setRefreshRate(t.getRefreshRate());
//        }
//        if (t.getName() != null) {
//            television.setScreenType(t.getScreenType());
//        }
//        if (t.getName() != null) {
//            television.setScreenQuality(t.getScreenQuality());
//        }
//        // Door in de klasse niet boolean met kleine letter (=primitieve variabele die alleen true of false kan teruggeven), maar Boolean met hoofdletter (die kan ook null zijn) te gebruiken, kan je checken of die in de body zit.
//        if (t.getSmartTv() != null) {
//            television.setSmartTv(t.getSmartTv());
//        }
//        if (t.getVoiceControl() != null) {
//            television.setVoiceControl(t.getVoiceControl());
//        }
//        if (t.getHdr() != null) {
//            television.setHdr(t.getHdr());
//        }
//        if (t.getBluetooth() != null) {
//            television.setBluetooth(t.getBluetooth());
//        }
//        if (t.getAmbiLight() != null) {
//            television.setAmbiLight(t.getAmbiLight());
//        }
//        if (t.getOriginalStock() != 0) {
//            television.setOriginalStock(t.getOriginalStock());
//        }
//        if (t.getSold() != 0) {
//            television.setSold(t.getSold());
//        }
//        repos.save(television);
//        return new ResponseEntity<>(television, HttpStatus.ACCEPTED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) throws RecordNotFoundException {
//        Optional<Television> optionalTV = repos.findById(id);
//        if (optionalTV.isEmpty()) {
//            throw new RecordNotFoundException("The TV with id number " + id + " does not exist");
//        }
//        Television television = optionalTV.get();
//        //tv wordt verwijderd en bij het opnieuw toevoegen van tv's worden nieuwe id's gegenereerd, dus er komt geen nieuwe tv op de oude id van deze verwijderde tv
//        repos.delete(television);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}

package com.example.huiswerktechiteasycontrollerspringboot.controllers;

import com.example.huiswerktechiteasycontrollerspringboot.exceptions.RecordNotFoundException;
import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import com.example.huiswerktechiteasycontrollerspringboot.repositories.TelevisionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionRepository repos;

    public TelevisionController(TelevisionRepository repos) {
        this.repos = repos;
    }

    @GetMapping
    public ResponseEntity<List<Television>> getAllTelevisions() {

        return ResponseEntity.ok(repos.findAll());
    }

    @GetMapping("/orderedbybrand")
    public ResponseEntity<List<Television>> getAllTelevisionsOrderedByBrand() {

        return ResponseEntity.ok(repos.findAllByOrderByBrand());
    }

    @GetMapping("/orderedbyprice")
    public ResponseEntity<List<Television>> getAllTelevisionsOrderedByPrice() {

        return ResponseEntity.ok(repos.findAllByOrderByPrice());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionById(@PathVariable Long id) throws RecordNotFoundException {
        Optional<Television> optionalTV = repos.findById(id);
        if (optionalTV.isEmpty()) {
            throw new RecordNotFoundException("The television with id " + id + " doesn't exist.");
        }
        Television television = optionalTV.get();
        return new ResponseEntity<>(television, HttpStatus.OK);
    }


    //omdat er met een lijst gewerkt wordt, wordt er altijd een lijst teruggegeven, die kan leeg zijn. Hier kan niet met optional gewerkt worden.
    @GetMapping("/brand")
    public ResponseEntity<List<Television>> getTelevisionByBrand(@RequestParam String brand) throws RecordNotFoundException {
        if (repos.findByBrandIgnoreCase(brand).isEmpty()) {
            throw new RecordNotFoundException("There are no televisions from the brand " + brand);
        }
        return new ResponseEntity<>(repos.findByBrandIgnoreCase(brand), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Television> createTelevision(@RequestBody Television t) {
        repos.save(t);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + t.getId()).toUriString());
        return ResponseEntity.created(uri).body(t);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable Long id, @RequestBody Television t) throws RecordNotFoundException {
        Optional<Television> optionalTV = repos.findById(id);
        if (optionalTV.isEmpty()) {
            throw new RecordNotFoundException("The television with id " + id + " doesn't exist.");
        }
        Television television = optionalTV.get();
        // als de variabelen niet ingevuld worden in postman, dan wordt de nieuwe tv met 0, 0.0, null, of false (=default boolean) gevuld (afhankelijk van type variabele)
        if (t.getBrand() != null) {
            television.setBrand(t.getBrand());
        }
        if (t.getType() != null) {
            television.setType(t.getType());
        }
        if (t.getName() != null) {
            television.setName(t.getName());
        }
        if (t.getPrice() != 0.0) {
            television.setPrice(t.getPrice());
        }
        if (t.getAvailableSize() != 0.0) {
            television.setAvailableSize(t.getAvailableSize());
        }
        if (t.getRefreshRate() != 0.0) {
            television.setRefreshRate(t.getRefreshRate());
        }
        if (t.getName() != null) {
            television.setScreenType(t.getScreenType());
        }
        if (t.getName() != null) {
            television.setScreenQuality(t.getScreenQuality());
        }
        // Nu is het zo dat als een boolean niet wordt ingevuld in de waarde, dan wordt de default waarde false toegekend. Dit kun je oplossen aan de voorkant, door altijd de gehele body te vullen, ook als bepaalde variabelen niet aangepast zijn, dan vul je die variabelen met de originele waarde die uit de database kwam.
        television.setSmartTv(t.isSmartTv());
        television.setVoiceControl(t.isVoiceControl());
        television.setHdr(t.isHdr());
        television.setBluetooth(t.isBluetooth());
        television.setAmbiLight(t.isAmbiLight());
        if (t.getOriginalStock() != 0) {
            television.setOriginalStock(t.getOriginalStock());
        }
        if (t.getSold() != 0) {
            television.setSold(t.getSold());
        }
        repos.save(television);
        return new ResponseEntity<>(television, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) throws RecordNotFoundException {
        Optional<Television> optionalTV = repos.findById(id);
        if (optionalTV.isEmpty()) {
            throw new RecordNotFoundException("The TV with id number " + id + " does not exist");
        }
        Television television = optionalTV.get();
        //tv wordt verwijderd en bij het opnieuw toevoegen van tv's worden nieuwe id's gegenereerd, dus er komt geen nieuwe tv op de oude id van deze verwijderde tv
        repos.delete(television);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

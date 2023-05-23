package com.example.huiswerktechiteasycontrollerspringboot.controllers;

import com.example.huiswerktechiteasycontrollerspringboot.dtos.input.TelevisionInputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.output.TelevisionOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.services.TelevisionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }


    @GetMapping
    public ResponseEntity<List<TelevisionOutputDto>> getAllTelevisions() {

        return new ResponseEntity<>(televisionService.getAllTelevisions(), HttpStatus.OK);
    }

    @GetMapping("/orderedbybrand")
    public ResponseEntity<List<TelevisionOutputDto>> getAllTelevisionsOrderedByBrand() {
        return new ResponseEntity<>(televisionService.getAllTelevisionsOrderedByBrand(), HttpStatus.OK);

    }

    @GetMapping("/orderedbyprice")
    public ResponseEntity<List<TelevisionOutputDto>> getAllTelevisionsOrderedByPrice() {
        return new ResponseEntity<>(televisionService.getAllTelevisionsOrderedByPrice(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionOutputDto> getTelevisionById(@PathVariable Long id) {
        return new ResponseEntity<>(televisionService.getTelevisionById(id), HttpStatus.OK);
    }

//    //omdat er met een lijst gewerkt wordt, wordt er altijd een lijst teruggegeven, die kan leeg zijn. Hier kan niet met optional gewerkt worden.
    @GetMapping("/brand")
    public ResponseEntity<List<TelevisionOutputDto>> getTelevisionByBrand(@RequestParam String brand) {
       return new ResponseEntity<>(televisionService.getTelevisionByBrand(brand), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            return ResponseEntity.badRequest().body(errorToStringHandling(bindingResult));
        }
        TelevisionOutputDto televisionOutputDto = televisionService.createTelevision(televisionInputDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + televisionOutputDto).toUriString());
        return ResponseEntity.created(uri).body(televisionOutputDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable Long id, @Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            return ResponseEntity.badRequest().body(errorToStringHandling(bindingResult));
        }
        return new ResponseEntity<>(televisionService.updateTelevision(id, televisionInputDto), HttpStatus.ACCEPTED);
    }
    @PutMapping("/{id}/remotecontroller/{remotecontrollerid}")
    public ResponseEntity<Object> assignRemoteControllerToTelevision(@PathVariable("id") Long id, @PathVariable("remotecontrollerid") Long remoteControllerId) {
        return new ResponseEntity<>(televisionService.assignRemoteControllerToTelevision(id, remoteControllerId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/wallbracket/{wallBracketId}")
    public ResponseEntity<Object> assignWallBracketToTelevision(@PathVariable("id") Long id, @PathVariable("wallBracketId") Long wallBracketId) {
        return new ResponseEntity<>(televisionService.assignWallBracketToTelevision(id, wallBracketId), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTelevision(@PathVariable Long id) {
        televisionService.deleteTelevision(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public String errorToStringHandling (BindingResult bindingResult){
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : bindingResult.getFieldErrors()){
            sb.append(fe.getField() + ": ");
            sb.append(fe.getDefaultMessage());
            sb.append("\n");
        }
        return sb.toString();
    }

    }


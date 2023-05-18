package com.example.huiswerktechiteasycontrollerspringboot.controllers;

import com.example.huiswerktechiteasycontrollerspringboot.dtos.input.CIModuleInputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.output.CIModuleOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.services.CIModuleService;
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
@RequestMapping("/cimodules")
public class CIModuleController {

    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }


    @GetMapping
    public ResponseEntity<List<CIModuleOutputDto>> getAllCIModules() {

        return new ResponseEntity<>(ciModuleService.getAllCIModules(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createCIModule(@Valid @RequestBody CIModuleInputDto ciModuleInputDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            return ResponseEntity.badRequest().body(errorToStringHandling(bindingResult));
        }
        CIModuleOutputDto ciModuleOutputDto = ciModuleService.createCIModule(ciModuleInputDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + ciModuleOutputDto).toUriString());
        return ResponseEntity.created(uri).body(ciModuleOutputDto);
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

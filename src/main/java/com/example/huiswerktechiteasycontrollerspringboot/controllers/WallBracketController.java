package com.example.huiswerktechiteasycontrollerspringboot.controllers;

import com.example.huiswerktechiteasycontrollerspringboot.dtos.input.WallBracketInputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.output.WallBracketOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.services.WallBracketService;
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
@RequestMapping("/wallbrackets")
public class WallBracketController {

    private final WallBracketService wallBracketService;

    public WallBracketController(WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }


    @GetMapping
    public ResponseEntity<List<WallBracketOutputDto>> getAllWallBrackets() {
        return new ResponseEntity<>(wallBracketService.getAllWallBrackets(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createWallBracket(@Valid @RequestBody WallBracketInputDto wallBracketInputDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(errorToStringHandling(bindingResult));
        }
        WallBracketOutputDto wallBracketOutputDto = wallBracketService.createWallBracket(wallBracketInputDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + wallBracketOutputDto).toUriString());
        return ResponseEntity.created(uri).body(wallBracketOutputDto);
    }

    public String errorToStringHandling(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : bindingResult.getFieldErrors()) {
            sb.append(fe.getField() + ": ");
            sb.append(fe.getDefaultMessage());
            sb.append("\n");
        }
        return sb.toString();
    }
}

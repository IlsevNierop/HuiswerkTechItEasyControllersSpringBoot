package com.example.huiswerktechiteasycontrollerspringboot.controllers;

import com.example.huiswerktechiteasycontrollerspringboot.dtos.input.RemoteControllerInputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.output.RemoteControllerOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.services.RemoteControllerService;
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
@RequestMapping("/remotecontrollers")
public class RemoteControllerController {

  private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping
    public ResponseEntity<List<RemoteControllerOutputDto>> getAllRemoteControllers() {

        return new ResponseEntity<>(remoteControllerService.getAllRemoteControllers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createRemoteController(@Valid @RequestBody RemoteControllerInputDto remoteControllerInputDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            return ResponseEntity.badRequest().body(errorToStringHandling(bindingResult));
        }
        RemoteControllerOutputDto remoteControllerOutputDto = remoteControllerService.createRemoteController(remoteControllerInputDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + remoteControllerOutputDto).toUriString());
        return ResponseEntity.created(uri).body(remoteControllerOutputDto);
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

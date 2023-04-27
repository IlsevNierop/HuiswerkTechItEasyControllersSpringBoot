package com.example.huiswerktechiteasycontrollerspringboot.controllers;

import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import com.example.huiswerktechiteasycontrollerspringboot.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/televisions")
public class TelevisionController {

    @Autowired
    TelevisionRepository repos;

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

    @GetMapping("/brand")
    public ResponseEntity<List<Television>> getTelevisionByBrand(@RequestParam String brand) {

        return ResponseEntity.ok(repos.findByBrandIgnoreCase(brand));
    }

    @PostMapping
    public ResponseEntity<Television> createTelevision (@RequestBody Television t){
        repos.save(t);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + t.getId()).toUriString());
        return ResponseEntity.created(uri).body(t);
    }
}

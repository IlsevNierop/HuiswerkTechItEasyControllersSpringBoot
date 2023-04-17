package com.example.huiswerktechiteasycontrollerspringboot.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class RecordNotFoundException {

    public static ArrayList<String> televisionDataBase = new ArrayList<>(Arrays.asList("Philips 200", "Samsung 531", "Nikkei 1", "LG OLED TV", "Sony Bravia TV", "TCL Roku TV"));

    @GetMapping("/televisions")
    public ResponseEntity<Object> getAllTelevisions() {
        return ResponseEntity.ok(televisionDataBase);
    }

    // heb begrepen dat het netter is om voor een id een long te gebruiken - maar omdat de id in de methode als index wordt gebruikt, moet het een int zijn. Daarom bij alle volgende methodes die gebruik maken van id, een int gebruikt ipv long.
    @GetMapping("/television/{id}")
    public ResponseEntity<Object> getOneTelevision(@PathVariable int id) {
        return ResponseEntity.ok("Television with index " + id + " is " + televisionDataBase.get(id));
    }

    @PostMapping("/addtelevision")
    public ResponseEntity<Object> addTelevision(@RequestBody String television) {
        televisionDataBase.add(television);
        return ResponseEntity.created(null).body("New list with televisions: " + televisionDataBase);
    }

    @PutMapping("/updatetelevision/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody String television) {
        televisionDataBase.set(id, television);
        return ResponseEntity.accepted().body("New list with televisions: " + televisionDataBase);
    }

    @DeleteMapping("/deletetelevision/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        televisionDataBase.set(id, null);
        return ResponseEntity.noContent().build();
    }

}


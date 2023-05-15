package com.example.huiswerktechiteasycontrollerspringboot.controllers;

import com.example.huiswerktechiteasycontrollerspringboot.exceptions.StringTooLongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {
    public static ArrayList<String> televisionDataBase = new ArrayList<>(Arrays.asList("Philips 200", "Samsung 531", "Nikkei 1", "LG OLED TV", "Sony Bravia TV", "TCL Roku TV"));

    @GetMapping()
    public ResponseEntity<Object> getAllTelevisions() {
        return new ResponseEntity<>(televisionDataBase, HttpStatus.OK);
    }

    // Gebruik voor id int, omdat ik de id als index gebruik. Begreep dat long netter is voor id.
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTelevision(@PathVariable int id) throws IndexOutOfBoundsException {
        if (id < 0 || id >= televisionDataBase.size()) {
            throw new IndexOutOfBoundsException("The TV with id number " + id + " does not exist");
        }
        return new ResponseEntity<>(televisionDataBase.get(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addTelevision(@RequestBody String television) throws StringTooLongException {
        if (television.length() > 20) {
            throw new StringTooLongException("The television name " + television + " is too long. The name of a TV can have a maximum of 20 characters.");
        }
        televisionDataBase.add(television);
        return new ResponseEntity<>("New list with televisions: "+televisionDataBase, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody String television) throws IndexOutOfBoundsException, StringTooLongException{
        if (id < 0 || id >= televisionDataBase.size()) {
            throw new IndexOutOfBoundsException("The TV with id number " + id + " does not exist");
        }
        if (television.length() > 20) {
            throw new StringTooLongException("The television name is too long. The name of a TV can have a maximum of 20 characters.");
        }
        televisionDataBase.set(id, television);
        return new ResponseEntity<>("New list with televisions: " + televisionDataBase, HttpStatus.ACCEPTED);
    }

    // heb de TV verwijderd door het de waarde null te geven - dan behoudt elke tv zijn originele 'id' (index) (zie ook omschrijving bonus)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) throws IndexOutOfBoundsException {
        if (id < 0 || id >= televisionDataBase.size()) {
            throw new IndexOutOfBoundsException("The TV with id number " + id + " does not exist");
        }
        televisionDataBase.set(id, null);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

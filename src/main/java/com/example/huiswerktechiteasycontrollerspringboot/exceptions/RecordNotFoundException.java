package com.example.huiswerktechiteasycontrollerspringboot.exceptions;

public class RecordNotFoundException extends RuntimeException {

    //Constructors
    public RecordNotFoundException() {
        super();
    }


    public RecordNotFoundException(String message) {
        super(message);
    }


}


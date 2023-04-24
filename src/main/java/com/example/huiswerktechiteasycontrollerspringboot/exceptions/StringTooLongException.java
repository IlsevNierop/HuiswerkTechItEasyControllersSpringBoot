package com.example.huiswerktechiteasycontrollerspringboot.exceptions;

public class StringTooLongException extends RuntimeException {

    public StringTooLongException(){
        super();
    }


    public StringTooLongException(String message){
        super(message);
    }

}


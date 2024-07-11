package com.devsuperior.dscommerce.services.exceptions;

//RuntimeException não exige try catch
public class DatabaseException extends RuntimeException{
    public DatabaseException(String msg){
        super(msg);
    }
}

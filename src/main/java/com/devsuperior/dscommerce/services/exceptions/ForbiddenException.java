package com.devsuperior.dscommerce.services.exceptions;

//RuntimeException não exige try catch
public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String msg){
        super(msg);
    }
}

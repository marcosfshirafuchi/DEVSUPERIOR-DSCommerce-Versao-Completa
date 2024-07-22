package com.devsuperior.dscommerce.services.exceptions;

/**
 * <h1> DEVSUPERIOR - Java Spring Professional - Capítulo: Login e controle de acesso</h1>
 * DESAFIO: Projeto Spring Boot estruturado
 * <p>
 * <b>Note:</b> Desenvolvido na linguagem Java.
 *
 * @author  Marcos Ferreira Shirafuchi
 * @version 1.0
 * @since   22/07/2024
 */

//RuntimeException não exige try catch
public class DatabaseException extends RuntimeException{
    public DatabaseException(String msg){
        super(msg);
    }
}

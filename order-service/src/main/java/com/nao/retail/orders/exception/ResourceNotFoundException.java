package com.nao.retail.orders.exception;

/** Excepción 404 para recursos inexistentes. */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) { super(message); }
}

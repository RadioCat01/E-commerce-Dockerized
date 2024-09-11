package com.Ecom.Order.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinesException extends RuntimeException {

    private final String msg;
}

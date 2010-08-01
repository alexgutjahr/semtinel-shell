package org.semtinel.core.shell.util;

/**
 * @author Alexander Hanschke <dev@alexander-hanschke.de>
 * @version 01.08.2010
 */
public class ConversionException extends RuntimeException {

    ConversionException(String message) {
        super(message);
    }

    ConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}

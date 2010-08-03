package org.semtinel.core.shell;

/**
 * @author Alexander Hanschke <dev@alexander-hanschke.de>
 * @version 03.08.2010
 */
public class UnsupportedParameterTypeException extends CommandExecutionException {

    public UnsupportedParameterTypeException(String message) {
        super(message);
    }

    public UnsupportedParameterTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedParameterTypeException(Class<?> parameterType) {
        this(String.format("%s is currently not supported as method parameter!", parameterType));
    }
}
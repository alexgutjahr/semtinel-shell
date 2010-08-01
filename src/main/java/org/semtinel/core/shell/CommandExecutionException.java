package org.semtinel.core.shell;

/**
 * @author Alexander Hanschke <dev@alexander-hanschke.de>
 * @version 01.08.2010
 */
public class CommandExecutionException extends RuntimeException {

    public CommandExecutionException(String message) {
        super(message);
    }

    public CommandExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

}
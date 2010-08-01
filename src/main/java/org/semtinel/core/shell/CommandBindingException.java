package org.semtinel.core.shell;

/**
 * @author Alexander Hanschke <dev@alexander-hanschke.de>
 * @version 01.08.2010
 */
public class CommandBindingException extends CommandExecutionException {

    CommandBindingException(String message) {
        super(message);
    }

    CommandBindingException(String message, Throwable cause) {
        super(message, cause);
    }

}
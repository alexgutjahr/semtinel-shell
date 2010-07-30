/**
 * This file is part of Semtinel (http://www.semtinel.org).
 * Copyright (c) 2007-2010 Kai Eckert (http://www.kaiec.org).
 *
 * Semtinel is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Semtinel is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Semtinel. If not, see <http://www.gnu.org/licenses/>.
 */

package org.semtinel.core.shell;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import static org.semtinel.core.shell.util.ReflectionUtils.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexander Hanschke <dev@alexander-hanschke.de>
 * @version 30.07.2010
 */
public class CommandRunner {

    @Inject
    private CommandExecutionStrategy strategy;

    @Inject
    private Shell shell;

    private Collection<Method> commandAnnotatedMethods;

    private Map<Command, Method> commandMapping = new HashMap<Command, Method>();

    private Collection<Method> getCommandAnnotatedMethods() {
        if (commandAnnotatedMethods == null) {
            commandAnnotatedMethods = getMethodsAnnotatedWith(strategy, Command.class);
        }

        return commandAnnotatedMethods;
    }

    public void executeCommand(String commandName, String[] arguments) {
        if (commandName.isEmpty()) {
            return;
        }
        
        Method mappedMethod;

        try {
            mappedMethod = getCommandMethodForCommandName(commandName);
        } catch (CommandNotBoundException e) {
            shell.flashMessage(e.getMessage());
            return;
        }

        if (isNoArgsMethod(mappedMethod)) {
            invokeMethod(mappedMethod);
        }
    }

    private void invokeMethod(Method method, Object... args) {
        try {
            method.invoke(strategy, args);
        } catch (IllegalAccessException e1) {
            shell.flashMessage(
                    String.format("method %s is not accessible!", method.getName())
            );
        } catch (IllegalArgumentException e2) {
            shell.flashMessage(
                    String.format("invalid arguments for method %s!", method.getName())
            );
        }  catch (InvocationTargetException e3) {
            shell.flashMessage(
                String.format("invocation target exception for method %s", method.getName())
            );
        } 
    }

    private Method getCommandMethodForCommandName(String commandName) {
        for (Method method : getCommandAnnotatedMethods()) {
            if (method.getAnnotation(Command.class).name().equals(commandName)) {
                return method;
            }
        }

        throw new CommandNotBoundException(
                String.format("command '%s' is not bound!", commandName)
        );
    }
}
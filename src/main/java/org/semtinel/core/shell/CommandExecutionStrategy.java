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
 * along with Semtinel.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.semtinel.core.shell;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.Date;

/**
 * @author Alexander Hanschke <dev@alexander-hanschke.de>
 * @version 30.07.2010
 */
public class CommandExecutionStrategy {

    @Inject
    private Shell shell;

    @Command(name = "time", help = "display the current time")
    public void time() {
        shell.flashMessage(new Date().toString());
    }

    @Command(name = "help", help = "display the help")
    public void help(@Option(key = "commands", help = "a collection of all available commands")
    Collection<Command> commands) {
        
        for (Command command : commands) {
            shell.flashMessage(
                    String.format("%s\n\t%s", command.name(), command.help())
            );
        }
    }

    @Command(name = "quit", help = "shutdown the shell")
    public void shutdown() {
        shell.requestShutdown();
    }
}

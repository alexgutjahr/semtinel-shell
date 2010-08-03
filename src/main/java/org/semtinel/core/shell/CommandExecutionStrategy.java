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

import com.google.inject.Inject;

import javax.swing.text.DateFormatter;
import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * @author Alexander Hanschke <dev@alexander-hanschke.de>
 * @version 30.07.2010
 */
public class CommandExecutionStrategy {

    @Inject
    private Shell shell;

    private DateFormat df;

    public CommandExecutionStrategy() {
        df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM, Locale.getDefault());
    }

    @Command(name = "time", help = "display the current time", usage = "time")
    public void time() {
        shell.flashMessage(df.format(new Date()));
    }

    @Command(name = "quit", help = "shutdown the shell", usage = "quit")
    public void shutdown() {
        shell.requestShutdown();
    }

    @Command(name = "config", help = "configure the shell", usage = "config verbose=[true|false]")
    public void config(@Option(key = "verbose", help = "define logging behavior") boolean verbose) {
        shell.setVerbose(verbose);                
    }

    @Command(name = "echo", help = "echo the value", usage = "echo value=hello")
    public void echo(@Option(key = "value") String value) {
        shell.flashMessage(value);
    }

}
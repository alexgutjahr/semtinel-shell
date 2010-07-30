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

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Alexander Hanschke <dev@alexander-hanschke.de>
 * @version 30.07.2010
 */
public class SemtinelShell extends Thread implements Shell {

    private final String SHELL_PROMPT = "semtinel> ";

    private final String VERSION = "v0.0.1";

    private boolean shutdownRequested;

    private boolean verbose = false;

    @Inject
    private Parser parser;

    @Inject
    private CommandRunner runner;

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ShellModule());
        injector.getInstance(SemtinelShell.class).start();
    }

    @Override
    public void run() {
        init();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;

        try {
            while (!shutdownRequested && (input = readUserInputFrom(reader)) != null) {
                ParseResult result = parser.parse(input);
                runner.executeCommand(result.getCommandName(), result.getArguments());
            }
        } catch (IOException e) {
            shutdown();
        }

        shutdown();
    }

    private void init() {
        setStatus(ShellStatus.BOOTING);
        flashMessage(getWelcomeMessage());
    }

    private String readUserInputFrom(BufferedReader reader) throws IOException {
        System.out.print(SHELL_PROMPT);

        return reader.readLine();
    }

    private void shutdown() {
        setStatus(ShellStatus.SHUTTING_DOWN);
    }

    @Override
    public void requestShutdown() {
        shutdownRequested = true;
    }

    @Override
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    @Override
    public void flashMessage(String message) {
        System.out.println(SHELL_PROMPT + message);
    }

    @Override
    public void setStatus(ShellStatus status) {
        if (verbose) {
            flashMessage(status.getMessage());
        }
    }

    @Override
    public String getShellPrompt() {
        return SHELL_PROMPT;
    }

    private String getWelcomeMessage() {
        String newLine = System.getProperty("line.separator");

        StringBuilder sb = new StringBuilder(newLine);

        sb.append(" ____                       __                        ___      ").append(newLine);
        sb.append("/\\  _`\\                    /\\ \\__  __                /\\_ \\     ").append(newLine);
        sb.append("\\ \\,\\L\\_\\     __    ___ ___\\ \\ ,_\\/\\_\\    ___      __\\//\\ \\    ").append(newLine);
        sb.append(" \\/_\\__ \\   /'__`\\/' __` __`\\ \\ \\/\\/\\ \\ /' _ `\\  /'__`\\\\ \\ \\   ").append(newLine);
        sb.append("   /\\ \\L\\ \\/\\  __//\\ \\/\\ \\/\\ \\ \\ \\_\\ \\ \\/\\ \\/\\ \\/\\  __/ \\_\\ \\_ ").append(newLine);
        sb.append("   \\ `\\____\\ \\____\\ \\_\\ \\_\\ \\_\\ \\__\\\\ \\_\\ \\_\\ \\_\\ \\____\\/\\____\\").append(newLine);
        sb.append("    \\/_____/\\/____/\\/_/\\/_/\\/_/\\/__/ \\/_/\\/_/\\/_/\\/____/\\/____/").append(newLine);
        sb.append("                                         Semtinel Shell ").append(VERSION);
        
        return sb.toString();
    }

}
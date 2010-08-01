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

/**
 * @author Alexander Hanschke <dev@alexander-hanschke.de>
 * @version 30.07.2010
 */
public interface Shell {

    /**
     *
     * @param message the message to be flashed on the shell
     */
    public void flashMessage(String message);

    /**
     *
     * @param status the {@link ShellStatus} of the shell
     */
    public void setStatus(ShellStatus status);

    /**
     *
     * @return the shell prompt
     */
    public String getShellPrompt();

    /**
     * requests the shell to shutdown - however this action does not guarantee an immediate shutdown,
     * as the shell may perform activities before shutting down eventually
     */
    public void requestShutdown();

    /**
     *
     * @param verbose {@code true} will flash all status changes on the shell
     */
    public void setVerbose(boolean verbose);
    
}
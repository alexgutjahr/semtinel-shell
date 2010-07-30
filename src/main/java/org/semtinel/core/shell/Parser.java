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

/**
 * @author Alexander Hanschke <dev@alexander-hanschke.de>
 * @version 30.07.2010
 */
public class Parser {

    @Inject
    private Shell shell;

    public ParseResult parse(String request) {
        shell.setStatus(ShellStatus.PARSING);
        String[] tokens = tokenize(request);

        Preconditions.checkNotNull(tokens, "error while parsing request!");
        Preconditions.checkState(tokens.length > 0);

        return new ParseResult(tokens[0], new String[]{""});
    }

    private String[] tokenize(String request) {
        Preconditions.checkNotNull(request, "input must not be null!");

        return request.trim().split(" ");
    }

}
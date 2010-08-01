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

package org.semtinel.core.shell.util;

import java.lang.reflect.Method;

/**
 * @author Alexander Hanschke <dev@alexander-hanschke.de>
 * @version 01.08.2010
 */
public class ConversionUtils {

    public static Object parseToType(String value, Class<?> requiredType) {
        if (requiredType.isPrimitive()) {
            return parseToPrimitive(value, requiredType);
        }

        if (requiredType == String.class) {
            return value;
        }

        return null;
    }

    private static Object parseToPrimitive(String value, Class<?> requiredType) {
        if (requiredType == boolean.class) {
            return parseToBoolean(value);
        }

        if (requiredType == int.class) {
            return parseToInt(value);
        }

        if (requiredType == double.class) {
            return parseToDouble(value);
        }

        if (requiredType == float.class) {
            return parseToFloat(value);
        }

        if (requiredType == long.class) {
            return parseToLong(value);
        }

        if (requiredType == char.class) {
            return parseToChar(value);
        }

        throw new ConversionException(
                String.format("'%s' cannot be parsed to primitive of type %s", value, requiredType)
        );
    }

    public static boolean parseToBoolean(String value) {
        return value.equalsIgnoreCase("true");

    }

    public static int parseToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ConversionException(
                    String.format("could not convert '%s' to int!", value)
            );
        }
    }

    public static double parseToDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new ConversionException(
                    String.format("could not convert '%s' to double!", value)
            );
        }
    }

    public static char parseToChar(String value) {
        if (value.isEmpty()) {
            throw new ConversionException("could not convert empty String to char");
        }

        return value.charAt(0);
    }

    public static float parseToFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            throw new ConversionException(
                    String.format("could not convert '%s' to float!", value)
            );
        }
    }

    public static long parseToLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new ConversionException(
                    String.format("could not convert '%s' to long!", value)
            );
        }
    }
}

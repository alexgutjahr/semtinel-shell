package org.semtinel.core.shell.util;

import java.lang.reflect.Method;

/**
 * @author Alexander Hanschke <dev@alexander-hanschke.de>
 * @version 01.08.2010
 */
public class ConversionUtils {

    public static Object parseToType(String value, Class<?> requiredType) {
        if (requiredType.isPrimitive()) {
            if (requiredType == boolean.class) {
                return parseToBoolean(value);
            }

            if (requiredType == int.class) {
                return parseToInt(value);
            }
        }

        return null;
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
}

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

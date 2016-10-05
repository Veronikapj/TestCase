package com.sample.testcase;

/**
 * Created by veronikapj on 16. 10. 4.
 */
public class TemperatureConverter {

    public static final double ABSOLUTE_ZERO_C = -273.15d;
    public static final double ABSOLUTE_ZERO_F = -459.67d;
    private static final String ERROR_MESSAGE_BELOW_ZERO_FMT =
            "Invalid temperature: %.2f%c below absolute zero";

    public static double celsiusToFahrenheit(double c) {
        if (c < ABSOLUTE_ZERO_C) {
            throw new InvalidTemperatureException(
                    String.format(ERROR_MESSAGE_BELOW_ZERO_FMT, c, 'C'));
        }
        return (c * 1.8d + 32);
    }

    /**
     * C2F : celsiusToFahrenheit
     * F2C : fahrenheitToCelsius
     */
    public static enum OP {
        C2F, F2C
    }

    public static double fahrenheitToCelsius(double f) {
        if (f < ABSOLUTE_ZERO_F) {
            throw new InvalidTemperatureException(
                    String.format(ERROR_MESSAGE_BELOW_ZERO_FMT, f, 'F'));
        }
        return ((f - 32) / 1.8d);
    }

    public static class InvalidTemperatureException extends RuntimeException {
        public InvalidTemperatureException(String f) {
            super(f);
        }
    }
}

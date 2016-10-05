package com.sample.testcase;


import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by veronikapj on 16. 10. 4.
 */
public class TemperatureConverterTest {

    private static final HashMap<Double, Double> conversionTableDouble =
            new HashMap<>();

    static {
        // initialize (c, f) pairs
        conversionTableDouble.put(0.0, 32.0);
        conversionTableDouble.put(100.0, 212.0);
        conversionTableDouble.put(-1.0, 30.20);
        conversionTableDouble.put(-100.0, -148.0);
        conversionTableDouble.put(32.0, 89.60);
        conversionTableDouble.put(-40.0, -40.0);
        conversionTableDouble.put(-273.0, -459.40);
    }

    @Test
    public void convertFahrenheitToCelsius() {
        for(double c : conversionTableDouble.keySet()) {
            final double f = conversionTableDouble.get(c);
            final double ca = TemperatureConverter.fahrenheitToCelsius(f);
            final double delta = Math.abs(ca - c);
            final String msg = "" + f + "F -> " + c + "C but is "
                    + ca + " (delta " + delta + ")";
            assertTrue(msg, delta < 0.0001);
        }
    }

    @Test
    public final void convertCelsiusToFahrenheit() {
        for (double c: conversionTableDouble.keySet()) {
            final double f = conversionTableDouble.get(c);
            final double fa = TemperatureConverter.celsiusToFahrenheit(c);
            final double delta = Math.abs(fa - f);
            final String msg = "" + c + "C -> " + f + "F but is " + fa +
                    " (delta " + delta + ")";
            assertTrue(msg, delta < 0.0001);
        }
    }

    @Test
    public final void exceptionForLessThanAbsoluteZeroF() {
        try {
            TemperatureConverter.fahrenheitToCelsius(
                    TemperatureConverter.ABSOLUTE_ZERO_F - 1
            );
            fail();
        } catch (TemperatureConverter.InvalidTemperatureException ex) {

        }
    }

    @Test
    public final void exceptionForLessThanAbsoluteZeroC() {
        try {
            TemperatureConverter.celsiusToFahrenheit(
                    TemperatureConverter.ABSOLUTE_ZERO_C - 1
            );
            fail();
        } catch (TemperatureConverter.InvalidTemperatureException ex) {

        }
    }
}
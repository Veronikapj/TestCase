package com.sample.testcase;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.util.concurrent.ExecutionError;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by veronikapj on 16. 10. 4.
 */
@RunWith(AndroidJUnit4.class)
public class EditNumberTest {

    EditNumber mEditNumber;

    @Before
    public void setUp() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        mEditNumber = new EditNumber(appContext);
        mEditNumber.setFocusable(true);
    }

    @Test
    public void setNumber() throws Exception {
        mEditNumber.setNumber(123.45);
        final String expected = "123.45";
        final String actual = mEditNumber.getText().toString();
        assertEquals(expected, actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void getNumber() throws Exception {
        mEditNumber.setNumber(123.45);
        final double expected = 123.45;
        final double actual = mEditNumber.getNumber();
        assertEquals(expected, actual, 0);
        assertThat(actual, is(expected));
    }

    @Test
    public void clear() throws Exception {
        final String value = "123.45";
        mEditNumber.setText(value);
        mEditNumber.clear();
        String expectedString = "";
        String actualString = mEditNumber.getText().toString();
        assertEquals(expectedString, actualString);
    }

}
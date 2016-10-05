package com.sample.testcase;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftAlignedWith;
import static android.support.test.espresso.assertion.PositionAssertions.isRightAlignedWith;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


/**
 * Created by veronikapj on 16. 10. 4.
 */
@RunWith(AndroidJUnit4.class)
public class TemperatureConverterActivityTest {

    private Activity mActivity;
    private TextView mCelsiusText;
    private TextView mFahrenheitText;
    private EditNumber mCelsius;
    private EditNumber mFahrenheit;

    @Rule
    public ActivityTestRule<TemperatureConverterActivity> mActivityRule =
            new ActivityTestRule<>(TemperatureConverterActivity.class);

    @Rule
    public UiThreadTestRule uiThreadTestRule = new UiThreadTestRule();

    @Before
    public void beforeTest() {
        mActivity = mActivityRule.getActivity();
        mCelsiusText = (TextView)mActivity.findViewById(R.id.celsius_label);
        mFahrenheitText = (TextView)mActivity.findViewById(R.id.fahrenheit_label);
        mCelsius = (EditNumber)mActivity.findViewById(R.id.celsius);
        mFahrenheit = (EditNumber)mActivity.findViewById(R.id.fahrenheit);
    }

    @Test
    public void fieldOnScreen() {
        // Should be displayed
        onView(withId(R.id.celsius))
//                .inRoot(withDecorView(is(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));

        onView(withId(R.id.fahrenheit))
//                .inRoot(withDecorView(is(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void inputFieldsShouldStartEmpty() {
        //	6. 처음에는 입력창이 비어있어야 한다.
        onView(withId(R.id.celsius)).check(matches(withText("")));
        onView(withId(R.id.fahrenheit)).check(matches(withText("")));
    }

    @Test
    public final void alignment() {
        onView(withId(R.id.celsius_label)).check(isLeftAlignedWith(withId(R.id.celsius)));
        onView(withId(R.id.fahrenheit_label)).check(isLeftAlignedWith(withId(R.id.fahrenheit)));
        onView(withId(R.id.celsius)).check(isLeftAlignedWith(withId(R.id.fahrenheit)));
        onView(withId(R.id.celsius)).check(isRightAlignedWith(withId(R.id.fahrenheit)));
    }

    @Test
    public final void textFontSize() {
        final float expected = 48.0f;
        assertEquals(expected, mCelsiusText.getTextSize());
        assertEquals(expected, mFahrenheitText.getTextSize());
    }

    @Test
    public final void virtualKeyboardSpaceReserved() {
        final int expected = 280;
        EditText mFahrenheit = (EditText)mActivity.findViewById(R.id.fahrenheit);
        final int actual = mFahrenheit.getBottom();
        assertTrue(actual >= expected);
    }

    @Test
    public final void fahrenheitToCelsiusConversion() throws Throwable{
        uiThreadTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCelsius.clear();
                mFahrenheit.clear();

                final double f = 32.5f;
                mFahrenheit.requestFocus();
                mFahrenheit.setNumber(f);

                mCelsius.requestFocus();
                final double expectedC = TemperatureConverter.fahrenheitToCelsius(f);
                final double actualC = mCelsius.getNumber();
                final double delta = Math.abs(expectedC - actualC);
                final String msg = "" + f + "F ->  " + expectedC +"C but was " + actualC+ "C(delta " + delta + ")";
                assertTrue(msg, delta < 0.005);
            }
        });
    }

    @Test
    public void inputFilter() {
        onView(withId(R.id.celsius)).perform(typeTextIntoFocusedView("-1.23qw4"));
        assertEquals(mCelsius.getNumber(), -1.234);
    }



}
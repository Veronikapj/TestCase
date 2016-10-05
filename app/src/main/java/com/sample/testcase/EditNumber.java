package com.sample.testcase;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by veronikapj on 16. 10. 4.
 */

public class EditNumber extends EditText {
    
    public EditNumber(Context context) {
        super(context);
        init();
    }

    public EditNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        final InputFilter[] filters = new InputFilter[] {
                DigitsKeyListener.getInstance(true, true)
        };
        setFilters(filters);
    }

    public void clear() {
        setText("");
    }

    private static final String DEFAULT_FORMAT = "%.2f";
    public void setNumber(double f) {
        super.setText(String.format(DEFAULT_FORMAT, f));
    }

    public double getNumber() {
        return Double.valueOf(getText().toString());
    }
}

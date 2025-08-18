package com.ekn.gruzer.gaugelibrary;

import com.ekn.gruzer.gaugelibrary.contract.ValueFormatter;

public class ValueFormatterImpl implements ValueFormatter {
    public String getFormattedValue(double d) {
        return String.valueOf(d);
    }
}

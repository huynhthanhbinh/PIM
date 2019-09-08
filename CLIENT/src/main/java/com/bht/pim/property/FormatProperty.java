package com.bht.pim.property;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.util.LanguageUtil;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author bht
 */
public final class FormatProperty {

    public static final StringProperty DATE_PATTERN_PROPERTY = new SimpleStringProperty();

    public FormatProperty() {
        initDatePatternProperty();
        addEventListener();
    }

    private void addEventListener() {
        AppConfiguration.LANGUAGE_PROPERTY.getLocaleProperty()
                .addListener((observable, oldLocale, newLocale) -> reloadDatePattern());
    }

    private void initDatePatternProperty() {
        reloadDatePattern();
    }

    private void reloadDatePattern() {
        DATE_PATTERN_PROPERTY.set(LanguageUtil.getCurrentLabelOfKey("pattern.date"));
    }
}
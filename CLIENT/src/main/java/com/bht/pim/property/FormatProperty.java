package com.bht.pim.property;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.bht.pim.configuration.AppConfiguration;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author bht
 */
public final class FormatProperty {

    public FormatProperty() {
        initDatePatternMap();
        initDatePatternProperty();
        addEventListener();
    }

    private Map<Locale, String> datePatternMap;
    public static final StringProperty DATE_PATTERN_PROPERTY = new SimpleStringProperty();

    private void initDatePatternMap() {
        datePatternMap = new HashMap<>();
        datePatternMap.put(Locale.ENGLISH, "MM/dd/yyyy");
        datePatternMap.put(Locale.FRENCH, "dd/MM/yyyy");
    }

    private void addEventListener() {
        AppConfiguration.LANGUAGE_PROPERTY.getLocaleProperty()
                .addListener((observable, oldLocale, newLocale) -> {
                    reloadDatePattern(newLocale);
                });
    }

    private void initDatePatternProperty() {
        reloadDatePattern(AppConfiguration.LANGUAGE_PROPERTY.getLocaleProperty().get());
    }

    private void reloadDatePattern(Locale locale) {
        DATE_PATTERN_PROPERTY.set(getDatePattern(locale));
    }

    private String getDatePattern(Locale locale) {
        return datePatternMap.get(locale);
    }
}
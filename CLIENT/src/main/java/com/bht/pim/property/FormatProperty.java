package com.bht.pim.property;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseBean;
import com.bht.pim.util.LanguageUtil;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;

/**
 *
 * @author bht
 */
@Component
public final class FormatProperty implements BaseBean {

    public static final StringProperty DATE_PATTERN_PROPERTY = new SimpleStringProperty();
    public static final ObjectProperty<StringConverter<LocalDate>> DATE_STRING_CONVERTER = new SimpleObjectProperty<>();
    public static final ObjectProperty<DateTimeFormatter> DATE_FORMATTER_PROPERTY = new SimpleObjectProperty<>();

    @Autowired
    private LanguageProperty languageProperty;

    @Override
    public void initialize() throws IOException {
        BaseBean.super.initialize();
        addAllEventListeners();
        initDatePatternProperty();
    }

    private void addAllEventListeners() {
        languageProperty.getLocaleProperty()
                .addListener((observable, oldLocale, newLocale) -> reloadDatePattern());
    }

    private void initDatePatternProperty() {
        reloadDatePattern();
    }

    // localeProperty --> datePatternProperty --> dateFormatProperty --> dateStringConverter (flow of property change)
    private void reloadDatePattern() {
        DATE_PATTERN_PROPERTY.set(LanguageUtil.getCurrentLabelOfKey("pattern.date"));
        DATE_FORMATTER_PROPERTY.set(DateTimeFormatter.ofPattern(DATE_PATTERN_PROPERTY.get()));
        DATE_STRING_CONVERTER.set(dateStringConverter(DATE_FORMATTER_PROPERTY.get()));
    }

    private static StringConverter<LocalDate> dateStringConverter(DateTimeFormatter formatter) {
        return new StringConverter<LocalDate>() {

            @Override
            public String toString(LocalDate localDate) {
                return getString(localDate);
            }

            @Override
            public LocalDate fromString(String string) {
                if (string == null || string.trim().isEmpty()) {
                    return null;
                }
                try {
                    return LocalDate.parse(string, formatter);

                } catch (Exception exception) {
                    return null;
                }
            }
        };
    }

    public static String getString(LocalDate localDate) {
        return (localDate != null)
                ? DATE_FORMATTER_PROPERTY.get().format(localDate)
                : "";
    }
}
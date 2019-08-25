package com.bht.pim.util;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import lombok.extern.log4j.Log4j;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Log4j
@Component
public class PimUtil {

    public final StringConverter<LocalDate> dateStringConverter = dateStringConverter();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Format Date : convert from Timestamp to LocalDate
    public <T> TableCell<T, LocalDate> dateFormat(TableColumn<T, LocalDate> column) {
        return new TableCell<T, LocalDate>() {
            @Override
            protected void updateItem(LocalDate localDate, boolean empty) {
                if (localDate == null || empty) {
                    setText(null);
                    setStyle("");

                } else {
                    setText(dateFormatter.format(localDate));
                    setStyle("");
                }
            }
        };
    }

    // Converter for pim date-picker controls
    private StringConverter<LocalDate> dateStringConverter() {
        return new StringConverter<LocalDate>() {

            private DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate object) {
                return (object != null) ?
                        (dateTimeFormatter.format(object)) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                if (string == null || string.trim().isEmpty()) {
                    return null;
                }
                try {
                    return LocalDate.parse(string, dateTimeFormatter);

                } catch (Exception exception) {

                    log.info(exception);
                    return null;
                }
            }
        };
    }

    public static Image getImage(String path) {
        return new Image(Objects.requireNonNull(PimUtil.class.getClassLoader()
                .getResourceAsStream("pictures/" + path + ".png")));
    }

    public static void alignPane(VBox mainPane, Context context) {
        mainPane.setAlignment(Pos.CENTER);
        mainPane.prefWidthProperty().bind(context
                .getComponentLayout()
                .getGlassPane()
                .widthProperty()
                .subtract(227));
    }
}

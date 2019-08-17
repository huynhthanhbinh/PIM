package com.bht.pim.util;

import com.bht.pim.dto.ProjectDto;
import com.bht.pim.mapper.DateTimeMapper;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.StringConverter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j
@Component
public class ProjectUtil {

    public final StringConverter<LocalDate> dateStringConverter = dateStringConverter();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private DateTimeMapper dateTimeMapper;

    // Format Date : convert from Timestamp to LocalDate
    public TableCell<ProjectDto, LocalDate> dateFormat(TableColumn<ProjectDto, LocalDate> column) {
        return new TableCell<ProjectDto, LocalDate>() {
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
}

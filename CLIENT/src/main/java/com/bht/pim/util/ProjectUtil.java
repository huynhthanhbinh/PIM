package com.bht.pim.util;

import com.bht.pim.mapper.DateTimeMapper;
import com.bht.pim.mapper.StatusMapper;
import com.bht.pim.proto.projects.ProjectInfo;
import com.google.protobuf.Timestamp;
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
    private final Timestamp nonSetup = Timestamp.newBuilder().build();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private DateTimeMapper dateTimeMapper;

    // Format Status : convert from server data to status
    public TableCell<ProjectInfo, String> statusFormat(TableColumn<ProjectInfo, String> column) {
        return new TableCell<ProjectInfo, String>() {

            @Override
            protected void updateItem(String item, boolean empty) {

                if (item == null || empty) {
                    setText(null);

                } else {
                    setText(statusMapper.toGuiStatus(item));
                }
            }
        };
    }

    // Format Date : convert from Timestamp to LocalDate
    public TableCell<ProjectInfo, Timestamp> dateFormat(TableColumn<ProjectInfo, Timestamp> column) {
        return new TableCell<ProjectInfo, Timestamp>() {
            @Override
            protected void updateItem(Timestamp item, boolean empty) {
                if (item == null || empty) {
                    setText(null);
                    setStyle("");

                } else {
                    // Format date
                    if (item.equals(nonSetup)) { // Not set date yet
                        setText("/");

                    } else {
                        LocalDate localDate = dateTimeMapper.toLocalDate(item);
                        setText(dateFormatter.format(localDate));
                    }
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

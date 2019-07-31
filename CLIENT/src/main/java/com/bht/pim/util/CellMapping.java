package com.bht.pim.util;

import com.bht.pim.proto.project.Project;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CellMapping {
    public static TableCell<Project, Long> DATE(TableColumn<Project, Long> column) {
        return new TableCell<Project, Long>() {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            protected void updateItem(Long item, boolean empty) {
                if (item == null || empty) {
                    setText(null);
                    setStyle("");

                } else {
                    // Format date
                    if (item == 0) { // Not set date yet
                        setText("         /");

                    } else {
                        Date date = new Date(item);
                        setText(dateFormatter.format(date));
                    }
                    setStyle("");
                }
            }
        };
    }

    public static TableCell<Project, String> STATUS(TableColumn<Project, String> column) {
        return new TableCell<Project, String>() {

            @Override
            protected void updateItem(String item, boolean empty) {

                if (item == null || empty) {
                    setText(null);

                } else {
                    switch (item) {
                        case "NEW":
                            setText("New");
                            return;

                        case "PLA":
                            setText("Planned");
                            return;

                        case "INP":
                            setText("In progress");
                            return;

                        case "FIN":
                            setText("Finished");
                            return;

                        default:
                            setText("Not set");
                    }
                }
            }
        };
    }
}

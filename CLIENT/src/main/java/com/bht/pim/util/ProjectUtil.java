package com.bht.pim.util;

import com.bht.pim.proto.projects.ProjectInfo;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public class ProjectUtil {

    // Format Status : convert from server data to status
    public static TableCell<ProjectInfo, String> statusFormat(TableColumn<ProjectInfo, String> column) {
        return new TableCell<ProjectInfo, String>() {

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

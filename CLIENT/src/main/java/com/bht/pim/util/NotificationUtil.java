package com.bht.pim.util;

import com.bht.pim.notification.JFXNotifications;
import com.bht.pim.notification.NotificationStyle;
import javafx.geometry.Pos;
import javafx.util.Duration;

public class NotificationUtil {
    public static void showNotification(NotificationStyle style, Pos position, String message) {
        switch (style) {

            case INFO:
                JFXNotifications.create()
                        .title("INFORMATION")
                        .text(message)
                        .hideAfter(Duration.seconds(8))
                        .position(position)
                        .showInfo();
                break;

            case SUCCESS:
                JFXNotifications.create()
                        .title("SUCCESS")
                        .text(message)
                        .hideAfter(Duration.seconds(8))
                        .position(position)
                        .showSuccess();
                break;

            case WARNING:
                JFXNotifications.create()
                        .title("WARNING")
                        .text(message)
                        .hideAfter(Duration.seconds(8))
                        .position(position)
                        .showWarning();
                break;

            case ERROR:
                JFXNotifications.create()
                        .title("ERROR")
                        .text(message)
                        .hideAfter(Duration.seconds(8))
                        .position(position)
                        .showError();
                break;

            default:
                break;
        }
    }
}
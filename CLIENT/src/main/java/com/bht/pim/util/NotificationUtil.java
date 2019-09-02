package com.bht.pim.util;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.notification.JFXNotifications;
import com.bht.pim.notification.NotificationStyle;
import javafx.geometry.Pos;
import javafx.util.Duration;

/**
 * @author bht
 */
public final class NotificationUtil {

    private static final int PERIOD = 5;

    private NotificationUtil() {

    }

    public static void showNotification(NotificationStyle style, Pos position, String message) {
        switch (style) {

            case INFO:
                JFXNotifications.create()
                        .title(AppConfiguration.INFORMATION_TITLE.get())
                        .text(message)
                        .hideAfter(Duration.seconds(PERIOD))
                        .position(position)
                        .showInfo();
                break;

            case SUCCESS:
                JFXNotifications.create()
                        .title(AppConfiguration.SUCCESS_TITLE.get())
                        .text(message)
                        .hideAfter(Duration.seconds(PERIOD))
                        .position(position)
                        .showSuccess();
                break;

            case WARNING:
                JFXNotifications.create()
                        .title(AppConfiguration.WARNING_TITLE.get())
                        .text(message)
                        .hideAfter(Duration.seconds(PERIOD))
                        .position(position)
                        .showWarning();
                break;

            case ERROR:
                JFXNotifications.create()
                        .title(AppConfiguration.ERROR_TITLE.get())
                        .text(message)
                        .hideAfter(Duration.seconds(PERIOD))
                        .position(position)
                        .showError();
                break;

            default:
                break;
        }
    }
}
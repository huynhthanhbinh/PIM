package com.bht.pim.util;

import com.bht.pim.notification.JFXNotifications;
import com.bht.pim.notification.NotificationType;

import javafx.geometry.Pos;
import javafx.util.Duration;

/**
 *
 * @author bht
 */
public final class NotificationUtil {

    private static final int LASTING = 3; // time showing notification in seconds

    private NotificationUtil() {
    }

    public static void showNotification(NotificationType type, Pos position, String message) {
        JFXNotifications.getCurrentInstance()
                .setType(type)
                .setMessage(message)
                .setDuration(Duration.seconds(LASTING))
                .setPosition(position)
                .show();
    }
}
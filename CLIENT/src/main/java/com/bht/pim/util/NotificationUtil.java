package com.bht.pim.util;

import com.bht.pim.notification.JFXNotificationCustomized;
import com.bht.pim.notification.JFXNotificationType;

import javafx.geometry.Pos;

/**
 *
 * @author bht
 */
public final class NotificationUtil {

    private NotificationUtil() {
    }

    public static void showNotification(JFXNotificationType type, Pos position, String message) {
        JFXNotificationCustomized.getCurrentInstance()
                .setType(type)
                .setMessage(message)
                .setPosition(position)
                .show();
    }
}
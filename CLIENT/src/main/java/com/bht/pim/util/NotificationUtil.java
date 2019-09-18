package com.bht.pim.util;

import com.bht.pim.notification.JFXNotifications;
import com.bht.pim.notification.NotificationStyle;

import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.util.Duration;

/**
 * @author bht
 */
public final class NotificationUtil {
    /**
     * time showing notification in seconds
     */
    private static final int PERIOD = 8;

    private static final StringProperty INFORMATION_TITLE = LanguageUtil.getTextPropertyOfKey("label.notification.information");
    private static final StringProperty SUCCESS_TITLE = LanguageUtil.getTextPropertyOfKey("label.notification.success");
    private static final StringProperty WARNING_TITLE = LanguageUtil.getTextPropertyOfKey("label.notification.warning");
    private static final StringProperty ERROR_TITLE = LanguageUtil.getTextPropertyOfKey("label.notification.error");

    private NotificationUtil() {
    }

    public static void showNotification(NotificationStyle style, Pos position, String message) {
        switch (style) {

            case INFO:
                JFXNotifications.create()
                        .title(INFORMATION_TITLE.get())
                        .text(message)
                        .hideAfter(Duration.seconds(PERIOD))
                        .position(position)
                        .showInfo();
                break;

            case SUCCESS:
                JFXNotifications.create()
                        .title(SUCCESS_TITLE.get())
                        .text(message)
                        .hideAfter(Duration.seconds(PERIOD))
                        .position(position)
                        .showSuccess();
                break;

            case WARNING:
                JFXNotifications.create()
                        .title(WARNING_TITLE.get())
                        .text(message)
                        .hideAfter(Duration.seconds(PERIOD))
                        .position(position)
                        .showWarning();
                break;

            case ERROR:
                JFXNotifications.create()
                        .title(ERROR_TITLE.get())
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
package com.bht.pim.notification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseBean;
import com.bht.pim.spring.SpringApplicationContext;
import com.bht.pim.util.LanguageUtil;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.stage.PopupWindow;
import javafx.stage.Screen;
import javafx.stage.Window;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * original: reference from JFXNotifications by TioCoding
 * customized for using in PIM project of ELCA by me
 *
 * @author bht
 * @author TioCoding
 */
@Getter
@Setter
@Component
@Accessors(chain = true)
@SuppressWarnings("all")
public final class JFXNotificationCustomized implements BaseBean {

    // time showing notification in seconds
    private static final int LASTING = 3;

    private EnumMap<JFXNotificationType, ImageView> iconMap;
    private EnumMap<JFXNotificationType, StringProperty> titleMap;

    private boolean hideCloseButton;
    private JFXNotificationType type;
    private String title;
    private String message;
    private String typeMessage;
    private Node graphic;
    private Pos position;
    private Duration duration;
    private EventHandler<ActionEvent> onAction;
    private Window owner;
    private Screen screen = Screen.getPrimary();
    private List<String> styleClass = new ArrayList<>();

    public static JFXNotificationCustomized getCurrentInstance() {
        return SpringApplicationContext.getBean(JFXNotificationCustomized.class);
    }

    @Override
    public void initialize() throws IOException {
        BaseBean.super.initialize();
        initTitleMap();
        initIconMap();
    }

    private void initTitleMap() {
        titleMap = new EnumMap<>(JFXNotificationType.class);
        titleMap.put(JFXNotificationType.INFO, LanguageUtil.getTextPropertyOfKey("label.notification.information"));
        titleMap.put(JFXNotificationType.SUCCESS, LanguageUtil.getTextPropertyOfKey("label.notification.success"));
        titleMap.put(JFXNotificationType.WARNING, LanguageUtil.getTextPropertyOfKey("label.notification.warning"));
        titleMap.put(JFXNotificationType.ERROR, LanguageUtil.getTextPropertyOfKey("label.notification.error"));
    }

    private void initIconMap() {
        iconMap = new EnumMap<>(JFXNotificationType.class);
        iconMap.put(JFXNotificationType.INFO, new ImageView(getClass().getResource("icon/info.png").toExternalForm()));
        iconMap.put(JFXNotificationType.SUCCESS, new ImageView(getClass().getResource("icon/success.png").toExternalForm()));
        iconMap.put(JFXNotificationType.WARNING, new ImageView(getClass().getResource("icon/warning.png").toExternalForm()));
        iconMap.put(JFXNotificationType.ERROR, new ImageView(getClass().getResource("icon/error.png").toExternalForm()));
    }

    public void show() {
        setDuration(Duration.seconds(LASTING));
        setTitle(titleMap.get(type).get());
        setGraphic(iconMap.get(type));
        showNotification();
    }

    private void showNotification() {
        JFXNotificationPopupHandler.getInstance().show(this);
    }

    /**
     * The dialog window owner - which can be {@link Screen}, {@link Window}
     * or {@link Node}. If specified, the notifications will be inside the owner,
     * otherwise the notifications will be shown within the whole primary (default) screen.
     */
    public JFXNotificationCustomized setOwner(Object owner) {
        if (owner instanceof Screen) {
            screen = (Screen) owner;

        } else {
            this.owner = getWindow(owner);
        }
        return this;
    }

    public static Window getWindow(Object owner) throws IllegalArgumentException {
        if (owner == null) {
            Window window = null;

            // lets just get the focused stage and show the dialog in there
            Iterator<Window> windows = Window.impl_getWindows();

            while (windows.hasNext()) {
                window = windows.next();
                if (window.isFocused() && !(window instanceof PopupWindow)) {
                    break;
                }
            }
            return window;

        } else if (owner instanceof Window) {
            return (Window) owner;

        } else if (owner instanceof Node) {
            return ((Node) owner).getScene().getWindow();

        } else {
            throw new IllegalArgumentException("Unknown owner: " + owner.getClass());
        }
    }
}
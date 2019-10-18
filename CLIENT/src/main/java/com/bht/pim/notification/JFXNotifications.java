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
 *
 * customized for using in PIM project of ELCA by me
 * @author bht
 * @author TioCoding
 */
@Getter
@Setter
@Component
@Accessors(chain = true)
@SuppressWarnings("all")
public final class JFXNotifications implements BaseBean {

    private EnumMap<NotificationType, ImageView> iconMap;
    private EnumMap<NotificationType, StringProperty> titleMap;

    private static final StringProperty INFORMATION_TITLE = LanguageUtil.getTextPropertyOfKey("label.notification.information");
    private static final StringProperty SUCCESS_TITLE = LanguageUtil.getTextPropertyOfKey("label.notification.success");
    private static final StringProperty WARNING_TITLE = LanguageUtil.getTextPropertyOfKey("label.notification.warning");
    private static final StringProperty ERROR_TITLE = LanguageUtil.getTextPropertyOfKey("label.notification.error");

    private boolean hideCloseButton;
    private NotificationType type;
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

    public static JFXNotifications getCurrentInstance() {
        return SpringApplicationContext.getBean(JFXNotifications.class);
    }

    @Override
    public void initialize() throws IOException {
        initTitleMap();
        initIconMap();
    }

    private void initTitleMap() {
        titleMap = new EnumMap<>(NotificationType.class);
        titleMap.put(NotificationType.INFO, INFORMATION_TITLE);
        titleMap.put(NotificationType.SUCCESS, SUCCESS_TITLE);
        titleMap.put(NotificationType.WARNING, WARNING_TITLE);
        titleMap.put(NotificationType.ERROR, ERROR_TITLE);
    }

    private void initIconMap() {
        iconMap = new EnumMap<>(NotificationType.class);
        iconMap.put(NotificationType.INFO, new ImageView(getClass().getResource("icon/info.png").toExternalForm()));
        iconMap.put(NotificationType.SUCCESS, new ImageView(getClass().getResource("icon/success.png").toExternalForm()));
        iconMap.put(NotificationType.WARNING, new ImageView(getClass().getResource("icon/warning.png").toExternalForm()));
        iconMap.put(NotificationType.ERROR, new ImageView(getClass().getResource("icon/error.png").toExternalForm()));
    }

    public void show() {
        setTitle(titleMap.get(type).get());
        setGraphic(iconMap.get(type));
        showNotification();
    }

    private void showNotification() {
        NotificationPopupHandler.getInstance().show(this);
    }

    /**
     * The dialog window owner - which can be {@link Screen}, {@link Window}
     * or {@link Node}. If specified, the notifications will be inside
     * the owner, otherwise the notifications will be shown within the whole
     * primary (default) screen.
     */
    public JFXNotifications setOwner(Object owner) {
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
            throw new IllegalArgumentException("Unknown owner: " + owner.getClass()); //$NON-NLS-1$
        }
    }
}
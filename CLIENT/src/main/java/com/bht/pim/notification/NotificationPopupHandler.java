package com.bht.pim.notification;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * separated from JFXNotifications
 *
 * @author TioCoding
 */
@SuppressWarnings("all")
final class NotificationPopupHandler {

    private static final NotificationPopupHandler INSTANCE = new NotificationPopupHandler();
    private static final double PADDING = 15;

    private final Map<Pos, List<Popup>> popupsMap = new HashMap<>();
    private double startX;
    private double startY;
    private double screenWidth;
    private double screenHeight;
    // for animating in the notifications
    private ParallelTransition parallelTransition = new ParallelTransition();
    private boolean isShowing = false;

    static NotificationPopupHandler getInstance() {
        return INSTANCE;
    }

    public void show(JFXNotifications notification) {
        Window window;
        if (notification.getOwner() == null) {
            /*
             * If the owner is not set, we work with the whole screen.
             */
            Rectangle2D screenBounds = notification.getScreen().getVisualBounds();
            startX = screenBounds.getMinX();
            startY = screenBounds.getMinY();
            screenWidth = screenBounds.getWidth();
            screenHeight = screenBounds.getHeight();

            window = JFXNotifications.getWindow(null);
        } else {
            /*
             * If the owner is set, we will make the notifications popup
             * inside its window.
             */
            startX = notification.getOwner().getX();
            startY = notification.getOwner().getY();
            screenWidth = notification.getOwner().getWidth();
            screenHeight = notification.getOwner().getHeight();
            window = notification.getOwner();
        }
        show(window, notification);
    }

    private void show(Window owner, final JFXNotifications notification) {
        // Stylesheets which are added to the scene of a popup aren't
        // considered for styling. For this reason, we need to find the next
        // window in the hierarchy which isn't a popup.
        Window ownerWindow = owner;
        while (ownerWindow instanceof PopupWindow) {
            ownerWindow = ((PopupWindow) ownerWindow).getOwnerWindow();
        }
        // need to install our CSS
        Scene ownerScene = ownerWindow.getScene();
        if (ownerScene != null) {
            String stylesheetUrl = getClass().getResource("css/NotificationPopup.css").toExternalForm(); //$NON-NLS-1$
            if (!ownerScene.getStylesheets().contains(stylesheetUrl)) {
                // The stylesheet needs to be added at the beginning so that
                // the styling can be adjusted with custom stylesheets.
                ownerScene.getStylesheets().add(0, stylesheetUrl);
            }
        }

        final Popup popup = new Popup();
        popup.setAutoFix(false);

        final Pos position = notification.getPosition();

        final JFXNotificationBar notificationBar = new JFXNotificationBar() {
            @Override
            public String getTitle() {
                return notification.getTitle();
            }

            @Override
            public String getText() {
                return notification.getMessage();
            }

            @Override
            public NotificationType getNotificationType() {
                return notification.getType();
            }

            @Override
            public Node getGraphic() {
                return notification.getGraphic();
            }

            @Override
            public boolean isShowing() {
                return isShowing;
            }

            @Override
            protected double computeMinWidth(double height) {
                String text = getText();
                Node graphic = getGraphic();
                if ((text == null || text.isEmpty()) && (graphic != null)) {
                    return graphic.minWidth(height);
                }
                return 400;
            }

            @Override
            protected double computeMinHeight(double width) {
                String text = getText();
                Node graphic = getGraphic();
                if ((text == null || text.isEmpty()) && (graphic != null)) {
                    return graphic.minHeight(width);
                }
                return 100;
            }

            @Override
            public boolean isShowFromTop() {
                return NotificationPopupHandler.this.isShowFromTop(notification.getPosition());
            }

            @Override
            public void hide() {
                isShowing = false;

                // animate out the popup by fading it
                createHideTimeline(popup, this, position, Duration.ZERO).play();
            }

            @Override
            public boolean isCloseButtonVisible() {
                return !notification.isHideCloseButton();
            }

            @Override
            public double getContainerHeight() {
                return startY + screenHeight;
            }

            @Override
            public void relocateInParent(double x, double y) {
                // this allows for us to slide the notification upwards
                switch (position) {
                    case BOTTOM_LEFT:
                    case BOTTOM_CENTER:
                    case BOTTOM_RIGHT:
                        popup.setAnchorY(y - PADDING);
                        break;
                    default:
                        // no-op
                        break;
                }
            }
        };

        notificationBar.getStyleClass().addAll(notification.getStyleClass());

        notificationBar.setOnMouseClicked(event -> {
            if (notification.getOnAction() != null) {
                ActionEvent actionEvent = new ActionEvent(notificationBar, notificationBar);
                notification.getOnAction().handle(actionEvent);

                // animate out the popup
                createHideTimeline(popup, notificationBar, position, Duration.ZERO).play();
            }
        });

        popup.getContent().add(notificationBar);
        popup.show(owner, 0, 0);

        // determine location for the popup
        double anchorX;
        double anchorY;
        final double barWidth = notificationBar.getWidth();
        final double barHeight = notificationBar.getHeight();

        // get anchorX
        switch (position) {
            case TOP_LEFT:
            case CENTER_LEFT:
            case BOTTOM_LEFT:
                anchorX = PADDING + startX;
                break;

            case TOP_CENTER:
            case CENTER:
            case BOTTOM_CENTER:
                anchorX = startX + (screenWidth / 2.0) - barWidth / 2.0 - PADDING / 2.0;
                break;

            default:
            case TOP_RIGHT:
            case CENTER_RIGHT:
            case BOTTOM_RIGHT:
                anchorX = startX + screenWidth - barWidth - PADDING;
                break;
        }

        // get anchorY
        switch (position) {
            case TOP_LEFT:
            case TOP_CENTER:
            case TOP_RIGHT:
                anchorY = PADDING + startY;
                break;

            case CENTER_LEFT:
            case CENTER:
            case CENTER_RIGHT:
                anchorY = startY + (screenHeight / 2.0) - barHeight / 2.0 - PADDING / 2.0;
                break;

            default:
            case BOTTOM_LEFT:
            case BOTTOM_CENTER:
            case BOTTOM_RIGHT:
                anchorY = startY + screenHeight - barHeight - PADDING;
                break;
        }

        popup.setAnchorX(anchorX);
        popup.setAnchorY(anchorY);

        isShowing = true;
        notificationBar.doShow();

        addPopupToMap(position, popup);

        // begin a timeline to get rid of the popup
        Timeline timeline = createHideTimeline(popup, notificationBar, position, notification.getDuration());
        timeline.play();
    }

    private void hide(Popup popup, Pos position) {
        popup.hide();
        removePopupFromMap(position, popup);
    }

    private Timeline createHideTimeline(final Popup popup, JFXNotificationBar bar, final Pos position, Duration startDelay) {
        KeyValue fadeOutBegin = new KeyValue(bar.opacityProperty(), 1.0);
        KeyValue fadeOutEnd = new KeyValue(bar.opacityProperty(), 0.0);

        KeyFrame kfBegin = new KeyFrame(Duration.ZERO, fadeOutBegin);
        KeyFrame kfEnd = new KeyFrame(Duration.millis(500), fadeOutEnd);

        Timeline timeline = new Timeline(kfBegin, kfEnd);
        timeline.setDelay(startDelay);
        timeline.setOnFinished(e -> hide(popup, position));

        return timeline;
    }

    private void addPopupToMap(Pos position, Popup popup) {
        List<Popup> popups;
        if (!popupsMap.containsKey(position)) {
            popups = new LinkedList<>();
            popupsMap.put(position, popups);
        } else {
            popups = popupsMap.get(position);
        }

        doAnimation(position, popup);

        // add the popup to the list so it is kept in memory and can be
        // accessed later on
        popups.add(popup);
    }

    private void removePopupFromMap(Pos position, Popup popup) {
        if (popupsMap.containsKey(position)) {
            List<Popup> popups = popupsMap.get(position);
            popups.remove(popup);
        }
    }

    private void doAnimation(Pos position, Popup changedPopup) {
        List<Popup> popups = popupsMap.get(position);
        if (popups == null) {
            return;
        }

        final double newPopupHeight = changedPopup.getContent().get(0).getBoundsInParent().getHeight();

        parallelTransition.stop();
        parallelTransition.getChildren().clear();

        final boolean isShowFromTop = isShowFromTop(position);

        // animate all other popups in the list upwards so that the new one
        // is in the 'new' area.
        // firstly, we need to determine the target positions for all popups
        double sum = 0;
        double[] targetAnchors = new double[popups.size()];
        for (int i = popups.size() - 1; i >= 0; i--) {
            Popup popup = popups.get(i);

            final double popupHeight = popup.getContent().get(0).getBoundsInParent().getHeight();

            if (isShowFromTop) {
                if (i == popups.size() - 1) {
                    sum = startY + newPopupHeight + PADDING;
                } else {
                    sum += popupHeight;
                }
                targetAnchors[i] = sum;
            } else {
                if (i == popups.size() - 1) {
                    sum = changedPopup.getAnchorY() - popupHeight;
                } else {
                    sum -= popupHeight;
                }

                targetAnchors[i] = sum;
            }
        }

        // then we set up animations for each popup to animate towards the
        // target
        for (int i = popups.size() - 1; i >= 0; i--) {
            final Popup popup = popups.get(i);
            final double anchorYTarget = targetAnchors[i];
            if (anchorYTarget < 0) {
                popup.hide();
            }
            final double oldAnchorY = popup.getAnchorY();
            final double distance = anchorYTarget - oldAnchorY;

            Transition t = new NotificationPopupHandler.CustomTransition(popup, oldAnchorY, distance);
            t.setCycleCount(1);
            parallelTransition.getChildren().add(t);
        }
        parallelTransition.play();
    }

    private boolean isShowFromTop(Pos position) {
        switch (position) {
            case TOP_LEFT:
            case TOP_CENTER:
            case TOP_RIGHT:
                return true;
            default:
                return false;
        }
    }

    private class CustomTransition extends Transition {

        private WeakReference<Popup> popupWeakReference;
        private double oldAnchorY;
        private double distance;

        private CustomTransition(Popup popup, double oldAnchorY, double distance) {
            popupWeakReference = new WeakReference<>(popup);
            this.oldAnchorY = oldAnchorY;
            this.distance = distance;
            setCycleDuration(Duration.millis(350.0));
        }

        @Override
        protected void interpolate(double frac) {
            Popup popup = popupWeakReference.get();
            if (popup != null) {
                double newAnchorY = oldAnchorY + distance * frac;
                popup.setAnchorY(newAnchorY);
            }
        }

    }
}
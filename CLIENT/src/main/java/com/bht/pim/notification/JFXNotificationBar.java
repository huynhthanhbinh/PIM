package com.bht.pim.notification;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import javafx.animation.Animation.Status;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

@SuppressWarnings("all")
public abstract class JFXNotificationBar extends Region {

    private static final double MIN_HEIGHT = 40;
    private static final Duration TRANSITION_DURATION = new Duration(350.0);
    private static final EventType<Event> ON_SHOWING = new EventType<>(Event.ANY, "NOTIFICATION_PANE_ON_SHOWING");
    private static final EventType<Event> ON_SHOWN = new EventType<>(Event.ANY, "NOTIFICATION_PANE_ON_SHOWN");
    private static final EventType<Event> ON_HIDING = new EventType<>(Event.ANY, "NOTIFICATION_PANE_ON_HIDING");
    private static final EventType<Event> ON_HIDDEN = new EventType<>(Event.ANY, "NOTIFICATION_PANE_ON_HIDDEN");

    private Timeline timeline;
    private double transitionStartValue;
    private final ScrollPane pane;
    private final HBox paneGeneral;
    private final VBox paneImage;
    private final HBox paneBody;

    public DoubleProperty transition = new SimpleDoubleProperty() {
        @Override
        protected void invalidated() {
            requestContainerLayout();
        }
    };

    public JFXNotificationBar() {
        getStyleClass().add("notification-bar"); //$NON-NLS-1$

        setVisible(isShowing());

        pane = new ScrollPane();
        pane.setFitToWidth(true);
        pane.setFitToHeight(true);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        pane.getStyleClass().add("pane"); //$NON-NLS-1$

        getChildren().setAll(pane);
        paneGeneral = new HBox();

        // initialise title area, if one is set
        paneImage = new VBox();
        paneImage.setAlignment(Pos.CENTER);
        ImageView imageView = (ImageView) getGraphic();
        imageView.setFitHeight(45.0);
        imageView.setFitWidth(45.0);
        JFXDepthManager.setDepth(imageView, 2);
        paneImage.getChildren().add(imageView);
        paneImage.setPadding(new Insets(0.0, 10.0, 0.0, 10.0));

        paneBody = new HBox();
        HBox.setHgrow(paneBody, Priority.ALWAYS);
        VBox paneText = new VBox();
        paneText.setMinHeight(50.0);
        paneText.setPrefWidth(300.0);
        paneText.setSpacing(10.0);
        HBox.setHgrow(paneText, Priority.ALWAYS);

        String titleStr = getTitle();
        if (titleStr != null && !titleStr.isEmpty()) {
            Label title = new Label(getTitle());
            title.getStyleClass().add("title"); //$NON-NLS-1$
            title.setWrapText(true);
            paneText.getChildren().add(title);
        }

        String mesaggeStr = getText();
        if (mesaggeStr != null && !mesaggeStr.isEmpty()) {
            Label message = new Label(getText());
            message.getStyleClass().add("message"); //$NON-NLS-1$
            message.setWrapText(true);
            paneText.getChildren().add(message);
        }

        VBox paneButtonClose = new VBox();

        JFXButton closeBtn = new JFXButton();
        closeBtn.setTextFill(Color.WHITE);
        closeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                hide();
            }
        });
        closeBtn.requestFocus();
        closeBtn.requestLayout();
        closeBtn.getStyleClass().setAll("close-button"); //$NON-NLS-1$
        StackPane graphic = new StackPane();
        graphic.getStyleClass().setAll("graphic"); //$NON-NLS-1$
        closeBtn.setGraphic(graphic);
        closeBtn.setRipplerFill(Color.WHITE);
        closeBtn.setStyle("-fx-background-radius: 20");
        closeBtn.setMinSize(20, 20);
        closeBtn.setPrefSize(20, 20);

        paneButtonClose.getChildren().add(closeBtn);

        paneBody.getChildren().add(paneText);
        paneBody.getChildren().add(paneButtonClose);

        paneBody.setPadding(new Insets(10.0));

        switch (getNotificationType()) {

            case SUCCESS:
                paneImage.setStyle("-fx-background-color: #78A840;");
                paneBody.setStyle("-fx-background-color: #8BC34A;");
                break;

            case INFO:
                paneImage.setStyle("-fx-background-color: #0392D3;");
                paneBody.setStyle("-fx-background-color: #03A9F4;");
                break;

            case ERROR:
                paneImage.setStyle("-fx-background-color: #D33A2F;");
                paneBody.setStyle("-fx-background-color: #F44336;");
                break;

            case WARNING:
                paneImage.setStyle("-fx-background-color: #FFC107;");
                paneBody.setStyle("-fx-background-color: #DCA706;");
                break;

            default:
                break;
        }

        // put it all together
        updatePane();
    }

    public void requestContainerLayout() {
        layoutChildren();
    }

    public String getTitle() {
        return ""; //$NON-NLS-1$
    }

    public boolean isCloseButtonVisible() {
        return true;
    }

    public abstract String getText();

    public abstract NotificationType getNotificationType();

    public abstract Node getGraphic();

    public abstract void hide();

    public abstract boolean isShowing();

    public abstract boolean isShowFromTop();

    public abstract double getContainerHeight();

    public abstract void relocateInParent(double x, double y);

    private void updatePane() {
        paneGeneral.getChildren().clear();
        paneGeneral.getChildren().add(paneImage);
        paneGeneral.getChildren().add(paneBody);
        pane.setContent(paneGeneral);
    }

    @Override
    protected void layoutChildren() {
        final double width = getWidth();
        final double height = computePrefHeight(-1);

        final double notificationBarHeight = prefHeight(width);
        final double notificationMinHeight = minHeight(width);

        if (isShowFromTop()) {
            // place at top of area
            pane.resize(width, height);
            relocateInParent(0, (transition.get() - 1) * notificationMinHeight);
        } else {
            // place at bottom of area
            pane.resize(width, notificationBarHeight);
            relocateInParent(0, getContainerHeight() - notificationBarHeight);
        }
    }

    @Override
    protected double computeMinHeight(double width) {
        return Math.max(super.computePrefHeight(width), MIN_HEIGHT);
    }

    @Override
    protected double computePrefHeight(double width) {
        return Math.max(pane.prefHeight(width), minHeight(width)) * transition.get();
    }

    public void doShow() {
        transitionStartValue = 0;
        doAnimationTransition();
    }

    public void doHide() {
        transitionStartValue = 1;
        doAnimationTransition();
    }

    private void doAnimationTransition() {
        Duration duration;

        if (timeline != null && (timeline.getStatus() != Status.STOPPED)) {
            duration = timeline.getCurrentTime();

            // fix for #70 - the notification pane freezes up as it has zero
            // duration to expand / contract
            duration = duration == Duration.ZERO ? TRANSITION_DURATION : duration;
            transitionStartValue = transition.get();
            // --- end of fix

            timeline.stop();
        } else {
            duration = TRANSITION_DURATION;
        }

        timeline = new Timeline();
        timeline.setCycleCount(1);

        KeyFrame keyFrame1, keyFrame2;

        if (isShowing()) {
            keyFrame1 = new KeyFrame(
                    Duration.ZERO,
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            // start expand
                            setCache(true);
                            setVisible(true);

                            pane.fireEvent(new Event(ON_SHOWING));
                        }
                    },
                    new KeyValue(transition, transitionStartValue)
            );

            keyFrame2 = new KeyFrame(
                    duration,
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            // end expand
                            pane.setCache(false);
                            pane.fireEvent(new Event(ON_SHOWN));
                        }
                    },
                    new KeyValue(transition, 1, Interpolator.EASE_OUT)

            );
        } else {
            keyFrame1 = new KeyFrame(
                    Duration.ZERO,
                    event -> {
                        // Start collapse
                        pane.setCache(true);
                        pane.fireEvent(new Event(ON_HIDING));
                    },
                    new KeyValue(transition, transitionStartValue)
            );

            keyFrame2 = new KeyFrame(
                    duration,
                    event -> {
                        // end collapse
                        setCache(false);
                        setVisible(false);

                        pane.fireEvent(new Event(ON_HIDDEN));
                    },
                    new KeyValue(transition, 0, Interpolator.EASE_IN)
            );
        }

        timeline.getKeyFrames().setAll(keyFrame1, keyFrame2);
        timeline.play();
    }
}
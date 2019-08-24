package com.bht.pim.handler;

import com.bht.pim.configuration.AppConfiguration;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import org.jacpfx.api.handler.ErrorDialogHandler;
import org.jacpfx.rcp.context.Context;


public class PimErrorHandler implements ErrorDialogHandler<Node> {

    public static final ObjectProperty<Context> CONTEXT_PROPERTY
            = new SimpleObjectProperty<>();

    @Override
    public void handleExceptionInDialog(Throwable throwable) {
        CONTEXT_PROPERTY.get().send(AppConfiguration.PERSPECTIVE_DEFAULT, "999999999999999999999999999999999999999");
    }

    @Override
    public Node createExceptionDialog(Throwable throwable) {
        return null;
    }
}

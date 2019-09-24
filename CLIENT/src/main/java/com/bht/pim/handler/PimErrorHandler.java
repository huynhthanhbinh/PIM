package com.bht.pim.handler;

import org.jacpfx.api.handler.ErrorDialogHandler;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.perspective.DefaultPerspective;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

/**
 *
 * @author bht
 */
public final class PimErrorHandler implements ErrorDialogHandler<Node> {

    public static final ObjectProperty<Context> CONTEXT_PROPERTY = new SimpleObjectProperty<>();

    @Override
    public void handleExceptionInDialog(Throwable throwable) {
        CONTEXT_PROPERTY.get().send(DefaultPerspective.ID, throwable);
    }

    @Override
    public Node createExceptionDialog(Throwable throwable) {
        return null;
    }
}
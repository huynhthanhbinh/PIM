package com.bht.pim.handler;

import org.jacpfx.api.handler.ErrorDialogHandler;
import org.jacpfx.rcp.context.Context;
import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseBean;
import com.bht.pim.perspective.DefaultPerspective;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

/**
 *
 * @author bht
 */
@Component
public final class PimErrorHandler implements BaseBean, ErrorDialogHandler<Node> {

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
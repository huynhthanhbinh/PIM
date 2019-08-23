package com.bht.pim.handler;

import javafx.scene.Node;
import org.jacpfx.api.handler.ErrorDialogHandler;


public class PimErrorHandler implements ErrorDialogHandler<Node> {

    @Override
    public void handleExceptionInDialog(Throwable throwable) {

    }

    @Override
    public Node createExceptionDialog(Throwable throwable) {
        return null;
    }
}

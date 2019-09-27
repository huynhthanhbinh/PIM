package com.bht.pim.handler;

import org.jacpfx.api.handler.ErrorDialogHandler;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.perspective.DefaultPerspective;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

/**
 * Handling all errors/exceptions occurs in runtime
 *
 * @author bht
 */
public final class PimErrorHandler implements ErrorDialogHandler<Node>, Thread.UncaughtExceptionHandler {

    public static final ObjectProperty<Context> CONTEXT_PROPERTY = new SimpleObjectProperty<>();

    public PimErrorHandler() {
        Thread.setDefaultUncaughtExceptionHandler(this);                    // control all uncaught exceptions
    }

    @Override
    public void handleExceptionInDialog(Throwable throwable) {
        CONTEXT_PROPERTY.get().send(DefaultPerspective.ID, throwable);      // show exception in ErrorHandlingFragment
        printErrorDetailToConsole(throwable);                               // print detail to console for debug/dev
    }

    // as exception will be showed in ErrorHandlingFragment, don't need to show dialog anymore
    @Override
    public Node createExceptionDialog(Throwable throwable) {
        return null;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        handleExceptionInDialog(throwable);
    }

    @SuppressWarnings({"squid:S1148", "squid:S106"})
    private void printErrorDetailToConsole(Throwable throwable) {
        System.out.println();
        throwable.printStackTrace();
        System.out.println();
    }
}
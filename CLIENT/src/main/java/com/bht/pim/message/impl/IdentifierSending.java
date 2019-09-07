package com.bht.pim.message.impl;

import com.bht.pim.component.MainPane;
import com.bht.pim.message.PimMessage;

import javafx.scene.Node;
import lombok.AllArgsConstructor;

/**
 * @author bht
 */
@AllArgsConstructor
public final class IdentifierSending implements PimMessage {

    private Class fragmentSent;
    private Class fragmentTarget;
    private long id;

    @Override
    public Class getSender() {
        return fragmentSent;
    }

    @Override
    public Node postHandle(MainPane mainPane) {
        MainPane.sendIdentifier(id, mainPane, fragmentSent, fragmentTarget);
        return null;
    }
}

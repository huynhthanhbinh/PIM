package com.bht.pim.message.impl;

import com.bht.pim.component.MainPane;
import com.bht.pim.message.PimMessage;
import javafx.scene.Node;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IdentifierSending implements PimMessage {

    private Class fragmentSent;
    private Class fragmentTarget;
    private long id;

    @Override
    public Class getFragmentSent() {
        return fragmentSent;
    }

    @Override
    public Node postHandle(Node node, MainPane mainPane) {
        MainPane.sendIdentifier(id, mainPane, fragmentSent, fragmentTarget);
        return null;
    }
}

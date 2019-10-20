package com.bht.pim.message.impl;

import com.bht.pim.base.BaseFragment;
import com.bht.pim.component.MainPane;
import com.bht.pim.message.PimMessage;

import javafx.scene.Node;

/**
 *
 * @author bht
 */
public final class IdentifierSending extends PimMessage {

    private long id;

    public IdentifierSending(Class<? extends BaseFragment> sender,
                             Class<? extends BaseFragment> target,
                             long id) {
        this.sender = sender;
        this.target = target;
        this.id = id;
    }

    @Override
    public Node postHandle(MainPane mainPane) {
        MainPane.sendIdentifier(id, mainPane, sender, target);
        return null;
    }
}
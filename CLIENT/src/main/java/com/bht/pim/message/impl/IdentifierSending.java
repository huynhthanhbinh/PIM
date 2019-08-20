package com.bht.pim.message.impl;

import com.bht.pim.component.MainPane;
import com.bht.pim.fragment.parent.IdentifierNeeding;
import com.bht.pim.fragment.parent.SuccessNeeding;
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
    @SuppressWarnings("unchecked")
    public Node postHandle(Node node, MainPane mainPane) {
        if (!((IdentifierNeeding) mainPane.getContext()
                .getManagedFragmentHandler(fragmentTarget)
                .getController())
                .getObjectWithIdentifier(id)) {

            ((SuccessNeeding) mainPane.getContext()
                    .getManagedFragmentHandler(fragmentSent)
                    .getController())
                    .setSuccess(false);
        }

        return null;
    }
}

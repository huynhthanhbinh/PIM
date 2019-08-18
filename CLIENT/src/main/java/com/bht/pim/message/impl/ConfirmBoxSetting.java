package com.bht.pim.message.impl;

import com.bht.pim.component.MainPane;
import com.bht.pim.fragment.children.confirm.ConfirmBoxContaining;
import com.bht.pim.message.PimMessage;
import javafx.scene.Node;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfirmBoxSetting implements PimMessage {

    private String fragmentSent;
    private String newLabel;

    @Override
    public String getIdFragmentSent() {
        return fragmentSent;
    }

    @Override
    public Node postHandle(Node node, MainPane mainPane) {
        ((ConfirmBoxContaining) mainPane.getMainFragment())
                .setConfirmLabel(newLabel);
        return null;
    }
}

package com.bht.pim.message.impl;

import com.bht.pim.component.MainPane;
import com.bht.pim.message.PimMessage;
import javafx.scene.Node;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MainLabelUpdating implements PimMessage {

    private String fragmentSent;
    private String newLabel;

    @Override
    public String getFragmentSent() {
        return fragmentSent;
    }

    @Override
    public Node postHandle(Node node, MainPane mainPane) {
        mainPane.getLabelFragment().getController().setLabelText(newLabel);
        return null;
    }
}

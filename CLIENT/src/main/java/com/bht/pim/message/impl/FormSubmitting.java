package com.bht.pim.message.impl;

import com.bht.pim.component.MainPane;
import com.bht.pim.message.PimMessage;
import javafx.scene.Node;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FormSubmitting implements PimMessage {

    private String fragmentSent;

    @Override
    public String getIdFragmentSent() {
        return fragmentSent;
    }

    @Override
    public Node postHandle(Node node, MainPane mainPane) {
        return null;
    }
}

package com.bht.pim.message.impl;

import com.bht.pim.component.MainPane;
import com.bht.pim.message.PimMessage;
import javafx.scene.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class LabelUpdating implements PimMessage {
    private String newLabel;

    @Override
    public Node postHandle(Node node, MainPane mainPane) {
        mainPane.getLabelFragment().getController().setLabelText(newLabel);
        return null;
    }
}

package com.bht.pim.message.impl;

import com.bht.pim.component.MainPane;
import com.bht.pim.message.PimMessage;
import javafx.scene.Node;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LeaderChoosing implements PimMessage {

    private long leaderId;

    @Override
    public Node postHandle(Node node, MainPane mainPane) {
        return null;
    }
}

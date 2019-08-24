package com.bht.pim.message.impl;

import com.bht.pim.component.MainPane;
import com.bht.pim.message.PimMessage;
import javafx.scene.Node;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FragmentSwitching implements PimMessage {

    private Class fragmentSent;
    private Class fragmentTarget;

    @Override
    public Class getSender() {
        return fragmentSent;
    }

    @Override
    public Node postHandle(Node node, MainPane mainPane) {
        MainPane.switchFragment(mainPane, fragmentTarget);
        return null;
    }
}

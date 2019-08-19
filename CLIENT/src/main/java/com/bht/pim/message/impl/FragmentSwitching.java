package com.bht.pim.message.impl;

import com.bht.pim.component.MainPane;
import com.bht.pim.message.PimMessage;
import javafx.scene.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FragmentSwitching implements PimMessage {

    private String idFragmentSent;
    private String idFragmentTarget;

    @Override
    public Node postHandle(Node node, MainPane mainPane) {
        mainPane.switchFragment(mainPane, idFragmentTarget);
        return null;
    }
}

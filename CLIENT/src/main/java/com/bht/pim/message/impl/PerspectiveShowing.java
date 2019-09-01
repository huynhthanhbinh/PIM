package com.bht.pim.message.impl;

import com.bht.pim.component.MainPane;
import com.bht.pim.message.PimMessage;
import javafx.scene.Node;
import lombok.AllArgsConstructor;

/**
 * @author bht
 */
@AllArgsConstructor
public class PerspectiveShowing implements PimMessage {

    private Class sender;

    @Override
    public Class getSender() {
        return sender;
    }

    @Override
    public Node postHandle(MainPane mainPane) {
        MainPane.onShowPerspective(mainPane);
        return null;
    }
}

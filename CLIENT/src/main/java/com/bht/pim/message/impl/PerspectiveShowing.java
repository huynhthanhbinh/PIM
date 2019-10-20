package com.bht.pim.message.impl;

import com.bht.pim.component.MainPane;
import com.bht.pim.message.PimMessage;

import javafx.scene.Node;
import lombok.NoArgsConstructor;

/**
 *
 * @author bht
 */
@NoArgsConstructor
public final class PerspectiveShowing extends PimMessage {

    @Override
    public Node postHandle(MainPane mainPane) {
        MainPane.onShowPerspective(mainPane);
        return null;
    }
}
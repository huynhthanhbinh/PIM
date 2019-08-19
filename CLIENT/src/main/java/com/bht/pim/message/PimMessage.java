package com.bht.pim.message;

import com.bht.pim.component.MainPane;
import javafx.scene.Node;

public interface PimMessage {

    String getIdFragmentSent();

    // post handle Pim messages for MainPane
    Node postHandle(Node node, MainPane mainPane);
}

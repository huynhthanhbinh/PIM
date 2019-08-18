package com.bht.pim.fragment.children.confirm;

import javafx.scene.input.MouseEvent;

public interface Confirmable {

    // handle action when user clicking submit button
    void onSubmit(MouseEvent event);

    // handle action when user clicking cancel button
    void onCancel(MouseEvent event);
}

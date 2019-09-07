package com.bht.pim.message.impl;

import com.bht.pim.base.BaseComponent;
import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.component.MainPane;
import com.bht.pim.message.PimMessage;

import javafx.scene.Node;
import lombok.AllArgsConstructor;

/**
 * @author bht
 */
@AllArgsConstructor
public final class FragmentSwitching implements PimMessage {

    private Class fragmentSent;
    private Class<? extends BaseComponentFragment> fragmentTarget;

    @Override
    public Class getSender() {
        return fragmentSent;
    }

    @Override
    public Node postHandle(MainPane mainPane) {
        BaseComponent.switchComponentFragment(mainPane, fragmentTarget);
        return null;
    }
}

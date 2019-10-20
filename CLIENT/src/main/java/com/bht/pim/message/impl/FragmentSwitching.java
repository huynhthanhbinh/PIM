package com.bht.pim.message.impl;

import com.bht.pim.base.BaseComponent;
import com.bht.pim.base.BaseComponentFragment;
import com.bht.pim.base.BaseFragment;
import com.bht.pim.component.MainPane;
import com.bht.pim.message.PimMessage;

import javafx.scene.Node;

/**
 *
 * @author bht
 */
public final class FragmentSwitching extends PimMessage {

    public FragmentSwitching(Class<? extends BaseFragment> sender,
                             Class<? extends BaseComponentFragment> target) {

        this.sender = sender;
        this.target = target;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Node postHandle(MainPane mainPane) {
        BaseComponent.switchComponentFragment(mainPane, (Class<BaseComponentFragment>) target);
        return null;
    }
}
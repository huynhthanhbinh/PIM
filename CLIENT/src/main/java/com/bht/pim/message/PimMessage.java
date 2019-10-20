package com.bht.pim.message;

import org.jacpfx.api.message.Message;

import com.bht.pim.base.BaseFragment;
import com.bht.pim.component.MainPane;

import javafx.event.Event;
import javafx.scene.Node;

/**
 *
 * @author bht
 */
public abstract class PimMessage {

    protected Class<? extends BaseFragment> sender;
    protected Class<? extends BaseFragment> target;

    protected abstract Node postHandle(MainPane mainPane);

    public static Node handleMessage(Message<Event, Object> message, MainPane mainPane) {

        if (message.isMessageBodyTypeOf(PimMessage.class)) {
            return ((PimMessage) message.getMessageBody()).postHandle(mainPane);
        }

        return mainPane; // otherwise, it won't show UI
    }
}
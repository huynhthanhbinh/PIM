package com.bht.pim.message;

import com.bht.pim.component.MainPane;
import javafx.event.Event;
import javafx.scene.Node;
import org.apache.log4j.Logger;
import org.jacpfx.api.message.Message;

/**
 * @author bht
 */
public interface PimMessage {

    Class getSender();

    // post handle Pim messages for MainPane
    Node postHandle(MainPane mainPane);

    // Message handler for PimPerspective messages between parant fragments
    static Node messageHandler(Message<Event, Object> message, MainPane mainPane) {
        if (message.isMessageBodyTypeOf(PimMessage.class)) {
            Logger logger = Logger.getLogger(PimMessage.class);
            PimMessage messageBody = (PimMessage) message.getMessageBody();

            logger.info("[PIM Message] " + messageBody.getClass().getSimpleName() +
                    " >>> sent from: " + messageBody.getSender().getSimpleName());

            return messageBody.postHandle(mainPane);
        }
        return null;
    }
}

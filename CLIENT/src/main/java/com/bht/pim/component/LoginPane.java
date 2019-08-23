package com.bht.pim.component;


import com.bht.pim.configuration.AppConfiguration;
import javafx.event.Event;
import javafx.scene.Node;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.component.DeclarativeView;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;

@Log4j
@Getter
@Setter
@DeclarativeView(id = AppConfiguration.COMPONENT_BOTTOM, name = "LoginPane",
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        initialTargetLayoutId = AppConfiguration.TARGET_CONTAINER_BOTTOM,
        viewLocation = "/com/bht/pim/component/LoginPane.fxml")
public class LoginPane implements FXComponent {

    @Override
    public Node postHandle(Node node, Message<Event, Object> message) throws Exception {
        return null;
    }

    @Override
    public Node handle(Message<Event, Object> message) throws Exception {
        return null;
    }
}

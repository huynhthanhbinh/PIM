package com.bht.pim.component;


import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.base.BaseComponent;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.supplementary.ErrorHandlingFragment;
import com.bht.pim.fragment.supplementary.LoginFragment;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bht
 */
@Getter
@Setter
@View(id = BottomPane.ID, name = "BottomPane",
        initialTargetLayoutId = BottomPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class BottomPane extends BaseComponent {

    public static final String ID = "idcBottom";
    public static final String CONTAINER = "PBottom";

    private ManagedFragmentHandler<LoginFragment> loginFragment;
    private ManagedFragmentHandler<ErrorHandlingFragment> errorHandlingFragment;

    @Resource
    private Context context;

    @Override
    protected void initComponent() {
        componentContext = context;
    }

    @Override
    protected void initLayout() {
        setAlignment(Pos.CENTER);
        setMinSize(1024, 600);
        setPrefSize(1280, 600);
    }

    @Override
    protected void loadFragments() {
        loginFragment = registerComponentFragment(LoginFragment.class);
        errorHandlingFragment = registerComponentFragment(ErrorHandlingFragment.class);
    }

    @Override
    protected Node handleMessage(Message<Event, Object> message) {
        if (message.getMessageBody() instanceof Throwable) {
            switchComponentFragment(this, ErrorHandlingFragment.class);
            errorHandlingFragment.getController()
                    .setDetail((Throwable) message.getMessageBody());

        } else {
            switchComponentFragment(this, LoginFragment.class);
        }
        return this; // otherwise, it won't show UI
    }
}
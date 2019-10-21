package com.bht.pim.component;


import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.AppConfiguration;
import com.bht.pim.base.BaseComponent;
import com.bht.pim.fragment.supplementary.ErrorHandlingFragment;
import com.bht.pim.fragment.supplementary.LoginFragment;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;

/**
 *
 * @author bht
 */
@View(id = BottomPane.ID, name = BottomPane.ID,
        initialTargetLayoutId = BottomPane.CONTAINER,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES)
public final class BottomPane extends BaseComponent {

    public static final String ID = "bottomComponent";
    public static final String CONTAINER = "PBottom";

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
        registerComponentFragment(LoginFragment.class);
        errorHandlingFragment = registerComponentFragment(ErrorHandlingFragment.class);
    }

    @Override
    protected Node handleMessage(Message<Event, Object> message) {

        if (message.getMessageBody() instanceof Throwable) {
            errorHandlingFragment.getController()
                    .setDetail((Throwable) message.getMessageBody());
            switchComponentFragment(this, ErrorHandlingFragment.class);
        } else {
            switchComponentFragment(this, LoginFragment.class);
        }
        return this; // otherwise, it won't show UI
    }
}
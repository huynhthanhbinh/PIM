package com.bht.pim.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;
import org.thymeleaf.util.Validate;

import com.bht.pim.fragment.menu.TopMenuFragment;
import com.bht.pim.util.LoggingUtil;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import lombok.Getter;

/**
 * this class using Template Method Design-Pattern ^^
 *
 * @author bht
 */
public abstract class BaseComponent extends VBox implements BaseBean, FXComponent {

    private static final Logger LOGGER = Logger.getLogger(BaseComponent.class);
    protected Context componentContext;
    @Getter
    private List<BaseComponentFragment> fragments;
    @Getter
    protected ManagedFragmentHandler<? extends BaseComponentFragment> currentFragment;

    public BaseComponent() {
        fragments = new ArrayList<>();
    }


    private void onStarted() {
        initLayout();
        loadFragments();
    }

    protected abstract void initComponent();

    protected abstract void initLayout();

    protected abstract void loadFragments();

    protected abstract Node handleMessage(Message<Event, Object> message);

    @PostConstruct
    public final void onStartComponent(final FXComponentLayout componentLayout) {
        initComponent();
        onStarted();
    }


    @PreDestroy
    public final void onTearDownComponent(final FXComponentLayout componentLayout) {
        LOGGER.info(LoggingUtil.format("DESTROY", "FXComponent", getClass().getSimpleName()));
    }


    @Override
    public final Node handle(Message<Event, Object> message) {
        return null;
    }


    @Override
    public final Node postHandle(Node node, Message<Event, Object> message) {
        if (message.getMessageBody() instanceof BasePerspective) {
            ((BasePerspective) message.getMessageBody()).addChildComponent(this);
        }
        return handleMessage(message);
    }


    final <F extends BaseFragment> ManagedFragmentHandler<F> registerNewFragment(Class<F> fClass) {
        LOGGER.info(LoggingUtil.format("REGISTER", "FXFragment", fClass.getSimpleName()));
        return componentContext.getManagedFragmentHandler(fClass);
    }


    // for adding new ComponentFragment to parent
    protected final <F extends BaseComponentFragment> ManagedFragmentHandler<F> registerComponentFragment(Class<F> fClass) {

        // Except TopMenuFragment's scope is PROTOTYPE using on both perspectives
        // All other ComponentFragment's scope must be SINGLETON !!!
        if (!fClass.isAssignableFrom(TopMenuFragment.class)) {
            Fragment fragmentAnnotation = fClass.getAnnotation(Fragment.class);
            Validate.isTrue(fragmentAnnotation.scope() == Scope.SINGLETON,
                    "ComponentFragment must be in SINGLETON scope");
        }

        ManagedFragmentHandler<F> fragmentHandler = registerNewFragment(fClass);
        fragments.add(fragmentHandler.getController().initialize(this));
        return fragmentHandler;
    }


    // switch current-(parent)-fragment
    // this method is use only for SINGLETON fragment
    // not for PROTOTYPE fragment !!! eg. TopMenuFragment
    public static <C extends BaseComponent, F extends BaseComponentFragment> void switchComponentFragment(
            C component, Class<F> fragmentClazz) {

        if (component.currentFragment != null && component.currentFragment.getController() != null) {

            BaseComponentFragment current = component.currentFragment.getController();
            if (current.getClass().equals(fragmentClazz)) { // check if current & target is the same
                return;
            }
            current.preLeft();
        }

        ObservableList<Node> nodes = component.getChildren();
        nodes.clear();

        // as BaseComponentFragment's scope is SINGLETON
        ManagedFragmentHandler<F> target = component.componentContext.getManagedFragmentHandler(fragmentClazz);
        target.getController().onSwitch();

        component.currentFragment = target;
        nodes.add(component.currentFragment.getFragmentNode());
    }
}
package com.bht.pim.base;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bht
 */
public abstract class ParentFragment extends VBox { // is-parent-fragment, scope SINGLETON !!!
    // logger using for logging purposes
    protected static final Logger LOGGER = Logger.getLogger(ParentFragment.class);
    // list of children fragment of this parent fragment
    private List<ChildFragment> childFragments;

    // this method will be invoked when switching main fragment
    public final void onSwitchParentFragment() {
        childFragments.forEach(ChildFragment::onSwitchParentFragment);
    }

    // add all Children fragments of the parent fragments
    public final <T extends ChildFragment> void addAllChildren(List<Pair<T, Node>> children) {
        configureLayout();
        determineChildren(children);
        getChildrenFragments(childFragments);
        initializeEachChildFragment();
        configureEachChildFragment();
        bindChildrenFragments();
    }

    private void configureLayout() {
        setPrefWidth(1050.0);
        setPrefHeight(600.0);
        setSpacing(5.0);
    }

    private <T extends ChildFragment> void determineChildren(List<Pair<T, Node>> children) {
        children.stream()
                .map(Pair::getValue)
                .forEach(getChildren()::add);

        childFragments = children.stream()
                .map(Pair::getKey)
                .collect(Collectors.toList());
    }

    private void initializeEachChildFragment() {
        childFragments.forEach(childFragment -> {
            childFragment.setParentFragment(this);
            childFragment.onCreated();
        });
    }

    protected abstract void getChildrenFragments(List<ChildFragment> children);

    protected abstract void configureEachChildFragment();

    protected abstract void bindChildrenFragments();
}
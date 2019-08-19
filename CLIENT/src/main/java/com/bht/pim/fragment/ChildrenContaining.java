package com.bht.pim.fragment;


import javafx.scene.Node;
import javafx.util.Pair;


public interface ChildrenContaining {

    <T> void addAllChildren(Pair<T, Node>[] children);
}

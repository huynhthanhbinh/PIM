package com.bht.pim.fragment.parent;


import javafx.scene.Node;
import javafx.util.Pair;

// is-parent-fragment
// using scope SINGLETON !!!
public interface ChildrenContaining {

    <T> void addAllChildren(Pair<T, Node>[] children);
}

package com.bht.pim.base;

import com.bht.pim.annotation.InheritedComponent;

import javafx.scene.layout.VBox;

/**
 *
 * @author bht
 */
@InheritedComponent
public abstract class BaseDialog extends VBox implements BaseBean {

    public abstract VBox getInstance();
}

package com.bht.pim.fragment.children.confirm;

import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;

public interface ConfirmBoxContaining {

    // Get confirm-label
    String getConfirmLabel();

    // Set label for confirm-button
    void setConfirmLabel(String confirmLabel);

    // Get confirm-box
    ManagedFragmentHandler getConfirmBox();

    // Get confirm-form
    ManagedFragmentHandler getConfirmForm();
}

package com.bht.pim.fragment.children.confirm;

import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;

public interface ConfirmBoxContaining {

    // Set label for confirm-button
    void setConfirmLabel(String confirmLabel);

    // Get confirm-box
    Confirm getConfirmBox();

    // Get confirm-form
    ManagedFragmentHandler getConfirmForm();
}

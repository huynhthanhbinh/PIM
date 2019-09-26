package com.bht.pim;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.workbench.Workbench;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.base.BaseWorkbench;
import com.bht.pim.handler.PimErrorHandler;
import com.bht.pim.perspective.DefaultPerspective;
import com.bht.pim.perspective.PimPerspective;
import com.bht.pim.preloader.PreLoader;

/**
 *
 * @author bht
 */
@Workbench(id = AppWorkbench.ID, name = AppWorkbench.ID,
        perspectives = {
                PimPerspective.ID,
                DefaultPerspective.ID})
public final class AppWorkbench extends BaseWorkbench {

    public static final String ID = "pimWorkbench";

    @Resource
    private Context context;

    @Override
    public void initialize() {
        super.initialize();
        PreLoader.closePreloader();
    }

    @Override
    protected void shareContext() {
        // using for handling error / exception
        // to send message to perspective default
        PimErrorHandler.CONTEXT_PROPERTY.set(context);
    }
}
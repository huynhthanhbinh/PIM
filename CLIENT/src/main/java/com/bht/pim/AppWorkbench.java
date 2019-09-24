package com.bht.pim;

import org.jacpfx.api.annotations.workbench.Workbench;

import com.bht.pim.base.BaseWorkbench;
import com.bht.pim.perspective.DefaultPerspective;
import com.bht.pim.perspective.PimPerspective;

import lombok.extern.log4j.Log4j;

/**
 *
 * @author bht
 */
@Log4j
@Workbench(id = AppWorkbench.ID, name = AppWorkbench.ID,
        perspectives = {
                PimPerspective.ID,
                DefaultPerspective.ID})
public final class AppWorkbench extends BaseWorkbench {

    public static final String ID = "pimWorkbench";
}
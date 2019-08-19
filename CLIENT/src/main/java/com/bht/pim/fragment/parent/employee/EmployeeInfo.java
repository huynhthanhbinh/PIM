package com.bht.pim.fragment.parent.employee;

import com.bht.pim.configuration.AppConfiguration;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.stereotype.Controller;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_EMPLOYEE_INFO,
<<<<<<< HEAD:CLIENT/src/main/java/com/bht/pim/fragment/parent/employee/EmployeeInfo.java
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES_LOCATION,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/parent/employee/EmployeeInfo.fxml")
=======
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/employee/EmployeeInfo.fxml")
>>>>>>> parent of f33f974... [ProjectCreate] Saving Project:CLIENT/src/main/java/com/bht/pim/fragment/employee/EmployeeInfo.java
public class EmployeeInfo {
}

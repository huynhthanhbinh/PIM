package com.bht.pim.fragment.children.project;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.dto.ProjectDto;
import com.bht.pim.fragment.children.ParentOwning;
import com.bht.pim.service.GroupService;
import com.bht.pim.service.ProjectService;
import javafx.fxml.Initializable;
import lombok.extern.log4j.Log4j;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
@Controller
@Fragment(id = AppConfiguration.FRAGMENT_PROJECT_DETAIL,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/project/ProjectDetail.fxml")
public class ProjectDetail implements Initializable, ParentOwning {

    private ProjectDto projectDto;

    @Autowired
    private ProjectService projectService;
    @Autowired
    private GroupService groupService;
    @Resource
    private Context context;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("[Project Detail] Initialization");
    }

    @Override
    public void onSwitchParentFragment() {
        log.info(projectDto.getId());
        log.info(projectDto.getNumber());
        log.info(projectDto.getName());
    }

    public boolean getProjectById(long projectId) {
        projectDto = projectService.getProjectById(projectId);
        return projectDto.getId() > 0;
    }
}

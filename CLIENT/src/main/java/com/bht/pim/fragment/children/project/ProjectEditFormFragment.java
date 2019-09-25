package com.bht.pim.fragment.children.project;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;

import com.bht.pim.AppConfiguration;
import com.bht.pim.component.MainPane;
import com.bht.pim.dto.EmployeeDto;
import com.bht.pim.dto.GroupDto;
import com.bht.pim.dto.ProjectDto;
import com.bht.pim.fragment.parent.project.ProjectListFragment;
import com.bht.pim.message.impl.FragmentSwitching;
import com.bht.pim.notification.NotificationStyle;
import com.bht.pim.util.LanguageUtil;
import com.bht.pim.util.NotificationUtil;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author bht
 */
@Fragment(id = ProjectEditFormFragment.ID,
        resourceBundleLocation = AppConfiguration.LANGUAGE_BUNDLES,
        scope = Scope.PROTOTYPE,
        viewLocation = "/com/bht/pim/fragment/children/project/ProjectEditFormFragment.fxml")
public final class ProjectEditFormFragment extends ProjectEditFormFragmentBase {

    static final String ID = "projectEditFormFragment";

    @Resource
    private Context context;

    @Override
    public void onCancel(MouseEvent event) {
        LOGGER.info("[bCancel] onClick");

        FragmentSwitching switching = new FragmentSwitching(
                ProjectEditFormFragment.class, ProjectListFragment.class);

        context.send(MainPane.ID, switching);
    }

    @Override
    public void onSubmit(MouseEvent event) {
        boolean emptyNumber = number.getText().isEmpty();
        boolean emptyName = name.getText().isEmpty();
        boolean emptyCustomer = name.getText().isEmpty();
        boolean emptyStart = start.getEditor().getText().isEmpty();
        boolean emptyEnd = end.getEditor().getText().isEmpty();
        boolean valid = chose && !(emptyNumber || emptyName || emptyCustomer || emptyStart);

        LOGGER.info("[bSubmit] onClick");
        hideAllValidation();

        if (valid && validProjectNumber() && validEndDate(emptyEnd)) {
            onSaveOrUpdateProject();
        } else {
            warnOnInvalid(emptyNumber, emptyName, emptyCustomer, emptyStart);
        }
    }

    private void onSaveOrUpdateProject() {
        LOGGER.info("<<< PimPerspective - On saving project >>>");

        try {
            EmployeeDto groupLeader = EmployeeDto.newBuilder().setId(leader.getId()).build();
            GroupDto groupDto = GroupDto.newBuilder().setLeader(groupLeader).build();

            if (!saveNewGroupIfRequired(groupDto)) {
                return;
            }

            NotificationUtil.showNotification(NotificationStyle.INFO, Pos.CENTER,
                    "[INFO] On saving project !");

            saveOrUpdateProject(projectDto.toBuilder()
                    .setNumber(Long.parseLong(number.getText()))
                    .setName(name.getText())
                    .setCustomer(customer.getText())
                    .setGroup(groupDto)
                    .setMembers(members)
                    .setStatus(new SimpleStringProperty(getProjectStatus()))
                    .setStart(start.getValue())
                    .setEnd(end.getValue())
                    .build());

        } catch (Exception exception) {
            LOGGER.info(exception);
        }
    }

    private boolean saveNewGroupIfRequired(GroupDto groupDto) {
        if (getGroupOption().equals(LanguageUtil.getCurrentLabelOfKey("label.project.form.newgroup"))) {
            if (saveNewGroup(groupDto)) {
                NotificationUtil.showNotification(NotificationStyle.SUCCESS, Pos.CENTER,
                        "[INFO] Successfully create new group !");
            } else {
                NotificationUtil.showNotification(NotificationStyle.WARNING, Pos.CENTER,
                        "[INFO] Failed to create new group !");
                return false;
            }
        }
        return true;
    }

    private boolean saveNewGroup(GroupDto groupDto) {
        LOGGER.info("<<< PimPerspective - On creating new group >>>");
        return groupService.addNewGroup(groupDto);
    }

    private void saveOrUpdateProject(ProjectDto projectDto) {
        if (isUpdate) {
            saveOrUpdateProject(projectService.updateProject(projectDto), "update");
        } else {
            saveOrUpdateProject(projectService.addNewProject(projectDto), "create");
        }
    }

    private void saveOrUpdateProject(boolean success, String action) {
        if (success) {
            NotificationUtil.showNotification(NotificationStyle.SUCCESS, Pos.CENTER,
                    "[INFO] Successfully " + action + " project !");

            FragmentSwitching switching = new FragmentSwitching(
                    ProjectEditFormFragment.class, ProjectListFragment.class);

            context.send(MainPane.ID, switching);

        } else {
            loadProjectEditForm();
            NotificationUtil.showNotification(NotificationStyle.WARNING, Pos.CENTER,
                    "[INFO] Failed to " + action + " project !");
        }
    }
}
package com.bht.pim.message.impl;

import com.bht.pim.component.MainPane;
import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.fragment.employee.EmployeeInfo;
import com.bht.pim.fragment.employee.EmployeeList;
import com.bht.pim.fragment.group.GroupInfo;
import com.bht.pim.fragment.group.GroupList;
import com.bht.pim.fragment.project.ProjectCreate;
import com.bht.pim.fragment.project.ProjectInfo;
import com.bht.pim.fragment.project.ProjectList;
import com.bht.pim.fragment.project.ProjectUpdate;
import com.bht.pim.message.PimMessage;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FragmentSwitching implements PimMessage {
    private String newFragment;

    @Override
    @SuppressWarnings("unchecked")
    public Node postHandle(Node node, MainPane mainPane) {
        Class clazz = toFragmentClass(newFragment);

        ObservableList<Node> nodes =
                mainPane.getMainPane().getChildren();

        nodes.remove(nodes.get(1));

        mainPane.setMainFragment(mainPane.getContext()
                .getManagedFragmentHandler(clazz));

        nodes.add(mainPane.getMainFragment().getFragmentNode());

        return null;
    }

    private Class toFragmentClass(String fragment) {

        switch (fragment) {

            case AppConfiguration.FRAGMENT_PROJECT_LIST:
                return ProjectList.class;

            case AppConfiguration.FRAGMENT_PROJECT_INFO:
                return ProjectInfo.class;

            case AppConfiguration.FRAGMENT_PROJECT_CREATE:
                return ProjectCreate.class;

            case AppConfiguration.FRAGMENT_PROJECT_UPDATE:
                return ProjectUpdate.class;

            case AppConfiguration.FRAGMENT_GROUP_LIST:
                return GroupList.class;

            case AppConfiguration.FRAGMENT_GROUP_INFO:
                return GroupInfo.class;

            case AppConfiguration.FRAGMENT_EMPLOYEE_LIST:
                return EmployeeList.class;

            case AppConfiguration.FRAGMENT_EMPLOYEE_INFO:
                return EmployeeInfo.class;

            default:
                return Object.class;
        }
    }
}

package com.bht.pim.fragment.parent;

import com.bht.pim.fragment.children.confirm.Confirm;
import com.bht.pim.fragment.children.employee.EmployeeDetail;
import com.bht.pim.fragment.children.employee.EmployeeListTable;
import com.bht.pim.fragment.children.group.GroupDetail;
import com.bht.pim.fragment.children.group.GroupListTable;
import com.bht.pim.fragment.children.label.MainLabel;
import com.bht.pim.fragment.children.pagination.PimPagination;
import com.bht.pim.fragment.children.project.ProjectDetail;
import com.bht.pim.fragment.children.project.ProjectEditableForm;
import com.bht.pim.fragment.children.project.ProjectListTable;
import com.bht.pim.fragment.children.project.ProjectListUtil;
import lombok.Builder;
import lombok.Getter;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;

@Getter
@Builder(builderClassName = "Builder", builderMethodName = "newBuilder")
public class ChildrenContainer {

    private ManagedFragmentHandler<Confirm> confirmFragment;
    private ManagedFragmentHandler<MainLabel> mainLabelFragment;
    private ManagedFragmentHandler<PimPagination> paginationFragment;


    private ManagedFragmentHandler<EmployeeDetail> employeeDetailFragment;
    private ManagedFragmentHandler<EmployeeListTable> employeeListTableFragment;


    private ManagedFragmentHandler<GroupDetail> groupDetailFragment;
    private ManagedFragmentHandler<GroupListTable> groupListTableFragment;


    private ManagedFragmentHandler<ProjectDetail> projectDetailFragment;
    private ManagedFragmentHandler<ProjectListTable> projectListTableFragment;
    private ManagedFragmentHandler<ProjectEditableForm> projectEditableFormFragment;
    private ManagedFragmentHandler<ProjectListUtil> projectListUtilFragment;
}

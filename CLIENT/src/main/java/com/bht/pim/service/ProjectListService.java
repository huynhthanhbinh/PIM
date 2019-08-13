package com.bht.pim.service;

import com.bht.pim.proto.projects.Project;
import javafx.collections.ObservableList;

import java.util.List;

public interface ProjectListService {

    // Get all project numbers
    List<Long> getProjectNumbers();

    // Get all of projects
    ObservableList<Project> getAllProjects();
}

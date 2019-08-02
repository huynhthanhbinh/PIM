package com.bht.pim.fragment.group;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller
public class GroupInfo {

    private Logger logger = Logger.getLogger(GroupInfo.class);

    // Get 1 group info =======================================

//    GroupServiceGrpc.GroupServiceBlockingStub stub1 =
//            GroupServiceGrpc.newBlockingStub(channel);
//
//    GroupId groupId = GroupId.newBuilder()
//            .setId(1)
//            .build();
//
//    GroupInfo group = stub1.getGroupById(groupId);
//
//        logger.info(group);
//
//        group.getEnrolledProjectsList().forEach(project -> logger.info(project.getName()));
}

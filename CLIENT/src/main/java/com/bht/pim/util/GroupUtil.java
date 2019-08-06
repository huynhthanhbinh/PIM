package com.bht.pim.util;

import com.bht.pim.proto.groups.Group;
import com.bht.pim.proto.groups.GroupList;
import com.bht.pim.proto.groups.GroupListServiceGrpc;
import com.bht.pim.proto.groups.GroupServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.Channel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;


public class GroupUtil {

    private static Logger logger = Logger.getLogger(GroupUtil.class);

    private GroupUtil() {
    }

    // Add a new group ============================================
    public static boolean addNewGroup(Channel channel, Group newGroup) {
        try {
            GroupServiceGrpc.GroupServiceBlockingStub stub =
                    GroupServiceGrpc.newBlockingStub(channel);

            return stub.addNewGroup(newGroup).getValue();

        } catch (Exception exception) {

            logger.info(exception);
            return false;
        }
    }

    // Get all groups
    public static ObservableList<Group> getAllGroups(Channel channel) {

        GroupListServiceGrpc.GroupListServiceBlockingStub stub =
                GroupListServiceGrpc.newBlockingStub(channel);

        GroupList groupList = stub.getGroupList(Empty.getDefaultInstance());

        return FXCollections.observableList(groupList.getGroupsList());
    }
}

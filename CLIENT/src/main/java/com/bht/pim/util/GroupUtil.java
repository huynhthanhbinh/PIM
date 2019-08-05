package com.bht.pim.util;

import com.bht.pim.proto.groups.*;
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

            return stub.addNewGroup(newGroup).getIsSuccess();

        } catch (Exception exception) {

            logger.info(exception);
            return false;
        }
    }

    // Get all groups
    public static ObservableList<Group> getAllGroups(Channel channel) {

        GroupListServiceGrpc.GroupListServiceBlockingStub stub =
                GroupListServiceGrpc.newBlockingStub(channel);

        NoParam noParam = NoParam.newBuilder().build();

        GroupList groupList = stub.getGroupList(noParam);

        return FXCollections.observableList(groupList.getGroupsList());
    }
}

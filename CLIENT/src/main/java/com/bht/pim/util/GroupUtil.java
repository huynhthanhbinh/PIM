package com.bht.pim.util;

import com.bht.pim.proto.group.Group;
import com.bht.pim.proto.group.GroupServiceGrpc;
import io.grpc.Channel;
import org.apache.log4j.Logger;


public class GroupUtil {

    private static Logger logger = Logger.getLogger(GroupUtil.class);

    private GroupUtil() {
    }

    // Add a new group ============================================
    public static boolean addNewGroup(Channel channel, long leaderId) {
        try {
            GroupServiceGrpc.GroupServiceBlockingStub stub =
                    GroupServiceGrpc.newBlockingStub(channel);

            Group newGroup = Group.newBuilder()
                    .setGroupLeaderId(leaderId)
                    .build();

            return stub.addNewGroup(newGroup).getIsSuccess();

        } catch (Exception exception) {

            logger.info(exception);
            return false;
        }
    }
}

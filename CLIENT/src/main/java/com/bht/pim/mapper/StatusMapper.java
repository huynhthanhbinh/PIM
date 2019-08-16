package com.bht.pim.mapper;

import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.Map;

@Mapper
public abstract class StatusMapper {

    private Map<String, String> availableStatus() {
        Map<String, String> availableStatuses = new HashMap<>();
        availableStatuses.put("NEW", "New");
        availableStatuses.put("PLA", "Planned");
        availableStatuses.put("INP", "In progress");
        availableStatuses.put("FIN", "Finished");
        return availableStatuses;
    }

    private Map<String, String> availableStatus = availableStatus();

    public String toGUIstatus(String sqlStatus) {
        return availableStatus.get(sqlStatus);
    }

    public String toSQLstatus(String guiStatus) {
        for (Map.Entry<String, String> entry : availableStatus.entrySet()) {
            if (entry.getValue().equals(guiStatus)) {
                return entry.getKey();
            }
        }
        return "";
    }
}

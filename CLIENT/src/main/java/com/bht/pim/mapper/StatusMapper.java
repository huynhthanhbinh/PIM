package com.bht.pim.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

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

    @Named("toGuiStatus")
    public String toGuiStatus(String sqlStatus) {
        return availableStatus.get(sqlStatus);
    }

    @Named("toSqlStatus")
    public String toSqlStatus(String guiStatus) {
        for (Map.Entry<String, String> entry : availableStatus.entrySet()) {
            if (entry.getValue().equals(guiStatus)) {
                return entry.getKey();
            }
        }
        return "";
    }
}

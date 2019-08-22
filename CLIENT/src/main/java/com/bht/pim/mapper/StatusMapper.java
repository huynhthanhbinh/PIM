package com.bht.pim.mapper;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.util.LanguageUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.HashMap;
import java.util.Map;

@Mapper
public abstract class StatusMapper {

    private Map<String, StringProperty> availableStatus = availableStatus();

    private StringProperty getLabelProperty(String labelKey) {
        StringProperty labelProperty = new SimpleStringProperty();
        LanguageUtil.initLabel(labelProperty, labelKey);
        return labelProperty;
    }

    private Map<String, StringProperty> availableStatus() {
        Map<String, StringProperty> availableStatuses = new HashMap<>();
        availableStatuses.put("NEW", getLabelProperty(AppConfiguration.LABEL_STATUS_NEW));
        availableStatuses.put("PLA", getLabelProperty(AppConfiguration.LABEL_STATUS_PLANNED));
        availableStatuses.put("INP", getLabelProperty(AppConfiguration.LABEL_STATUS_IN_PROGRESS));
        availableStatuses.put("FIN", getLabelProperty(AppConfiguration.LABEL_STATUS_FINISHED));
        return availableStatuses;
    }

    @Named("toGuiStatus")
    public StringProperty toGuiStatus(String sqlStatus) {
        return availableStatus.get(sqlStatus);
    }

    @Named("toSqlStatus")
    public String toSqlStatus(StringProperty guiStatus) {
        for (Map.Entry<String, StringProperty> entry : availableStatus.entrySet()) {
            if (entry.getValue().get().equals(guiStatus.get())) {
                return entry.getKey();
            }
        }
        return "";
    }
}

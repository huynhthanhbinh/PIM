package com.bht.pim.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.bht.pim.annotation.InheritedComponent;
import com.bht.pim.base.BaseBean;
import com.bht.pim.util.LanguageUtil;

import javafx.beans.property.StringProperty;
import lombok.Getter;

/**
 *
 * @author bht
 */
@Mapper
@InheritedComponent
public abstract class StatusMapper implements BaseBean {

    @Getter
    private Map<String, StringProperty> availableStatus;

    @Override
    public void initialize() {
        BaseBean.super.initialize();
        availableStatus = new HashMap<>();
        availableStatus.put("TOT", LanguageUtil.getTextPropertyOfKey("label.project.status.total"));
        availableStatus.put("NEW", LanguageUtil.getTextPropertyOfKey("label.project.status.new"));
        availableStatus.put("PLA", LanguageUtil.getTextPropertyOfKey("label.project.status.planned"));
        availableStatus.put("INP", LanguageUtil.getTextPropertyOfKey("label.project.status.inprogress"));
        availableStatus.put("FIN", LanguageUtil.getTextPropertyOfKey("label.project.status.finished"));
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
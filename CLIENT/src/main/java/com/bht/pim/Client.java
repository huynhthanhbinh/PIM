package com.bht.pim;

import org.jacpfx.api.handler.ErrorDialogHandler;
import org.jacpfx.rcp.workbench.FXWorkbench;
import org.jacpfx.spring.launcher.AFXSpringJavaConfigLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bht.pim.base.BaseBean;
import com.bht.pim.configuration.SpringConfiguration;
import com.bht.pim.handler.PimErrorHandler;
import com.bht.pim.util.ImageUtil;
import com.bht.pim.util.LanguageUtil;
import com.bht.pim.workbench.PimWorkbench;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;

/**
 * @author bht
 */
@Log4j
@SpringBootApplication
public class Client extends AFXSpringJavaConfigLauncher implements BaseBean {

    @Autowired
    private PimErrorHandler pimErrorHandler;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    protected Class<?>[] getConfigClasses() {
        return new Class[]{SpringConfiguration.class};
    }

    @Override
    protected Class<? extends FXWorkbench> getWorkbenchClass() {
        return PimWorkbench.class;
    }

    @Override
    protected String[] getBasePackages() {
        return new String[]{SpringConfiguration.BASE_PACKAGE};
    }

    @Override
    protected void postInit(Stage stage) {
        LanguageUtil.initLabel(stage.titleProperty(), SpringConfiguration.LABEL_PIM_MAIN);
        stage.getIcons().add(ImageUtil.getImage("icon"));
        stage.sizeToScene();
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
    }

    @Override
    protected ErrorDialogHandler<Node> getErrorHandler() {
        return pimErrorHandler;
    }
}
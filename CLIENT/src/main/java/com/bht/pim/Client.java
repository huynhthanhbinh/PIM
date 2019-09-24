package com.bht.pim;

import org.jacpfx.api.handler.ErrorDialogHandler;
import org.jacpfx.rcp.workbench.FXWorkbench;
import org.jacpfx.spring.launcher.AFXSpringJavaConfigLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.DependsOn;

import com.bht.pim.base.BaseBean;
import com.bht.pim.handler.PimErrorHandler;
import com.bht.pim.util.ImageUtil;
import com.bht.pim.util.LanguageUtil;
import com.bht.pim.workbench.PimWorkbench;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author bht
 */
@SpringBootApplication
@DependsOn("languageProperty")
public class Client extends AFXSpringJavaConfigLauncher implements BaseBean {

    @Autowired
    private PimErrorHandler pimErrorHandler;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    protected Class<?>[] getConfigClasses() {
        return new Class[]{AppConfiguration.class};
    }

    @Override
    protected Class<? extends FXWorkbench> getWorkbenchClass() {
        return PimWorkbench.class;
    }

    @Override
    protected String[] getBasePackages() {
        return new String[]{AppConfiguration.BASE_PACKAGE};
    }

    @Override
    protected void postInit(Stage stage) {
        LanguageUtil.initLabel(stage.titleProperty(), AppConfiguration.LABEL_PIM_MAIN);
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
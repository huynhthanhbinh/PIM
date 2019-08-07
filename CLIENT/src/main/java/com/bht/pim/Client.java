package com.bht.pim;

import com.bht.pim.configuration.AppConfiguration;
import com.bht.pim.workbench.PimWorkbench;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import org.jacpfx.rcp.workbench.FXWorkbench;
import org.jacpfx.spring.launcher.AFXSpringJavaConfigLauncher;

import java.util.Objects;

@Log4j
public class Client extends AFXSpringJavaConfigLauncher {

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
        return new String[]{"com.bht.pim"};
    }

    @Override
    protected void postInit(Stage stage) {
        ClassLoader classLoader = Client.class.getClassLoader();

        stage.setTitle("Project Information Management");
        stage.getIcons().add(
                new Image(Objects.requireNonNull(classLoader
                        .getResourceAsStream("pictures/icon.png"))));
    }
}
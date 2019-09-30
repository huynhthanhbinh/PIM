package com.bht.pim.dialog.content;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import com.bht.pim.base.BaseDialogContent;
import com.bht.pim.fragment.children.confirm.Confirmable;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import lombok.Setter;

/**
 *
 * @author bht
 */
@Controller
public class ExitDialogContent extends BaseDialogContent implements Confirmable {

    @Setter
    private Event exitEvent;
    @Setter
    private EventHandler<WindowEvent> closeAppEventHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

    @Override
    public void onSubmit(MouseEvent event) {
        //
    }

    @Override
    public void onCancel(MouseEvent event) {
        //
    }
}
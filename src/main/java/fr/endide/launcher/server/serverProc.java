package fr.endide.launcher.server;

import javafx.concurrent.Service;
import javafx.scene.control.TextArea;

public class serverProc {
    Service proc;
    TextArea console;

    public serverProc(Service proc, TextArea console) {
        this.proc = proc;
        this.console = console;
    }

}

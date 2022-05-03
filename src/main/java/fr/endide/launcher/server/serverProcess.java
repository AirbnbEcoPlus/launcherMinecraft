package fr.endide.launcher.server;

import javafx.concurrent.Service;

import java.security.Provider;

public class serverProcess {
    String name;
    Service<Void> proc;
    public serverProcess(String name, Service<Void> proc) {
        this.name = name;
        this.proc = proc;
    }
}

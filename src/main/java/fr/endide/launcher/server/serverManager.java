package fr.endide.launcher.server;

import fr.endide.launcher.system.fileManager;
import fr.endide.launcher.system.pageManager;
import fr.endide.launcher.system.saveManager;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class serverManager {
    public static String consoleHelp;
    static Map<String, serverProc> listProcess = new HashMap<String, serverProc>();

    public static void setupServer() throws IOException {
        if(!fileManager.getServerDir().exists()){
            fileManager.getServerDir().mkdir();
        }
        if(!fileManager.getServerInstallDir().exists()){
            fileManager.getServerInstallDir().mkdir();
        }
        URL buildToolsUrl = new URL("https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar");
        File buildTools = new File(fileManager.getServerInstallDir() + File.separator + "BuildTools.jar");
        if(!buildTools.exists()){
            FileUtils.copyURLToFile(buildToolsUrl, buildTools);
        }
    }
    public static void loadServers(){
        listProcess.clear();
        for(int index = 0; index < saveManager.serverList.size() ; index++) {
            int finalIndex = index;
            TextArea console = new TextArea();
            Service<TextArea> launchServerService = new Service<TextArea>() {
                @Override
                protected Task<TextArea> createTask() {
                    return new Task<TextArea>() {
                        @Override
                        protected TextArea call() {
                            String command = "java -jar " + saveManager.serverList.get(finalIndex).path + File.separator + saveManager.serverList.get(finalIndex).api + "-" + saveManager.serverList.get(finalIndex).version + ".jar nogui";
                            System.out.println(command);
                            Process proc = null;
                            try {
                                proc = Runtime.getRuntime().exec(command, null, new File(fileManager.getServerDir() + File.separator + saveManager.serverList.get(finalIndex).name));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                            String line = "";
                            while (true) {
                                try {
                                    if (!((line = reader.readLine()) != null)) break;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                String finalLine = line;
                                Platform.runLater(new Runnable() {
                                    @Override public void run() {
                                        console.appendText(finalLine + "\n");

                                    }
                                });

                            }
                            return null;
                        }
                    };
                }
            };
            serverProc procItems = new serverProc(launchServerService, console);
            listProcess.put(saveManager.serverList.get(index).name, procItems);
        }
    }
    public void createServer(String name, String version, String api, String ram, TextArea consoleArea) throws IOException, InterruptedException {
        File serverPath = new File(fileManager.getServerDir() + File.separator + name);
        Service<Void> createServerService = new Service<Void>(){
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>(){

                    @Override
                    protected Void call()  {
                        try {
                            setupServer();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String command = "java -jar " + fileManager.getServerInstallDir() + File.separator + "BuildTools.jar --rev " + version + " --output-dir " + serverPath;
                        Process proc = null;
                        try {
                            proc = Runtime.getRuntime().exec(command, null, fileManager.getServerInstallDir());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                        BufferedReader errorReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                        String line = "";
                        while (true) {
                            try {
                                if (!((line = reader.readLine()) != null)) break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String finalLine = line;
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    consoleArea.appendText(finalLine + "\n");

                                }
                            });

                        }
                        String errorLine = "";
                        while (true) {
                            try {
                                if (!((errorLine = errorReader.readLine()) != null)) break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setHeaderText(errorLine);
                            errorAlert.show();
                        }
                        return null;
                    }
                };
            }
        };
        createServerService.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
            switch (newValue) {
                case FAILED:
                case CANCELLED:
                case SUCCEEDED:
                    Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                    errorAlert.setHeaderText("Le serveur a été créé avec succès");
                    errorAlert.show();
                    saveManager.saveServer(name, ram, api, version, serverPath.getPath(), true);
                    pageManager.devCreatorStage.close();
                    break;
            }
        });
        createServerService.start();
    }

    public static void loadServerDashboard(String name, TextArea consoleArea, TreeView treeView, Button startButton, Button stopButton, Button killButton, Button sendButton) {
        consoleArea.setDisable(false);
        treeView.setDisable(false);
        startButton.setDisable(false);
        killButton.setDisable(false);
        stopButton.setDisable(false);
        sendButton.setDisable(false);
        for(int index = 0; index < saveManager.serverList.size() ; index++){
            if(saveManager.serverList.get(index).name.equals(name)){

            }
        }
        loadConsole(name, consoleArea);

    }
    public static void loadConsole(String name, TextArea consoleArea) {
        consoleHelp = name;
        ScheduledService<Void> loadConsole = new ScheduledService<Void>(){
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>(){

                    @Override
                    protected Void call()  {
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    String savedName = name;
                                        if (listProcess.get(name) != null) {
                                            if(consoleHelp == savedName) {
                                                consoleArea.setText(listProcess.get(name).console.getText());
                                            }
                                        }

                                }
                            });
                        return null;
                    }
                };
            }
        };
        loadConsole.setDelay(Duration.millis(100));
        loadConsole.setPeriod(Duration.millis(100));
        loadConsole.start();
    }
    public static void launchServer(String name) throws IOException {
        listProcess.get(name).proc.start();
    }
}

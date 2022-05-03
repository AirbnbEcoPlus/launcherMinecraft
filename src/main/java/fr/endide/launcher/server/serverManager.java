package fr.endide.launcher.server;

import fr.endide.launcher.system.fileManager;
import fr.endide.launcher.system.pageManager;
import fr.endide.launcher.system.saveManager;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.control.*;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class serverManager {
    static List<serverProcess> serverProcessList = new ArrayList<>();

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

    public static void syncConfigAndProc(){
        serverProcessList.clear();
        for(int index = 0; index < saveManager.serverList.size() ; index++) {
            serverProcess serverProcessItem = new serverProcess(saveManager.serverList.get(index).name, null);
            serverProcessList.add(serverProcessItem);
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
    public static void createServerLaunchService(String name){
        for(int index = 0; index < serverProcessList.size() ; index++) {
            if (serverProcessList.get(index).name.equals(name)) {
                int finalIndex = index;
                Service<Void> launchServerService = new Service<Void>() {
                    @Override
                    protected Task<Void> createTask() {
                        return new Task<Void>() {

                            @Override
                            protected Void call() {
                                TextArea consoleArea = null;
                                try {
                                    setupServer();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                String command = "java -jar " + fileManager.getServerDir() + File.separator + name + saveManager.serverList.get(finalIndex).api + "-" + saveManager.serverList.get(finalIndex).version + ".jar";
                                System.out.println(command);
                                Process proc = null;
                                try {
                                    proc = Runtime.getRuntime().exec(command, null, new File(fileManager.getServerInstallDir() + File.separator + name));
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
                                        @Override
                                        public void run() {
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
                serverProcess serverProcess = new serverProcess(name, launchServerService);
                serverProcessList.remove(index);
                serverProcessList.add(serverProcess);
            }
        }
    }
    public static void loadServers() {



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
                consoleArea.appendText("Le serveur est eteint");
                System.out.println("OKAY");
            }
        }

    }
    //Commande a finir + Stockage des Reader et Error Mais COMMENT FAIRE ????
    public static void launchServer(String name) throws IOException {
        for(int index = 0; index < serverProcessList.size(); index++){
            createServerLaunchService(name);
            if(serverProcessList.get(index).equals(name)){
                System.out.println("OKDD");
                serverProcessList.get(index).proc.start();
            }
        }

    }
}

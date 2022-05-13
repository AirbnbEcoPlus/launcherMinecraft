package fr.endide.launcher.system;

import com.google.gson.*;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.control.Alert;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class updater {
    static Gson gson = createGsonInstance();
    public static Gson createGsonInstance(){
        return  new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
    }
    public static List<versionsManifest.versions> versionsItems = new ArrayList<>();
    public static versionsManifest.latest latestItems;

    static fileManager fileManager = new fileManager();
    public void getMinecraftVersion() throws IOException {
        URL getMcVer = new URL("https://launchermeta.mojang.com/mc/game/version_manifest.json");
        URLConnection request = getMcVer.openConnection();
        request.connect();

    }
    public void getThemes(String name) throws IOException {
        URL getThemesUrl = new URL("http://api.endide.com:2052/launchermc/getThemes" + File.separator + name);
        URLConnection request = getThemesUrl.openConnection();
        request.connect();
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject();
        FileUtils.copyURLToFile(new URL(rootobj.get("borderPane").getAsString()), new File(fileManager.getThemeDir() + File.separator + name + File.separator + "borderPane.fxml"));
        FileUtils.copyURLToFile(new URL(rootobj.get("borderPaneSetup").getAsString()), new File(fileManager.getThemeDir() + File.separator + name + File.separator + "borderPaneSetup.fxml"));
        FileUtils.copyURLToFile(new URL(rootobj.get("dev").getAsString()), new File(fileManager.getThemeDir() + File.separator + name + File.separator + "dev.fxml"));
        FileUtils.copyURLToFile(new URL(rootobj.get("devCreate").getAsString()), new File(fileManager.getThemeDir() + File.separator + name + File.separator + "devCreate.fxml"));
        FileUtils.copyURLToFile(new URL(rootobj.get("devManager").getAsString()), new File(fileManager.getThemeDir() + File.separator + name + File.separator + "devManager.fxml"));
        FileUtils.copyURLToFile(new URL(rootobj.get("firstSetup").getAsString()), new File(fileManager.getThemeDir() + File.separator + name + File.separator + "firstSetup.fxml"));
        FileUtils.copyURLToFile(new URL(rootobj.get("help").getAsString()), new File(fileManager.getThemeDir() + File.separator + name + File.separator + "help.fxml"));
        FileUtils.copyURLToFile(new URL(rootobj.get("login").getAsString()), new File(fileManager.getThemeDir() + File.separator + name + File.separator + "login.fxml"));
        FileUtils.copyURLToFile(new URL(rootobj.get("mcAccountManager").getAsString()), new File(fileManager.getThemeDir() + File.separator + name + File.separator + "mcAccountManager.fxml"));
        FileUtils.copyURLToFile(new URL(rootobj.get("play").getAsString()), new File(fileManager.getThemeDir() + File.separator + name + File.separator + "play.fxml"));
        FileUtils.copyURLToFile(new URL(rootobj.get("settings").getAsString()), new File(fileManager.getThemeDir() + File.separator + name + File.separator + "settings.fxml"));

    }
    public void internetTest() throws IOException {
        URL getVersions = new URL("https://google.com");
        URLConnection request = getVersions.openConnection();
        request.connect();
    }
    public void getOnlineVersions() throws IOException {
        URL getVersions = new URL("https://launchermeta.mojang.com/mc/game/version_manifest.json");
        URLConnection request = getVersions.openConnection();
        request.connect();
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        versionsManifest versionsManifest = gson.fromJson(rootobj, versionsManifest.class);
        latestItems = versionsManifest.latest;
        for(int index = 0; index < versionsManifest.versions.size(); index++){
            if(versionsManifest.versions.get(index).type.equals("release")) {
                versionsItems.add(versionsManifest.versions.get(index));
            }
        }
    }
    public static void downloadMinecraft(String versionSelected) throws IOException {
        for (int index = 0; index < versionsItems.size(); index++) {
            if (versionsItems.get(index).id.equals(versionSelected)) {
                URL getVersions = new URL(versionsItems.get(index).url);
                URLConnection request = getVersions.openConnection();
                request.connect();
                JsonParser jp = new JsonParser(); //from gson
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonObject rootobj = root.getAsJsonObject();
                version version = gson.fromJson(rootobj, version.class);

                URL getAssets = new URL(version.assetIndex.url);
                URLConnection requestAssets = getAssets.openConnection();
                requestAssets.connect();
                JsonParser jpAssets = new JsonParser();
                JsonElement rootAssets = jpAssets.parse(new InputStreamReader((InputStream) requestAssets.getContent()));
                JsonObject rootObjAssets = rootAssets.getAsJsonObject();
                assets assets = gson.fromJson(rootObjAssets, assets.class);

                Service<Void> downloadMc = new Service<Void>() {
                    @Override
                    protected Task<Void> createTask() {
                        return new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
                                if(!fileManager.getRuntimeDir().exists()) {
                                    fileManager.getRuntimeDir().mkdir();
                                }
                                FileUtils.copyURLToFile((new URL(version.downloads.client.url)), new File(fileManager.getRuntimeDir() + File.separator + version.id + File.separator + "client-"+ version.id +".jar"));

                                if(!fileManager.getLibsDir().exists()) {
                                    fileManager.getLibsDir().mkdir();
                                }
                                for(int index = 0; index < version.libraries.size(); index++){
                                    FileUtils.copyURLToFile((new URL(version.libraries.get(index).downloads.artifact.url)), new File(fileManager.getLibsDir() + File.separator + version.libraries.get(index).name + ".jar"));

                                }
                                if(!fileManager.getNativesDir().exists()) {
                                    fileManager.getNativesDir().mkdir();
                                }
                                if(!fileManager.getAssetsDir().exists()) {
                                    fileManager.getAssetsDir().mkdir();
                                }
                                for(Map.Entry<String, fr.endide.launcher.system.assets.path> objects : assets.objects.entrySet()){
                                    FileUtils.copyURLToFile(new URL("http://resources.download.minecraft.net/" + objects.getValue().hash.substring(0, 2) + File.separator + objects.getValue().hash), new File(fileManager.getAssetsDir() + File.separator + objects.getKey()));
                                }
                                return null;
                            }
                        };
                    }
                };
                downloadMc.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
                    switch (newValue) {
                        case FAILED:
                        case CANCELLED:
                        case SUCCEEDED:
                            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                            errorAlert.setHeaderText("Minecraft a été téléchargé avec succès");
                            errorAlert.show();
                            saveManager.saveVersion(versionSelected, versionSelected, "vanilla", fileManager.getRuntimeDir().getPath() + File.separator + "client-"+ version.id +".jar",  true);
                            break;
                    }
                });
                downloadMc.start();
            }
        }
    }


}



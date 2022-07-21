package fr.endide.bootstraplaunchermc;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class update {
    double progressBar;
    String textUpdater;
    fileManager fileManager = new fileManager();

    public void updateLauncher(){

        try {
            getThemes("default");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void getJava(){

    }
    public void getLauncherMcJar() throws IOException {
        double configVersion = 1;
        URL getThemesUrl = new URL("https://api.endide.com/launchermc/getJar");
        URLConnection request = getThemesUrl.openConnection();
        request.connect();
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject();
        textUpdater = "Téléchargements du launcher";
        FileUtils.copyURLToFile(new URL(rootobj.get("link").getAsString()), new File(String.valueOf(fileManager.getGameDir())));
        progressBar = 0.25;
    }
    public void getThemes(String name) throws IOException {
        textUpdater = "Téléchargements du themes";
        URL getThemesUrl = new URL("https://api.endide.com/launchermc/getThemes" + File.separator + name);
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
        progressBar = 0.25;
    }
    public double getProgressBar(){
        return progressBar;
    }
    public String getTextUpdater(){
        return textUpdater;
    }

}

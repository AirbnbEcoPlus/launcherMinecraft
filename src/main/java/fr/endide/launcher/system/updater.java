package fr.endide.launcher.system;

import com.google.gson.*;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class updater {
    fileManager fileManager = new fileManager();
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
}
class versions {
    public String id;
    public String type;
    public String url;
    public String time;
    public String releaseTime;

    public versions(String id, String type, String url, String time, String releaseTime){
        this.id = id;
        this.type = type;
        this.url = url;
        this.time = time;
        this.releaseTime = releaseTime;
    }
    public String getAllVersion(){
        return id;
    }

}


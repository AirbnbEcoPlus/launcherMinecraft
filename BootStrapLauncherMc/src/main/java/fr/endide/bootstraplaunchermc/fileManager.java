package fr.endide.bootstraplaunchermc;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

public class fileManager {

    getOs getOS = new getOs();
    private String nameOfLauncher = "endideLauncher";

    public File createGameDir() {
        final String userhome = System.getProperty("user.home");
        if (getOS.isWindows() == true) {
            return new File(userhome + File.separator + "AppData" + File.separator + "Roaming" + File.separator + "." + this.nameOfLauncher);
        }
        return new File(userhome + File.separator + "." + this.nameOfLauncher);
    }

    public File getThemeDir(){
        return new File(createGameDir(), "themes");
    }
    public URL getThemes(String fxml, String name) throws MalformedURLException {
        return Path.of(getThemeDir().toString() + File.separator + name + File.separator + fxml + ".fxml").toUri().toURL();
    }
    public URL getGameDir() throws MalformedURLException {
        return Path.of(createGameDir().toString()).toUri().toURL();
    }
    public File getJavaDir(){
        return new File(createGameDir(), "java");
    }
    public String getThemeName(){
        return "default";
    }
}

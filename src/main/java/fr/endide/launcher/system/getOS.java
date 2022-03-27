package fr.endide.launcher.system;

public class getOS {
    private String OS = System.getProperty("os.name").toLowerCase();
    public boolean isWindows() {

        if (OS == "windows 10"){
            return true;
        }else {
            return false;
        }
    }
}

package fr.endide.bootstraplaunchermc;

public class getOs {
    private String OS = System.getProperty("os.name").toLowerCase();
    public boolean isWindows() {

        if (OS == "windows 10"){
            return true;
        }else {
            return false;
        }
    }
}

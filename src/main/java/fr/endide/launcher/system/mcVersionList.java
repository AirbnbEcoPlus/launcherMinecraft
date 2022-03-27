package fr.endide.launcher.system;

import java.util.List;

public class mcVersionList {
    public String latest;
    public static class mcVersionsItems {
        String id;
        String type;
        String url;
        String time;
        String releaseTime;

        public mcVersionsItems(String id, String type, String url, String time, String releaseTime) {
            this.id = id;
            this.type = type;
            this.url = url;
            this.time = time;
            this.releaseTime = releaseTime;
        }
    }
}

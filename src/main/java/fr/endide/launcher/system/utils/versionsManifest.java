package fr.endide.launcher.system.utils;

import java.util.List;
import java.util.Map;

public class versionsManifest {
    public latest latest;
    public List<versions> versions;
    public versionsManifest(latest latest, List<versions> versions){
        this.latest = latest;
        this.versions = versions;
    }

    public static class latest {
    public String release;
    public String snapshot;
    public latest(String release, String snapshot){
        this.release =release;
        this.snapshot = snapshot;
    }
    }
    public static class versions {

        public String id;
        public String type;
        public String url;
        public String time;
        public String releaseTime;


        public versions(String id, String type, String url, String time, String releaseTime) {
            this.id = id;
            this.type = type;
            this.url = url;
            this.time = time;
            this.releaseTime = releaseTime;
        }


    }
}



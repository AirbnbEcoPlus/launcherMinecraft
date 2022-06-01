package fr.endide.launcher.system.utils;

import java.util.Map;

public class assets {
    public Map<String, path> objects;

    public assets(Map<String, path> objects) {
        this.objects = objects;
    }

    public static class path {
        public String hash;
        String size;

        public path(String hash, String size) {
            this.hash = hash;
            this.size = size;
        }
    }
}

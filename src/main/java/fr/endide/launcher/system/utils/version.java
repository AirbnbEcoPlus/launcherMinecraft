package fr.endide.launcher.system.utils;

import java.util.List;
import java.util.Map;

public class version {
    public assetIndex assetIndex;
    public downloads downloads;
    public String id;
    public List<libraries> libraries;


    public static class assetIndex {
        String id;
        String sha1;
        String size;
        String totalSize;
        public String url;

        public assetIndex(String id, String sha1, String size, String totalSize, String url) {
            this.id = id;
            this.sha1 = sha1;
            this.size = size;
            this.totalSize = totalSize;
            this.url = url;
        }
    }

    public static class downloads {
        public client client;

        public static class client {
            String sh1;
            String size;
            public String url;

            public client(String sh1, String size, String url) {
                this.sh1 = sh1;
                this.size = size;
                this.url = url;
            }
        }
    }

    public static class libraries {
        public downloads downloads;
        public String name;
        public natives natives;

        public libraries(downloads downloads, String name, natives natives) {
            this.downloads = downloads;
            this.name = name;
            this.natives = natives;
        }

        public static class natives {
            public String linux;
            public String osx;
            public String windows;


            public natives(String linux, String windows, String osx) {
                this.linux = linux;
                this.osx = osx;
                this.windows = windows;
            }
        }

        public static class downloads {
            public artifact artifact;
            public Map<String, artifact> classifiers;

            public downloads(artifact artifact, Map<String, artifact> classifiers) {
                this.artifact = artifact;
                this.classifiers = classifiers;
            }

            public static class artifact {
                public String path;
                public String sha1;
                public String size;
                public String url;

                public artifact(String path, String sha1, String size, String url) {
                    this.path = path;
                    this.sha1 = sha1;
                    this.size = size;
                    this.url = url;
                }
            }

        }

    }
}

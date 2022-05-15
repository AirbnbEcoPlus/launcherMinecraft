package fr.endide.launcher.system;

import java.util.List;
import java.util.Map;

public class versionsManifest {
    latest latest;
    List<versions> versions;
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
class version {
    assetIndex assetIndex;
    String assets;
    String complianceLevel;
    downloads downloads;
    String id;
    List<libraries> libraries;


    public static class assetIndex {
        String id;
        String sha1;
        String size;
        String totalSize;
        String url;

        public assetIndex(String id, String sha1, String size, String totalSize, String url) {
            this.id = id;
            this.sha1 = sha1;
            this.size = size;
            this.totalSize = totalSize;
        }
    }

    public static class downloads {
        client client;

        public static class client {
            String sh1;
            String size;
            String url;

            public client(String sh1, String size, String url) {
                this.sh1 = sh1;
                this.size = size;
                this.url = url;
            }
        }
    }

    public static class libraries {
        downloads downloads;
        String name;

        natives natives;

        public libraries(downloads downloads, String name, natives natives) {
            this.downloads = downloads;
            this.name = name;
            this.natives = natives;
        }

        public static class natives {
            String linux;
            String osx;
            String windows;


            public natives(String linux, String windows, String osx) {
                this.linux = linux;
                this.osx = osx;
                this.windows = windows;
            }
        }

        public static class downloads {
            artifact artifact;
            classifiers classifiers;

            public downloads(artifact artifact, classifiers classifiers) {
                this.artifact = artifact;
                this.classifiers = classifiers;
            }
            public static class classifiers{



                Map<String, nativesArtifact> nativesArtifact;


                public classifiers(Map<String, nativesArtifact> nativesArtifact){
                    this.nativesArtifact = nativesArtifact;
                }

                public static class nativesArtifact {
                    String hash;
                    String sha1;
                    String size;
                    String url;

                    public nativesArtifact(String hash, String size, String sha1, String url) {
                        this.hash = hash;
                        this.size = size;
                        this.sha1 = sha1;
                        this.url = url;
                    }
                }
            }




            public static class artifact {
                String path;
                String sha1;
                String size;
                String url;

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


class assets {
    Map<String, path> objects;
    public assets(Map<String, path> objects) {
        this.objects = objects;
    }
    public static class path{
          String hash;
          String size;
          public path(String hash, String size) {
              this.hash = hash;
              this.size = size;
            }
    }




}

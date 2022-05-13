package fr.endide.launcher.system;

import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.internal.InternalLaunchProfile;
import fr.theshark34.openlauncherlib.internal.InternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.*;

import java.io.File;

public class launchGame {
    fileManager fileManager = new fileManager();
    public void launchGame(String version, String tweaks, String playerUsername, String token, String uuid) throws LaunchException {
        GameInfos infos = new GameInfos("endideLauncher", new GameVersion(version, GameType.V1_8_HIGHER), GetGameTweaks(tweaks));
        GameFolder folder = new GameFolder("/assets" , "/libs" , "", "/runtime" + File.separator + "client-" + version + ".jar");
        AuthInfos authInfos = new AuthInfos(playerUsername, token, uuid);
        ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(infos, folder, authInfos);
        ExternalLauncher launcher = new ExternalLauncher(profile);
        launcher.launch();
    }
    public GameTweak[] GetGameTweaks(String tweaks){
        if(tweaks.equals("optifine")){
            return new GameTweak[] {GameTweak.OPTIFINE};
        }
        if(tweaks.equals("forge")){
            return new GameTweak[] {GameTweak.FORGE};
        }
        return new GameTweak[0];
    }
    public GameType getGameType(String version){
        String formatVersion = version.replace(".", "");
        int intVersion = Integer.parseInt(formatVersion);
        if(intVersion >172){
            return GameType.V1_7_2_LOWER;
        }
        if(intVersion >172){
            return GameType.V1_5_2_LOWER;
        }
        if(intVersion >172){
            return GameType.V1_7_10;

        }
        if(intVersion >1800){
            return GameType.V1_8_HIGHER;

        }if(intVersion < 1130){
            return GameType.V1_13_HIGHER_FORGE;
        }
        return null;
    }
}

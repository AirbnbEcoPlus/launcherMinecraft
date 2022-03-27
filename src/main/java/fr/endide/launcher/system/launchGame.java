package fr.endide.launcher.system;

import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.internal.InternalLaunchProfile;
import fr.theshark34.openlauncherlib.internal.InternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.*;

public class launchGame {
    public void launchGame(String version, String tweaks, String playerUsername, String token, String uuid) throws LaunchException {
        GameInfos infos = new GameInfos("EndideLauncher", new GameVersion(version, getGameType(version)), GetGameTweaks(tweaks));
        AuthInfos authInfos = new AuthInfos(playerUsername, token, uuid);
        InternalLaunchProfile profile = MinecraftLauncher.createInternalProfile(infos, GameFolder.BASIC, authInfos);
        InternalLauncher launcher = new InternalLauncher(profile);
        launcher.launch();
    }
    public GameTweak[] GetGameTweaks(String tweaks){
        if(tweaks == "optifine"){
            return new GameTweak[] {GameTweak.OPTIFINE};
        }
        if(tweaks == "forge"){
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

//#if MODERN==0 || FABRIC==1
package cc.woverflow.overflowanimations;

import cc.woverflow.onecore.utils.Updater;
import cc.woverflow.overflowanimations.config.AnimationsConfig;
import java.io.File;

//#if MODERN==0
import club.sk1er.oldanimations.config.OldAnimationsSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.fml.common.Mod;
import cc.woverflow.overflowanimations.command.AnimationsCommand;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
//#else
//$$ import cc.woverflow.onecore.utils.Utils;
//#endif

//#if MODERN==0
@Mod(modid = OverflowAnimations.ID, name = OverflowAnimations.NAME, version = OverflowAnimations.VER)
//#endif
public class OverflowAnimations {
    public static final String NAME = "@NAME@", VER = "@VER@", ID = "@ID@";
    public static File modDir = new File(new File("./W-OVERFLOW"), NAME);
    public static AnimationsConfig config;

    //#if MODERN==0
    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    //#else
    //$$ public void onPreInit()
    //#endif
    {
        if (!modDir.exists()) modDir.mkdirs();
        //#if FABRIC==1
        //$$ File file = Utils.getFileOfMod(ID);
        //$$ if (file == null) return;
        //#endif
        Updater.INSTANCE.addToUpdater(
                //#if FABRIC==0
                event.getSourceFile()
                //#else
                //$$ file
                //#endif
                , NAME, ID, VER, "W-OVERFLOW/" + ID);
    }

    //#if MODERN==0
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event)
    //#else
    //$$ public void onInit()
    //#endif
    {
        //#if MODERN==1
        //$$ onPreInit();
        //#endif
        //#if MODERN==0
        new AnimationsCommand().register();
        //#endif
        config = new AnimationsConfig();
        config.preload();
    }

    public static boolean shouldPunch() {
        return OldAnimationsSettings.punching && (!AnimationsConfig.autoDisablePunching || OverflowAnimations.isNotMinemenClub());
    }

    private static boolean isNotMinemenClub() {
        ServerData data = Minecraft.getMinecraft().getCurrentServerData();
        return data == null || !data.serverIP.endsWith("minemen.club");
    }
}
//#endif
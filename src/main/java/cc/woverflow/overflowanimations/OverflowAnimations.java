//#if MODERN==0 || FABRIC==1
package cc.woverflow.overflowanimations;

import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import cc.woverflow.overflowanimations.command.AnimationsCommand;
import cc.woverflow.overflowanimations.config.AnimationsConfig;
import club.sk1er.oldanimations.config.OldAnimationsSettings;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;

//#if MODERN==0
@Mod(modid = OverflowAnimations.ID, name = OverflowAnimations.NAME, version = OverflowAnimations.VER)
//#endif
public class OverflowAnimations {
    public static final String NAME = "@NAME@", VER = "@VER@", ID = "@ID@";
    public static File modDir = new File(new File("./W-OVERFLOW"), NAME);
    public static AnimationsConfig config;
    public static ItemStack renderingStack = null;

    //#if MODERN==0
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event)
    //#else
    //$$ public void onInit()
    //#endif
    {
        CommandManager.INSTANCE.registerCommand(AnimationsCommand.class);
        config = new AnimationsConfig();
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * @see cc.woverflow.overflowanimations.plugin.AnimationsMixinPlugin#postApply(String, ClassNode, String, IMixinInfo) 
     */
    @SuppressWarnings("unused")
    public static boolean shouldPunch() {
        return OldAnimationsSettings.punching && !AnimationsConfig.spiderfrogPunching;
    }
}
//#endif
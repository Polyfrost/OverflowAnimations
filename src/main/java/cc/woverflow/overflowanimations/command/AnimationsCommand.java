package cc.woverflow.overflowanimations.command;

//#if MODERN==0

import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import cc.woverflow.overflowanimations.OverflowAnimations;

@Command(OverflowAnimations.ID)
public class AnimationsCommand {

    @Main
    private static void handle() {
        OverflowAnimations.config.openGui();
    }
}
//#endif
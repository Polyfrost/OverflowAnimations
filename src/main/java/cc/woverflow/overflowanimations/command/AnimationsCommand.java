package cc.woverflow.overflowanimations.command;

import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import cc.woverflow.overflowanimations.OverflowAnimations;

@Command(value = OverflowAnimations.ID, description = "Open the Overflow Animations GUI")
public class AnimationsCommand {

    @Main
    public static void handle() {
        OverflowAnimations.config.openGui();
    }
}
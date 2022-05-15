package cc.woverflow.overflowanimations.command;

//#if MODERN==0

import cc.woverflow.onecore.utils.GuiUtils;
import cc.woverflow.overflowanimations.OverflowAnimations;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;

public class AnimationsCommand extends Command {
    public AnimationsCommand() {
        super(OverflowAnimations.ID, true);
    }

    @DefaultHandler
    public void handle() {
        GuiUtils.openScreen(OverflowAnimations.config);
    }
}
//#endif
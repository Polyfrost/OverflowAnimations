//#if MODERN==0 || FABRIC==1
package cc.woverflow.overflowanimations.config;

import cc.woverflow.overflowanimations.OverflowAnimations;
import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;

import java.io.File;

public class AnimationsConfig extends Vigilant {

    @Property(
            type = PropertyType.SWITCH,
            name = "1.7 Enchantment Glint",
            description = "Make the enchantment glint like the 1.7 animation.",
            category = "General"
    )
    public static boolean enchantmentGlint = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "1.15+ Enchantment Glint",
            description = "Make the enchantment glint like the 1.15 animation.\nWorks with the 1.7 enchantment glint feature.",
            category = "General"
    )
    public static boolean enchantmentGlintNew = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "1.7 Third Person Blockhit Rendering",
            description = "Render the third person blockhit like in 1.7.",
            category = "General"
    )
    public static boolean thirdPersonItemRendering = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Disable 1.7 Punching in MinemenClub",
            description = "Disable the 1.7 punching feature in MinemenClub which false bans users.",
            category = "General"
    )
    public static boolean autoDisablePunching = true;

    public AnimationsConfig() {
        super(new File(OverflowAnimations.modDir, OverflowAnimations.ID + ".toml"), OverflowAnimations.NAME);
        initialize();
    }
}
//#endif
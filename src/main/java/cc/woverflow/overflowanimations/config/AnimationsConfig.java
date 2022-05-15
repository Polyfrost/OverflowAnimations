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
            name = "Cleaner Enchantment Glint",
            description = "Make the enchantment glint only display up to down.",
            category = "General"
    )
    public static boolean enchantmentGlint = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "1.15+ Armor Enchantment Glint",
            description = "Make the armor enchantment glint like the 1.15 animation.\nWorks with the cleaner enchantment glint feature.",
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
            name = "Spiderfrog Old Animations Punching",
            description = "Enable 1.7 punching like Spiderfrog's Old Animations. Fixes false anti-cheat detections.\nRequires Punching on in Sk1er Old Animations.",
            category = "General"
    )
    public static boolean spiderfrogPunching = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Mixces Animations",
            description = "Use Mixces' 1st / 3rd person animations instead of Sk1er's.\nMore accurate than Sk1er's animations.",
            category = "General"
    )
    public static boolean mixcesAnimations = true;

    public AnimationsConfig() {
        super(new File(OverflowAnimations.modDir, OverflowAnimations.ID + ".toml"), OverflowAnimations.NAME);
        initialize();
    }
}
//#endif
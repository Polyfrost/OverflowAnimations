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
            name = "2D Items",
            description = "Make items always face the player.",
            category = "General"
    )
    public static boolean items2D = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Better 2D Items",
            description = "Fix a bug with vanilla 1.7 2D Items.",
            category = "General"
    )
    public static boolean better2DItems = true;

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
            name = "Spiderfrog Old Animations Item Usage Punching",
            description = "Enable 1.7 item usage whilst punching like Spiderfrog's Old Animations. Fixes false anti-cheat detections.\n§eRequires Punch During Usage to be enabled in Sk1er Old Animations.",
            category = "General"
    )
    public static boolean spiderfrogPunching = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "1.7 Mixces Item / Block Positions",
            description = "Uses the 1.7 item positions for all items and blocks in both the first and third person POV.\n§eUse with 1.7 First Person Carpet Position for a true 1.7 experience.",
            category = "General"
    )
    public static boolean mixcesAnimations = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "1.7 First Person Carpet Position",
            description = "Lowers the carpets in the first person POV similar to how it's done in 1.7.\n§eUse with 1.7 Item Positions for a true 1.7 experience.",
            category = "General"
    )
    public static boolean firstPersonCarpetPosition = false;

    public AnimationsConfig() {
        super(new File(OverflowAnimations.modDir, OverflowAnimations.ID + ".toml"), OverflowAnimations.NAME);
        initialize();
        addDependency("firstPersonCarpetPosition", "mixcesAnimations");
        addDependency("better2DItems", "items2D");
    }
}
//#endif

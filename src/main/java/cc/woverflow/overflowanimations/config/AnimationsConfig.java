//#if MODERN==0 || FABRIC==1
package cc.woverflow.overflowanimations.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Checkbox;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.migration.VigilanceMigrator;
import cc.woverflow.overflowanimations.OverflowAnimations;

import java.io.File;

public class AnimationsConfig extends Config {

    @Switch(
            name = "2D Items",
            subcategory = "2D Items"
    )
    public static boolean items2D = false;

    @Checkbox(
            name = "Microextent 2D Sprites",
            subcategory = "2D Items"
    )
    public static boolean itemSprites = true;

    @Switch(
            name = "Cleaner Enchantment Glint",
            subcategory = "Enchantment Glint"
    )
    public static boolean enchantmentGlint = true;

    @Switch(
            name = "1.15+ New Armor Enchantment Glint",
            subcategory = "Enchantment Glint"
    )
    public static boolean enchantmentGlintNew = true;

    @Switch(
            name = "1.7 Third Person Blockhit Rendering",
            subcategory = "1.7 Animations"
    )
    public static boolean thirdPersonItemRendering = true;

    @Switch(
            name = "1.7 Item Switching (only switch on slot change)",
            subcategory = "1.7 Animations"
    )
    public static boolean itemSwitch = true;

    @Switch(
            name = "Spiderfrog Old Animations Item Usage Punching (Patched)",
            subcategory = "1.7 Animations", size = 2
    )
    public static boolean spiderfrogPunching = true;

    @Switch(
            name = "1.7 Mixces Item / Block Hand Positions",
            subcategory = "1.7 Animations"
    )
    public static boolean mixcesAnimations = true;

    @Checkbox(
            name = "1.7 Mixces First Person Carpet Position",
            subcategory = "1.7 Animations"
    )
    public static boolean firstPersonCarpetPosition = false;

    @Switch(
            name = "1.7 Projectiles",
            subcategory = "1.7 Animations"
    )
    public static boolean oldProjectiles = true;

    @Checkbox(
            name = "Microextent Projectile Sprites",
            subcategory = "1.7 Animations"
    )
    public static boolean projectileSprites = true;

    public AnimationsConfig() {
        super(new Mod(OverflowAnimations.NAME, ModType.PVP, new VigilanceMigrator(new File(new File(new File("./W-OVERFLOW"), OverflowAnimations.NAME), OverflowAnimations.NAME + ".toml").getPath())), OverflowAnimations.NAME + ".json");
        initialize();
        addDependency("firstPersonCarpetPosition", "mixcesAnimations");
        addDependency("itemSprites", "items2D");
        addDependency("projectileSprites", "oldProjectiles");
    }
}
//#endif

//#if MODERN==0 || FABRIC==1
package cc.woverflow.overflowanimations.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.woverflow.overflowanimations.OverflowAnimations;

public class AnimationsConfig extends Config {

    @Switch(
            name = "2D Items"
    )
    public static boolean items2D = false;

    @Switch(
            name = "2D Items Bug Fix"
    )
    public static boolean better2DItems = true;

    @Switch(
            name = "Cleaner Enchantment Glint"
    )
    public static boolean enchantmentGlint = true;

    @Switch(
            name = "1.15+ Armor Enchantment Glint"
    )
    public static boolean enchantmentGlintNew = true;

    @Switch(
            name = "1.7 Third Person Blockhit Rendering"
    )
    public static boolean thirdPersonItemRendering = true;

    @Switch(
            name = "1.7 Item Switching"
    )
    public static boolean itemSwitch = true;

    @Switch(
            name = "Better Item Usage Punching"
    )
    public static boolean spiderfrogPunching = true;

    @Switch(
            name = "1.7 Mixces Item / Block Positions"
    )
    public static boolean mixcesAnimations = true;

    @Switch(
            name = "1.7 Mixces First Person Carpet Position"
    )
    public static boolean firstPersonCarpetPosition = false;

    @Switch(
            name = "1.7 Projectiles"
    )
    public static boolean oldProjectiles = true;

    @Switch(
            name = "Better 1.7 Projectiles"
    )
    public static boolean oldProjectilesFix = true;

    public AnimationsConfig() {
        super(new Mod(OverflowAnimations.NAME, ModType.PVP), OverflowAnimations.ID + ".json");
        initialize();
        addDependency("firstPersonCarpetPosition", "mixcesAnimations");
        addDependency("better2DItems", "items2D");
        addDependency("oldProjectilesFix", "oldProjectiles");
    }
}
//#endif

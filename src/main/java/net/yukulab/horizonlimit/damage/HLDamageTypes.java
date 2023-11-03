package net.yukulab.horizonlimit.damage;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.yukulab.horizonlimit.HorizonLimit;

public class HLDamageTypes {

    public static RegistryKey<DamageType> HORIZONTAL_LIMIT = register("horizontal_limit");

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(
                world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }

    private static RegistryKey<DamageType> register(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(HorizonLimit.MOD_ID, name));
    }
}

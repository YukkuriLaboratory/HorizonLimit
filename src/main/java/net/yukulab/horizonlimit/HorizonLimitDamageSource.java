package net.yukulab.horizonlimit;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;

public class HorizonLimitDamageSource extends DamageSource {

    public HorizonLimitDamageSource() {
        super(null);
    }

    @Override
    public Text getDeathMessage(LivingEntity killed) {
        return Text.empty().append(killed.getDisplayName()).append("は世界の境界線を超えてしまった");
    }

    @Override
    public boolean isIn(TagKey<DamageType> tag) {
        return false;
    }

    @Override
    public boolean isOf(RegistryKey<DamageType> typeKey) {
        return false;
    }
}

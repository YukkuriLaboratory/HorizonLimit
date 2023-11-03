package net.yukulab.horizonlimit.damage;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class HorizonLimitDamageSource extends DamageSource {

    public HorizonLimitDamageSource(Registry<DamageType> registry) {
        super(registry.entryOf(HLDamageTypes.HORIZONTAL_LIMIT));
    }

    @Override
    public Text getDeathMessage(LivingEntity killed) {
        return Text.translatable("death.attack.border.over", killed.getDisplayName());
    }
}

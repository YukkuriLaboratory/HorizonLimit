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

    // 本来であれば言語ファイルに設定するべき
    @Override
    public Text getDeathMessage(LivingEntity killed) {
        return Text.empty().append(killed.getDisplayName()).append("は世界の境界線を超えてしまった");
    }
}

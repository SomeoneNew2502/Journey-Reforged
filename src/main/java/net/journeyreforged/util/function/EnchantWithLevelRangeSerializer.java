package net.journeyreforged.util.function;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class EnchantWithLevelRangeSerializer {
    public static final Codec<EnchantWithLevelRangeFunction> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC
                    .xmap(id -> Registries.ENCHANTMENT.get(id), Registries.ENCHANTMENT::getId)
                    .fieldOf("enchantment").forGetter(EnchantWithLevelRangeFunction::getEnchantment),
            Codec.INT.fieldOf("minLevel").forGetter(EnchantWithLevelRangeFunction::getMinLevel),
            Codec.INT.fieldOf("maxLevel").forGetter(EnchantWithLevelRangeFunction::getMaxLevel)
    ).apply(instance, EnchantWithLevelRangeFunction::new));
}

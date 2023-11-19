package io.chasehuegel.moreshields;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

class BlockingPropertyFunction implements ItemPropertyFunction {

    @Override
    public float call(ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {
        return entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F;
    }
}

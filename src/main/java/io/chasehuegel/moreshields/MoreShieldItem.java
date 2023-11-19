package io.chasehuegel.moreshields;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;

public class MoreShieldItem extends ShieldItem {

    private int enchantmentValue;

    public MoreShieldItem(int enchantmentValue, Properties p_43089_) {
        super(p_43089_);
        this.enchantmentValue = enchantmentValue;
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return enchantmentValue;
    }
}

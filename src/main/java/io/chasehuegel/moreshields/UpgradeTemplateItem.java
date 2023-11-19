package io.chasehuegel.moreshields;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class UpgradeTemplateItem extends SmithingTemplateItem {

    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation("sevensmoreshields:upgrade_template.upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("sevensmoreshields:upgrade_template.upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("sevensmoreshields:upgrade_template.upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("sevensmoreshields:upgrade_template.upgrade.base_slot_description")));
    private static final Component UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("sevensmoreshields:upgrade_template.upgrade.additions_slot_description")));
    private static final ResourceLocation EMPTY_SLOT_SHIELD = new ResourceLocation("sevensmoreshields:item/empty_slot_shield");
    private static final ResourceLocation EMPTY_SLOT_SCUTE = new ResourceLocation("sevensmoreshields:item/empty_slot_scute");
    private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");
    private static final ResourceLocation EMPTY_SLOT_AMETHYST_SHARD = new ResourceLocation("item/empty_slot_amethyst_shard");

    public UpgradeTemplateItem(Component appliesTo, Component ingredients, Component upgradeDescription, Component baseSlotDescription, Component additionsSlotDescription, List<ResourceLocation> baseSlotEmptyIcons, List<ResourceLocation> additionalSlotEmptyIcons) {
        super(appliesTo, ingredients, upgradeDescription, baseSlotDescription, additionsSlotDescription, baseSlotEmptyIcons, additionalSlotEmptyIcons);
    }

    public static SmithingTemplateItem createUpgradeTemplate() {
        return new SmithingTemplateItem(UPGRADE_APPLIES_TO, UPGRADE_INGREDIENTS, UPGRADE, UPGRADE_BASE_SLOT_DESCRIPTION, UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createUpgradeIconList(), createUpgradeMaterialList());
    }

    private static List<ResourceLocation> createUpgradeIconList() {
        return List.of(EMPTY_SLOT_SHIELD);
    }

    private static List<ResourceLocation> createUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT, EMPTY_SLOT_AMETHYST_SHARD, EMPTY_SLOT_SCUTE);
    }
}

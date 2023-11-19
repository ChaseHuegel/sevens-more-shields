package io.chasehuegel.moreshields;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(MoreShields.MODID)
public class MoreShields {

    public static final String MODID = "sevensmoreshields";

    private static final Logger LOGGER = LogUtils.getLogger();

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    private static final ItemPropertyFunction BLOCKING_PROPERTY_FUNCTION = new BlockingPropertyFunction();

    private static final List<RegistryObject<Item>> REGISTERED_SHIELDS = new ArrayList();
    private static final Items SHIELD_REGISTRY = new Items();

    public static final RegistryObject<CreativeModeTab> CREATIVE_MODE_TAB = CREATIVE_MODE_TABS.register("mod", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("itemGroup." + MODID))
            .icon(() -> Items.AMETHYST_SHIELD.get().getDefaultInstance())
            .displayItems((parameters, output) -> {

                output.accept(Items.UPGRADE_TEMPLATE.get());
                for (RegistryObject<Item> registeredShield : REGISTERED_SHIELDS) {
                    output.accept(registeredShield.get());
                }
            }).build());

    public MoreShields() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static RegistryObject<Item> register(String name, int enchantmentValue, Float durabilityModifier, Boolean fireResistant) {
        RegistryObject<Item> item = ITEMS.register(name, () -> new MoreShieldItem(enchantmentValue, createShieldProperties(durabilityModifier, fireResistant)));
        REGISTERED_SHIELDS.add(item);
        return item;
    }

    private static Item.Properties createShieldProperties(Float durabilityModifier, Boolean fireResistant) {
        if (fireResistant) {
            return new Item.Properties().fireResistant().defaultDurability(Math.round(336 * durabilityModifier));
        }

        return new Item.Properties().defaultDurability(Math.round(336 * durabilityModifier));
    }

    @Mod.EventBusSubscriber(modid = MoreShields.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    private static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            //  Register blocking properties.
            for (RegistryObject<Item> registeredShield : REGISTERED_SHIELDS) {
                ItemProperties.register(registeredShield.get(), new ResourceLocation("blocking"), BLOCKING_PROPERTY_FUNCTION);
            }
        }
    }

    public static class Items {
        public static void Initialize() {};

        public static final RegistryObject<Item> UPGRADE_TEMPLATE = ITEMS.register("upgrade_template", () -> UpgradeTemplateItem.createUpgradeTemplate());
        public static final RegistryObject<Item> AMETHYST_SHIELD = register("amethyst_shield", 30, 1.12f, false);
        public static final RegistryObject<Item> GOLDEN_SHIELD = register("golden_shield", 22, 1.12f, false);
        public static final RegistryObject<Item> NETHERITE_SHIELD = register("netherite_shield", 15, 1.3f, true);
        public static final RegistryObject<Item> TURTLE_SHIELD = register("turtle_shield", 10, 2.0f, false);
    }
}

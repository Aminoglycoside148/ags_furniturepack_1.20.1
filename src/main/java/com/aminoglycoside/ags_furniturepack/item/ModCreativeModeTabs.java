package com.aminoglycoside.ags_furniturepack.item;


import com.aminoglycoside.ags_furniturepack.AGSFurnitureMod;
import com.aminoglycoside.ags_furniturepack.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGSFurnitureMod.MODID);

    public static final RegistryObject<CreativeModeTab> FLOOR_MARKING_TAB = CREATIVE_MODE_TABS.register("floor_markings_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.CHAIR_BASIC.get()))
                    .title(Component.translatable("creativetab.ags_furniture"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.ADDON_FURNISHINGS.get());

                        pOutput.accept(ModBlocks.CHAIR_BASIC.get());
                        pOutput.accept(ModBlocks.CHAIR_BASIC_ARMCHAIR.get());
                        pOutput.accept(ModBlocks.CHAIR_BASIC_ARMCHAIR_BLACK.get());
                        pOutput.accept(ModBlocks.TEST_DESK.get());
                        pOutput.accept(ModBlocks.DRAWER_MODERN_WHITE.get());
                        pOutput.accept(ModBlocks.DRAWER_MODERN_BLACK.get());
                        pOutput.accept(ModBlocks.DESK_MODERN_WHITE.get());
                        pOutput.accept(ModBlocks.DESK_MODERN_BLACK.get());
                        pOutput.accept(ModBlocks.DRAWER_MODERN_WHITE_TALL.get());
                        pOutput.accept(ModBlocks.DRAWER_MODERN_BLACK_TALL.get());

                        pOutput.accept(ModBlocks.TABLE_BASIC.get());
                        pOutput.accept(ModBlocks.TABLE_DINER_RED.get());
                        pOutput.accept(ModBlocks.CHAIR_DINER_RED.get());

                        pOutput.accept(ModBlocks.TABLE_FOLDING_GRAY.get());
                        pOutput.accept(ModBlocks.CHAIR_FOLDING_GRAY.get());

                        pOutput.accept(ModBlocks.GLASS_RAILING.get());
                        pOutput.accept(ModBlocks.GLASS_STAIR_RAILING.get());
                    }).build());
    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

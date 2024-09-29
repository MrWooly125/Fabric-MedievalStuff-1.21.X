package net.mrwooly.medievalstuff.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mrwooly.medievalstuff.MedievalStuff;
import net.mrwooly.medievalstuff.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup MEDIEVALSTUFF_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_items"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.medievalstuff_items"))
                    .icon(() -> new ItemStack(ModItems.RAW_SILVER)).entries((displayContext, entries) -> {
                        entries.add(ModItems.RAW_SILVER);
                        entries.add(ModItems.SILVER_INGOT);
                        entries.add(ModItems.SILVER_NUGGET);
                    }).build());

    public static final ItemGroup MEDIEVALSTUFF_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_blocks"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.medievalstuff_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.RAW_SILVER_BLOCK)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.RAW_SILVER_BLOCK);
                        entries.add(ModBlocks.SILVER_BLOCK);
                        entries.add(ModBlocks.SILVER_ORE);
                        entries.add(ModBlocks.DEEPSLATE_SILVER_ORE);
                    }).build());

    public static void registerItemGroups() {
        MedievalStuff.LOGGER.info("Registering Item Groups for " + MedievalStuff.MOD_ID);
    }
}

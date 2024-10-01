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


                        entries.add(ModItems.PIECE_OF_JELLY);
                        entries.add(ModItems.JAR_OF_JELLY);
                    }).build());

    public static final ItemGroup MEDIEVALSTUFF_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_blocks"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.medievalstuff_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.RAW_SILVER_BLOCK)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.GLOOMY_STONE);
                        entries.add(ModBlocks.GLOOMY_STONE_STAIRS);
                        entries.add(ModBlocks.GLOOMY_STONE_SLAB);
                        entries.add(ModBlocks.GLOOMY_STONE_PRESSURE_PLATE);
                        entries.add(ModBlocks.GLOOMY_STONE_BUTTON);

                        entries.add(ModBlocks.GLOOMY_STONE_BRICKS);
                        entries.add(ModBlocks.GLOOMY_STONE_BRICK_STAIRS);
                        entries.add(ModBlocks.GLOOMY_STONE_BRICK_SLAB);
                        entries.add(ModBlocks.GLOOMY_STONE_BRICK_WALL);


                        entries.add(ModBlocks.RAW_SILVER_BLOCK);
                        entries.add(ModBlocks.SILVER_BLOCK);
                        entries.add(ModBlocks.SILVER_ORE);
                        entries.add(ModBlocks.DEEPSLATE_SILVER_ORE);
                    }).build());

    public static final ItemGroup MEDIEVALSTUFF_EQUIPMENT = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_equipment"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.medievalstuff_equipment"))
                    .icon(() -> new ItemStack(ModItems.SILVER_SWORD)).entries((displayContext, entries) -> {
                        entries.add(ModItems.SILVER_SWORD);
                        entries.add(ModItems.SILVER_PICKAXE);
                        entries.add(ModItems.SILVER_AXE);
                        entries.add(ModItems.SILVER_SHOVEL);
                        entries.add(ModItems.SILVER_HOE);
                    }).build());

    public static void registerItemGroups() {
        MedievalStuff.LOGGER.info("Registering Item Groups for " + MedievalStuff.MOD_ID);
    }
}

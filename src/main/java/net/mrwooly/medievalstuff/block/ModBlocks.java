package net.mrwooly.medievalstuff.block;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.mrwooly.medievalstuff.MedievalStuff;

public class ModBlocks {
        public static final Block GLOOMY_STONE = registerBlock("gloomy_stone",
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)
                    .strength(2f).requiresTool()));

    public static final Block GLOOMY_STONE_STAIRS = registerBlock("gloomy_stone_stairs",
            new StairsBlock(ModBlocks.GLOOMY_STONE.getDefaultState(),
            AbstractBlock.Settings.create().strength(2f).requiresTool()));

    public static final Block GLOOMY_STONE_SLAB = registerBlock("gloomy_stone_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));

    public static final Block GLOOMY_STONE_BUTTON = registerBlock("gloomy_stone_button",
            new ButtonBlock(BlockSetType.STONE, 10, AbstractBlock.Settings.create().strength(0.5f).requiresTool()));

    public static final Block GLOOMY_STONE_PRESSURE_PLATE = registerBlock("gloomy_stone_pressure_plate",
            new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.create().strength(1f).requiresTool()));


    public static final Block GLOOMY_STONE_BRICKS = registerBlock("gloomy_stone_bricks",
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)
                    .strength(2f).requiresTool()));

    public static final Block GLOOMY_STONE_BRICK_STAIRS = registerBlock("gloomy_stone_brick_stairs",
            new StairsBlock(ModBlocks.GLOOMY_STONE_BRICKS.getDefaultState(),
                    AbstractBlock.Settings.create().strength(2f).requiresTool()));

    public static final Block GLOOMY_STONE_BRICK_SLAB = registerBlock("gloomy_stone_brick_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));

    public static final Block GLOOMY_STONE_BRICK_WALL = registerBlock("gloomy_stone_brick_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));



    public static final Block RAW_SILVER_BLOCK = registerBlock("raw_silver_block",
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)
                    .strength(4.5f).requiresTool()));

    public static final Block SILVER_BLOCK = registerBlock("silver_block",
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.METAL)
                    .strength(4.5f).requiresTool()));


    public static final Block SILVER_ORE = registerBlock("silver_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 3),
                    AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)
                    .strength(2.5f).requiresTool()));

    public static final Block DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 3),
                    AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE)
                    .strength(4f).requiresTool()));

    public static final Block COPY_BLOCK = registerBlock("copy_block",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BUTTON)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MedievalStuff.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MedievalStuff.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        MedievalStuff.LOGGER.info("Registering Mod Blocks for " + MedievalStuff.MOD_ID);
    }
}

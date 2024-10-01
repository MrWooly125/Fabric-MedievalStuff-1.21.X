package net.mrwooly.medievalstuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.mrwooly.medievalstuff.block.ModBlocks;
import net.mrwooly.medievalstuff.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool gloomyStoneTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GLOOMY_STONE);

        gloomyStoneTexturePool.stairs(ModBlocks.GLOOMY_STONE_STAIRS);
        gloomyStoneTexturePool.slab(ModBlocks.GLOOMY_STONE_SLAB);
        gloomyStoneTexturePool.button(ModBlocks.GLOOMY_STONE_BUTTON);
        gloomyStoneTexturePool.pressurePlate(ModBlocks.GLOOMY_STONE_PRESSURE_PLATE);

        BlockStateModelGenerator.BlockTexturePool gloomyStoneBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GLOOMY_STONE_BRICKS);

        gloomyStoneBrickTexturePool.stairs(ModBlocks.GLOOMY_STONE_BRICK_STAIRS);
        gloomyStoneBrickTexturePool.slab(ModBlocks.GLOOMY_STONE_BRICK_SLAB);
        gloomyStoneBrickTexturePool.wall(ModBlocks.GLOOMY_STONE_BRICK_WALL);


        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_SILVER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SILVER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SILVER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_SILVER_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RAW_SILVER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_NUGGET, Models.GENERATED);

        itemModelGenerator.register(ModItems.PIECE_OF_JELLY, Models.GENERATED);
        itemModelGenerator.register(ModItems.JAR_OF_JELLY, Models.GENERATED);

        itemModelGenerator.register(ModItems.SILVER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SILVER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SILVER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SILVER_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SILVER_HOE, Models.HANDHELD);
    }
}

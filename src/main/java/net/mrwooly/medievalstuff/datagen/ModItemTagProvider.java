package net.mrwooly.medievalstuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.mrwooly.medievalstuff.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.LUMISHROOM_LOG.asItem(), ModBlocks.LUMISHROOM_WOOD.asItem(),
                        ModBlocks.STRIPPED_LUMISHROOM_LOG.asItem(), ModBlocks.STRIPPED_LUMISHROOM_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.LUMISHROOM_PLANKS.asItem());
    }
}

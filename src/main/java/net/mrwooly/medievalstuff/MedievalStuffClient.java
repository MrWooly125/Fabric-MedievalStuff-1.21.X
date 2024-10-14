package net.mrwooly.medievalstuff;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.mrwooly.medievalstuff.block.ModBlocks;
import net.mrwooly.medievalstuff.entity.ModEntities;
import net.mrwooly.medievalstuff.entity.client.JellyModel;
import net.mrwooly.medievalstuff.entity.client.JellyRenderer;
import net.mrwooly.medievalstuff.entity.client.ModEntityModelLayers;

public class MedievalStuffClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LUMISHROOM, RenderLayer.getCutout());

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.JELLY, JellyModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.JELLY, JellyRenderer::new);
    }
}

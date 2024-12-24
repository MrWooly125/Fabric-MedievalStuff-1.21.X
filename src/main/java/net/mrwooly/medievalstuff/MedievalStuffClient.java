package net.mrwooly.medievalstuff;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.mrwooly.medievalstuff.block.ModBlocks;
import net.mrwooly.medievalstuff.entity.ModEntities;
import net.mrwooly.medievalstuff.entity.client.*;

public class MedievalStuffClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LUMISHROOM, RenderLayer.getCutout());

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.JELLY, JellyModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.JELLY, JellyRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.MAGIC_SOUL_FIRE_CHARGE_TIER_1, MagicSoulFireChargeTier1ProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MAGIC_SOUL_FIRE_CHARGE_TIER_1, MagicSoulFireChargeTier1ProjectileRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.MAGIC_SOUL_FIRE_CHARGE_TIER_3, MagicSoulFireChargeTier3ProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MAGIC_SOUL_FIRE_CHARGE_TIER_3, MagicSoulFireChargeTier3ProjectileRenderer::new);
    }
}

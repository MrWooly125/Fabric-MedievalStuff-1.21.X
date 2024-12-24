package net.mrwooly.medievalstuff.entity.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.mrwooly.medievalstuff.MedievalStuff;
import net.mrwooly.medievalstuff.entity.custom.MagicSoulFireChargeTier3ProjectileEntity;

public class MagicSoulFireChargeTier3ProjectileRenderer extends EntityRenderer<MagicSoulFireChargeTier3ProjectileEntity> {
    public static final Identifier TEXTURE = Identifier.of(MedievalStuff.MOD_ID, "textures/entity/magic_soul_fire_charge_tier_3/magic_soul_fire_charge_tier_3.png");
    protected MagicSoulFireChargeTier3ProjectileModel model;


    public MagicSoulFireChargeTier3ProjectileRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        model = new MagicSoulFireChargeTier3ProjectileModel(ctx.getPart(ModEntityModelLayers.MAGIC_SOUL_FIRE_CHARGE_TIER_3));
    }

    @Override
    public Identifier getTexture(MagicSoulFireChargeTier3ProjectileEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(MagicSoulFireChargeTier3ProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        VertexConsumer vertexconsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.model.getLayer(TEXTURE), false, false);
        this.model.render(matrices, vertexconsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}

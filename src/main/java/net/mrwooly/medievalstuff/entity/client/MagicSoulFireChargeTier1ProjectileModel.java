package net.mrwooly.medievalstuff.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.mrwooly.medievalstuff.entity.custom.MagicSoulFireChargeTier1ProjectileEntity;

public class MagicSoulFireChargeTier1ProjectileModel extends EntityModel<MagicSoulFireChargeTier1ProjectileEntity> {
    private final ModelPart magic_soul_fire_charge_tier_1;
    public MagicSoulFireChargeTier1ProjectileModel(ModelPart root) {
        this.magic_soul_fire_charge_tier_1 = root.getChild("magic_soul_fire_charge");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData magic_soul_fire_charge = modelPartData.addChild("magic_soul_fire_charge", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 6).cuboid(1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(2, 6).cuboid(1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 5).cuboid(1.0F, -1.0F, 0.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 4).cuboid(1.0F, 1.0F, -1.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 4).cuboid(2.0F, -1.0F, -1.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 5).cuboid(2.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, 0.0F));
        return TexturedModelData.of(modelData, 16, 16);
    }

    @Override
    public void setAngles(MagicSoulFireChargeTier1ProjectileEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        magic_soul_fire_charge_tier_1.render(matrices, vertices, light, overlay, color);
    }
}
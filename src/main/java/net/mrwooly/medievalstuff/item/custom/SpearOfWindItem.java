package net.mrwooly.medievalstuff.item.custom;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SpearOfWindItem extends SwordItem implements ProjectileItem {


    public SpearOfWindItem(ToolMaterial toolMaterial, Item.Settings settings) {
        super(toolMaterial, settings);
    }

    public static AttributeModifiersComponent createAttributeModifiersComponent(ToolMaterial material) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 5.5, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ).add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.3F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ).add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(ATTACK_RANGE, 1F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ).add(
                        EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE,
                        new EntityAttributeModifier(BLOCK_RANGE, 1F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ).build();
    }
    public static final Identifier ATTACK_RANGE = Identifier.of("attack_range");
    public static final Identifier BLOCK_RANGE = Identifier.of("block_range");

    int  counter = 0;
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            if (counter == 1) {
                WindChargeEntity windChargeEntity1 = new WindChargeEntity(user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                windChargeEntity1.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.5F, 3.0F);
                world.spawnEntity(windChargeEntity1);

                WindChargeEntity windChargeEntity2 = new WindChargeEntity(user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                windChargeEntity2.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.0F, 3.0F);
                world.spawnEntity(windChargeEntity2);

                int chanceForAdditionalProjectile = MathHelper.nextInt(Random.createLocal(), 1, 3);
                if(chanceForAdditionalProjectile == 1) {

                    WindChargeEntity windChargeEntity3 = new WindChargeEntity(user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                    windChargeEntity3.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 4.0F);
                    world.spawnEntity(windChargeEntity3);

                    user.getItemCooldownManager().set(this, 150);
                } else {

                    user.getItemCooldownManager().set(this, 125);
                }
                counter = 2;
            }
            if(counter == 0) {
                WindChargeEntity windChargeEntity1 = new WindChargeEntity(user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                windChargeEntity1.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.0F, 2.5F);
                world.spawnEntity(windChargeEntity1);
                counter = 1;
                user.getItemCooldownManager().set(this, 100);
            }
            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    SoundEvents.ENTITY_WIND_CHARGE_THROW,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            user.getStackInHand(hand).damage(1, user, EquipmentSlot.MAINHAND);

            if (counter == 2) {
                counter = 0;
            }
        }
        ItemStack itemStack = user.getStackInHand(hand);
        return  TypedActionResult.success(itemStack, world.isClient);
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int chanceForWindExplosion = MathHelper.nextInt(Random.createLocal(), 1, 2);
        if (chanceForWindExplosion == 2) {
            double x = target.getX();
            double y = target.getY() + (double) (target.getHeight() / 2.0F);
            double z = target.getZ();
            float power = 1.0F + target.getRandom().nextFloat() * 2.0F;
            if (target.getWorld() instanceof ServerWorld world) {
                world.createExplosion(
                        target,
                        null,
                        AbstractWindChargeEntity.EXPLOSION_BEHAVIOR,
                        x, y, z, power,
                        false,
                        World.ExplosionSourceType.TRIGGER,
                        ParticleTypes.GUST_EMITTER_SMALL,
                        ParticleTypes.GUST_EMITTER_LARGE,
                        SoundEvents.ENTITY_BREEZE_WIND_BURST
                );
                target.damage(target.getDamageSources().magic(), 2);
                super.postDamageEntity(stack, target, attacker);
            }
        }
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        Random random = world.getRandom();
        double x = random.nextTriangular(direction.getOffsetX(), 0.11485000000000001);
        double y = random.nextTriangular(direction.getOffsetY(), 0.11485000000000001);
        double z = random.nextTriangular(direction.getOffsetZ(), 0.11485000000000001);
        Vec3d vec3d = new Vec3d(x, y, z);
        WindChargeEntity windChargeEntity = new WindChargeEntity(world, pos.getX(), pos.getY(), pos.getZ(), vec3d);
        windChargeEntity.setVelocity(vec3d);
        return windChargeEntity;
    }

    @Override
    public void initializeProjectile(ProjectileEntity entity, double x, double y, double z, float power, float uncertainty) {
        ProjectileItem.super.initializeProjectile(entity, x, y, z, power, uncertainty);
    }
}

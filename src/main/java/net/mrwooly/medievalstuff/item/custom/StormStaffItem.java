package net.mrwooly.medievalstuff.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ProjectileItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class StormStaffItem extends Item implements ProjectileItem {

    public StormStaffItem(Item.Settings settings) {
        super(settings);
    }
    public static AttributeModifiersComponent createAttributeModifiersComponent() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 5.5, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.6F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(ATTACK_RANGE, 0.5F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE,
                        new EntityAttributeModifier(BLOCK_RANGE, 0.5F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ).build();
    }
    public static final Identifier ATTACK_RANGE = Identifier.of("attack_range");
    public static final Identifier BLOCK_RANGE = Identifier.of("block_range");

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2);
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.BREEZE_ROD);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 1200;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            int useTicks = this.getMaxUseTime(stack, user) - remainingUseTicks;
            if (!world.isClient) {
                if (useTicks <= 300) {
                    if (useTicks >= 15) {
                        stack.damage(1, playerEntity, LivingEntity.getSlotForHand(user.getActiveHand()));

                        WindChargeEntity windChargeEntity1 = new WindChargeEntity((PlayerEntity) user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                        windChargeEntity1.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.0F, 1.0F);
                        world.spawnEntity(windChargeEntity1);

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
                        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                        playerEntity.getItemCooldownManager().set(this, 75);
                    }
                    if (useTicks >= 20) {
                        stack.damage(1, playerEntity, LivingEntity.getSlotForHand(user.getActiveHand()));

                        WindChargeEntity windChargeEntity2 = new WindChargeEntity((PlayerEntity) user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                        windChargeEntity2.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.25F, 1.5F);
                        world.spawnEntity(windChargeEntity2);

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
                        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                        playerEntity.getItemCooldownManager().set(this, 90);
                    }
                    if (useTicks >= 25) {
                        stack.damage(1, playerEntity, LivingEntity.getSlotForHand(user.getActiveHand()));

                        int chanceForAdditionalWindCharge1 = MathHelper.nextInt(Random.createLocal(), 1, 3);
                        if (chanceForAdditionalWindCharge1 == 1) {
                            WindChargeEntity windChargeEntity3 = new WindChargeEntity((PlayerEntity) user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                            windChargeEntity3.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.5F, 1.0F);
                            world.spawnEntity(windChargeEntity3);

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
                            playerEntity.getItemCooldownManager().set(this, 115);
                        } else {
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
                            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                            playerEntity.getItemCooldownManager().set(this, 105);
                        }
                        if (useTicks >= 30) {
                            stack.damage(1, playerEntity, LivingEntity.getSlotForHand(user.getActiveHand()));

                            WindChargeEntity windChargeEntity4 = new WindChargeEntity((PlayerEntity) user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                            windChargeEntity4.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.75F, 1.0F);
                            world.spawnEntity(windChargeEntity4);

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
                            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                            playerEntity.getItemCooldownManager().set(this, 120);
                        }
                        if (useTicks >= 35) {
                            stack.damage(1, playerEntity, LivingEntity.getSlotForHand(user.getActiveHand()));

                            WindChargeEntity windChargeEntity5 = new WindChargeEntity((PlayerEntity) user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                            windChargeEntity5.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3.0F, 1.5F);
                            world.spawnEntity(windChargeEntity5);

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
                            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                            playerEntity.getItemCooldownManager().set(this, 135);
                        }
                        if (useTicks >= 40) {
                            stack.damage(1, playerEntity, LivingEntity.getSlotForHand(user.getActiveHand()));

                            int chanceForAdditionalWindCharge2 = MathHelper.nextInt(Random.createLocal(), 1, 3);
                            if (chanceForAdditionalWindCharge2 == 1) {
                                WindChargeEntity windChargeEntity6 = new WindChargeEntity((PlayerEntity) user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                                windChargeEntity6.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3.25F, 1.0F);
                                world.spawnEntity(windChargeEntity6);

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
                                playerEntity.getItemCooldownManager().set(this, 160);
                            } else {
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
                                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                                playerEntity.getItemCooldownManager().set(this, 150);
                            }
                        }
                    }
                } else {
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.WIND_CHARGED, 200, 0, true, true));
                    user.damage(playerEntity.getDamageSources().magic(), 2.0F);
                    double x = playerEntity.getX();
                    double y = playerEntity.getY() + (double) (playerEntity.getHeight() / 2.0F);
                    double z = playerEntity.getZ();
                    float power = 7.5F + playerEntity.getRandom().nextFloat() * 2.0F;
                    world.createExplosion(
                            playerEntity,
                            null,
                            AbstractWindChargeEntity.EXPLOSION_BEHAVIOR,
                            x, y, z, power,
                            false,
                            World.ExplosionSourceType.TRIGGER,
                            ParticleTypes.GUST_EMITTER_SMALL,
                            ParticleTypes.GUST_EMITTER_LARGE,
                            SoundEvents.ENTITY_BREEZE_WIND_BURST
                    );
                    playerEntity.getItemCooldownManager().set(this, 250);
                }
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if(isAboutToBreak(itemStack)) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    private static boolean isAboutToBreak(ItemStack stack) {
        return stack.getDamage() >= stack.getMaxDamage() - 1;
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        Random random = world.getRandom();
        double x = random.nextTriangular(direction.getOffsetX(), 0.11485000000000001);
        double y = random.nextTriangular(direction.getOffsetY() - 0.5, 0.11485000000000001);
        double z = random.nextTriangular(direction.getOffsetZ(), 0.11485000000000001);
        Vec3d vec3d = new Vec3d(x, y, z);
        WindChargeEntity windChargeEntity = new WindChargeEntity(world, pos.getX(), pos.getY(), pos.getZ(), vec3d);
        windChargeEntity.setVelocity(vec3d);
        return windChargeEntity;
    }

    @Override
    public void initializeProjectile(ProjectileEntity entity, double x, double y, double z, float power, float uncertainty) {
    }
}
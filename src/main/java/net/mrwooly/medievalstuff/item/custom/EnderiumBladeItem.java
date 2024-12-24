package net.mrwooly.medievalstuff.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class EnderiumBladeItem extends CustomSweepWeaponItem {
    public EnderiumBladeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    int counter = 1;

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (counter == 4) {
            World world = target.getWorld();
            if (!world.isClient) {
                for (int i = 0; i < 32; i++) {
                    double xHelper = MathHelper.nextDouble(Random.create(), 1, 3);
                    double yHelper = MathHelper.nextDouble(Random.create(), 1, 3);
                    double zHelper = MathHelper.nextDouble(Random.create(), 1, 3);

                    double originalTargetX = target.getX();
                    double originalTargetY = target.getY();
                    double originalTargetZ = target.getZ();

                    double newTargetX;
                    double newRawTargetY;
                    double newTargetY;
                    double newTargetZ;

                    int xSelector = MathHelper.nextInt(Random.create(), 0, 1);
                    if (xSelector == 0) {
                        newTargetX = originalTargetX + xHelper;
                    } else {
                        newTargetX = originalTargetX - xHelper;
                    }

                    int ySelector = MathHelper.nextInt(Random.create(), 0, 1);
                    if (ySelector == 0) {
                        newRawTargetY = originalTargetY + yHelper;
                    } else {
                        newRawTargetY = originalTargetY - yHelper;
                    }
                    newTargetY = MathHelper.clamp(
                            newRawTargetY,
                            world.getBottomY(),
                            world.getBottomY() + ((ServerWorld) world).getLogicalHeight() - 1
                    );

                    int zSelector = MathHelper.nextInt(Random.create(), 0, 1);
                    if (zSelector == 0) {
                        newTargetZ = originalTargetZ + zHelper;
                    } else {
                        newTargetZ = originalTargetZ - zHelper;
                    }

                    if (target.hasVehicle()) {
                        target.stopRiding();
                    }
                    if (target.isUsingItem()) {
                        target.stopUsingItem();
                    }

                    Vec3d vec3d = target.getPos();
                    if (target.teleport(newTargetX, newTargetY, newTargetZ, true)) {
                        world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(target));
                        SoundEvent soundEvent;
                        SoundCategory soundCategory;

                        if (target instanceof FoxEntity) {
                            soundEvent = SoundEvents.ENTITY_FOX_TELEPORT;
                            soundCategory = SoundCategory.NEUTRAL;
                        } else if (target instanceof ShulkerEntity) {
                            soundEvent = SoundEvents.ENTITY_SHULKER_TELEPORT;
                            soundCategory = SoundCategory.HOSTILE;
                        } else if (target instanceof EndermanEntity) {
                            soundEvent = SoundEvents.ENTITY_ENDERMAN_TELEPORT;
                            soundCategory = SoundCategory.HOSTILE;
                        } else {
                            soundEvent = SoundEvents.ENTITY_PLAYER_TELEPORT;
                            soundCategory = SoundCategory.PLAYERS;
                        }
                        world.playSound(null, originalTargetX, originalTargetY, originalTargetZ, soundEvent, soundCategory);
                        break;
                    }
                }
                target.damage(target.getDamageSources().magic(), 3);
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0), attacker);
            }
            counter = 1;
        } else if (counter == 3) {
            counter = 4;
        } else if (counter == 2) {
            counter = 3;
        } else if (counter == 1) {
            counter = 2;
        }
        super.postDamageEntity(stack, target, attacker);
    }
}

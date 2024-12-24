package net.mrwooly.medievalstuff.item.custom;

import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LodestoneTrackerComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.mrwooly.medievalstuff.MedievalStuff;
import net.mrwooly.medievalstuff.item.ModItems;

import java.util.Optional;

public class RitualDaggerItem extends CustomSweepWeaponItem {
    public RitualDaggerItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    int teleportToBlockX;
    int teleportToBlockY;
    int teleportToBlockZ;
    int toDefault;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 36000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.contains(DataComponentTypes.LODESTONE_TRACKER) || super.hasGlint(stack);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            int useTicks = this.getMaxUseTime(stack, playerEntity) - remainingUseTicks;
            if (!world.isClient) {
                if (useTicks >= 30) {
                    double originalUserX = user.getX();
                    double originalUserY = user.getY();
                    double originalUserZ = user.getZ();

                    double distanceFromPlayerToBlockX = originalUserX - teleportToBlockX;
                    if (distanceFromPlayerToBlockX <= 500) {

                        double distanceFromPlayerToBlockY = originalUserY - teleportToBlockY;
                        if (distanceFromPlayerToBlockY <= 500) {

                            double distanceFromPlayerToBlockZ = originalUserZ - teleportToBlockZ;
                            if (distanceFromPlayerToBlockZ <= 500) {

                                if (stack.contains(DataComponentTypes.LODESTONE_TRACKER)) {

                                    if (world.getBlockState(new BlockPos(teleportToBlockX, teleportToBlockY, teleportToBlockZ)).isOf(Blocks.LODESTONE)) {
                                        for (int i = 0; i < 64; i++) {
                                            double xHelper = MathHelper.nextDouble(Random.create(), 1, 2);
                                            double yHelper = MathHelper.nextDouble(Random.create(), 1, 2);
                                            double zHelper = MathHelper.nextDouble(Random.create(), 1, 2);

                                            double newUserX;
                                            double newRawUserY;
                                            double newUserY;
                                            double newUserZ;

                                            int xSelector = MathHelper.nextInt(Random.create(), 0, 1);
                                            if (xSelector == 0) {
                                                newUserX = teleportToBlockX + xHelper;
                                            } else {
                                                newUserX = teleportToBlockX - xHelper;
                                            }

                                            int ySelector = MathHelper.nextInt(Random.create(), 0, 1);
                                            if (ySelector == 0) {
                                                newRawUserY = teleportToBlockY + yHelper;
                                            } else {
                                                newRawUserY = teleportToBlockY - yHelper;
                                            }
                                            newUserY = MathHelper.clamp(
                                                    newRawUserY,
                                                    world.getBottomY(),
                                                    world.getBottomY() + ((ServerWorld) world).getLogicalHeight() - 1
                                            );

                                            int zSelector = MathHelper.nextInt(Random.create(), 0, 1);
                                            if (zSelector == 0) {
                                                newUserZ = teleportToBlockZ + zHelper;
                                            } else {
                                                newUserZ = teleportToBlockZ - zHelper;
                                            }

                                            if (user.hasVehicle()) {
                                                user.stopRiding();
                                            }

                                            Vec3d vec3d = user.getPos();
                                            if (user.teleport(newUserX, newUserY, newUserZ, true)) {
                                                world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
                                                SoundEvent soundEventFromPlace = SoundEvents.ENTITY_PLAYER_TELEPORT;
                                                SoundEvent soundEventToPlace = SoundEvents.ENTITY_PLAYER_TELEPORT;
                                                SoundCategory soundCategory = SoundCategory.PLAYERS;

                                                int soundSelectorFromPlaceX1 = MathHelper.nextInt(Random.create(), 1, 2);
                                                int soundSelectorFromPlaceY1 = MathHelper.nextInt(Random.create(), 1, 2);
                                                int soundSelectorFromPlaceZ1 = MathHelper.nextInt(Random.create(), 1, 2);

                                                int soundSelectorFromPlaceX2 = MathHelper.nextInt(Random.create(), 1, 2);
                                                int soundSelectorFromPlaceY2 = MathHelper.nextInt(Random.create(), 1, 2);
                                                int soundSelectorFromPlaceZ2 = MathHelper.nextInt(Random.create(), 1, 2);

                                                int soundSelectorToPlaceX1 = MathHelper.nextInt(Random.create(), 1, 2);
                                                int soundSelectorToPlaceY1 = MathHelper.nextInt(Random.create(), 1, 2);
                                                int soundSelectorToPlaceZ1 = MathHelper.nextInt(Random.create(), 1, 2);

                                                int soundSelectorToPlaceX2 = MathHelper.nextInt(Random.create(), 1, 2);
                                                int soundSelectorToPlaceY2 = MathHelper.nextInt(Random.create(), 1, 2);
                                                int soundSelectorToPlaceZ2 = MathHelper.nextInt(Random.create(), 1, 2);

                                                int soundHelperFromPlaceX1 = MathHelper.nextInt(Random.create(), 2, 3);
                                                int soundHelperFromPlaceY1 = MathHelper.nextInt(Random.create(), 2, 3);
                                                int soundHelperFromPlaceZ1 = MathHelper.nextInt(Random.create(), 2, 3);

                                                int soundHelperFromPlaceX2 = MathHelper.nextInt(Random.create(), 2, 3);
                                                int soundHelperFromPlaceY2 = MathHelper.nextInt(Random.create(), 2, 3);
                                                int soundHelperFromPlaceZ2 = MathHelper.nextInt(Random.create(), 2, 3);

                                                int soundHelperToPlaceX1 = MathHelper.nextInt(Random.create(), 2, 3);
                                                int soundHelperToPlaceY1 = MathHelper.nextInt(Random.create(), 2, 3);
                                                int soundHelperToPlaceZ1 = MathHelper.nextInt(Random.create(), 2, 3);

                                                int soundHelperToPlaceX2 = MathHelper.nextInt(Random.create(), 2, 3);
                                                int soundHelperToPlaceY2 = MathHelper.nextInt(Random.create(), 2, 3);
                                                int soundHelperToPlaceZ2 = MathHelper.nextInt(Random.create(), 2, 3);

                                                int soundPosX1;
                                                int soundPosY1;
                                                int soundPosZ1;

                                                int soundPosX2;
                                                int soundPosY2;
                                                int soundPosZ2;

                                                int soundPosX3;
                                                int soundPosY3;
                                                int soundPosZ3;

                                                int soundPosX4;
                                                int soundPosY4;
                                                int soundPosZ4;

                                                BlockPos normalSoundPositionFromPlace = new BlockPos((int) originalUserX, (int) originalUserY, (int) originalUserZ);
                                                world.playSound(null, normalSoundPositionFromPlace, soundEventFromPlace, soundCategory, 3.0F, 0.9F);

                                                if (soundSelectorFromPlaceX1 == 1) {
                                                    soundPosX1 = (int) (originalUserX + soundHelperFromPlaceX1);
                                                } else {
                                                    soundPosX1 = (int) (originalUserX - soundHelperFromPlaceX1);
                                                }
                                                if (soundSelectorFromPlaceY1 == 1) {
                                                    soundPosY1 = (int) (originalUserY + soundHelperFromPlaceY1);
                                                } else {
                                                    soundPosY1 = (int) (originalUserY - soundHelperFromPlaceY1);
                                                }
                                                if (soundSelectorFromPlaceZ1 == 1) {
                                                    soundPosZ1 = (int) (originalUserZ + soundHelperFromPlaceZ1);
                                                } else {
                                                    soundPosZ1 = (int) (originalUserZ - soundHelperFromPlaceZ1);
                                                }
                                                BlockPos additionalSoundPositionFromPlace1 = new BlockPos(soundPosX1, soundPosY1, soundPosZ1);
                                                world.playSound(null, additionalSoundPositionFromPlace1, soundEventFromPlace, soundCategory, 2.75F, 0.7F);

                                                if (soundSelectorFromPlaceX2 == 1) {
                                                    soundPosX2 = (int) (originalUserX + soundHelperFromPlaceX2);
                                                } else {
                                                    soundPosX2 = (int) (originalUserX - soundHelperFromPlaceX2);
                                                }
                                                if (soundSelectorFromPlaceY2 == 1) {
                                                    soundPosY2 = (int) (originalUserY + soundHelperFromPlaceY2);
                                                } else {
                                                    soundPosY2 = (int) (originalUserY - soundHelperFromPlaceY2);
                                                }
                                                if (soundSelectorFromPlaceZ2 == 1) {
                                                    soundPosZ2 = (int) (originalUserZ + soundHelperFromPlaceZ2);
                                                } else {
                                                    soundPosZ2 = (int) (originalUserZ - soundHelperFromPlaceZ2);
                                                }
                                                BlockPos additionalSoundPositionFromPlace2 = new BlockPos(soundPosX2, soundPosY2, soundPosZ2);
                                                world.playSound(null, additionalSoundPositionFromPlace2, soundEventFromPlace, soundCategory, 2.5F, 0.5F);


                                                BlockPos normalSoundPositionToPlace = new BlockPos((int) originalUserX, (int) originalUserY, (int) originalUserZ);
                                                world.playSound(null, normalSoundPositionToPlace, soundEventToPlace, soundCategory, 3.0F, 1.0F);

                                                if (soundSelectorToPlaceX1 == 1) {
                                                    soundPosX3 = (int) (originalUserX + soundHelperToPlaceX1);
                                                } else {
                                                    soundPosX3 = (int) (originalUserX - soundHelperToPlaceX1);
                                                }
                                                if (soundSelectorToPlaceY1 == 1) {
                                                    soundPosY3 = (int) (originalUserY + soundHelperToPlaceY1);
                                                } else {
                                                    soundPosY3 = (int) (originalUserY - soundHelperToPlaceY1);
                                                }
                                                if (soundSelectorToPlaceZ1 == 1) {
                                                    soundPosZ3 = (int) (originalUserZ + soundHelperToPlaceZ1);
                                                } else {
                                                    soundPosZ3 = (int) (originalUserZ - soundHelperToPlaceZ1);
                                                }
                                                BlockPos additionalSoundPositionToPlace1 = new BlockPos(soundPosX3, soundPosY3, soundPosZ3);
                                                world.playSound(null, additionalSoundPositionToPlace1, soundEventToPlace, soundCategory, 2.75F, 1.25F);

                                                if (soundSelectorToPlaceX2 == 1) {
                                                    soundPosX4 = (int) (originalUserX + soundHelperToPlaceX2);
                                                } else {
                                                    soundPosX4 = (int) (originalUserX - soundHelperToPlaceX2);
                                                }
                                                if (soundSelectorToPlaceY2 == 1) {
                                                    soundPosY4 = (int) (originalUserY + soundHelperToPlaceY2);
                                                } else {
                                                    soundPosY4 = (int) (originalUserY - soundHelperToPlaceY2);
                                                }
                                                if (soundSelectorToPlaceZ2 == 1) {
                                                    soundPosZ4 = (int) (originalUserZ + soundHelperToPlaceZ2);
                                                } else {
                                                    soundPosZ4 = (int) (originalUserZ - soundHelperToPlaceZ2);
                                                }
                                                BlockPos additionalSoundPositionToPlace2 = new BlockPos(soundPosX4, soundPosY4, soundPosZ4);
                                                world.playSound(null, additionalSoundPositionToPlace2, soundEventToPlace, soundCategory, 2.5F, 1.5F);

                                                break;
                                            }
                                            ((PlayerEntity) user).getItemCooldownManager().set(this, 30);


                                        }
                                    } else {
                                        ItemStack itemStack1 = user.getStackInHand(user.getActiveHand());
                                        if (itemStack1.isOf(ModItems.RITUAL_DAGGER)) {
                                            ItemStack itemStack2 = itemStack1.copyComponentsToNewStack(ModItems.RITUAL_DAGGER, 1);
                                            itemStack1.decrement(1);
                                            itemStack2.set(DataComponentTypes.LODESTONE_TRACKER, null);

                                            teleportToBlockX = toDefault;
                                            teleportToBlockY = toDefault;
                                            teleportToBlockZ = toDefault;

                                            if (!playerEntity.getInventory().insertStack(itemStack2)) {
                                                playerEntity.dropItem(itemStack2, false);
                                            }
                                        }
                                    }
                                }
                                float damage = (float) (user.getMaxHealth() * 0.7);
                                user.damage(user.getDamageSources().create(RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
                                        Identifier.of(MedievalStuff.MOD_ID, "player_ritual_dagger_ability_damage_source"))), damage);

                                stack.damage(16, user, EquipmentSlot.MAINHAND);
                            }
                        }
                    }
                }
            }
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();

        if (!world.getBlockState(blockPos).isOf(Blocks.LODESTONE)) {
            return super.useOnBlock(context);
        } else {
            world.playSound(null, blockPos, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.PLAYERS, 1.0F, 1.0F);
            PlayerEntity playerEntity = context.getPlayer();
            ItemStack itemStack1 = context.getStack();
            assert playerEntity != null;
            boolean bl = !playerEntity.isInCreativeMode() && itemStack1.getCount() == 1;
            LodestoneTrackerComponent lodestoneTrackerComponent = new LodestoneTrackerComponent(Optional.of(GlobalPos.create(world.getRegistryKey(), blockPos)), true);
            if (bl) {
                itemStack1.set(DataComponentTypes.LODESTONE_TRACKER, lodestoneTrackerComponent);
            } else {
                ItemStack itemStack2 = itemStack1.copyComponentsToNewStack(ModItems.RITUAL_DAGGER, 1);
                itemStack1.decrementUnlessCreative(1, playerEntity);
                itemStack2.set(DataComponentTypes.LODESTONE_TRACKER, lodestoneTrackerComponent);

                if (!playerEntity.getInventory().insertStack(itemStack2)) {
                    playerEntity.dropItem(itemStack2, false);
                }

                teleportToBlockX = blockPos.getX();
                teleportToBlockY = blockPos.getY();
                teleportToBlockZ = blockPos.getZ();
            }
        }
        return super.useOnBlock(context);
    }
}

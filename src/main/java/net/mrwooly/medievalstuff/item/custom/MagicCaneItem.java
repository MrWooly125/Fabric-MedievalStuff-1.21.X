package net.mrwooly.medievalstuff.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.ProjectileItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.mrwooly.medievalstuff.entity.custom.MagicSoulFireChargeTier1ProjectileEntity;
import net.mrwooly.medievalstuff.entity.custom.MagicSoulFireChargeTier3ProjectileEntity;

public class MagicCaneItem extends Item implements ProjectileItem {

    public MagicCaneItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
        if (!world.isClient) {
            PlayerEntity player = (PlayerEntity) user;
            int useTimeInTicks = this.getMaxUseTime(stack, user) - remainingUseTicks;

            int userX = (int) user.getX();
            int userY = (int) user.getY();
            int userZ = (int) user.getZ();
            BlockPos intUserBlockPos = new BlockPos(userX, userY, userZ);

            SoundEvent useSoundEvent = SoundEvents.ITEM_FIRECHARGE_USE;
            SoundCategory useSoundCategory = SoundCategory.PLAYERS;

            float useSoundVolume = 0.75F;

            float basicUseSoundPitch = 1F;
            int additionalUseSoundPitchHelper = MathHelper.nextInt(Random.create(), 1, 2);
            float additionalUseSoundPitch = MathHelper.nextFloat(Random.create(), 0F, 0.25F);
            float useSoundPitch;
            if (additionalUseSoundPitchHelper == 1) {
                useSoundPitch = basicUseSoundPitch + additionalUseSoundPitch;
            } else {
                useSoundPitch = basicUseSoundPitch - additionalUseSoundPitch;
            }

            int useItemDamage = 1;
            int tier1CooldownInTicks = 40;
            int tier2CooldownInTicks = 50;
            int tier3CooldownInTicks = 60;


            if (useTimeInTicks < 25) {
                MagicSoulFireChargeTier1ProjectileEntity magicSoulFireChargeProjectileTier1 =
                        new MagicSoulFireChargeTier1ProjectileEntity((PlayerEntity) user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                magicSoulFireChargeProjectileTier1.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.5F, 1.5F);
                world.spawnEntity(magicSoulFireChargeProjectileTier1);


                player.getItemCooldownManager().set(this, tier1CooldownInTicks);
                player.getStackInHand(Hand.MAIN_HAND).damage(useItemDamage, player, EquipmentSlot.MAINHAND);
                world.playSound(null, intUserBlockPos, useSoundEvent, useSoundCategory, useSoundVolume, useSoundPitch);

            } else if (useTimeInTicks > 25 && useTimeInTicks < 50) {
                MagicSoulFireChargeTier1ProjectileEntity magicSoulFireChargeProjectileTier2 =
                        new MagicSoulFireChargeTier1ProjectileEntity((PlayerEntity) user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                magicSoulFireChargeProjectileTier2.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3.0F, 1.5F);
                world.spawnEntity(magicSoulFireChargeProjectileTier2);

                player.getItemCooldownManager().set(this, tier2CooldownInTicks);
                player.getStackInHand(Hand.MAIN_HAND).damage(useItemDamage, player, EquipmentSlot.MAINHAND);
                world.playSound(null, intUserBlockPos, useSoundEvent, useSoundCategory, useSoundVolume, useSoundPitch);

            } else if (useTimeInTicks >= 50) {
                MagicSoulFireChargeTier3ProjectileEntity magicSoulFireChargeProjectileTier3 =
                        new MagicSoulFireChargeTier3ProjectileEntity((PlayerEntity) user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                magicSoulFireChargeProjectileTier3.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3.0F, 1.5F);
                world.spawnEntity(magicSoulFireChargeProjectileTier3);

                player.getItemCooldownManager().set(this, tier3CooldownInTicks);
                player.getStackInHand(Hand.MAIN_HAND).damage(useItemDamage, player, EquipmentSlot.MAINHAND);

                world.playSound(null, intUserBlockPos, useSoundEvent, useSoundCategory, useSoundVolume, useSoundPitch);
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxCount() {
        return 1;
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        Random random = world.getRandom();
        double x = random.nextTriangular(direction.getOffsetX(), 0.11485000000000001);
        double y = random.nextTriangular(direction.getOffsetY(), 0.11485000000000001);
        double z = random.nextTriangular(direction.getOffsetZ(), 0.11485000000000001);
        Vec3d vec3d = new Vec3d(x, y, z);

        MagicSoulFireChargeTier1ProjectileEntity magicSoulFireChargeTier1 = new MagicSoulFireChargeTier1ProjectileEntity(world, pos.getX(), pos.getY(), pos.getZ(), vec3d);
        magicSoulFireChargeTier1.setVelocity(vec3d);

        return magicSoulFireChargeTier1;
    }
}

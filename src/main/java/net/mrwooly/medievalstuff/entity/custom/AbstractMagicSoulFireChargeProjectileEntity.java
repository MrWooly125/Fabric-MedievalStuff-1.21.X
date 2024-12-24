package net.mrwooly.medievalstuff.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.ExplosionBehavior;

import java.util.Optional;
import java.util.function.Function;

public abstract class AbstractMagicSoulFireChargeProjectileEntity extends ExplosiveProjectileEntity {
    public float damageToTargets;
    public int targetBurningTimeInTicks;

    public AbstractMagicSoulFireChargeProjectileEntity(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.accelerationPower = 0;
    }

    public AbstractMagicSoulFireChargeProjectileEntity(EntityType<? extends ExplosiveProjectileEntity> type, Entity owner, World world, double x, double y, double z) {
        super(type, x, y, z, world);
        this.setOwner(owner);
        this.accelerationPower = 0;
    }

    AbstractMagicSoulFireChargeProjectileEntity(EntityType<? extends AbstractWindChargeEntity> entityType, double d, double e, double f, Vec3d vec3d, World world) {
        super(entityType, d, e, f, vec3d, world);
        this.accelerationPower = 0.0;
    }

    public static final ExplosionBehavior EXPLOSION_BEHAVIOR = new AdvancedExplosionBehavior(
            false, true, Optional.empty(), Registries.BLOCK.getEntryList(BlockTags.AIR).map(Function.identity()));

    public float setDamageToTargets(float damage) {
        return damageToTargets = damage;
    }

    public float setTargetBurningTimeInTicks(int ticks) {
        return targetBurningTimeInTicks = ticks;
    }

    protected abstract void createExplosion(Vec3d position);

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient) {
            Entity target = entityHitResult.getEntity();

            if (target instanceof LivingEntity livingTarget) {
                livingTarget.setOnFireForTicks(targetBurningTimeInTicks);
                livingTarget.damage(this.getDamageSources().magic(), damageToTargets);
            }

            this.createExplosion(this.getPos());
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.getWorld().isClient) {
            Vec3i vec3i = blockHitResult.getSide().getVector();
            Vec3d vec3d1 = Vec3d.of(vec3i).multiply(0.15, 0.15, 0.15);
            Vec3d vec3d2 = blockHitResult.getPos().add(vec3d1);
            this.createExplosion(vec3d2);
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.discard();
        }
    }

    @Override
    protected boolean isBurning() {
        return false;
    }

    @Override
    protected float getDrag() {
        return 1.0F;
    }

    @Override
    protected float getDragInWater() {
        return this.getDrag();
    }

    @Override
    public void tick() {
        if (!this.getWorld().isClient && this.getBlockY() > this.getWorld().getTopY() + 10) {
            this.createExplosion(this.getPos());
            this.discard();
        } else {
            super.tick();
        }
    }
}

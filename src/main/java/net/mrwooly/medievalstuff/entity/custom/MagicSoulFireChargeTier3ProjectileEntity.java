package net.mrwooly.medievalstuff.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.ExplosionBehavior;

import java.util.Optional;
import java.util.function.Function;

public class MagicSoulFireChargeTier3ProjectileEntity extends AbstractMagicSoulFireChargeProjectileEntity{
    public float EXPLOSION_POWER = 0.75F;
    public float DAMAGE_TO_TARGETS = 3F;
    public int TARGET_BURNING_TIME = 60;
    private float rotation;

    private static final ExplosionBehavior EXPLOSION_BEHAVIOR =
            new AdvancedExplosionBehavior(false, true, Optional.of(0.5F), Registries.BLOCK.getEntryList(BlockTags.AIR).map(Function.identity()));

    public MagicSoulFireChargeTier3ProjectileEntity(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public MagicSoulFireChargeTier3ProjectileEntity(PlayerEntity player, World world, double x, double y, double z) {
        super(EntityType.FIREBALL, player, world, x, y, z);
    }

    public MagicSoulFireChargeTier3ProjectileEntity(EntityType<? extends ExplosiveProjectileEntity> type, Entity owner, World world, double x, double y, double z) {
        super(type, owner, world, x, y, z);
    }

    public MagicSoulFireChargeTier3ProjectileEntity(World world, double x, double y, double z, Vec3d velocity) {
        super(EntityType.WIND_CHARGE, x, y, z, velocity, world);
    }

    public float setDamageToTargets(float damage) {
        return damageToTargets = DAMAGE_TO_TARGETS;
    }

    @Override
    public float setTargetBurningTimeInTicks(int ticks) {
        return super.setTargetBurningTimeInTicks(TARGET_BURNING_TIME);
    }

    @Override
    protected void createExplosion(Vec3d position) {
        this.getWorld()
                .createExplosion(
                        this,
                        null,
                        EXPLOSION_BEHAVIOR,
                        position.getX(),
                        position.getY(),
                        position.getZ(),
                        EXPLOSION_POWER,
                        false,
                        World.ExplosionSourceType.TRIGGER,
                        ParticleTypes.GUST_EMITTER_SMALL,
                        ParticleTypes.ELECTRIC_SPARK,
                        SoundEvents.ENTITY_GENERIC_EXPLODE
                );
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if (rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }
}

package ru.pinkgoosik.mods.figurkiechpochmak.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import ru.pinkgoosik.mods.figurkiechpochmak.item.BouncyMiniBomb;


@SuppressWarnings("EntityConstructor") //Тупой плагин mc dev
public class BouncyMiniBombEntity extends ThrownItemEntity{
    private static final TrackedData<Integer> NUMBER_OF_JUMPS = DataTracker.registerData(BouncyMiniBombEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Float> PLAYER_YAW = DataTracker.registerData(BouncyMiniBombEntity.class, TrackedDataHandlerRegistry.FLOAT);

    public BouncyMiniBombEntity(EntityType<? extends BouncyMiniBombEntity> entityType, World world) {
        super(entityType, world);
    }
    public BouncyMiniBombEntity(World world, LivingEntity owner) {
        super(EntityType.SNOWBALL, owner, world);
    }

    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }

    protected void initDataTracker() {
        this.dataTracker.startTracking(NUMBER_OF_JUMPS, 0);
        this.dataTracker.startTracking(PLAYER_YAW, BouncyMiniBomb.getPlayerYaw());
        super.initDataTracker();
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 3) {
            for(int i = 0; i < 8; ++i) {
                this.world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
            }
        }
    }
    protected void onCollision(HitResult hitResult) {
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            world.createExplosion(null,hitResult.getPos().getX(), hitResult.getPos().getY(), hitResult.getPos().getZ(), 1.0F, Explosion.DestructionType.NONE);
            this.world.sendEntityStatus(this, (byte)3);
            this.remove();
        }
        if (!this.world.isClient){
            this.dataTracker.set(NUMBER_OF_JUMPS, this.dataTracker.get(NUMBER_OF_JUMPS) + 1);

            if (this.dataTracker.get(NUMBER_OF_JUMPS) == 4) {
                world.createExplosion(null, hitResult.getPos().getX(), hitResult.getPos().getY(), hitResult.getPos().getZ(), 1.0F, Explosion.DestructionType.NONE);
                this.world.sendEntityStatus(this, (byte) 3);
                this.remove();
            } else {
                world.playSound(null, hitResult.getPos().getX(), hitResult.getPos().getY(), hitResult.getPos().getZ(), SoundEvents.ENTITY_SLIME_JUMP_SMALL, SoundCategory.AMBIENT, 1F, 1F);
                this.world.sendEntityStatus(this, (byte) 3);
                this.setProperties(this, -60, this.dataTracker.get(PLAYER_YAW), 0.0F, 0.25F, 0.25F);
            }
        }
    }
}
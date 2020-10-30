package ru.pinkgoosik.mods.figurkiechpochmak.figurki;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Random;

public class MiniSomik extends Figurka{
    public MiniSomik(Settings settings){
        super(settings);
    }
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (Figurka.isReceivingTetsaPower(pos, world)) {
            world.removeBlock(pos, false);
            world.createExplosion(null,pos.getX(), pos.getY(), pos.getZ(), 4.0F, Explosion.DestructionType.NONE);
        }
    }
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (Figurka.isReceivingGoosikPower(pos, world)) {
            Random randomX = new Random();
            world.addParticle(ParticleTypes.HEART,pos.getX()+randomX.nextInt(2),pos.getY()
                    +randomX.nextInt(2),pos.getZ()+randomX.nextInt(2),0.0D,0.0D,0.0D);

            world.addParticle(ParticleTypes.HEART,pos.getX()-randomX.nextInt(2),pos.getY()
                    +randomX.nextInt(2),pos.getZ()-randomX.nextInt(2),0.0D,0.0D,0.0D);
        }
    }
}

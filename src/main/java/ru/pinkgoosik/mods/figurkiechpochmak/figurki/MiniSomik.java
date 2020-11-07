package ru.pinkgoosik.mods.figurkiechpochmak.figurki;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import ru.pinkgoosik.mods.figurkiechpochmak.FigurkiEchpochmak;

import java.util.Random;

public class MiniSomik extends Figurka{
    public MiniSomik(Settings settings){
        super(settings);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if(itemStack.getItem()== Items.CAKE){
            if (!world.isClient) {
                world.playSound(null,pos, FigurkiEchpochmak.SOMIK_DISAPPEAR_EVENT, SoundCategory.AMBIENT, 1, 1);
                world.removeBlock(pos,false);
                if (!player.abilities.creativeMode) {
                    itemStack.decrement(1);
                }
            }
            return ActionResult.SUCCESS;
        }else{
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (Figurka.isReceivingSomeonesPower(pos, world,FigurkiEchpochmak.MINI_TETSA)) {
            world.removeBlock(pos, false);
            world.createExplosion(null,pos.getX(), pos.getY(), pos.getZ(), 4.0F, Explosion.DestructionType.NONE);
        }
    }
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (Figurka.isReceivingSomeonesPower(pos, world, FigurkiEchpochmak.MINI_GOOSIK)) {
            Random randomX = new Random();
            world.addParticle(ParticleTypes.HEART,pos.getX()+randomX.nextInt(2),pos.getY()
                    +randomX.nextInt(2),pos.getZ()+randomX.nextInt(2),0.0D,0.0D,0.0D);

            world.addParticle(ParticleTypes.HEART,pos.getX()-randomX.nextInt(2),pos.getY()
                    +randomX.nextInt(2),pos.getZ()-randomX.nextInt(2),0.0D,0.0D,0.0D);
        }
    }
}

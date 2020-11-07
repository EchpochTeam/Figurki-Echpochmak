package ru.pinkgoosik.mods.figurkiechpochmak.figurki;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import ru.pinkgoosik.mods.figurkiechpochmak.FigurkiEchpochmak;

public class MiniTetsa extends Figurka {
    public MiniTetsa(Settings settings) {
        super(settings);
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (Figurka.isReceivingSomeonesPower(pos, world, FigurkiEchpochmak.MINI_SOMIK)) {
            world.removeBlock(fromPos, false);
            world.createExplosion(null,fromPos.getX(), fromPos.getY(), fromPos.getZ(), 4.0F, Explosion.DestructionType.NONE);

        }

    }
}
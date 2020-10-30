package ru.pinkgoosik.mods.figurkiechpochmak;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class FigurkiEchpochmakClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(FigurkiEchpochmak.MINI_SOMIK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FigurkiEchpochmak.MINI_TETSA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FigurkiEchpochmak.MINI_GOOSIK, RenderLayer.getCutout());
    }

}

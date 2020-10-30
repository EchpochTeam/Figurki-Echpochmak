package ru.pinkgoosik.mods.figurkiechpochmak;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ru.pinkgoosik.mods.figurkiechpochmak.figurki.Figurka;
import ru.pinkgoosik.mods.figurkiechpochmak.figurki.MiniGoosik;
import ru.pinkgoosik.mods.figurkiechpochmak.figurki.MiniSomik;
import ru.pinkgoosik.mods.figurkiechpochmak.figurki.MiniTetsa;

public class FigurkiEchpochmak implements ModInitializer {

	public static final Block MINI_SOMIK = new MiniSomik(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).nonOpaque().sounds(BlockSoundGroup.WOOL));
	public static final Block MINI_TETSA = new MiniTetsa(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).nonOpaque().sounds(BlockSoundGroup.WOOL));
	public static final Block MINI_GOOSIK = new MiniGoosik(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).nonOpaque().sounds(BlockSoundGroup.WOOL));
	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier("figurkiechpochmak", "mini_somik"), MINI_SOMIK);
		Registry.register(Registry.ITEM, new Identifier("figurkiechpochmak", "mini_somik"), new BlockItem(MINI_SOMIK, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("figurkiechpochmak", "mini_tetsa"), MINI_TETSA);
		Registry.register(Registry.ITEM, new Identifier("figurkiechpochmak", "mini_tetsa"), new BlockItem(MINI_TETSA, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("figurkiechpochmak", "mini_goosik"), MINI_GOOSIK);
		Registry.register(Registry.ITEM, new Identifier("figurkiechpochmak", "mini_goosik"), new BlockItem(MINI_GOOSIK, new Item.Settings()));


	}
}

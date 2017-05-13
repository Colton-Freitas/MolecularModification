package ossium_team.mmod.common.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import ossium_team.mmod.MolecularModification;
import ossium_team.mmod.common.lib.ConstLib;
import ossium_team.mmod.handler.ModGuiHandler;


public class ClientProxy extends ServerProxy {

    @Override
    public void registerItemRenderer(Item item, int meta, String name) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ConstLib.MODID + ":" + name, "inventory"));
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }
}

package ossium_team.mmod.common.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ServerProxy {

    public void registerItemRenderer (Item item, int meta, String name) {
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }
}

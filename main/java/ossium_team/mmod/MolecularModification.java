package ossium_team.mmod;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import ossium_team.mmod.common.ArmourAbsorbtionListener;
import ossium_team.mmod.common.block.ModBlocks;
import ossium_team.mmod.common.creativeTab.MMTab;
import ossium_team.mmod.common.item.ModItems;
import ossium_team.mmod.common.lib.ConstLib;
import ossium_team.mmod.common.proxy.ServerProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ossium_team.mmod.common.recipies.ModCraftingRecipies;
import ossium_team.mmod.common.recipies.ModReinforcementValues;
import ossium_team.mmod.common.shape.MolecularizableBlockRegistry;
import ossium_team.mmod.handler.ModGuiHandler;

@Mod(modid = ConstLib.MODID, version = ConstLib.VERSION + ConstLib.BUILD, name = ConstLib.Name)
public class MolecularModification
{

    public static ItemArmor.ArmorMaterial reinforcedArmor = EnumHelper.addArmorMaterial("mmReinforcedArmour", "diamond" , 33, new int[]{4, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    @Mod.Instance(ConstLib.MODID)
    public static MolecularModification instance;

    public static MMTab tab = new MMTab();

    @SidedProxy(serverSide = ConstLib.SERVERPROXY, clientSide = ConstLib.CLIENTPROXY)
    public static ServerProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(MolecularModification.instance, new ModGuiHandler());
        ModCraftingRecipies.init();
        ModReinforcementValues.init();
        MolecularizableBlockRegistry.init();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.init();
        ModItems.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ArmourAbsorbtionListener());
    }
}

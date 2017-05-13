package ossium_team.mmod.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderOverworld;
import ossium_team.mmod.MolecularModification;
import ossium_team.mmod.common.Providers.ItemModelProvider;
import ossium_team.mmod.helper.NBTHelper;


public class BaseItem extends Item implements ItemModelProvider {

    protected String name;

    public BaseItem (String name)
    {
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        this.setCreativeTab(MolecularModification.tab);
    }

    @Override
    public void registerItemModel(Item item) {
        MolecularModification.proxy.registerItemRenderer(this, 0, name);
    }

    public BaseItem setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }
}

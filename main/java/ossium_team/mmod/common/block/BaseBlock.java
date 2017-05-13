package ossium_team.mmod.common.block;

import ossium_team.mmod.MolecularModification;
import ossium_team.mmod.common.Providers.ItemModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BaseBlock extends Block implements ItemModelProvider {

    protected String name;

    public BaseBlock (Material material, String name) {
        super(material);
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        this.setCreativeTab(MolecularModification.tab);
        this.setHardness(5.0f);
    }

    @Override
    public void registerItemModel(Item itemBlock) {
        MolecularModification.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    @Override
    public Block setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}

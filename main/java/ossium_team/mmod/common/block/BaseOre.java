package ossium_team.mmod.common.block;

import ossium_team.mmod.common.item.ItemOreDict;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.oredict.OreDictionary;


public class BaseOre extends BaseBlock implements ItemOreDict {

    private String oreDictionaryName;

    public BaseOre (String name, String oreDictionaryName)
    {
        super(Material.ROCK, name);
        this.oreDictionaryName = oreDictionaryName;
        setHardness(3f);
        setResistance(5f);
    }

    public BaseOre setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public void initOreDict() {
        OreDictionary.registerOre(oreDictionaryName, this);
    }
}

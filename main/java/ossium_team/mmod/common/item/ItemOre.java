package ossium_team.mmod.common.item;

import net.minecraftforge.oredict.OreDictionary;


public class ItemOre extends BaseItem implements ItemOreDict {

    private String oreDictName;

    public ItemOre (String name, String oreDictName)
    {
        super(name);
        this.oreDictName = oreDictName;
    }

    @Override
    public void initOreDict() {
        OreDictionary.registerOre(oreDictName, this);
    }
}

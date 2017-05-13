package ossium_team.mmod.common.item.tools;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import ossium_team.mmod.MolecularModification;
import ossium_team.mmod.common.Providers.ItemModelProvider;

public class MMReinforcedArmour extends ItemArmor implements ItemModelProvider {

    String name;

    public MMReinforcedArmour(ArmorMaterial material, String name, EntityEquipmentSlot slot) {
        super(material, 0, slot);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(MolecularModification.tab);
        this.name = name;
    }

    @Override
    public void registerItemModel(Item item) {
        MolecularModification.proxy.registerItemRenderer(this, 0, name);
    }
}

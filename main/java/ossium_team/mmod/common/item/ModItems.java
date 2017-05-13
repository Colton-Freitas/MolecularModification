package ossium_team.mmod.common.item;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.oredict.OreDictionary;
import ossium_team.mmod.MolecularModification;
import ossium_team.mmod.common.Providers.ItemModelProvider;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ossium_team.mmod.common.item.tools.*;

public class ModItems {

    public static DebugItem debugItem;
    public static BaseItem itemStrengthenedIronIngot;
    public static BaseItem itemStrengthenedDiamond;
    public static BaseItem itemConcaveLens;
    public static BaseItem itemConvexLens;
    public static BaseItem itemConcaveMould;
    public static BaseItem itemConvexMould;
    public static BaseItem itemEnlightenedConcaveLens;
    public static BaseItem itemEnlightenedConvexLens;
    public static BaseItem itemEnderGlazedConcaveLens;
    public static BaseItem itemEnderGlazedConvexLens;

    public static MMPickaxe itemMMPickaxe;
    public static MMShovel itemMMShovel;
    public static MMAxe itemMMAxe;
    public static MMSword itemMMSword;

    public static MMReinforcedPickaxe itemReinforcedPickaxe;
    public static MMReinforcedShovel itemReinforcedShovel;
    public static MMReinforcedAxe itemReinforcedAxe;
    public static MMReinforcedSword itemReinforcedSword;

    public static BaseItem itemReinforcedStick;
    public static BaseItem itemReinforcedIron;
    public static BaseItem itemReinforcedDiamond;
    public static BaseItem itemUpgradeCore;
    public static BaseItem itemConductiveIron;

    public static MMArmourStandin itemArmourHead;
    public static MMArmourStandin itemArmourChest;
    public static MMArmourStandin itemArmourLegs;
    public static MMArmourStandin itemArmourBoots;

    public static MMReinforcedArmour reinforcedHead;
    public static MMReinforcedArmour reinforcedBody;
    public static MMReinforcedArmour reinforcedLegs;
    public static MMReinforcedArmour reinforcedBoots;

    public static void init() {
//        debugItem = register(new DebugItem());
        itemStrengthenedIronIngot = register(new BaseItem("item_strengthened_iron"));
        itemStrengthenedDiamond = register(new BaseItem("item_strengthened_diamond"));
        itemConductiveIron = register(new BaseItem("item_conductive_iron"));
        itemConcaveLens = register(new BaseItem("item_concave_lens"));
        itemConvexLens = register(new BaseItem("item_convex_lens"));
        itemEnlightenedConcaveLens = register(new BaseItem("item_enlightened_concave_lens"));
        itemEnlightenedConvexLens = register(new BaseItem("item_enlightened_convex_lens"));
        itemEnderGlazedConcaveLens = register(new BaseItem("item_ender_glazed_concave_lens"));
        itemEnderGlazedConvexLens = register(new BaseItem("item_ender_glazed_convex_lens"));

        itemMMPickaxe = register(new MMPickaxe());
        itemMMShovel = register(new MMShovel());
        itemMMAxe = register(new MMAxe());
        itemMMSword = register(new MMSword());

        itemReinforcedPickaxe = register(new MMReinforcedPickaxe());
        itemReinforcedAxe = register(new MMReinforcedAxe());
        itemReinforcedShovel = register(new MMReinforcedShovel());
        itemReinforcedSword = register(new MMReinforcedSword());

        itemConcaveMould = new BaseItem("item_concave_mould");
        itemConvexMould = new BaseItem("item_convex_mould");
        itemConcaveMould.setMaxStackSize(1);
        itemConvexMould.setMaxStackSize(1);
        register(itemConcaveMould);
        register(itemConvexMould);

        itemReinforcedDiamond = register(new BaseItem("item_reinforced_diamond"));
        itemReinforcedIron = register(new BaseItem("item_reinforced_iron"));
        itemReinforcedStick = register(new BaseItem("item_reinforced_rod"));

        itemUpgradeCore = new BaseItem("item_upgrade_core");
        itemUpgradeCore.setMaxStackSize(1);
        register(itemUpgradeCore);

        itemArmourHead = register(new MMArmourStandin("item_armour_head"));
        itemArmourChest = register(new MMArmourStandin("item_armour_chest"));
        itemArmourLegs = register(new MMArmourStandin("item_armour_legs"));
        itemArmourBoots = register(new MMArmourStandin("item_armour_boots"));

        reinforcedHead = register(new MMReinforcedArmour(MolecularModification.reinforcedArmor, "item_reinforced_head", EntityEquipmentSlot.HEAD));
        reinforcedBody = register(new MMReinforcedArmour(MolecularModification.reinforcedArmor, "item_reinforced_body", EntityEquipmentSlot.CHEST));
        reinforcedLegs = register(new MMReinforcedArmour(MolecularModification.reinforcedArmor, "item_reinforced_legs", EntityEquipmentSlot.LEGS));
        reinforcedBoots = register(new MMReinforcedArmour(MolecularModification.reinforcedArmor, "item_reinforced_boots", EntityEquipmentSlot.FEET));
    }

    private static <Type extends Item> Type register (Type item)
    {
        GameRegistry.register(item);
        if (item instanceof ItemModelProvider)
            ((ItemModelProvider) item).registerItemModel(item);
        if (item instanceof ItemOreDict)
            ((ItemOreDict) item).initOreDict();

        return item;
    }
}

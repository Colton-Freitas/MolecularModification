package ossium_team.mmod.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ossium_team.mmod.common.Providers.ItemModelProvider;
import ossium_team.mmod.common.block.tile.*;
import ossium_team.mmod.common.item.ItemOreDict;
import ossium_team.mmod.common.lib.ConstLib;

public class ModBlocks {

    public static BlockMeltingTable meltingTable;
    public static BlockStrengtheningTable strengtheningTable;
    public static BlockReinforcingTable reinforcingTable;
    public static BlockMolecularCraftingTable molecularCraftingTable;
    public static BlockBlockMoleculizer blockMoleculizer;
    public static BlockMolecularCraftingTableEnergy molecularCraftingTableEnergy;
    public static BlockExaminationTable examinationTable;
    public static BlockArmourApplier armourApplier;
    public static BlockArmourSurgeon armourSurgeon;
    public static BaseBlock strengthenedDiamond;
    public static BaseBlock strengthenedIron;
    public static BaseBlock conductiveIron;


    public static void init() {
        meltingTable = register(new BlockMeltingTable());
        strengtheningTable = register(new BlockStrengtheningTable());
        reinforcingTable = register(new BlockReinforcingTable());
        molecularCraftingTable = register(new BlockMolecularCraftingTable());
        blockMoleculizer = register(new BlockBlockMoleculizer());
        molecularCraftingTableEnergy = register(new BlockMolecularCraftingTableEnergy());
        examinationTable = register(new BlockExaminationTable());
        armourApplier = register(new BlockArmourApplier());
        armourSurgeon = register(new BlockArmourSurgeon());
        strengthenedDiamond = register(new BaseBlock(Material.IRON, "block_strengthened_diamond"));
        strengthenedIron = register(new BaseBlock(Material.IRON, "block_strengthened_iron"));
        conductiveIron = register(new BaseBlock(Material.IRON, "block_conductive_iron"));
        registerTileEntities();
    }

    private static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileMeltingTable.class, ConstLib.MODID + ":tile_melting_table");
        GameRegistry.registerTileEntity(TileStrengtheningTable.class, ConstLib.MODID+ ":tile_strengthening_table");
        GameRegistry.registerTileEntity(TileReinforcingTable.class, ConstLib.MODID + ":tile_reinforcing_table");
        GameRegistry.registerTileEntity(TileMolecularCraftingTable.class, ConstLib.MODID + ":tile_molecular_crafting_table");
        GameRegistry.registerTileEntity(TileBlockMoleculizer.class, ConstLib.MODID + ":tile_block_moleculizer");
        GameRegistry.registerTileEntity(TileMolecularCraftingTableEnergy.class, ConstLib.MODID + ":tile_molecular_crafting_table_energy");
        GameRegistry.registerTileEntity(TileExaminationTable.class, ConstLib.MODID + ":tile_examination_table");
        GameRegistry.registerTileEntity(TileArmourApplier.class, ConstLib.MODID + ":tile_armour_applier");
        GameRegistry.registerTileEntity(TileArmourSurgeon.class, ConstLib.MODID + ":tile_armour_surgeon");
    }

    private static <Type extends Block> Type register (Type block, ItemBlock itemBlock)
    {
        GameRegistry.register(block);
        if (itemBlock != null) {
            GameRegistry.register(itemBlock);

            if (block instanceof ItemModelProvider)
                ((ItemModelProvider) block).registerItemModel(itemBlock);
            if (block instanceof ItemOreDict)
                ((ItemOreDict) block).initOreDict();
            if (itemBlock instanceof ItemOreDict)
                ((ItemOreDict) itemBlock).initOreDict();
        }
        return block;
    }

    private static <Type extends Block> Type register (Type block)
    {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return register(block, itemBlock);
    }
}

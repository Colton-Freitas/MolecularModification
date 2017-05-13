package ossium_team.mmod.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ossium_team.mmod.client.gui.*;
import ossium_team.mmod.common.block.container.*;
import ossium_team.mmod.common.block.tile.*;

import javax.annotation.Nullable;

public class ModGuiHandler implements IGuiHandler {

    public static final int MELTING_TABLE_ID = 0;
    public static final int STRENGTHENING_TABLE_ID = 1;
    public static final int REINFORCING_TABLE_ID = 2;
    public static final int MOLECULAR_CRAFTING_TABLE_ID = 3;
    public static final int BLOCK_MOLECULIZER_ID = 4;
    public static final int MOLECULAR_CRAFTING_TABLE_ENERGY_ID = 5;
    public static final int BLOCK_EXAMINATION_TABLE_ID = 6;
    public static final int ARMOUR_APPLIER_ID = 7;

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == MELTING_TABLE_ID)
            return new GuiMeltingTable(player.inventory, (TileMeltingTable)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == STRENGTHENING_TABLE_ID)
            return new GuiStrengtheningTable(player.inventory, (TileStrengtheningTable)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == REINFORCING_TABLE_ID)
            return new GuiReinforcingTable(player.inventory, (TileReinforcingTable)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == MOLECULAR_CRAFTING_TABLE_ID)
            return new GuiMolecularCraftingTable(player.inventory, (TileMolecularCraftingTable)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == BLOCK_MOLECULIZER_ID)
            return new GuiBlockMoleculizer(player.inventory, (TileBlockMoleculizer)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == MOLECULAR_CRAFTING_TABLE_ENERGY_ID)
            return new GuiMolecularCraftingTableEnergy(player.inventory, (TileMolecularCraftingTableEnergy)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == BLOCK_EXAMINATION_TABLE_ID)
            return new GuiExaminationTable(player.inventory, (TileExaminationTable)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == ARMOUR_APPLIER_ID)
            return new GuiArmourApplier(player.inventory, (TileArmourApplier)world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == MELTING_TABLE_ID)
            return new ContainerMeltingTable(player.inventory, (TileMeltingTable)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == STRENGTHENING_TABLE_ID)
            return new ContainerStrengtheningTable(player.inventory, (TileStrengtheningTable)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == REINFORCING_TABLE_ID)
            return new ContainerReinforcingTable(player.inventory, (TileReinforcingTable)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == MOLECULAR_CRAFTING_TABLE_ID)
            return new ContainerMolecularCraftingTable(player.inventory, (TileMolecularCraftingTable)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == BLOCK_MOLECULIZER_ID)
            return new ContainerBlockMoleculizer(player.inventory, (TileBlockMoleculizer)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == MOLECULAR_CRAFTING_TABLE_ENERGY_ID)
            return new ContainerMolecularCraftingTableEnergy(player.inventory, (TileMolecularCraftingTableEnergy)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == BLOCK_EXAMINATION_TABLE_ID)
            return new ContainerExaminationTable(player.inventory, (TileExaminationTable)world.getTileEntity(new BlockPos(x, y, z)));
        else if (ID == ARMOUR_APPLIER_ID)
            return new ContainerArmourApplier(player.inventory, (TileArmourApplier)world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }
}

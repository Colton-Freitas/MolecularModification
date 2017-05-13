package ossium_team.mmod.common.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;
import ossium_team.mmod.MolecularModification;
import ossium_team.mmod.client.gui.GuiMeltingTable;
import ossium_team.mmod.common.block.tile.TileMeltingTable;
import ossium_team.mmod.handler.ModGuiHandler;

import javax.annotation.Nullable;

public class BlockMeltingTable extends BaseBlock implements ITileEntityProvider{

    public BlockMeltingTable() {
        super(Material.ROCK, "block_melting_table");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote && !playerIn.isSneaking()) {
            playerIn.openGui(MolecularModification.instance, ModGuiHandler.MELTING_TABLE_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileMeltingTable();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileMeltingTable table = (TileMeltingTable)worldIn.getTileEntity(pos);
        if (table != null) {
            IItemHandlerModifiable handler = table.getItemHandler();
            for (int i = 0; i < table.getSizeInventory(); i++)
                worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), handler.getStackInSlot(i)));
        }
        super.breakBlock(worldIn, pos, state);
    }
}

package ossium_team.mmod.common.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import ossium_team.mmod.common.lib.TagLib;

import javax.annotation.Nullable;

public abstract class BaseModTile extends TileEntity implements ITickable, ICapabilityProvider {

    protected ItemStackHandler itemHandler;
    protected int invSize;
    protected int operationTicks = 0;
    protected boolean hasClientUpdated = false;

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemHandler);
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    public int getSizeInventory() {
        return invSize;
    }

    public IItemHandlerModifiable getItemHandler() {
        return itemHandler;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        itemHandler.deserializeNBT(compound.getCompoundTag(TagLib.TILE_INV_HANDLER));
        operationTicks = compound.getInteger(TagLib.TILE_OP_TICKS);
        hasClientUpdated = compound.getBoolean(TagLib.TILE_CLIENT_UPDATE);
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag(TagLib.TILE_INV_HANDLER, itemHandler.serializeNBT());
        compound.setInteger(TagLib.TILE_OP_TICKS, operationTicks);
        compound.setBoolean(TagLib.TILE_CLIENT_UPDATE, hasClientUpdated);
        return super.writeToNBT(compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        readFromNBT(tag);
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        int meta = getBlockMetadata();
        return new SPacketUpdateTileEntity(this.pos, meta, nbt);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public NBTTagCompound getTileData() {
        return writeToNBT(new NBTTagCompound());
    }
}

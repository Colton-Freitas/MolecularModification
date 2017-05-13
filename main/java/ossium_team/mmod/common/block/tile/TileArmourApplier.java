package ossium_team.mmod.common.block.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.items.ItemStackHandler;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.helper.NBTHelper;

import java.util.List;

public class TileArmourApplier extends BaseModTile {

    public TileArmourApplier() {
        invSize = 4;
        itemHandler = new ItemStackHandler(4);
    }

    @Override
    public void update() {
        if(!world.isRemote && world.isBlockPowered(pos)) {
            if(!(itemHandler.getStackInSlot(0).isEmpty()) && !(itemHandler.getStackInSlot(1).isEmpty()) &&
                    !(itemHandler.getStackInSlot(2).isEmpty()) && !(itemHandler.getStackInSlot(3).isEmpty()))
            {
                AxisAlignedBB operationArea = new AxisAlignedBB(pos.north(2), pos.north(3).up(2).east());
                List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, operationArea);
                if (!players.isEmpty())
                {
                    EntityPlayer playerToAffect = players.get(0);
                    if (!playerToAffect.getEntityData().getBoolean(TagLib.PLAYER_HAS_MM_ARMOUR)) {
                        int efficiency = 0;
                        for (int i = 0; i < 4; i++) {
                            efficiency += NBTHelper.getInt(itemHandler.getStackInSlot(i), TagLib.MM_EFFICIENCY, 0);
                        }
                        efficiency /= 4;
                        playerToAffect.getEntityData().setBoolean(TagLib.PLAYER_HAS_MM_ARMOUR, true);
                        playerToAffect.getEntityData().setFloat(TagLib.PLAYER_MM_ARMOUR_AMOUNT, efficiency);
                        for (int i = 0; i < 4; i++) {
                            itemHandler.getStackInSlot(i).shrink(1);
                        }
                    }
                }
            }
        }
    }
}

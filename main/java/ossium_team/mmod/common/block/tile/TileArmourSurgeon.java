package ossium_team.mmod.common.block.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.ItemStackHandler;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.helper.NBTHelper;

import java.util.List;

public class TileArmourSurgeon extends BaseModTile {

    public TileArmourSurgeon() {
        invSize = 0;
        itemHandler = new ItemStackHandler(0);
    }

    @Override
    public void update() {
        if(!world.isRemote && world.isBlockPowered(pos)) {
            AxisAlignedBB operationArea = new AxisAlignedBB(pos.north(2), pos.north(3).up(2).east());
            List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, operationArea);
            if (!players.isEmpty())
            {
                EntityPlayer playerToAffect = players.get(0);
                if (playerToAffect.getEntityData().getBoolean(TagLib.PLAYER_HAS_MM_ARMOUR)) {
                    operationTicks++;
                    operationTicks %= 200;
                    world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 8);
                    if (operationTicks % 20 == 0) {
                        playerToAffect.sendStatusMessage(new TextComponentString(new TextComponentTranslation("text.surgeonAffected").getFormattedText() + " " + (10 - (operationTicks / 20)) +
                            " " + new TextComponentTranslation("text.surgeonAffected2").getFormattedText()), false);
                    }
                    if (operationTicks == 199)
                    {
                        playerToAffect.getEntityData().removeTag(TagLib.PLAYER_HAS_MM_ARMOUR);
                        playerToAffect.getEntityData().removeTag(TagLib.PLAYER_MM_ARMOUR_AMOUNT);
                        playerToAffect.setAbsorptionAmount(0.0f);
                        playerToAffect.setHealth(1.0f);
                    }
                }
            } else {
                operationTicks = 0;
                world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 8);
            }
        } else if(!world.isRemote)
        {
            operationTicks = 0;
            world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 8);
        }
    }
}

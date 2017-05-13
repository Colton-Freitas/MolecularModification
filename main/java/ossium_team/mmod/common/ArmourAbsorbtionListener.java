package ossium_team.mmod.common;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ossium_team.mmod.common.lib.TagLib;

public class ArmourAbsorbtionListener {

    @SubscribeEvent
    public void onEntityLivingUpdate(PlayerEvent.LivingUpdateEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if(entity.getEntityData().getBoolean(TagLib.PLAYER_HAS_MM_ARMOUR) && entity instanceof EntityPlayer) {
            if(entity.getAbsorptionAmount() != entity.getEntityData().getFloat(TagLib.PLAYER_MM_ARMOUR_AMOUNT) &&
                    entity.getEntityData().getLong(TagLib.PLAYER_HURT_LAST) == 0)
                entity.setAbsorptionAmount(entity.getEntityData().getFloat(TagLib.PLAYER_MM_ARMOUR_AMOUNT));
            if (entity.hurtTime != 0) {
                entity.getEntityData().setLong(TagLib.PLAYER_HURT_LAST, entity.getEntityWorld().getWorldTime());
            }
            if (entity.hurtTime == 0 && entity.getEntityData().getLong(TagLib.PLAYER_HURT_LAST) != 0)
                if (entity.getEntityWorld().getWorldTime() - 100 >= entity.getEntityData().getLong(TagLib.PLAYER_HURT_LAST)) {
                    entity.getEntityData().setLong(TagLib.PLAYER_HURT_LAST, 0);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerLogIn(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityPlayer)
            event.getEntity().getEntityData().setInteger(TagLib.PLAYER_HURT_LAST, 0);

    }
}

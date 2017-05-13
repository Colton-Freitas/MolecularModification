package ossium_team.mmod.common.item.tools;

import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ossium_team.mmod.common.item.BaseItem;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.helper.NBTHelper;

import java.util.HashMap;

public abstract class MMSwordBase extends BaseItem implements IToolNBTInstanciation{

    public MMSwordBase(String name) {
        super(name);
        setMaxStackSize(1);
    }

    @Override
    public ItemStack baseNBT(ItemStack stackIn, Integer hardness, Integer efficiency, Integer harvest, Integer range, Integer maxRange, Integer silk, Integer fortune, Integer selfRepair) {
        if(!NBTHelper.hasNBT(stackIn))
            NBTHelper.initNBT(stackIn);

        if(hardness == null)
            hardness = 1000;
        if (efficiency == null || efficiency / 1000 < 5)
            efficiency = 5000;
        if(fortune == null)
            fortune = 0;
        NBTHelper.setInt(stackIn, TagLib.MM_HARDNESS, hardness);
        NBTHelper.setInt(stackIn, TagLib.MM_EFFICIENCY, efficiency / 1000);
        NBTHelper.setInt(stackIn, TagLib.MM_FORTUNE, fortune);
        NBTHelper.setInt(stackIn, TagLib.MM_DURABILITY, NBTHelper.getInt(stackIn, TagLib.MM_HARDNESS, 0));
        NBTHelper.setInt(stackIn, TagLib.MM_DURABILITY_MAX, NBTHelper.getInt(stackIn, TagLib.MM_DURABILITY, 0));
        NBTHelper.setBool(stackIn, TagLib.MM_SELF_REPAIR, selfRepair != null && selfRepair == 1);
        return stackIn;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(!worldIn.isRemote && !NBTHelper.verify(stack, TagLib.MM_DURABILITY))
            baseNBT(stack, null, null, null, null, null, null, null, null);
        if(!worldIn.isRemote && NBTHelper.getInt(stack, TagLib.MM_DURABILITY, 0) <= 0)
            stack.shrink(0);
        if(!worldIn.isRemote && NBTHelper.getInt(stack, TagLib.MM_FORTUNE, 0) > 0 && EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(21), stack) == 0) {
            HashMap<Enchantment, Integer> looting = new HashMap<Enchantment, Integer>();
            looting.put(Enchantment.getEnchantmentByID(21), NBTHelper.getInt(stack, TagLib.MM_FORTUNE, 0));
            EnchantmentHelper.setEnchantments(looting, stack);
        }
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if(entity instanceof EntityLivingBase && ((EntityLivingBase) entity).hurtTime == 0)
        {
            int critMod = player.fallDistance != 0 ? 3 : 1;
            entity.attackEntityFrom(DamageSource.causePlayerDamage(player), NBTHelper.getInt(stack, TagLib.MM_EFFICIENCY, 1) * player.getCooledAttackStrength(0) * critMod);
            player.resetCooldown();
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        return state.getBlock().equals(Blocks.WEB) ? 10.0f : 1.0f;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return ((NBTHelper.getInt(stack, TagLib.MM_DURABILITY_MAX, 0) - NBTHelper.getInt(stack, TagLib.MM_DURABILITY, 0)) /
                (double)NBTHelper.getInt(stack, TagLib.MM_DURABILITY_MAX, 0));
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return getDurabilityForDisplay(stack) != 0;
    }
}

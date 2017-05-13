package ossium_team.mmod.common.item.tools;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import ossium_team.mmod.common.item.BaseItem;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.helper.NBTHelper;

import java.util.HashMap;

public class MMToolBase extends BaseItem implements IToolNBTInstanciation{

    protected String toolClass;
    protected final HashMap<String, Material> breakableMats = new HashMap<String, Material>();

    public MMToolBase(String name, String toolClass) {
        super(name);
        this.toolClass = toolClass;
        setMaxStackSize(1);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(!worldIn.isRemote) {
            if(!NBTHelper.verify(stack, TagLib.MM_HARDNESS))
                baseNBT(stack, null, null, null, null, null, null, null, null);
            if (NBTHelper.getInt(stack, TagLib.MM_DURABILITY, 0) > 100 && !NBTHelper.getBool(stack, TagLib.MM_CAN_MINE, false))
                NBTHelper.setBool(stack, TagLib.MM_CAN_MINE, true);
            if (NBTHelper.getBool(stack, TagLib.MM_SILK, false) && EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(33), stack) == 0) {
                HashMap<Enchantment, Integer> silk = new HashMap<Enchantment, Integer>();
                silk.put(Enchantment.getEnchantmentByID(33), 1);
                EnchantmentHelper.setEnchantments(silk, stack);
            }
            if (NBTHelper.getInt(stack, TagLib.MM_FORTUNE, 0) != 0 && EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(35), stack) == 0) {
                HashMap<Enchantment, Integer> fortune = new HashMap<Enchantment, Integer>();
                fortune.put(Enchantment.getEnchantmentByID(35), NBTHelper.getInt(stack, TagLib.MM_FORTUNE, 0));
                EnchantmentHelper.setEnchantments(fortune, stack);
            }
            if (NBTHelper.getBool(stack, TagLib.MM_SELF_REPAIR, false)) {
                if(worldIn.isDaytime() && worldIn.canSeeSky(entityIn.getPosition()) && !isSelected) {
                    if(NBTHelper.getInt(stack, TagLib.MM_DURABILITY, 0) != NBTHelper.getInt(stack, TagLib.MM_DURABILITY_MAX, 0)) {
                        NBTHelper.setInt(stack, TagLib.MM_DURABILITY, NBTHelper.getInt(stack, TagLib.MM_DURABILITY, 0) + 30);
                        if(NBTHelper.getInt(stack, TagLib.MM_DURABILITY, 0) > NBTHelper.getInt(stack, TagLib.MM_DURABILITY_MAX, 0))
                            NBTHelper.setInt(stack, TagLib.MM_DURABILITY, NBTHelper.getInt(stack, TagLib.MM_DURABILITY_MAX, 0));
                    }
                }
            }
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public ItemStack baseNBT(ItemStack stackIn, Integer hardness, Integer efficiency, Integer harvest, Integer range, Integer maxRange, Integer silky, Integer fortune, Integer selfRepair) {
        if(!NBTHelper.hasNBT(stackIn))
            NBTHelper.initNBT(stackIn);
        if (stackIn.getItem() instanceof MMReinforcedTool) {
            if (efficiency == null || efficiency / 1000 < 5)
                efficiency = null;
            else
                efficiency = efficiency / 1000;
            NBTHelper.setInt(stackIn, TagLib.MM_RANGE, range == null ? 0 : range);
            NBTHelper.setInt(stackIn, TagLib.MM_EFFICIENCY, efficiency == null ? 5 : efficiency);
            NBTHelper.setInt(stackIn, TagLib.MM_HARVEST, harvest == null ? 3 : harvest);
            NBTHelper.setInt(stackIn, TagLib.MM_HARDNESS, hardness == null ? 1000 : hardness);
            NBTHelper.setInt(stackIn, TagLib.MM_MAX_RANGE, maxRange == null ? 0 : maxRange);
            NBTHelper.setInt(stackIn, TagLib.MM_DURABILITY, NBTHelper.getInt(stackIn, TagLib.MM_HARDNESS, 1000));
            NBTHelper.setInt(stackIn, TagLib.MM_DURABILITY_MAX, NBTHelper.getInt(stackIn, TagLib.MM_DURABILITY, 0));
            NBTHelper.setBool(stackIn, TagLib.MM_CAN_MINE, true);
            NBTHelper.setBool(stackIn, TagLib.MM_SILK, false);
            NBTHelper.setInt(stackIn, TagLib.MM_FORTUNE, 0);
            NBTHelper.setBool(stackIn, TagLib.MM_SELF_REPAIR, false);
        }
        else if (stackIn.getItem() instanceof MMArmourStandin) {
            efficiency = efficiency == null ? 5 : efficiency;
            hardness = hardness == null ? 1000 : hardness;
            double efficiencyMod = efficiency > hardness ? hardness / (double)efficiency : efficiency / (double) hardness;
            efficiency = (int) (efficiency * efficiencyMod) / 1000;
            efficiency = efficiency > 200 ? 200 : efficiency;
            NBTHelper.setInt(stackIn, TagLib.MM_EFFICIENCY, efficiency);
        }
        else {
            if (efficiency == null || efficiency / 2000 < 5)
                efficiency = null;
            else
                efficiency = efficiency / 2000;
            NBTHelper.setInt(stackIn, TagLib.MM_MAX_RANGE, maxRange == null ? 0 : maxRange);
            NBTHelper.setInt(stackIn, TagLib.MM_RANGE, range == null ? 0 : range);
            NBTHelper.setInt(stackIn, TagLib.MM_EFFICIENCY, efficiency == null ? 5 : efficiency);
            NBTHelper.setInt(stackIn, TagLib.MM_HARVEST, harvest == null ? 2 : harvest);
            NBTHelper.setInt(stackIn, TagLib.MM_HARDNESS, hardness == null ? 1000 : hardness);
            NBTHelper.setInt(stackIn, TagLib.MM_DURABILITY, NBTHelper.getInt(stackIn, TagLib.MM_HARDNESS, 1000));
            NBTHelper.setInt(stackIn, TagLib.MM_DURABILITY_MAX, NBTHelper.getInt(stackIn, TagLib.MM_DURABILITY, 0));
            NBTHelper.setBool(stackIn, TagLib.MM_CAN_MINE, true);
            NBTHelper.setBool(stackIn, TagLib.MM_SILK, silky != null && silky == 1);
            NBTHelper.setInt(stackIn, TagLib.MM_FORTUNE, fortune == null ? 0 : fortune);
            NBTHelper.setBool(stackIn, TagLib.MM_SELF_REPAIR, selfRepair != null && selfRepair == 1);
        }
        return stackIn;
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        float currentMod = NBTHelper.getInt(stack, TagLib.MM_EFFICIENCY, 0);
        if(canHarvestBlock(state, stack) && NBTHelper.getBool(stack, TagLib.MM_CAN_MINE, false))
            return currentMod;
        else
            return 1.0f;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        if (state.getBlock().equals(Blocks.BEDROCK) || !NBTHelper.getBool(stack, TagLib.MM_CAN_MINE, false))
            return false;
        boolean blockUnderHarvestLevel = state.getBlock().getHarvestLevel(state) <= NBTHelper.getInt(stack, TagLib.MM_HARVEST, 0);
        if (state.getBlock().getHarvestTool(state) == null)
            return blockUnderHarvestLevel && breakableMats.get(state.getMaterial().toString()) != null;
        else if (state.getBlock().getHarvestTool(state).equals(toolClass))
            return blockUnderHarvestLevel;
        return false;
    }
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if (!worldIn.isRemote) {
            if (!(stack.getItem() instanceof MMReinforcedTool))
                updateNBTFromBrokenBlock(stack);
            RayTraceResult side = entityLiving.rayTrace(6.0, 0);
            if(!(side == null) && !entityLiving.isSneaking() && canHarvestBlock(state, stack)) {
                Vec3i direction = sideTo3X3Direction(side.sideHit);
                int max = NBTHelper.getInt(stack, TagLib.MM_MAX_RANGE, 0);
                int currentRange = NBTHelper.getInt(stack, TagLib.MM_RANGE, 0);
                int smaller = max >= currentRange ? currentRange : max;
                BlockPos start = pos.add(new Vec3i(direction.getX() * smaller, direction.getY() * smaller, direction.getZ() * smaller));
                breakSurroundingBlocksInRange(smaller, start, stack, worldIn, direction, entityLiving);
            }
        }
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    private Vec3i sideTo3X3Direction(EnumFacing side) {
        if (side.equals(EnumFacing.DOWN) || side.equals(EnumFacing.UP))
            return new Vec3i(1, 0, 1);
        else if (side.equals(EnumFacing.EAST) || side.equals(EnumFacing.WEST))
            return new Vec3i(0, 1, 1);
        else
            return new Vec3i(1, 1, 0);
    }

    private void breakSurroundingBlocksInRange(int range, BlockPos start, ItemStack stackToBreakWith, World worldIn, Vec3i direction, EntityLivingBase entityLiving) {
        if (range == 0)
            return;
        BlockPos current = new BlockPos(start);
        for (int i = 0; i < (range * 2) + 1; i++) {
            for (int j = 0; j < (range * 2) + 1; j++) {
                if (direction.getX() == 0)
                    current = start.add(0, -i, -j);
                else if (direction.getY() == 0)
                    current = start.add(-i, 0, -j);
                else if (direction.getZ() == 0)
                    current = start.add(-i, -j, 0);
                if (canHarvestBlock(worldIn.getBlockState(current), stackToBreakWith) && !(worldIn.getBlockState(current).getBlockHardness(worldIn, current) <= 0)) {
                    worldIn.destroyBlock(current, true);
                    updateNBTFromBrokenBlock(stackToBreakWith);
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote) {
            updateRange(playerIn, handIn);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private void updateRange(EntityPlayer player, EnumHand hand) {
        if (player.isSneaking()) {
            NBTHelper.setInt(player.getHeldItem(hand), TagLib.MM_RANGE, NBTHelper.getInt(player.getHeldItem(hand), TagLib.MM_RANGE, 0) + 1);
            if (NBTHelper.getInt(player.getHeldItem(hand), TagLib.MM_MAX_RANGE, 0) < NBTHelper.getInt(player.getHeldItem(hand), TagLib.MM_RANGE, 0))
                NBTHelper.setInt(player.getHeldItem(hand), TagLib.MM_RANGE, 0);
            player.sendStatusMessage(new TextComponentTranslation("text.Range").appendText(" " + (NBTHelper.getInt(player.getHeldItem(hand), TagLib.MM_RANGE, 0) * 2 + 1)), true);
        }
    }

    public static void updateNBTFromBrokenBlock(ItemStack stackIn) {
        if (NBTHelper.getBool(stackIn, TagLib.MM_CAN_MINE, false)) {
            int currentDurability = NBTHelper.getInt(stackIn, TagLib.MM_DURABILITY, 0);
            NBTHelper.setInt(stackIn, TagLib.MM_DURABILITY, currentDurability - 100);
            if (currentDurability - 200 <= 0)
                NBTHelper.setBool(stackIn, TagLib.MM_CAN_MINE, false);
        }
    }
}

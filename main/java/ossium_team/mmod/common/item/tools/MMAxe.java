package ossium_team.mmod.common.item.tools;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.helper.NBTHelper;

public class MMAxe extends MMToolBase {

    public MMAxe() {
        super("item_mm_axe", "axe");
        breakableMats.put(Material.CACTUS.toString(), Material.CACTUS);
        breakableMats.put(Material.WOOD.toString(), Material.WOOD);
        breakableMats.put(Material.CLOTH.toString(), Material.CLOTH);
        breakableMats.put(Material.LEAVES.toString(), Material.LEAVES);
        breakableMats.put(Material.GOURD.toString(), Material.GOURD);
        breakableMats.put(Material.CAKE.toString(), Material.CAKE);
        breakableMats.put(Material.PLANTS.toString(), Material.PLANTS);
        breakableMats.put(Material.VINE.toString(), Material.VINE);
        breakableMats.put(Material.WEB.toString(), Material.WEB);
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

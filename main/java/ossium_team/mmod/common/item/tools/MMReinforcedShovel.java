package ossium_team.mmod.common.item.tools;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class MMReinforcedShovel extends MMReinforcedTool {

    public MMReinforcedShovel() {
        super("item_mm_reinforced_shovel", "shovel");
        breakableMats.put(Material.GROUND.toString(), Material.GROUND);
        breakableMats.put(Material.GRASS.toString(), Material.GRASS);
        breakableMats.put(Material.CLAY.toString(), Material.CLAY);
        breakableMats.put(Material.CRAFTED_SNOW.toString(), Material.CRAFTED_SNOW);
        breakableMats.put(Material.SNOW.toString(), Material.SNOW);
        breakableMats.put(Material.SAND.toString(), Material.SAND);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        if (canHarvestBlock(state, stack))
            return super.getStrVsBlock(stack, state) * .5f;
        else
            return super.getStrVsBlock(stack, state);
    }
}

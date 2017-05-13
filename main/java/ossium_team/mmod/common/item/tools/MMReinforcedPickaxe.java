package ossium_team.mmod.common.item.tools;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class MMReinforcedPickaxe extends MMReinforcedTool {

    public MMReinforcedPickaxe() {
        super("item_mm_reinforced_pickaxe", "pickaxe");
        breakableMats.put(Material.ROCK.toString(), Material.ROCK);
        breakableMats.put(Material.GLASS.toString(), Material.GLASS);
        breakableMats.put(Material.IRON.toString(), Material.IRON);
        breakableMats.put(Material.PISTON.toString(), Material.PISTON);
        breakableMats.put(Material.ANVIL.toString(), Material.ANVIL);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        float current = super.getStrVsBlock(stack, state);
        if(state.getBlock().getHarvestLevel(state) >= Blocks.OBSIDIAN.getHarvestLevel(Blocks.OBSIDIAN.getDefaultState()) &&
                canHarvestBlock(state, stack))
            return current * 5;
        return current;
    }
}

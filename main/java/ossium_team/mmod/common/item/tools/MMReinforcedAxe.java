package ossium_team.mmod.common.item.tools;

import net.minecraft.block.material.Material;

public class MMReinforcedAxe extends MMReinforcedTool {

    public MMReinforcedAxe() {
        super("item_mm_reinforced_axe", "axe");
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
}

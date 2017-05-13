package ossium_team.mmod.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import ossium_team.mmod.common.block.container.ContainerStrengtheningTable;
import ossium_team.mmod.common.block.tile.TileStrengtheningTable;
import ossium_team.mmod.common.lib.ConstLib;
import ossium_team.mmod.common.lib.TagLib;

import java.io.IOException;

public class GuiStrengtheningTable extends GuiContainer {

    private final ResourceLocation TEXTURE = new ResourceLocation(ConstLib.MODID, "textures/gui/gui_strengthening_table.png");
    private final int TEXTURE_HEIGHT = 166, TEXTURE_WIDTH = 176;

    private IInventory playerInventory;
    private TileStrengtheningTable tile;

    public GuiStrengtheningTable(IInventory playerInventory, TileStrengtheningTable tile) {
        super(new ContainerStrengtheningTable(playerInventory, tile));
        this.playerInventory = playerInventory;
        this.tile = tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawDefaultBackground();
        int centerX = (width / 2) - (TEXTURE_WIDTH / 2);
        int centerY = (height / 2) - (TEXTURE_HEIGHT / 2);
        mc.renderEngine.bindTexture(TEXTURE);
        drawTexturedModalRect(centerX, centerY, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        fontRendererObj.drawString("Strengthening Table", centerX + 35, centerY + 15, 0X777777);
        GlStateManager.pushMatrix();
        {
            GlStateManager.color(1, 1, 1, 1);
        }
        GlStateManager.popMatrix();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        mc.renderEngine.bindTexture(TEXTURE);
        int tileTicks = tile.getTileData().getInteger(TagLib.TILE_OP_TICKS);
        float primaryTicks = tileTicks * .08f;
        drawTexturedModalRect(54, 34, 176, 0, (int)primaryTicks, 16);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (typedChar == 'e')
            mc.displayGuiScreen(null);
        super.keyTyped(typedChar, keyCode);
    }
}

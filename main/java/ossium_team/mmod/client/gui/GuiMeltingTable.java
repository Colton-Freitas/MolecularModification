package ossium_team.mmod.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import ossium_team.mmod.common.block.container.ContainerMeltingTable;
import ossium_team.mmod.common.block.tile.TileMeltingTable;
import ossium_team.mmod.common.lib.ConstLib;
import ossium_team.mmod.common.lib.TagLib;

import java.io.IOException;

public class GuiMeltingTable extends GuiContainer {

    private final ResourceLocation TEXTURE = new ResourceLocation(ConstLib.MODID, "textures/gui/gui_melting_table.png");
    private final int TEXTURE_HEIGHT = 166, TEXTURE_WIDTH = 176;

    private IInventory playerInventory;
    private TileMeltingTable tile;


    public GuiMeltingTable(IInventory playerInventory, TileMeltingTable te) {
        super(new ContainerMeltingTable(playerInventory, te));
        this.playerInventory = playerInventory;
        this.tile = te;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawDefaultBackground();
        int centerX = (width / 2) - (TEXTURE_WIDTH / 2);
        int centerY = (height / 2) - (TEXTURE_HEIGHT / 2);
        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
        drawTexturedModalRect(centerX, centerY, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        fontRendererObj.drawString("Glass Modification Table", centerX + 26, centerY + 20, 0X777777);
        GlStateManager.pushMatrix();
        {
            GlStateManager.color(1.0f, 1.0f, 1.0f);
        }
        GlStateManager.popMatrix();

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
        int tileTicks = tile.getTileData().getInteger(TagLib.TILE_OP_TICKS);
        float secondaryTicks = tileTicks >= 100 ? (tileTicks - 100) / 100f : 0;
        int primaryTicks = tileTicks <= 100 ? tileTicks / 5 : 0;
        drawTexturedModalRect(53, 40, 176, 0, primaryTicks, 22);
        if (secondaryTicks != 0) {
            GlStateManager.pushMatrix();
            {
                GlStateManager.enableAlpha();
                GlStateManager.enableBlend();
                GlStateManager.color(1, 1, 1, 1 - secondaryTicks);
                drawTexturedModalRect(74, 39, 220, 0, 24, 24);
            }
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(typedChar == 'e')
            Minecraft.getMinecraft().displayGuiScreen(null);
        super.keyTyped(typedChar, keyCode);
    }
}

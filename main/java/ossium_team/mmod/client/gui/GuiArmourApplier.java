package ossium_team.mmod.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import ossium_team.mmod.common.block.container.ContainerArmourApplier;
import ossium_team.mmod.common.block.tile.TileArmourApplier;
import ossium_team.mmod.common.lib.ConstLib;

import java.io.IOException;

public class GuiArmourApplier extends GuiContainer {

    private final ResourceLocation TEXTURE = new ResourceLocation(ConstLib.MODID, "textures/gui/gui_armor_applier.png");
    private final int TEXTURE_HEIGHT = 166, TEXTURE_WIDTH = 176;

    private TileArmourApplier tile;

    public GuiArmourApplier(IInventory playerInventory, TileArmourApplier table) {
        super(new ContainerArmourApplier(playerInventory, table));
        tile = table;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawDefaultBackground();
        int centerX = (width / 2) - (TEXTURE_WIDTH / 2);
        int centerY = (height / 2) - (TEXTURE_HEIGHT / 2);
        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
        drawTexturedModalRect(centerX, centerY, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        GlStateManager.pushMatrix();
        {
            GlStateManager.color(1.0f, 1.0f, 1.0f);
        }
        GlStateManager.popMatrix();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
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

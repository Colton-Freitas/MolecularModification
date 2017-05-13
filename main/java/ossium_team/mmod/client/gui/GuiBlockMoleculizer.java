package ossium_team.mmod.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import ossium_team.mmod.common.block.container.ContainerBlockMoleculizer;
import ossium_team.mmod.common.block.tile.TileBlockMoleculizer;
import ossium_team.mmod.common.lib.ConstLib;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.helper.NBTHelper;

import java.io.IOException;

public class GuiBlockMoleculizer extends GuiContainer {

    private final ResourceLocation TEXTURE = new ResourceLocation(ConstLib.MODID, "textures/gui/gui_block_moleculizer.png");
    private final int TEXTURE_HEIGHT = 166, TEXTURE_WIDTH = 176;

    private TileBlockMoleculizer tile;

    public GuiBlockMoleculizer(IInventory playerInventory, TileBlockMoleculizer tile) {
        super(new ContainerBlockMoleculizer(playerInventory, tile));
        this.tile = tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawDefaultBackground();
        int centerX = (width / 2) - (TEXTURE_WIDTH / 2);
        int centerY = (height / 2) - (TEXTURE_HEIGHT / 2);
        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
        drawTexturedModalRect(centerX, centerY, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        fontRendererObj.drawString("Block Moleculizer", centerX + 26, centerY + 20, 0X777777);
        if(!tile.getItemHandler().getStackInSlot(0).isEmpty() && NBTHelper.verify(tile.getItemHandler().getStackInSlot(0), TagLib.ITEM_REINFORCEMENT_HARDNESS))
            fontRendererObj.drawString(tile.getItemHandler().getStackInSlot(0).getTagCompound().toString(), centerX + 26, centerY + 50, 0X777777);
        GlStateManager.pushMatrix();
        {
            GlStateManager.color(1.0f, 1.0f, 1.0f);
        }
        GlStateManager.popMatrix();
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

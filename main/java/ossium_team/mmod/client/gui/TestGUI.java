package ossium_team.mmod.client.gui;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import ossium_team.mmod.common.lib.ConstLib;

import java.io.IOException;

public class TestGUI extends GuiScreen{

    private final ResourceLocation BOOK_TEXTURE = new ResourceLocation(ConstLib.MODID, "textures/gui/test_gui.png");
    private final int BOOK_HEIGHT = 228, BOOK_WIDTH = 151;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(BOOK_TEXTURE);
        drawTexturedModalRect((width / 2) - (BOOK_WIDTH/2), (height / 2) - (BOOK_HEIGHT/2), 0, 0, BOOK_WIDTH, BOOK_HEIGHT);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }
}

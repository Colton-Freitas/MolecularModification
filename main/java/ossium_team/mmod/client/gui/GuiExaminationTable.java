package ossium_team.mmod.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import ossium_team.mmod.common.block.container.ContainerExaminationTable;
import ossium_team.mmod.common.block.tile.TileExaminationTable;
import ossium_team.mmod.common.item.BaseItem;
import ossium_team.mmod.common.item.tools.MMReinforcedTool;
import ossium_team.mmod.common.item.tools.MMSwordBase;
import ossium_team.mmod.common.item.tools.MMToolBase;
import ossium_team.mmod.common.lib.ConstLib;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.common.recipies.ModReinforcementValues;
import ossium_team.mmod.common.shape.MolecularizableBlockRegistry;
import ossium_team.mmod.helper.NBTHelper;

import java.util.Iterator;

public class GuiExaminationTable extends GuiContainer {

    private final ResourceLocation TEXTURE = new ResourceLocation(ConstLib.MODID, "textures/gui/gui_block_examination_table.png");
    private final int TEXTURE_HEIGHT = 166, TEXTURE_WIDTH = 176;

    private TileExaminationTable table;

    public GuiExaminationTable(IInventory playerInventory, TileExaminationTable table) {
        super(new ContainerExaminationTable(playerInventory, table));
        this.table = table;
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
        if(!table.getItemHandler().getStackInSlot(0).isEmpty()) {
            if(table.getItemHandler().getStackInSlot(0).getItem() instanceof ItemBlock) {
                fontRendererObj.drawString(new TextComponentTranslation("text.MolecularizableBlock").getFormattedText(), 45, 20, 0X777777);
                fontRendererObj.drawString(new TextComponentTranslation("text.Hardness").getFormattedText() + " " +
                        (MolecularizableBlockRegistry.getHardness(((ItemBlock)table.getItemHandler().getStackInSlot(0).getItem()).getBlock()) / 2), 45, 38, 0X777777);
                fontRendererObj.drawString(new TextComponentTranslation("text.Efficiency").getFormattedText()
                        + " " + (MolecularizableBlockRegistry.getEfficiency((((ItemBlock) table.getItemHandler().getStackInSlot(0).getItem()).getBlock())) / 12), 45, 56, 0X777777);
            }
            else if (table.getItemHandler().getStackInSlot(0).getItem() instanceof MMToolBase && !(table.getItemHandler().getStackInSlot(0).getItem() instanceof MMReinforcedTool)) {
                    fontRendererObj.drawString(new TextComponentTranslation("nbt." + TagLib.MM_EFFICIENCY).getFormattedText() + " " +
                            NBTHelper.getInt(table.getItemHandler().getStackInSlot(0), TagLib.MM_EFFICIENCY, 0), 45, 20, 0X777777);
                    fontRendererObj.drawString(new TextComponentTranslation("nbt." + TagLib.MM_DURABILITY).getFormattedText() + " " +
                            (NBTHelper.getInt(table.getItemHandler().getStackInSlot(0), TagLib.MM_DURABILITY, 0) / 100), 45, 29, 0X777777);
                    fontRendererObj.drawString(new TextComponentTranslation("nbt." + TagLib.MM_HARVEST).getFormattedText() + " " +
                            NBTHelper.getInt(table.getItemHandler().getStackInSlot(0), TagLib.MM_HARVEST, 0), 45, 38, 0X777777);
                    fontRendererObj.drawString(new TextComponentTranslation("nbt." + TagLib.MM_RANGE).getFormattedText() + " " +
                            (NBTHelper.getInt(table.getItemHandler().getStackInSlot(0), TagLib.MM_RANGE, 0) * 2 + 1), 45, 47, 0X777777);
                    fontRendererObj.drawString(new TextComponentTranslation("nbt." + TagLib.MM_MAX_RANGE).getFormattedText() + " " +
                            (NBTHelper.getInt(table.getItemHandler().getStackInSlot(0), TagLib.MM_MAX_RANGE, 0) * 2 + 1), 45, 56, 0X777777);
                    fontRendererObj.drawString(new TextComponentTranslation("nbt." + TagLib.MM_SELF_REPAIR).getFormattedText() + " " +
                            ((NBTHelper.getBool(table.getItemHandler().getStackInSlot(0), TagLib.MM_SELF_REPAIR, false) ? "1" : "0")), 45, 65, 0X777777);
            }
            else if(table.getItemHandler().getStackInSlot(0).getItem() instanceof MMReinforcedTool) {
                fontRendererObj.drawString(new TextComponentTranslation("nbt." + TagLib.MM_EFFICIENCY).getFormattedText() + " " +
                        NBTHelper.getInt(table.getItemHandler().getStackInSlot(0), TagLib.MM_EFFICIENCY, 0), 45, 20, 0X777777);
                fontRendererObj.drawString(new TextComponentTranslation("nbt." + TagLib.MM_DURABILITY).getFormattedText() + " " +
                        (NBTHelper.getInt(table.getItemHandler().getStackInSlot(0), TagLib.MM_DURABILITY, 0)), 45, 29, 0X777777);
                fontRendererObj.drawString(new TextComponentTranslation("nbt." + TagLib.MM_HARVEST).getFormattedText() + " " +
                        NBTHelper.getInt(table.getItemHandler().getStackInSlot(0), TagLib.MM_HARVEST, 0), 45, 38, 0X777777);
                fontRendererObj.drawString(new TextComponentTranslation("nbt." + TagLib.MM_RANGE).getFormattedText() + " " +
                        (NBTHelper.getInt(table.getItemHandler().getStackInSlot(0), TagLib.MM_RANGE, 0) * 2 + 1), 45, 47, 0X777777);
                fontRendererObj.drawString(new TextComponentTranslation("nbt." + TagLib.MM_MAX_RANGE).getFormattedText() + " " +
                        (NBTHelper.getInt(table.getItemHandler().getStackInSlot(0), TagLib.MM_MAX_RANGE, 0) * 2 + 1), 45, 56, 0X777777);
            }
            else if(table.getItemHandler().getStackInSlot(0).getItem() instanceof MMSwordBase)
            {
                fontRendererObj.drawString(new TextComponentTranslation("text.attackDamage").getFormattedText() + " " +
                        NBTHelper.getInt(table.getItemHandler().getStackInSlot(0), TagLib.MM_EFFICIENCY, 0), 32, 20, 0X777777);
                fontRendererObj.drawString(new TextComponentTranslation("nbt." + TagLib.MM_DURABILITY).getFormattedText() + " " +
                        (NBTHelper.getInt(table.getItemHandler().getStackInSlot(0), TagLib.MM_DURABILITY, 0)), 32, 49, 0X777777);
                fontRendererObj.drawString(new TextComponentTranslation("nbt." + TagLib.MM_SELF_REPAIR).getFormattedText() + " " +
                        ((NBTHelper.getBool(table.getItemHandler().getStackInSlot(0), TagLib.MM_SELF_REPAIR, false) ? "1" : "0")), 45, 65, 0X777777);
            }
            else if (table.getItemHandler().getStackInSlot(0).getItem() instanceof BaseItem && NBTHelper.hasNBT(table.getItemHandler().getStackInSlot(0))) {
                if(NBTHelper.hasNBT(table.getItemHandler().getStackInSlot(0))) {
                    Iterator<String> iter = table.getItemHandler().getStackInSlot(0).getTagCompound().getKeySet().iterator();
                    int dY = 0;
                    while(iter.hasNext()) {
                        String tag = iter.next();
                        if(tag.contains("Range"))
                            fontRendererObj.drawString(new TextComponentTranslation("nbt." + tag).getFormattedText() + " " +
                                    (table.getItemHandler().getStackInSlot(0).getTagCompound().getInteger(tag) * 2 + 1), 45, 13 + dY * 9, 0X777777);
                        else
                        fontRendererObj.drawString(new TextComponentTranslation("nbt." + tag).getFormattedText() + " " +
                                table.getItemHandler().getStackInSlot(0).getTagCompound().getTag(tag).toString(), 45, 13 + dY * 9, 0X777777);
                        dY++;
                        if(dY == 8) {
                            break;
                        }
                    }
                    fontRendererObj.FONT_HEIGHT = 10;
                }
            }
            else {
                fontRendererObj.drawString(new TextComponentTranslation("text.ReinforcingMaterial").getFormattedText(), 45, 12, 0X777777);
                fontRendererObj.drawString(new TextComponentTranslation("text.Hardness").getFormattedText() + " " +
                        ModReinforcementValues.getHardnessValue(table.getItemHandler().getStackInSlot(0)), 45, 30, 0X777777);
                fontRendererObj.drawString(new TextComponentTranslation("text.Efficiency").getFormattedText() + " " +
                        ModReinforcementValues.getEfficiencyValue(table.getItemHandler().getStackInSlot(0)), 45, 48, 0X777777);
                fontRendererObj.drawString(new TextComponentTranslation("text.Flexibility").getFormattedText() + " " +
                        ModReinforcementValues.getFlexibilityValue(table.getItemHandler().getStackInSlot(0)), 45, 66, 0X777777);
            }
        }
    }
}

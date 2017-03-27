package theflogat.technomancy.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import theflogat.technomancy.client.gui.container.ContainerProcessor;
import theflogat.technomancy.common.tiles.base.TileProcessorBase;
import theflogat.technomancy.common.tiles.bloodmagic.machines.TileBMProcessor;
import theflogat.technomancy.lib.Reference;

public class GuiProcessorBM extends GuiContainer {

    TileBMProcessor processor;

    public GuiProcessorBM(InventoryPlayer inventory, TileBMProcessor tileBMProcessor) {
        super(new ContainerProcessor(inventory, (TileProcessorBase) tileBMProcessor, 52, 107, 56, 114));
        this.processor = tileBMProcessor;

        xSize = 176;
        ySize = 137;
    }

    private static final ResourceLocation texture = new ResourceLocation(Reference.GUI_BM_PROCESSOR);

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int k = 0;
        if (this.processor.isActive) {
            k = this.processor.getTimeScaled(37);
        }
        drawTexturedModalRect(guiLeft + 69, guiTop + 26, xSize + 1, 0, k, 16);
    }
}

package theflogat.technomancy.lib.compat.waila;

import java.util.List;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.SpecialChars;
import net.minecraft.item.ItemStack;
import theflogat.technomancy.common.tiles.base.TileDynamoBase;
import theflogat.technomancy.common.tiles.bloodmagic.dynamos.TileBloodDynamo;

public class DynamoHUDHandler extends WailaHudBase {

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        final TileDynamoBase tileEntity = (TileDynamoBase) accessor.getTileEntity();
        WailaHelper.drawDefault(currenttip, tileEntity);
        if (tileEntity instanceof TileBloodDynamo) {
            currenttip.add(SpecialChars.DRED + "Blood: " + ((TileBloodDynamo) tileEntity).liquid + " / " + TileBloodDynamo.CAPACITY);
        }

        return currenttip;
    }

}

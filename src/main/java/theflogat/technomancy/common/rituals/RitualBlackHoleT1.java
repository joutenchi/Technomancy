package theflogat.technomancy.common.rituals;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import theflogat.technomancy.api.rituals.Ritual;
import theflogat.technomancy.client.renderers.models.ModelBlackSphere;
import theflogat.technomancy.common.tiles.technom.TileCatalyst;
import theflogat.technomancy.lib.Ref;
import theflogat.technomancy.util.RitualHelper;

public class RitualBlackHoleT1 extends Ritual{

	ModelBlackSphere specialRender = new ModelBlackSphere(3F, -1F, 0, 1F);
	private static final ResourceLocation textLoc = new ResourceLocation(Ref.MODEL_REF_TEXTURE);

	@Override
	public boolean isCoreComplete(World w, int x, int y, int z) {
		return w.getBlockMetadata(x, y, z)==black;
	}

	@Override
	public boolean isFrameComplete(World w, int x, int y, int z) {
		return RitualHelper.checkForT1(w, x, y, z, black);
	}

	@Override
	public boolean applyEffect(World w, int x, int y, int z) {
		for(int i=-3; i<=3; i++){
			for(int j=-3; j<=3; j++){
				for(int k=-3; k<=3; k++){
					int xx = x + i;
					int yy = y + j;
					int zz = z + k;
					if(canDestroy(w, x, y, z, i, j, k)){
						if(i!=0 || j!=0 || k!=0){
							w.setBlockToAir(xx, yy, zz);
						}
					}
				}
			}
		}
		RitualHelper.removeT1(w, x, y, z);

		ArrayList<Entity> e = (ArrayList<Entity>) w.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(x-2, y-2, z-2, x+2, y+2, z+2));

		for(Entity ent : e){
			if(ent.isEntityInvulnerable()){
				ent.setDead();
			}
		}
		
		return true;
	}

	private boolean canDestroy(World w, int x, int y, int z, int i, int j, int k) {
		if(w.getBlock(x+i, y+j, z+k).getBlockHardness(w, x+i, y+j, z+k)<0)
			return false;
		
		return true;
	}

	@Override
	public void afterEffect(World w, int x, int y, int z) {
		((TileCatalyst)w.getTileEntity(x, y, z)).remCount = 60;
		((TileCatalyst)w.getTileEntity(x, y, z)).specialRender = specialRender;
		((TileCatalyst)w.getTileEntity(x, y, z)).textLoc = textLoc;
	}
}
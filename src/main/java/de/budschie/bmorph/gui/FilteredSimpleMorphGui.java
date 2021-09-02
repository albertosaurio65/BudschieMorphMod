package de.budschie.bmorph.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import de.budschie.bmorph.capabilities.IMorphCapability;
import de.budschie.bmorph.capabilities.MorphCapabilityAttacher;
import de.budschie.bmorph.main.References;
import de.budschie.bmorph.morph.MorphItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraftforge.common.util.LazyOptional;

public class FilteredSimpleMorphGui extends AbstractMorphGui
{
	private ArrayList<MorphWidget> morphWidgets;
	private int scroll = 0;
	private BiFunction<IMorphCapability, List<MorphItem>, List<MorphItem>> filter;
	
	public FilteredSimpleMorphGui(ResourceLocation morphGuiTypeIcon, String unlocalizedGuiType, BiFunction<IMorphCapability, List<MorphItem>, List<MorphItem>> filter)
	{
		super(morphGuiTypeIcon, unlocalizedGuiType);
		
		this.filter = filter;
		this.morphWidgets = new ArrayList<>();
	}
	
	@Override
	public void showGui()
	{
		LazyOptional<IMorphCapability> cap = Minecraft.getInstance().player.getCapability(MorphCapabilityAttacher.MORPH_CAP);
		
		if(cap.isPresent())
		{
			IMorphCapability resolved = cap.resolve().get();
	
			List<MorphItem> morphList = resolved.getMorphList().getMorphArrayList();
			morphList = filter.apply(resolved, morphList);
			
			morphWidgets.add(new MorphWidget(null, false));
			
			for(MorphItem item : morphList)
			{
				morphWidgets.add(new MorphWidget(item, resolved.getFavouriteList().containsMorphItem(item)));
			}
			
		}
	}
	
	@Override
	public void hideGui()
	{
		morphWidgets = new ArrayList<>();
	}
	
	/** This method iterates over every widget element and checks if the favourite status has changed. **/
	private void updateGui()
	{
		LazyOptional<IMorphCapability> cap = Minecraft.getInstance().player.getCapability(MorphCapabilityAttacher.MORPH_CAP);
		
		if(cap.isPresent())
		{
			IMorphCapability resolved = cap.resolve().get();
			
			for(int i = 1; i < morphWidgets.size(); i++)
				morphWidgets.get(i).isFavourite = resolved.getFavouriteList().containsMorphItem(morphWidgets.get(i).morphItem);
		}
	}
	
	@Override
	public void onFavouriteChanged()
	{
		// Reload every GUI element
		updateGui();
	}
	
	@Override
	public void renderWidgets(MatrixStack matrixStack)
	{							
		scroll = Math.max(Math.min(scroll, morphWidgets.size() - 1), 0);
		
		int startY = Minecraft.getInstance().getMainWindow().getScaledHeight() / 2 - MorphWidget.getHeight() / 2
				- scroll * MorphWidget.getHeight();
		int advanceY = 0;


		int rendered = 0;
				
		for (int i = 0; i < morphWidgets.size(); i++)
		{
			if((startY + advanceY + MorphWidget.getHeight()) > 0 && (startY + advanceY) < Minecraft.getInstance().getMainWindow().getScaledHeight())
			{
				rendered++;
				
				MorphWidget widget = morphWidgets.get(i);
				matrixStack.push();
				matrixStack.translate(6, startY + advanceY, 0);
				widget.render(matrixStack, i == scroll);
	//			Minecraft.getInstance().fontRenderer.drawText(matrixStack, new StringTextComponent("Index " + i), 0, 0,
	//					0xffffff);
				matrixStack.pop();
			}
			advanceY += MorphWidget.getHeight();
		}
	}
	
	@Override
	public int getMorphIndex()
	{
		return scroll - 1;
	}
	
	@Override
	public void scroll(int amount)
	{
		scroll += amount;
	}
	
	@Override
	public void setScroll(int scroll)
	{
		// Remap from -1 to x to 0 to x.
		this.scroll = scroll < 0 ? -1 : scroll + 1;
	}
	
	@Override
	public MorphItem getMorphItem()
	{
		return this.scroll < 0 ? null : morphWidgets.get(scroll).morphItem;
	}
	
	public ArrayList<MorphWidget> getMorphWidgets()
	{
		return morphWidgets;
	}
	
	// This class represents one morph entry on the side of the screen
	public static class MorphWidget
	{		
		public static final int WIDGET_WIDTH = 48;
		public static final int WIDGET_HEIGHT = 64;
		public static final double SCALE_FACTOR = 1.3;
		
		public static final float ENTITY_SCALE_FACTOR = 30;
		
		public static final Quaternion ENTITY_ROTATION = new Quaternion(10, 45, 0, true);
		
		private static final ResourceLocation MORPH_WINDOW_NORMAL = new ResourceLocation(References.MODID, "textures/gui/morph_window_normal.png");
		private static final ResourceLocation MORPH_WINDOW_SELECTED = new ResourceLocation(References.MODID, "textures/gui/morph_window_selected.png");
		private static final ResourceLocation DEMORPH = new ResourceLocation(References.MODID, "textures/gui/demorph.png");
		private static final ResourceLocation FAVOURITE = new ResourceLocation(References.MODID, "textures/gui/favourite_star.png");
		
		Optional<Entity> morphEntity = Optional.empty();
		MorphItem morphItem;
		boolean isFavourite;
		
		public MorphWidget(MorphItem morphItem, boolean isFavourite)
		{
			this.morphItem = morphItem;
			this.isFavourite = isFavourite;
		}
		
		public void render(MatrixStack stack, boolean isSelected)
		{
			RenderSystem.enableBlend();
			Minecraft.getInstance().getTextureManager().bindTexture(isSelected ? MORPH_WINDOW_SELECTED : MORPH_WINDOW_NORMAL);
			AbstractGui.blit(stack, 0, 0, 0, 0, getWidth(), getHeight(), getWidth(), getHeight());
			
			// Draw entity logic
			if(morphItem == null)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(DEMORPH);
				AbstractGui.blit(stack, 0, 0, 0, 0, getWidth(), getHeight(), getWidth(), getHeight());
			}
			else
			{
				if(!morphEntity.isPresent())
					morphEntity = Optional.of(morphItem.createEntity(Minecraft.getInstance().world));
				
			    IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
				
			    AxisAlignedBB aabb = morphEntity.get().getBoundingBox();
			    			    
				stack.push();
				stack.translate(30, 70, 50);
				stack.scale(ENTITY_SCALE_FACTOR, -ENTITY_SCALE_FACTOR, ENTITY_SCALE_FACTOR);
				stack.rotate(ENTITY_ROTATION);
				
				Minecraft.getInstance().getRenderManager().renderEntityStatic(morphEntity.get(), 0, 0, 0, 0, 0, stack, buffer, 15728880);
				
				buffer.finish();
				
				stack.pop();
			}
			
			if(isFavourite)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(FAVOURITE);
				AbstractGui.blit(stack, 7, 7, 0, 0, 16, 16, 16, 16);
			}
		}
		
		public static int getHeight()
		{
			return (int) (WIDGET_HEIGHT * SCALE_FACTOR);
		}
		
		public static int getWidth()
		{
			return (int) (WIDGET_WIDTH * SCALE_FACTOR);
		}
	}
}

package de.budschie.bmorph.capabilities.blacklist;

import java.io.File;
import java.io.IOException;

import de.budschie.bmorph.main.ServerSetup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;

public abstract class WorldConfigHandler
{
	public final String path;
	
	public WorldConfigHandler(String path)
	{
		this.path = path;
	}
	
	public abstract void read(CompoundTag data);
	public abstract CompoundTag write();
	
	public void readFromFile()
	{
		File resolvedPath = new File(ServerSetup.server.storageSource.getWorldDir().toFile(), "morph_blacklist.dat");
		
		if(resolvedPath.exists())
		{
			try
			{
				CompoundTag data = NbtIo.read(resolvedPath);
				
				read(data);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void writeToFile()
	{
		File resolvedPath = new File(ServerSetup.server.storageSource.getWorldDir().toFile(), "morph_blacklist.dat");
		
		CompoundTag serialized = write();
		
		try
		{
			NbtIo.write(serialized, resolvedPath);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

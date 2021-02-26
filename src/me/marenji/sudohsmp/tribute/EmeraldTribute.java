package me.marenji.sudohsmp.tribute;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EmeraldTribute extends Tribute {
  
  public EmeraldTribute( Player player, Block block ) {
    super( player, block );
  }
  
  @Override
  public boolean applyTribute() {
    lightningStrikeTributer();
    destroyTributedBlock( new ItemStack(Material.DIAMOND) );
    return true;
  }
    
}

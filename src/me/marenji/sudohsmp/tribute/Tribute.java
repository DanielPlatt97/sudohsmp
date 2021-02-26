package me.marenji.sudohsmp.tribute;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Tribute {
  
  protected Player tributingPlayer;
  protected Block tributedBlock;
  
  public Tribute( Player player, Block block ) {
    tributingPlayer = player;
    tributedBlock = block;
  }
  
  public static Block getBlockBelowPlayer( Player player ) {
    var location = player.getLocation();
    location.add(0, -1, 0);
    return location.getBlock();
  }
  
  public abstract boolean applyTribute();
  
  protected void lightningStrikeTributer() {
    var location = tributingPlayer.getLocation();
    var world = tributingPlayer.getWorld();
    world.strikeLightningEffect(location);
  }
  
  protected void destroyTributedBlock() {
    destroyTributedBlock( null );
  }
  
  protected void destroyTributedBlock( ItemStack blockDrop ) {
    var location = tributedBlock.getLocation();
    var world = tributedBlock.getWorld();
    tributedBlock.breakNaturally( new ItemStack(Material.DIRT) );
    if ( blockDrop != null ) {
      world.dropItem(location, blockDrop);
    }
  }
  
}

package me.marenji.sudohsmp.tribute;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import me.marenji.sudohsmp.health.PlayerHealthManager;

public class DiamondTribute extends Tribute {
  
  private PlayerHealthManager healthManager;

  public DiamondTribute( Player player, Block block, PlayerHealthManager healthManager ) {
    super( player, block );
    this.healthManager = healthManager;
  }
  
  @Override
  public boolean applyTribute() {
    if ( healthManager.applyDefaultHeartGain(tributingPlayer) ) {
      healthManager.setPlayerHealthToMaxHealth(tributingPlayer);
      lightningStrikeTributer();
      destroyTributedBlock();
      return true;
    } else {
      return false;
    }
  }
  
}

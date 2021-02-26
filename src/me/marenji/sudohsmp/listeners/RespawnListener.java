package me.marenji.sudohsmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.marenji.sudohsmp.Main;
import me.marenji.sudohsmp.health.PlayerHealthManager;
import me.marenji.sudohsmp.util.ChatHelper;

public class RespawnListener implements Listener {
  
  @SuppressWarnings({"unused"})
  private Main plugin;
  private PlayerHealthManager healthManager;
    
  public RespawnListener(Main plugin) {
    this.plugin = plugin;
    this.healthManager = new PlayerHealthManager(plugin);
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }
  
  @EventHandler
  public void onRespawn(PlayerRespawnEvent event) {
    Player player = event.getPlayer();
    
    // apply the status effect 3 ticks after respawn since the player will not be alive yet
    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
      @Override
      public void run() {
        healthManager.applyPenaltyImmunity(player);
        player.addPotionEffect(new PotionEffect(
            PotionEffectType.DAMAGE_RESISTANCE, 5 * 20, 4
        ));
      }
    }, 3L);
    
    player.sendMessage(ChatHelper.chat(
        plugin.getConfig()
          .getString("maxhealth_penaltyrespawn_message")
    ));
  }
  
}

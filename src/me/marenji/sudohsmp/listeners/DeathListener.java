package me.marenji.sudohsmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.marenji.sudohsmp.Main;
import me.marenji.sudohsmp.health.PlayerHealthManager;
import me.marenji.sudohsmp.util.ChatHelper;

public class DeathListener implements Listener {
  
  @SuppressWarnings({"unused"})
  private Main plugin;
  private PlayerHealthManager healthManager;
    
  public DeathListener(Main plugin) {
    this.plugin = plugin;
    this.healthManager = new PlayerHealthManager(plugin);
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }
  
  @EventHandler
  public void onDeath(PlayerDeathEvent event) {
    Player player = event.getEntity();
    if ( healthManager.isPlayerImmuneToPenalty(player) ) {
      player.sendMessage(ChatHelper.chat(
          plugin.getConfig()
            .getString("maxhealth_immunepenalty_message")
      ));
    } else {
      if ( healthManager.applyDefaultHeartLoss(player) ) {
        player.sendMessage(ChatHelper.chat(
            plugin.getConfig()
              .getString("maxhealth_healthlost_message")
        ));
      } else {
        player.sendMessage(ChatHelper.chat(
            plugin.getConfig()
              .getString("maxhealth_healthminimum_message")
        ));
      }
    }

  }
  
}

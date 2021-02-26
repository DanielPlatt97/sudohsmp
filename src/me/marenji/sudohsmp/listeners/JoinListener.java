package me.marenji.sudohsmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.marenji.sudohsmp.Main;
import me.marenji.sudohsmp.health.PlayerHealthManager;
import me.marenji.sudohsmp.util.ChatHelper;

public class JoinListener implements Listener {
  
  @SuppressWarnings({"unused"})
  private Main plugin;
  private PlayerHealthManager healthManager;
    
  public JoinListener(Main plugin) {
    this.plugin = plugin;
    this.healthManager = new PlayerHealthManager(plugin);
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }
    
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    if ( !player.hasPlayedBefore() ) {
      healthManager.setMaxHearts(player, plugin.getConfig().getInt("startinghearts"));
      healthManager.setPlayerHealthToMaxHealth(player);
      healthManager.applyPenaltyImmunity(player);
      player.sendMessage(ChatHelper.chat(
          plugin.getConfig()
            .getString("maxhealth_penaltyjoined_message")
      ));
    }
  }
    
}
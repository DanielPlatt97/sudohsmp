package me.marenji.sudohsmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import me.marenji.sudohsmp.Main;
import me.marenji.sudohsmp.health.PlayerHealthManager;
import me.marenji.sudohsmp.tribute.DiamondTribute;
import me.marenji.sudohsmp.tribute.EmeraldTribute;
import me.marenji.sudohsmp.tribute.TributeFactory;
import me.marenji.sudohsmp.util.ChatHelper;

public class GoldenFoodListener implements Listener {

  @SuppressWarnings({ "unused" })
  private Main plugin;
  private PlayerHealthManager healthManager;

  public GoldenFoodListener(Main plugin) {
    this.plugin = plugin;
    this.healthManager = new PlayerHealthManager(plugin);
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onConsumeGoldenApple(PlayerItemConsumeEvent event) {
    var itemStack = event.getItem();
    var foodType = itemStack.getType();
    var player = event.getPlayer();
    var tribute = TributeFactory.getTribute(player, foodType, healthManager);
    if (tribute == null) {
      return;
    }

    var tributeSuccess = tribute.applyTribute();
    if (tributeSuccess) {
      if (tribute instanceof DiamondTribute) {
        player.sendMessage(ChatHelper.chat(plugin.getConfig().getString("diamondtribute_success_message")));
      } else if (tribute instanceof EmeraldTribute) {
        player.sendMessage(ChatHelper.chat(plugin.getConfig().getString("emeraldtribute_success_message")));
      }
    } else {
      if (tribute instanceof DiamondTribute) {
        player.sendMessage(ChatHelper.chat(plugin.getConfig().getString("diamondtribute_fail_message")));
      }
    }
  }

}

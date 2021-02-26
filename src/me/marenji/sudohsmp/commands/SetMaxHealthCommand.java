package me.marenji.sudohsmp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.marenji.sudohsmp.util.ChatHelper;
import me.marenji.sudohsmp.util.PermissionsHelper;
import me.marenji.sudohsmp.Main;
import me.marenji.sudohsmp.health.PlayerHealthManager;

public class SetMaxHealthCommand implements CommandExecutor {
  
  //@SuppressWarnings({"unused"})
  private Main plugin;
  private PlayerHealthManager healthManager;
  
  public SetMaxHealthCommand(Main plugin) {
    this.plugin = plugin;
    this.healthManager = new PlayerHealthManager(plugin);
    plugin.getCommand("maxhealth").setExecutor(this);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if ( !PermissionsHelper.isSenderAdmin(sender) ) {
      sender.sendMessage(ChatHelper.chat(
          plugin.getConfig().getString("notadmin_message")
      ));
      return false;
    }
    
    // Getting command arguments
    String playerString = null;
    String heartsString = null;
    if ( args.length == 1 ) {
      heartsString = args[0];
    } else if ( args.length >= 2) {
      playerString = args[0];
      heartsString = args[1];
    }
    
    // no arguments specified
    if ( heartsString == null ) {
      sender.sendMessage(ChatHelper.chat(
          plugin.getConfig().getString("maxhealth_invalidargs_message")
      ));
      return false;
    }
    
    int hearts;
    try {
      hearts = Integer.parseInt(heartsString);
    } catch (Exception e) {
      sender.sendMessage(ChatHelper.chat(
          plugin.getConfig().getString("maxhealth_invalidargs_message")
      ));
      return false;
    }
    
    Player targetPlayer = null;
    if ( playerString == null) { // no player specified
      if ( sender instanceof Player ) {
        targetPlayer = (Player)sender;
      } else {
        playerString = "Unknown(probably console)";
      }
    } else {
      targetPlayer = Bukkit.getPlayerExact(playerString);
    }
    if ( targetPlayer == null ) {
      sender.sendMessage(ChatHelper.chat(
          plugin.getConfig()
            .getString("playernotfound_message")
            .replace("<player>", playerString)
      ));
      return false;
    }
    
    var success = healthManager.setMaxHearts(targetPlayer, hearts);
    if ( success ) {
      sender.sendMessage(ChatHelper.chat(
          plugin.getConfig()
            .getString("maxhealth_setsuccess_message")
            .replace("<player>", playerString)
      ));
      targetPlayer.sendMessage(ChatHelper.chat(
          plugin.getConfig()
            .getString("maxhealth_healthset_message")
      ));
      return true;
    } else {
      sender.sendMessage(ChatHelper.chat(
          plugin.getConfig()
            .getString("maxhealth_invalidhearts_message")
      ));
      return false;
    }
    
  }
  
}

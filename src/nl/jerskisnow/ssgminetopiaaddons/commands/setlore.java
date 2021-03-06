package nl.jerskisnow.ssgminetopiaaddons.commands;

import nl.jerskisnow.ssgminetopiaaddons.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Setlore implements CommandExecutor {

    Main main;
    public Setlore(Main plugin) { main = plugin; }

    @SuppressWarnings({ "deprecation", "unchecked", "rawtypes", "unused" })
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("setlore")) {
            if(sender.hasPermission("ssgminetopia.admin")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[&bSSG&f] &9Gebruik /rename of /setlore"));
                    return true;
                }

                ItemStack item = ((HumanEntity) sender).getItemInHand();
                ItemMeta itemMeta = item.getItemMeta();
                String message = args[0];

                for (int i = 1; i < args.length; i++) {
                    message = message + " " + args[i];
                }

                message = ChatColor.translateAlternateColorCodes('&', message);
                ArrayList itemLore = new ArrayList();
                itemLore.add(message);
                itemMeta.setLore(itemLore);

                item.setItemMeta(itemMeta);
                ((HumanEntity) sender).getItemInHand().setItemMeta(itemMeta);
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[&bSSG&f] &9Je hebt de lore van het item veranderd naar: &r" + message));
            }else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("NoPermissions")));
            }
        }

        return true;
    }
}

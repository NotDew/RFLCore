package me.notdew.com.rflcore.Ball;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class qbModeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        ItemStack ball = new ItemStack(Material.SNOWBALL);
        ItemMeta bmeta = ball.getItemMeta();
        bmeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "1 Football");
        ball.setItemMeta(bmeta);
        p.getInventory().addItem(ball);
        ItemStack ball2 = new ItemStack(Material.SNOWBALL);
        ItemMeta bmeta2 = ball2.getItemMeta();
        bmeta2.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "2 Football");
        ball2.setItemMeta(bmeta2);
        p.getInventory().addItem(ball2);
        ItemStack ball3 = new ItemStack(Material.SNOWBALL);
        ItemMeta bmeta3 = ball3.getItemMeta();
        bmeta3.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "3 Football");
        ball3.setItemMeta(bmeta3);
        p.getInventory().addItem(ball3);
        ItemStack ball4 = new ItemStack(Material.SNOWBALL);
        ItemMeta bmeta4 = ball4.getItemMeta();
        bmeta4.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "4 Football");
        ball4.setItemMeta(bmeta4);
        p.getInventory().addItem(ball4);
        


        return false;
    }
}

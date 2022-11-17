package me.notdew.com.rflcore.Ball;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class dropEvent implements Listener {
    public static HashMap<Player, Integer> lvl = new HashMap<Player, Integer>();

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        /*Player p = e.getPlayer();
        Item ball = e.getItemDrop();

        if (ball.getItemStack().getType().equals(Material.SNOWBALL)) {
            e.setCancelled(true);
            if (!(lvl.containsKey(p))) lvl.put(p, 1);

            if (getTotalExperience(p) >= 40.1) {
                setTotalExperience(p, 7);

            } else if (getTotalExperience(p) >= 27.1) {
                setTotalExperience(p, 40);


            } else if (getTotalExperience(p) >= 16.1) {
                setTotalExperience(p, 27);


            } else if (getTotalExperience(p) >= 7.1) {
                setTotalExperience(p, 16);

            }

        }*/




    }

    @EventHandler
    public void itemSwitch(PlayerItemHeldEvent e) {
        /*Player p = e.getPlayer();
        ItemStack ball = new ItemStack(Material.SNOWBALL);
        ItemMeta bmeta = ball.getItemMeta();
        bmeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "1 Football");
        ball.setItemMeta(bmeta);

        ItemStack ball2 = new ItemStack(Material.SNOWBALL);
        ItemMeta bmeta2 = ball2.getItemMeta();
        bmeta2.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "2 Football");
        ball2.setItemMeta(bmeta2);

        ItemStack ball3 = new ItemStack(Material.SNOWBALL);
        ItemMeta bmeta3 = ball3.getItemMeta();
        bmeta3.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "3 Football");
        ball3.setItemMeta(bmeta3);

        ItemStack ball4 = new ItemStack(Material.SNOWBALL);
        ItemMeta bmeta4 = ball4.getItemMeta();
        bmeta4.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "4 Football");
        ball4.setItemMeta(bmeta4);
        if (e.getPlayer().getInventory().getItem(e.getNewSlot()) == null) return;

        if (e.getPlayer().getInventory().getItem(e.getNewSlot()).equals(ball)) {
            e.getPlayer().setExp(0.25f);
            lvl.put(p, 1);
        } else if (e.getPlayer().getInventory().getItem(e.getNewSlot()).equals(ball2)) {
            e.getPlayer().setExp(0.5f);
            lvl.put(p, 2);
        } else if (e.getPlayer().getInventory().getItem(e.getNewSlot()).equals(ball3)) {
            e.getPlayer().setExp(.75f);
            lvl.put(p, 3);
        } else if (e.getPlayer().getInventory().getItem(e.getNewSlot()).equals(ball4)) {
            e.getPlayer().setExp(.99f);
            lvl.put(p, 4);
        }

*/

    }
    public static int getTotalExperience(int level) {
        int xp = 0;

        if (level >= 0 && level <= 15) {
            xp = (int) Math.round(Math.pow(level, 2) + 6 * level);
        } else if (level > 15 && level <= 30) {
            xp = (int) Math.round((2.5 * Math.pow(level, 2) - 40.5 * level + 360));
        } else if (level > 30) {
            xp = (int) Math.round(((4.5 * Math.pow(level, 2) - 162.5 * level + 2220)));
        }
        return xp;
    }

    public static int getTotalExperience(Player player) {
        return Math.round(player.getExp() * player.getExpToLevel()) + getTotalExperience(player.getLevel());
    }

    public void setTotalExperience(Player player, int amount) {
        int level = 0;
        int xp = 0;
        float a = 0;
        float b = 0;
        float c = -amount;

        if (amount > getTotalExperience(0) && amount <= getTotalExperience(15)) {
            a = 1;
            b = 6;
        } else if (amount > getTotalExperience(15) && amount <= getTotalExperience(30)) {
            a = 2.5f;
            b = -40.5f;
            c += 360;
        } else if (amount > getTotalExperience(30)) {
            a = 4.5f;
            b = -162.5f;
            c += 2220;
        }
        level = (int) Math.floor((-b + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a));
        xp = amount - getTotalExperience(level);
        player.setLevel(level);
        player.setExp(0);
        player.giveExp(xp);
        player.setExp((float).249*lvl.get(player));
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        //lvl.put(e.getPlayer(), 1);
        //setTotalExperience(e.getPlayer(), 7);


    }



}

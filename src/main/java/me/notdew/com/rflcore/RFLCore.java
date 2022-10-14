package me.notdew.com.rflcore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.notdew.com.rflcore.Ball.*;
import static me.notdew.com.rflcore.Field.FieldCommand.fieldnames;
import me.notdew.com.rflcore.Field.FieldCommand;
import me.notdew.com.rflcore.Field.FieldObject;
import me.notdew.com.rflcore.Game.GameCommand;
import me.notdew.com.rflcore.Game.StartGame;
import me.notdew.com.rflcore.Roster.RosterCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.*;

public final class RFLCore extends JavaPlugin {

    public static RFLCore instance;
    public static RFLCore getInstance() {
        return instance;
    }
    public static FileConfiguration config;

    public static List<String> Teams;


    @Override
    public void onEnable() {
        Gson gson = new Gson();
        instance = this;
        Teams = this.getConfig().getStringList("Teams");
        config = instance.getConfig();
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(new dropEvent(), this);
        this.getServer().getPluginManager().registerEvents(new throwBall(), this);
        this.getServer().getPluginManager().registerEvents(new snowballLand(), this);
        this.getServer().getPluginCommand("field").setExecutor(new FieldCommand());
        this.getServer().getPluginCommand("ballcam").setExecutor(new ballCam());
        this.getServer().getPluginCommand("qbmode").setExecutor(new qbModeCommand());
        this.getServer().getPluginCommand("game").setExecutor(new GameCommand());
        this.getServer().getPluginCommand("roster").setExecutor(new RosterCommand());
        this.getServer().getPluginCommand("startgame").setExecutor(new StartGame());

        ConfigurationSection sec = this.getConfig().getConfigurationSection("fields");
        if (!(sec == null)) {
            for(String key : sec.getKeys(false)){
                ArrayList<Object> raw= (ArrayList<Object>) this.getConfig().getList("fields." + key);
                System.out.println(raw);
                FieldObject field = new FieldObject(raw.toArray());
                fieldnames.put(field.getName(), field);
            }
        }






    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println(FieldCommand.fieldnames.toString());

        for (String key : FieldCommand.fieldnames.keySet()) {
            this.getConfig().set("fields." +key,  fieldnames.get(key).serialize());
        }
        this.saveConfig();


    }
}

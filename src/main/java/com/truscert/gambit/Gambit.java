package com.truscert.gambit;

import com.truscert.gambit.command.*;
import com.truscert.gambit.game.data.ConfigData;
import com.truscert.gambit.listener.GameListener;
import com.truscert.gambit.listener.MenuListener;
import com.truscert.gambit.manager.RoomManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public final class Gambit extends JavaPlugin {
    private static Gambit plugin;
    public static Gambit getInstance() {
        return plugin;
    }

    public final static boolean DEBUGGABLE = true;
    public final static String DEBUGNOTICE = "§l§e[§6DEBUG§e]§f ";
    public final static String PREFIX = "§l§6[Gambit] ";
    HashMap<String, Class<?>> dataMap = new HashMap<String, Class<?>>();

    @Override
    public void onEnable(){
        plugin = this;
        if(!getDataFolder().exists())
            getDataFolder().mkdir();

        if(getConfig() == null)
            saveDefaultConfig();

        Bukkit.getConsoleSender().sendMessage(PREFIX + "§aStarting...");

        initConfig();
        initCommand();

        getServer().getPluginManager().registerEvents(new GameListener(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        RoomManager.getManager().loadGames();

        if (DEBUGGABLE) {
            debug();
        }
    }

    private void initConfig() {
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§a +加载配置文件...");
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§a |  检查文件...");
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§a |  存入内存...");
        ConfigData.getData().roomCenter =
                plugin.getConfig().getSerializable("Center_point", Location.class);
        ConfigData.getData().roomTeam1 =
                plugin.getConfig().getSerializable("Team1_point", Location.class);
        ConfigData.getData().roomTeam2 =
                plugin.getConfig().getSerializable("Team2_point", Location.class);
    }

    /**
     * This fuction
     *
     */
    private void initCommand() {
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§a +初始化命令...");
        //CommandExecutor CE = new Command();

        dataMap.put("gambit", Create.class);
        dataMap.put("debug", Debug.class);
        dataMap.put("menu", Menu.class);
        dataMap.put("leave", Leave.class);
        dataMap.put("join", Join.class);
        dataMap.put("remove", Remove.class);
        dataMap.put("create", Create.class);

        dataMap.forEach((k, v) -> {
            CommandExecutor CE = null;
            try {
                CE = (CommandExecutor) v.getDeclaredConstructor().newInstance();
            } catch (
                    InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e
            ) {
                e.printStackTrace();
            }
            Bukkit.getConsoleSender().sendMessage(PREFIX + "§a |  注册命令：" + k);
            Bukkit.getPluginCommand(k + "").setExecutor(CE);
        });
    }

    @Override
    public void onDisable(){
        saveConfig();
    }

    private void debug() {
        //plugin.getConfig().notifyAll();
        Bukkit.getServer().getConsoleSender().sendMessage("§e#WARNING# The plugin is running in debug mode! The \"/debug\" command now available.");
        Bukkit.getServer().getConsoleSender().sendMessage(DEBUGNOTICE + "DataFolder: " + "\"" + Gambit.getInstance().getDataFolder().toString() + "\"");
        Bukkit.getServer().getConsoleSender().sendMessage(DEBUGNOTICE + "CT_SC: " + ConfigData.getData().roomCenter);
        Bukkit.getServer().getConsoleSender().sendMessage(DEBUGNOTICE + "CT_GT: " + plugin.getConfig().getSerializable("Center_point", Location.class));
        Bukkit.getServer().getConsoleSender().sendMessage(DEBUGNOTICE + "T1_SC: " + ConfigData.getData().roomTeam1);
        Bukkit.getServer().getConsoleSender().sendMessage(DEBUGNOTICE + "T1_GT: " + plugin.getConfig().getSerializable("Team1_point", Location.class));
        Bukkit.getServer().getConsoleSender().sendMessage(DEBUGNOTICE + "T2_SC: " + ConfigData.getData().roomTeam2);
        Bukkit.getServer().getConsoleSender().sendMessage(DEBUGNOTICE + "T2_GT: " + plugin.getConfig().getSerializable("Team2_point", Location.class));
    }
}

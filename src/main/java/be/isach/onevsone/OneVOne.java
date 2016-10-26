package be.isach.onevsone;

import be.isach.onevsone.arena.ArenaManager;
import be.isach.onevsone.command.SubCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class OneVOne extends JavaPlugin {

    private ArenaManager arenaManager;
    private SubCommandManager subCommandManager;

    @Override
    public void onEnable() {
        this.arenaManager = new ArenaManager(this);
        this.arenaManager.loadArenas();

        this.subCommandManager = new SubCommandManager(this);
    }

    @Override
    public void onDisable() {
    }
}

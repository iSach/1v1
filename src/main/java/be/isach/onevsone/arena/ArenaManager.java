package be.isach.onevsone.arena;

import be.isach.onevsone.OneVOne;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sacha on 26-10-16.
 */
public class ArenaManager {

    private OneVOne instance;
    private List<Arena> arenas;
    private File arenasDir;

    public ArenaManager(OneVOne instance) {
        this.instance = instance;
        this.arenas = new ArrayList<>();
        this.arenasDir = new File(this.instance.getDataFolder(), "arenas");

        if(!arenasDir.exists()) {
            arenasDir.mkdirs();
        }
    }

    private Arena loadArena() {
        return null;
    }

    private void saveArena() {

    }

    public void loadArenas() {
    }

    public void saveArenas() {

    }

}

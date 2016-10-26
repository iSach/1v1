package be.isach.onevsone.arena;

import be.isach.onevsone.player.CustomPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sacha on 26-10-16.
 */
public class Arena {

    /**
     * Players in the arena.
     * Can't exceed 2.
     */
    private List<CustomPlayer> players;

    /**
     * Arena Data.
     */
    private ArenaData data;

    /**
     * Arena Countdown.
     */
    private Countdown countdown;

    public Arena(ArenaData arenaData) {
        this.players = new ArrayList<>();
        this.data = arenaData;
        this.countdown = new Countdown();
    }

    /**
     * @return the countdown.
     */
    public Countdown getCountdown() {
        return countdown;
    }

    /**
     * @return the data.
     */
    public ArenaData getData() {
        return data;
    }

    /**
     * @return the in game players.
     */
    public List<CustomPlayer> getPlayers() {
        return players;
    }
}

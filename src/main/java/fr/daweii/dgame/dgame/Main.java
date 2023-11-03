package fr.daweii.dgame.dgame;

import fr.daweii.dgame.dgame.Listeners.DamageListeners;
import fr.daweii.dgame.dgame.Listeners.PlayerListeners;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    public ArrayList<Player> playersWating = new ArrayList<>();
    public ArrayList<Player> playersInGame = new ArrayList<>();
    public ArrayList<Player> playersEliminer = new ArrayList<>();


    private State state;

    public void setState(State state) { this.state = state; }

    public boolean isState(State state) { return this.state == state; }

    public List<Player> getPlayersWating() {return playersWating;}
    public List<Player> getPlayerInGame() {return playersInGame;}
    public List<Player> getPlayersEliminer() {return playersEliminer;}


    @Override
    public void onEnable() {
        // Plugin startup logic

        setState(State.WAITING);

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new PlayerListeners(this), this);
        pm.registerEvents(new DamageListeners(this), this);

        System.out.println("ON 00:04");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void eliminate(Player player) {
        if (playersInGame.contains(player)) { playersInGame.remove(player); }
        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage("Tu est NUL !!!");
        getPlayerInGame().remove(player);
        getPlayersEliminer().add(player);
        chekWin();
    }

    public void chekWin() {
        System.out.println("chekWin Ok");
        System.out.println(playersInGame.size());
        System.out.println(playersEliminer.size());

        if (playersInGame.size() == 1) {

            Player winner = playersInGame.get(0);

            Bukkit.broadcast(
                    Component.text(winner.getName() +" a gagner la parties !")
            );

            for (Player player : playersInGame) {
                playersInGame.remove(player);
            }

            for (Player player : playersEliminer) {
                playersEliminer.remove(player);
            }

            setState(State.WAITING);
        }
    }
}

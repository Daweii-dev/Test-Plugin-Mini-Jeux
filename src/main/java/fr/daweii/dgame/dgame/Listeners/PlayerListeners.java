package fr.daweii.dgame.dgame.Listeners;

import fr.daweii.dgame.dgame.Main;
import fr.daweii.dgame.dgame.State;
import fr.daweii.dgame.dgame.Tasks.AutoStart;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListeners implements Listener {

    private Main main;

    public PlayerListeners(Main main) {
        this.main = main;
    }

    public Location atente = new Location(Bukkit.getWorld("world"), 197, 65, 47, 0, 0);

    @EventHandler
    public void  onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        player.setGameMode(GameMode.ADVENTURE);
        player.teleport(atente);

        if (!main.isState(State.WAITING)) {

            player.sendMessage("Une Partie est en cours !");
            event.joinMessage(null);

            return;
        }

        if (!main.getPlayersWating().contains(player)) { main.getPlayersWating().add(player); }

        System.out.println(main.playersWating.size());

        if (main.isState(State.WAITING) && main.getPlayersWating().size() == 2) {

            AutoStart start = new AutoStart(main);
            start.runTaskTimer(main, 0, 20);

            main.setState(State.STARTING);

        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        if (main.getPlayersWating().contains(player)) {

            main.getPlayersWating().remove(player);

        } else if (main.getPlayerInGame().contains(player)) {

            main.getPlayerInGame().remove(player);

        } else if (main.getPlayersEliminer().contains(player)) {

            main.getPlayersEliminer().remove(player);

        }
    }
}

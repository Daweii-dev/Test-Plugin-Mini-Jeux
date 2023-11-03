package fr.daweii.dgame.dgame.Tasks;

import fr.daweii.dgame.dgame.Main;
import fr.daweii.dgame.dgame.State;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoStart extends BukkitRunnable {

    private Main main;

    public AutoStart(Main main) { this.main = main; }

    private int timer = 10;

    private void azerty(){
        for (Player playerinwating : main.getPlayersWating()) {
            main.getPlayersWating().remove(playerinwating);
            main.getPlayerInGame().add(playerinwating);
            System.out.println(main.playersInGame.size());
        }
        System.out.println(main.playersInGame.size());
    }

    @Override
    public void run() {

        for (Player player : main.getPlayersWating()) {
                player.setLevel(timer);
        }

        if (timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1 ) {

            for (Player player : main.getPlayersWating()) {
                player.sendMessage("Lancement dans " + timer + "s");
            }
        }

        if (timer == 0){
            Bukkit.broadcast(
                    Component.text("Lancement du jeux")
            );

            Player player1 = main.getPlayersWating().get(0);
            Player player2 = main.getPlayersWating().get(1);

            main.getPlayersWating().remove(player1);
            main.getPlayersWating().remove(player2);
            main.getPlayerInGame().add(player1);
            main.getPlayerInGame().add(player2);

            main.setState(State.PLAYING);
            System.out.println(main.playersInGame.size());

            GameCycle cycle = new GameCycle(main);
            cycle.runTaskTimer(main, 0, 20);

            cancel();
        }

        timer--;

    }
}

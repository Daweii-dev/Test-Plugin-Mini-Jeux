package fr.daweii.dgame.dgame.Tasks;

import fr.daweii.dgame.dgame.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameCycle extends BukkitRunnable {

    private Main main;

    public GameCycle(Main main) { this.main = main; }

    private int timer = 30;

    @Override
    public void run() {

        main.chekWin();

        if (timer == 0) {

            for (Player player : main.getPlayerInGame()) {
                Bukkit.broadcast(
                        Component.text("Fin de Partie")
                );
            }
            cancel();
        }
        timer--;

    }
}

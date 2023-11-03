package fr.daweii.dgame.dgame.Listeners;

import fr.daweii.dgame.dgame.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListeners implements Listener {

    private Main main;

    public DamageListeners(Main main){
        this.main = main;
    }

    @EventHandler
    public void onDomage(EntityDamageEvent event) {

        Entity victime = event.getEntity();

        if (victime instanceof Player) {

            Player player = (Player) victime;

            if (player.getHealth() <= event.getDamage()) {
                event.setDamage(0);
                main.eliminate(player);
            }
        }
    }
}

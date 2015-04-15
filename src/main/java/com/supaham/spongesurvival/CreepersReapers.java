package com.supaham.spongesurvival;

import com.google.common.base.Optional;

import com.flowpowered.math.vector.Vector3d;

import org.spongepowered.api.Game;
import org.spongepowered.api.data.manipulators.catalogs.CatalogEntityData;
import org.spongepowered.api.data.manipulators.entities.FuseData;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.Item;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.EntityDeathEvent;
import org.spongepowered.api.world.World;

/**
 * Creeper death listener that drops one more gunpowder depending on how low its fuse time is.
 */
public class CreepersReapers {

  private final Game game;

  public CreepersReapers(Game game) {
    this.game = game;
  }

  @Subscribe
  public void onCreeperDeath(EntityDeathEvent event) {
    Entity entity = event.getEntity();
    if (entity.getType().equals(EntityTypes.CREEPER)) {
      Optional<FuseData> data = entity.getData(CatalogEntityData.FUSE_DATA);
      if (data.isPresent()) {
        int fuseDuration = data.get().getFuseDuration();
        if (fuseDuration <= 60) {
          int itemAmount = 5; // in sync with (ticks - 10 / 10) in the if statement above
          while (fuseDuration > 10) {
            itemAmount--;
            fuseDuration -= 10;
          }

          World world = entity.getWorld();
          Vector3d position = entity.getLocation().getPosition().add(0, 1, 0);
          Item item = (Item) world.createEntity(EntityTypes.DROPPED_ITEM, position).get();
          // TODO Set Item to gunpowder
          world.spawnEntity(item);
        }
      }
    }
  }
}

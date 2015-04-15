package com.supaham.spongesurvival;

import com.google.inject.Inject;

import com.supaham.spongesurvival.commands.AmISurvivalCommand;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.ServerStartedEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.service.event.EventManager;

@Plugin(id = "spongesurvival", name = "SpongeSurvival", version = "1.0")
public class SpongeSurvivalPlugin {

  @Inject
  private Logger logger;
  @Inject
  private Game game;
  @Inject
  private PluginContainer instance;

  @Subscribe
  public void onServerStart(ServerStartedEvent event) {
    // Hey! The server has started!
    // Try instantiating your logger in here.
    // (There's a guide for that)

    game.getCommandDispatcher().register(instance, new AmISurvivalCommand(), "amisurvival");
    EventManager eventManager = game.getServiceManager().provide(EventManager.class).get();
    eventManager.register(instance, new CreepersReapers());
  }
}

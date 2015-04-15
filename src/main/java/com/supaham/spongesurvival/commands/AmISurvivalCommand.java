package com.supaham.spongesurvival.commands;

import org.spongepowered.api.data.manipulators.catalogs.CatalogEntityData;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.entity.player.gamemode.GameMode;
import org.spongepowered.api.entity.player.gamemode.GameModes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandSource;

import java.util.Collections;
import java.util.List;

public class AmISurvivalCommand implements CommandCallable {
  
  @Override
  public boolean call(CommandSource source, String arguments, List<String> parents)
      throws CommandException {
    if(!(source instanceof Player)) {
      source.sendMessage(Texts.of("Error! Error! Error! Gamemode unknown."));
      return true;
    }
    GameMode gameMode = ((Player) source).getData(CatalogEntityData.GAME_MODE_DATA).get()
        .getGameMode();
    if (gameMode.equals(GameModes.SURVIVAL)) {
      source.sendMessage(Texts.of(TextColors.YELLOW + "You are in Survival mode."));
    } else {
      source.sendMessage(Texts.of(TextColors.RED + "You are in " + gameMode.getId() + " mode."));
    }
    return true;
  }

  @Override
  public boolean testPermission(CommandSource source) {
    return true;
  }

  @Override
  public String getShortDescription(CommandSource source) {
    return "Checks whether the command sender is in survival mode.";
  }

  @Override
  public Text getHelp(CommandSource source) {
    return Texts.of("Checks whether the command sender is in survival mode.");
  }

  @Override
  public String getUsage(CommandSource source) {
    return "/<command>";
  }

  @Override
  public List<String> getSuggestions(CommandSource source, String arguments)
      throws CommandException {
    return Collections.emptyList();
  }
}

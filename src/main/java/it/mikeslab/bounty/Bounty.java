package it.mikeslab.bounty;

import it.mikeslab.bounty.config.ConfigKey;
import it.mikeslab.bounty.config.LanguageKey;
import it.mikeslab.commons.LabCommons;
import it.mikeslab.commons.api.config.Configurable;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public final class Bounty extends JavaPlugin {

    private LabCommons labCommons;
    private Configurable language, customConfig;

    @Override
    public void onEnable() {

        this.labCommons = new LabCommons();

        this.labCommons.initialize(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    private void initConfig() {

        // default config
        File configFile = new File(getDataFolder(), "config.yml");

        File languageConfigFile = new File(getDataFolder(), "language.yml");

        save(languageConfigFile.getName(), false);
        save(configFile.getName(), false);

        this.language = LabCommons.registerConfigurable(LanguageKey.class)
                .loadConfiguration(languageConfigFile);

        this.customConfig = LabCommons.registerConfigurable(ConfigKey.class)
                .loadConfiguration(configFile);

    }

    private void save(String resource, boolean replace) {
        if(!new File(getDataFolder(), resource).exists() || replace) {
            saveResource(resource, replace);
        }
    }
}

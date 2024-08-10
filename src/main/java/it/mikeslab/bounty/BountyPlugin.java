package it.mikeslab.bounty;

import it.mikeslab.bounty.config.ConfigKey;
import it.mikeslab.bounty.config.LanguageKey;
import it.mikeslab.bounty.handler.SetupCacheHandler;
import it.mikeslab.bounty.inventory.action.ActionRegistrarImpl;
import it.mikeslab.bounty.inventory.config.GuiConfigRegistrar;
import it.mikeslab.bounty.inventory.config.condition.ConditionParserImpl;
import it.mikeslab.commons.LabCommons;
import it.mikeslab.commons.api.config.Configurable;
import it.mikeslab.commons.api.inventory.config.ConditionParser;
import it.mikeslab.commons.api.inventory.config.GuiConfig;
import it.mikeslab.commons.api.inventory.event.GuiListener;
import it.mikeslab.commons.api.inventory.factory.GuiFactory;
import it.mikeslab.commons.api.inventory.factory.GuiFactoryImpl;
import it.mikeslab.commons.api.inventory.util.action.ActionHandler;
import it.mikeslab.commons.api.inventory.util.action.ActionHandlerImpl;
import it.mikeslab.commons.api.inventory.util.action.ActionRegistrar;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public final class BountyPlugin extends JavaPlugin {

    public static final Set<String> INVENTORY_IDENTIFIERS = new HashSet<>();

    private LabCommons labCommons;
    private Configurable language, customConfig;

    private GuiConfigRegistrar guiConfigRegistrar;

    private Map<String, GuiConfig> cachedGuiConfig;

    private ActionHandler actionHandler;

    private ConditionParser conditionParser;

    private GuiFactory guiFactory;

    private GuiListener guiListener;

    private SetupCacheHandler setupCacheHandler;

    private BukkitAudiences audiences;


    @Override
    public void onEnable() {

        this.labCommons = new LabCommons();

        this.labCommons.initialize(this);

        this.audiences = BukkitAudiences.create(this);

        this.initConfig();

        this.initInventories();

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

    private void initInventories() {

        this.setupCacheHandler = new SetupCacheHandler();

        this.cachedGuiConfig = new HashMap<>();

        this.initActions();

        if(guiFactory == null) {
            this.guiFactory = new GuiFactoryImpl(this);
        }

        if(this.guiListener == null)
            this.guiListener = new GuiListener(guiFactory, this);

        // from config
        this.guiConfigRegistrar = new GuiConfigRegistrar(
                this,
                Section.GUIS.getFieldName()
        );

        this.guiConfigRegistrar.register();

        INVENTORY_IDENTIFIERS.addAll(
                guiConfigRegistrar.getInventoryKeys()
        );

        this.guiFactory.setActionHandler(actionHandler);
        this.guiFactory.setConditionParser(conditionParser);
        this.guiFactory.setInventoryMap(this.getGuiConfigRegistrar().getPlayerInventories());

    }


    private void initActions() {

        ActionRegistrar actionRegistrar = new ActionRegistrarImpl(this);

        this.actionHandler = new ActionHandlerImpl(
                actionRegistrar.loadActions()
        );

        this.conditionParser = new ConditionParserImpl();

    }


    @Getter
    @RequiredArgsConstructor
    private enum Section {

        GUIS("guis");

        private final String fieldName;

    }

}

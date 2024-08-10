package it.mikeslab.bounty.inventory.impl;

import it.mikeslab.bounty.Bounty;
import it.mikeslab.bounty.inventory.impl.template.GuiTemplate;
import it.mikeslab.commons.api.inventory.pojo.GuiContext;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainMenu extends GuiTemplate {

    public MainMenu(Bounty instance, GuiContext guiContext) {
        super(instance, guiContext);
    }

}

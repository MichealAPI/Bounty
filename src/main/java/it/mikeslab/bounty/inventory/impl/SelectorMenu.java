package it.mikeslab.bounty.inventory.impl;

import it.mikeslab.bounty.BountyPlugin;
import it.mikeslab.bounty.inventory.action.ActionListener;
import it.mikeslab.bounty.inventory.impl.template.GuiTemplate;
import it.mikeslab.commons.api.inventory.pojo.GuiContext;

import java.util.Optional;

public class SelectorMenu extends GuiTemplate implements ActionListener {

    public SelectorMenu(final BountyPlugin instance, GuiContext context) {
        super(instance, context);

        this.injectAction(
                instance,
                "select",
                this.handleSelection(
                        instance,
                        Optional.empty(),
                        true,
                        Optional.empty()
                )
        );
    }


}

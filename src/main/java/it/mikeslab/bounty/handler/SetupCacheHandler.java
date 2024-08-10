package it.mikeslab.bounty.handler;

import it.mikeslab.bounty.BountyPlugin;
import it.mikeslab.bounty.inventory.pojo.Bounty;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SetupCacheHandler {

    private final Map<UUID, Bounty> cacheMap = new HashMap<>();

    /**
     * Initialize the setup for the player
     * @param instance the IdentityPlugin instance
     * @param target the player to set up
     */
    public void initSetup(BountyPlugin instance, Player target) {

        UUID targetUUID = target.getUniqueId();

        String fallBackIdentifier = instance
                .getGuiConfigRegistrar()
                .getFallbackGuiIdentifier();

        Bukkit.getScheduler().runTaskLater(instance, () -> {
                    instance.getGuiConfigRegistrar()
                            .getPlayerInventories()
                            .getInventory(targetUUID, fallBackIdentifier)
                            .show(target);
                }, 1L);

        Bounty cachedBounty = new Bounty(targetUUID);

        cacheMap.put(targetUUID, cachedBounty);

    }

    /**
     * Get the identity of the player
     * @param uuid the player's UUID
     * @return the player's identity
     */
    public Bounty getCachedBounty(UUID uuid) {
        return cacheMap.getOrDefault(uuid, null); // this has to return a default of
                                                                // null to confirm that an identity effectively doesn't exist
    }

    /**
     * Update the identity of the player
     * @param uuid the player's UUID
     * @param entryValue the entry value to put in the identity values
     */
    public void updateIdentity(UUID uuid, Map.Entry<String, Object> entryValue) {

        Bounty identity = cacheMap.computeIfAbsent(uuid, Bounty::new);

        identity.getAsMap().put(
                entryValue.getKey(),
                entryValue.getValue()
        );

    }

    /**
     * Removes a player from the setup cache,
     * this happens when the player has completed the setup and
     * has quit the server. On player quit, player's data gets saved
     * @param uuid the player's UUID
     */
    public void remove(UUID uuid) {
        cacheMap.remove(uuid);
    }


}

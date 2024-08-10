package it.mikeslab.bounty.config;

import it.mikeslab.commons.api.config.ConfigurableEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConfigKey implements ConfigurableEnum {

    BOUNTY_EXPIRATION("bounty.expiration", 604800),
    BOUNTY_EXPIRATION_WARNING("bounty.expiration-warning", 86400),
    BOUNTY_PLAYER_LIMIT("bounty.player-limit", 5),
    BOUNTY_REWARD_MAXIMUM("bounty.reward-limit", 1000),
    BOUNTY_REWARD_MINIMUM("bounty.reward-minimum", 100);

    private final String path;
    private final Object defaultValue;

}

package it.mikeslab.bounty.config;

import it.mikeslab.commons.api.config.ConfigurableEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LanguageKey implements ConfigurableEnum {

    BOUNTY_EXPIRATION_WARNING_MESSAGE("bounty.expiration-warning-message", "<red>Your bounty will expire in 24 hours!"),
    BOUNTY_EXPIRATION_MESSAGE("bounty.expiration-message", "<red>Your bounty has expired!"),
    BOUNTY_PLACED_MESSAGE("bounty.placed-message", "<green>You have placed a bounty on <player> for <reward>"),
    BOUNTY_CLAIMED_MESSAGE("bounty.claimed-message", "<green>You have claimed a bounty on <player> for <reward>"),
    BOUNTY_CLAIMED_BROADCAST("bounty.claimed-broadcast", "<green><player> has claimed a bounty on <player> for <reward>"),
    BOUNTY_PLACED_BROADCAST("bounty.placed-broadcast", "<green><player> has placed a bounty on <player> for <reward>");


    private final String path;
    private final Object defaultValue;

}

package it.mikeslab.bounty.inventory.pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Bounty {

    private final UUID issuer;
    private String target;
    private float reward;

    public Bounty(UUID issuer, String target, float reward) {
        this.issuer = issuer;
        this.target = target;
        this.reward = reward;
    }

    public Map<String, Object> getAsMap() {
        return Map.of(
            "issuer", issuer,
            "target", target,
            "reward", reward
        );
    }

}

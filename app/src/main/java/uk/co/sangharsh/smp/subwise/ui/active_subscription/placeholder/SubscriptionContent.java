package uk.co.sangharsh.smp.subwise.ui.active_subscription.placeholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class SubscriptionContent {

    /**
     * A placeholder item representing a piece of content.
     */
    public static class Platform {
        public final String id;
        public final String provider;
        public final String name;
        public final String iconUrl;

        public Platform(String id, String provider, String name, String iconUrl) {
            this.id = id;
            this.provider = provider;
            this.name = name;
            this.iconUrl = iconUrl;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
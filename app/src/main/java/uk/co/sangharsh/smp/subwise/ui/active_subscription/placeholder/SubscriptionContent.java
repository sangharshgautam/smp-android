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
     * An array of sample (placeholder) items.
     */
    public static final List<Platform> ITEMS = new ArrayList<>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, Platform> ITEM_MAP = new HashMap<>();

    static {
        // Add some sample items.
        addItem(createPlaceholderItem(1, "Amazon", "Prime Membership", "https://m.media-amazon.com/images/G/03/YMS/PrimeLogo-YMS-100px._TTD_.jpg"));
        addItem(createPlaceholderItem(2, "Disney", "Disney+ Hotstar Super", "https://dj7fdt04hl8tv.cloudfront.net/acm/media/shop/d-h_app_logo-256x256.png"));
        addItem(createPlaceholderItem(3, "Netflix", "Netflix", "https://duet-cdn.vox-cdn.com/thumbor/0x0:3151x2048/640x427/filters:focal(1575x1024:1576x1025):format(webp)/cdn.vox-cdn.com/uploads/chorus_asset/file/15844974/netflixlogo.0.0.1466448626.png"));
    }

    private static void addItem(Platform item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Platform createPlaceholderItem(int position, String provider, String name, String iconUrl) {
        return new Platform(String.valueOf(position), provider, name, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

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
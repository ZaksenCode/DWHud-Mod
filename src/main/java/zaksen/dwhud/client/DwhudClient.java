package zaksen.dwhud.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DwhudClient implements ClientModInitializer {

    public static String money = "0";
    public static String shards = "0";
    public static String online = "0";
    public static String kills = "0";
    public static String blocks = "0";
    public static String level = "0";
    public static String event = "нет";

    @Override
    public void onInitializeClient() {

    }
}

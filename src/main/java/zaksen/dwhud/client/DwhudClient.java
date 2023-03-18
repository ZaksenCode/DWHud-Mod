package zaksen.dwhud.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import zaksen.dwhud.event.KeyInputHandler;

@Environment(EnvType.CLIENT)
public class DwhudClient implements ClientModInitializer {

    public static String money = "0";
    public static String shards = "0";
    public static String online = "0";
    public static String kills = "0";
    public static String blocks = "0";
    public static String level = "0";
    public static String event = "";
//    public static String phase = "0";

    public static MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }

//    public String getPhaseByLevel(int Level)
//    {
//        if (Level <= 60)
//        {
//            return "1";
//        } else if (Level >= 61 && Level <= 120) {
//            return "2";
//        } else if (Level >= 121 && Level <= 181) {
//            return "3";
//        } else if (Level >= 182 && Level <= 241) {
//            return "4";
//        } else if (Level >= 242 && Level <= 293) {
//            return "5";
//        } else if (Level >= 294 && Level <= 354) {
//            return "6";
//        }  else {
//            return "7";
//        }
//    }
}

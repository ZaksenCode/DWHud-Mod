package zaksen.dwhud;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zaksen.dwhud.config.DwhudConfig;
import zaksen.dwhud.config.DwhudConfigScreen;

public class Dwhud implements ModInitializer {
    public static final String MOD_ID = "dwhud";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);
    public static DwhudConfig CONFIG = DwhudConfigScreen.init();



    @Override
    public void onInitialize()
    {

    }
}

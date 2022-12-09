package zaksen.dwhud;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zaksen.dwhud.config.DwhudConfig;

public class Dwhud implements ModInitializer {
    public static final String MOD_ID = "dwhud";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);
    public static DwhudConfig CONFIG;



    @Override
    public void onInitialize()
    {
        AutoConfig.register(DwhudConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(DwhudConfig.class).getConfig();
    }
}

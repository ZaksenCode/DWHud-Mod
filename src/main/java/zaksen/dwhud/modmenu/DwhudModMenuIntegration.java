package zaksen.dwhud.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import zaksen.dwhud.config.DwhudConfig;

public class DwhudModMenuIntegration implements ModMenuApi
{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            // Return the screen here with the one you created from Cloth Config Builder
            return AutoConfig.getConfigScreen(DwhudConfig.class, parent).get();
        };
    }
}

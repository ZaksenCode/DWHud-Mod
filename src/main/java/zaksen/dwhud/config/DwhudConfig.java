package zaksen.dwhud.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import static zaksen.dwhud.Dwhud.MOD_ID;

@Config(name = MOD_ID)
public class DwhudConfig implements ConfigData
{
    public boolean hideScoreboard = true;
    public boolean showElements = true;
    public enum presetType
    {
        text_minimal,
        icon_minimal,
        both_minimal
    }
    public presetType preset = presetType.icon_minimal;
    @ConfigEntry.Gui.CollapsibleObject
    public InnerStuff stuff = new InnerStuff();
    public static class InnerStuff {
        public boolean showMoney = true;
        public boolean showShards = true;
        public boolean showLevel = false;
        public boolean showBlocks = false;
        public boolean showKills = false;
        public boolean showOnline = false;
        public boolean showEvent = true;
    }
    @ConfigEntry.Gui.CollapsibleObject
    public positionStuff positions = new positionStuff();
    public static class positionStuff
    {
        public int moneyX = 10, moneyY = 10;
        public int shardsX = 10, shardsY = 20;
        public int levelX = 10, levelY = 30;
        public int blocksX = 10, blocksY = 40;
        public int killsX = 10, killsY = 50;
        public int onlineX = 10, onlineY = 60;
        public int eventX = 10, eventY = 70;
    }
    public int textColor = 16777215;
}
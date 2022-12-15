package zaksen.dwhud.config;

import com.google.common.collect.Lists;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import zaksen.dwhud.Dwhud;

import static zaksen.dwhud.Dwhud.MOD_ID;

@Config(name = MOD_ID)
public class DwhudConfigScreen extends DwhudConfig implements ConfigData {
    public static ConfigBuilder getScreen(Screen parent)
    {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(text("title"));
        builder.setDefaultBackgroundTexture(new Identifier("minecraft:textures/block/netherite_block.png"));
        builder.setGlobalized(true);
        builder.setGlobalizedExpanded(false);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory general = builder.getOrCreateCategory(text("category.general"));
        ConfigCategory showSetting = builder.getOrCreateCategory(text("category.showSettings"));
        ConfigCategory elementsPositions = builder.getOrCreateCategory(text("category.elementsPositions"));
        var config = Dwhud.CONFIG;
        //главные опции
        general.addEntry(entryBuilder.startBooleanToggle(text("general.option.hideScoreboard"), config.hideScoreboard)
                .setDefaultValue(true)
                .setTooltip(Text.of("Скрывать стадартный Scoreboard"))
                .setSaveConsumer(newValue -> config.hideScoreboard = newValue)
                .build());
        general.addEntry(entryBuilder.startBooleanToggle(text("general.option.showElements"), config.showElements)
                .setDefaultValue(true)
                .setTooltip(Text.of("Отображать Custom HUD"))
                .setSaveConsumer(newValue -> config.showElements = newValue)
                .build());
        general.addEntry(entryBuilder.startIntField(text("general.option.textColor"), config.textColor)
                .setDefaultValue(16777215)
                .setTooltip(Text.of("Задает цвет текста который используется в моде(ARGB)"))
                .setSaveConsumer(newValue -> config.textColor = newValue)
                .build());
        general.addEntry(entryBuilder.startDropdownMenu(text("general.option.preset"), DropdownMenuBuilder.TopCellElementBuilder.of(config.preset,
                s -> {
                    try {return Enum.valueOf(DwhudConfig.presetType.class, s);} catch (NumberFormatException ignored) {}
                    return null;
                }))
                .setDefaultValue(presetType.both_minimal)
                .setSuggestionMode(false)
                .setSelections(Lists.newArrayList(presetType.both_minimal,presetType.icon_minimal,presetType.text_minimal))
                .setSaveConsumer(newValue -> config.preset = newValue)
                .setTooltip(Text.of("Задает вид(тему) отображаемых элементов"))
                .build());
        //опции отображения
        showSetting.addEntry(entryBuilder.startBooleanToggle(text("showSettings.option.showMoney"), config.stuff.showMoney)
                .setDefaultValue(true) 
                .setTooltip(Text.of("Эта опция отвечает за показ строки баланса")) 
                .setSaveConsumer(newValue -> config.stuff.showMoney = newValue) 
                .build()); 
        showSetting.addEntry(entryBuilder.startBooleanToggle(text("showSettings.option.showShards"), config.stuff.showShards)
                .setDefaultValue(true) 
                .setTooltip(Text.of("Эта опция отвечает за показ строки шардов")) 
                .setSaveConsumer(newValue -> config.stuff.showShards = newValue) 
                .build()); 
        showSetting.addEntry(entryBuilder.startBooleanToggle(text("showSettings.option.showLevel"), config.stuff.showLevel)
                .setDefaultValue(true) 
                .setTooltip(Text.of("Эта опция отвечает за показ строки уровня")) 
                .setSaveConsumer(newValue -> config.stuff.showLevel = newValue) 
                .build()); 
        showSetting.addEntry(entryBuilder.startBooleanToggle(text("showSettings.option.showBlocks"), config.stuff.showBlocks)
                .setDefaultValue(true) 
                .setTooltip(Text.of("Эта опция отвечает за показ строки блоков")) 
                .setSaveConsumer(newValue -> config.stuff.showBlocks = newValue) 
                .build()); 
        showSetting.addEntry(entryBuilder.startBooleanToggle(text("showSettings.option.showKills"), config.stuff.showKills)
                .setDefaultValue(false) 
                .setTooltip(Text.of("Эта опция отвечает за показ строки убийств")) 
                .setSaveConsumer(newValue -> config.stuff.showKills = newValue) 
                .build()); 
        showSetting.addEntry(entryBuilder.startBooleanToggle(text("showSettings.option.showOnline"), config.stuff.showOnline)
                .setDefaultValue(false) 
                .setTooltip(Text.of("Эта опция отвечает за показ строки онлайна")) 
                .setSaveConsumer(newValue -> config.stuff.showOnline = newValue) 
                .build()); 
        showSetting.addEntry(entryBuilder.startBooleanToggle(text("showSettings.option.showEvent"), config.stuff.showEvent)
                .setDefaultValue(false) 
                .setTooltip(Text.of("Эта опция отвечает за показ строки события")) 
                .setSaveConsumer(newValue -> config.stuff.showEvent = newValue) 
                .build()); 
        //опции позиции
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.moneyX"), config.positions.moneyX)
                .setDefaultValue(10)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки денег по X(Ширина) кординате"))
                .setSaveConsumer(newValue -> config.positions.moneyX = newValue)
                .build());
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.moneyY"), config.positions.moneyY)
                .setDefaultValue(10)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки денег по Y(Высота) кординате"))
                .setSaveConsumer(newValue -> config.positions.moneyY = newValue)
                .build());
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.shardsX"), config.positions.shardsX)
                .setDefaultValue(10)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки шардов по X(Ширина) кординате"))
                .setSaveConsumer(newValue -> config.positions.shardsX = newValue)
                .build());
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.shardsY"), config.positions.shardsY)
                .setDefaultValue(20)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки шардов по Y(Высота) кординате"))
                .setSaveConsumer(newValue -> config.positions.shardsY = newValue)
                .build());
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.levelX"), config.positions.levelX)
                .setDefaultValue(10)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки уровня по X(Ширина) кординате"))
                .setSaveConsumer(newValue -> config.positions.levelX = newValue)
                .build());
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.levelY"), config.positions.levelY)
                .setDefaultValue(30)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки уровня по Y(Высота) кординате"))
                .setSaveConsumer(newValue -> config.positions.levelY = newValue)
                .build());
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.blocksX"), config.positions.blocksX)
                .setDefaultValue(10)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки блоков по X(Ширина) кординате"))
                .setSaveConsumer(newValue -> config.positions.blocksX = newValue)
                .build());
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.blocksY"), config.positions.blocksY)
                .setDefaultValue(40)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки блоков по Y(Высота) кординате"))
                .setSaveConsumer(newValue -> config.positions.blocksY = newValue)
                .build());
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.killsX"), config.positions.killsX)
                .setDefaultValue(10)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки убийств по X(Ширина) кординате"))
                .setSaveConsumer(newValue -> config.positions.killsX = newValue)
                .build());
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.killsY"), config.positions.killsY)
                .setDefaultValue(50)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки убийств по Y(Высота) кординате"))
                .setSaveConsumer(newValue -> config.positions.killsY = newValue)
                .build());
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.onlineX"), config.positions.onlineX)
                .setDefaultValue(10)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки онлайна по X(Ширина) кординате"))
                .setSaveConsumer(newValue -> config.positions.onlineX = newValue)
                .build());
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.onlineY"), config.positions.onlineY)
                .setDefaultValue(60)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки онлайна по Y(Высота) кординате"))
                .setSaveConsumer(newValue -> config.positions.onlineY = newValue)
                .build());
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.eventX"), config.positions.eventX)
                .setDefaultValue(10)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки событий по X(Ширина) кординате"))
                .setSaveConsumer(newValue -> config.positions.eventX = newValue)
                .build());
        elementsPositions.addEntry(entryBuilder.startIntField(text("elementsPositions.option.eventY"), config.positions.eventY)
                .setDefaultValue(70)
                .setTooltip(Text.of("Эта опиция отвечает за положения строки событий по Y(Высота) кординате"))
                .setSaveConsumer(newValue -> config.positions.eventY = newValue)
                .build());
        //Сохранение
        builder.setSavingRunnable(() -> {
            AutoConfig.getConfigHolder(DwhudConfigScreen.class).save();
        });
        builder.transparentBackground();
        return builder;
    }

    private static Text text(String key) {
        return Text.translatable("config.dwhud." + key);
    }

    public static DwhudConfigScreen init() {
        AutoConfig.register(DwhudConfigScreen.class, GsonConfigSerializer::new);
        return AutoConfig.getConfigHolder(DwhudConfigScreen.class).getConfig();
    }
}
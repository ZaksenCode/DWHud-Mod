package zaksen.dwhud.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import zaksen.dwhud.Dwhud;
import zaksen.dwhud.widget.DraggableText;

public class HudEditorScreen extends Screen
{
    DraggableText moneyText;
    DraggableText shardsText;
    DraggableText levelText;
    DraggableText blocksText;
    DraggableText killsText;
    DraggableText onlineText;
    DraggableText eventText;

    public HudEditorScreen(Text title) {
        super(title);
    }

    protected void init()
    {
        Dwhud.CONFIG.showElements = false;
        this.initWidgets();
    }

    private void initWidgets()
    {
        moneyText = this.addDrawableChild(new DraggableText(Dwhud.CONFIG.positions.moneyX, Dwhud.CONFIG.positions.moneyY, Text.of("Баланс: 0")));
        shardsText = this.addDrawableChild(new DraggableText(Dwhud.CONFIG.positions.shardsX, Dwhud.CONFIG.positions.shardsY, Text.of("Шарды: 0")));
        levelText = this.addDrawableChild(new DraggableText(Dwhud.CONFIG.positions.levelX, Dwhud.CONFIG.positions.levelY, Text.of("Уровень: 0")));
        blocksText = this.addDrawableChild(new DraggableText(Dwhud.CONFIG.positions.blocksX, Dwhud.CONFIG.positions.blocksY, Text.of("Блоки: 0")));
        killsText = this.addDrawableChild(new DraggableText(Dwhud.CONFIG.positions.killsX, Dwhud.CONFIG.positions.killsY, Text.of("Убийства: 0")));
        onlineText = this.addDrawableChild(new DraggableText(Dwhud.CONFIG.positions.onlineX, Dwhud.CONFIG.positions.onlineY, Text.of("Онлайн: 0")));
        eventText = this.addDrawableChild(new DraggableText(Dwhud.CONFIG.positions.eventX, Dwhud.CONFIG.positions.eventY, Text.of("Событие: ")));
        this.addDrawableChild(ButtonWidget.builder(Text.of("Сохранить"), (button) -> {
            Dwhud.CONFIG.showElements = true;
            this.savePositions();
            assert this.client != null;
            this.client.setScreen((Screen)null);
            this.client.mouse.lockCursor();
        }).dimensions(this.width / 2 - 50, this.height - 20 - 5, 100, 20).build());
    }

    public void savePositions()
    {
        Dwhud.CONFIG.positions.moneyX = moneyText.x;
        Dwhud.CONFIG.positions.moneyY = moneyText.y;
        Dwhud.CONFIG.positions.shardsX = shardsText.x;
        Dwhud.CONFIG.positions.shardsY = shardsText.y;
        Dwhud.CONFIG.positions.levelX = levelText.x;
        Dwhud.CONFIG.positions.levelY = levelText.y;
        Dwhud.CONFIG.positions.blocksX = blocksText.x;
        Dwhud.CONFIG.positions.blocksY = blocksText.y;
        Dwhud.CONFIG.positions.killsX = killsText.x;
        Dwhud.CONFIG.positions.killsY = killsText.y;
        Dwhud.CONFIG.positions.onlineX = onlineText.x;
        Dwhud.CONFIG.positions.onlineY = onlineText.y;
        Dwhud.CONFIG.positions.eventX = eventText.x;
        Dwhud.CONFIG.positions.eventY = eventText.y;
    }


    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {
        this.textRenderer.drawWithShadow(matrices, this.title, this.width / 2 - (this.textRenderer.getWidth(this.title) / 2), 10, Dwhud.CONFIG.textColor);
        super.render(matrices, mouseX, mouseY, delta);
    }

    public void tick() {
        super.tick();
    }
}

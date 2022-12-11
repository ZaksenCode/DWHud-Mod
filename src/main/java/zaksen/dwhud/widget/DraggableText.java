package zaksen.dwhud.widget;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import zaksen.dwhud.Dwhud;
import zaksen.dwhud.client.DwhudClient;

public class DraggableText extends DrawableHelper implements Drawable, Element, Selectable
{
    private final TextRenderer textRenderer = DwhudClient.client.textRenderer;
    public int x;
    public int y;
    private final int width;
    private final int height;
    private final Text text;
    private boolean isDragging = false;

    public DraggableText(int x, int y, Text text)
    {
        this.x = x;
        this.y = y;
        this.text = text;
        this.width = textRenderer.getWidth(text);
        this.height = textRenderer.fontHeight;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {
        if(isDragging)
        {
            int xr = mouseX - (this.width / 2);
            this.x = (int)(Math.round( xr / 5.0) * 5);
            int yr = mouseY - (this.height / 2);
            this.y = (int)(Math.round( yr / 5.0) * 5);
        }
        this.textRenderer.drawWithShadow(matrices, text, this.x, this.y, Dwhud.CONFIG.textColor);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY)
    {
        return mouseX > this.x && mouseY > this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(isMouseOver(mouseX,mouseY))
        {
            this.isDragging = true;
        }
        return Element.super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.isDragging = false;
        return Element.super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public SelectionType getType() {
        return SelectionType.NONE;
    }

    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {

    }
}

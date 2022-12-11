package zaksen.dwhud.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import zaksen.dwhud.screen.HudEditorScreen;

public class KeyInputHandler
{
    public static final String KEY_CATEGORY_DWHUD = "key.category.dwhud";
    public static final String KEY_OPEN_EDITOR = "key.dwhud.open_editor";
    public static KeyBinding openEditor;

    public static void registerKeyInputs()
    {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(openEditor.wasPressed())
            {
                client.setScreen(new HudEditorScreen(Text.translatable("text.dwhud.editor")));
            }
        });
    }

    public static void register()
    {
        openEditor = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_OPEN_EDITOR,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                KEY_CATEGORY_DWHUD
        ));
        registerKeyInputs();
    }
}

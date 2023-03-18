package zaksen.dwhud.mixin;

import net.minecraft.network.packet.s2c.play.ScoreboardPlayerUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import zaksen.dwhud.Dwhud;

import static zaksen.dwhud.client.DwhudClient.*;

@Mixin(ScoreboardPlayerUpdateS2CPacket.class)
public class ScoreBoardMixin
{
    @Inject(at = @At("TAIL"), method = "getPlayerName")
    private void onGetPName(CallbackInfoReturnable<String> cir)
    {
        try
        {
            String name = cir.getReturnValue().toString();
            if(!name.contains("Информация") && !name.contains("Событие"))
            {
                name = name.replaceAll("§f","");
                name = name.replaceAll("§a","");
                name = name.replaceAll(" ", "");
            }
            if(name.contains("Баланс"))
            {
                name = name.replaceAll("\uE364","");
                String[] Names = name.split(":");
                money = Names[1];;
            }
            else if(name.contains("Шарды"))
            {
                name = name.replaceAll("\uE365","");
                String[] Names = name.split(":");
                shards = Names[1];
            }
            else if(name.contains("Уровень"))
            {
                name = name.replaceAll("\uE35F","").replace("I", "");
                String[] Names = name.split(":");
                level = Names[1];
            }
            else if(name.contains("Добытоблоков"))
            {
                name = name.replaceAll("\uE35D","");
                String[] Names = name.split(":");
                blocks = Names[1];
            }
            else if(name.contains("Убийства"))
            {
                name = name.replaceAll("\uE35E","");
                String[] Names = name.split(":");
                kills = Names[1];
            }
            else if(name.contains("Онлайнрежима"))
            {
                name = name.replaceAll("\uE352","");
                String[] Names = name.split(":");
                online = Names[1];
            }
            else if(name.contains("Событие"))
            {
                name = name.replaceAll("\uE366","");
                String[] Names = name.split(":");
                event = Names[1];
            }
        }
        catch (Exception e)
        {
            Dwhud.LOG.error("ScoreBoardMixin: " + e);
        }
    }
}

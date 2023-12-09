package zaksen.dwhud.mixin;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zaksen.dwhud.Dwhud;
import zaksen.dwhud.config.DwhudConfig;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static net.minecraft.client.gui.DrawableHelper.fill;
import static zaksen.dwhud.client.DwhudClient.*;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin
{
    @Shadow public abstract TextRenderer getTextRenderer();
    @Shadow private int scaledWidth;
    @Shadow private int scaledHeight;
    @Final
    @Shadow private MinecraftClient client;
    @Inject(at = @At("RETURN"), method = "render")
    public void onRender(MatrixStack matrices, float tickDelta, CallbackInfo ci)
    {
        TextRenderer font = this.getTextRenderer();
        if(Dwhud.CONFIG.showElements)
        {
            if(Dwhud.CONFIG.preset.equals(DwhudConfig.presetType.text_minimal))
            {
                if(Dwhud.CONFIG.stuff.showMoney)
                {
                    font.drawWithShadow(matrices, Text.of("Баланс: " + money), Dwhud.CONFIG.positions.moneyX, Dwhud.CONFIG.positions.moneyY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showShards)
                {
                    font.drawWithShadow(matrices, Text.of("Шарды: " + shards), Dwhud.CONFIG.positions.shardsX, Dwhud.CONFIG.positions.shardsY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showLevel)
                {
                    font.drawWithShadow(matrices, Text.of("Уровень: " + level), Dwhud.CONFIG.positions.levelX, Dwhud.CONFIG.positions.levelY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showKills)
                {
                    font.drawWithShadow(matrices, Text.of("Убийства: " + kills), Dwhud.CONFIG.positions.killsX, Dwhud.CONFIG.positions.killsY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showBlocks)
                {
                    font.drawWithShadow(matrices, Text.of("Блоки: " + blocks), Dwhud.CONFIG.positions.blocksX, Dwhud.CONFIG.positions.blocksY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showOnline)
                {
                    font.drawWithShadow(matrices, Text.of("Онлайн: " + online), Dwhud.CONFIG.positions.onlineX, Dwhud.CONFIG.positions.onlineY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showEvent)
                {
                    font.drawWithShadow(matrices, Text.of("Событие: " + event), Dwhud.CONFIG.positions.eventX, Dwhud.CONFIG.positions.eventY, Dwhud.CONFIG.textColor);
                }
            }
            else if (Dwhud.CONFIG.preset.equals(DwhudConfig.presetType.icon_minimal))
            {
                if(Dwhud.CONFIG.stuff.showMoney)
                {
                    font.drawWithShadow(matrices, Text.of("\uE364"), Dwhud.CONFIG.positions.moneyX, Dwhud.CONFIG.positions.moneyY, 16777215);
                    font.drawWithShadow(matrices, Text.of(money), Dwhud.CONFIG.positions.moneyX + 15, Dwhud.CONFIG.positions.moneyY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showShards)
                {
                    font.drawWithShadow(matrices, Text.of("\uE365"), Dwhud.CONFIG.positions.shardsX, Dwhud.CONFIG.positions.shardsY, 16777215);
                    font.drawWithShadow(matrices, Text.of(shards), Dwhud.CONFIG.positions.shardsX + 15, Dwhud.CONFIG.positions.shardsY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showLevel)
                {
                    font.drawWithShadow(matrices, Text.of("\uE35F"), Dwhud.CONFIG.positions.levelX, Dwhud.CONFIG.positions.levelY, 16777215);
                    font.drawWithShadow(matrices, Text.of(level), Dwhud.CONFIG.positions.levelX + 15, Dwhud.CONFIG.positions.levelY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showKills)
                {
                    font.drawWithShadow(matrices, Text.of("\uE35E"), Dwhud.CONFIG.positions.killsX, Dwhud.CONFIG.positions.killsY, 16777215);
                    font.drawWithShadow(matrices, Text.of(kills), Dwhud.CONFIG.positions.killsX + 15, Dwhud.CONFIG.positions.killsY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showBlocks)
                {
                    font.drawWithShadow(matrices, Text.of("\uE35D"), Dwhud.CONFIG.positions.blocksX, Dwhud.CONFIG.positions.blocksY, 16777215);
                    font.drawWithShadow(matrices, Text.of(blocks), Dwhud.CONFIG.positions.blocksX + 15, Dwhud.CONFIG.positions.blocksY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showOnline)
                {
                    font.drawWithShadow(matrices, Text.of("\uE352"), Dwhud.CONFIG.positions.onlineX, Dwhud.CONFIG.positions.onlineY, 16777215);
                    font.drawWithShadow(matrices, Text.of(online), Dwhud.CONFIG.positions.onlineX + 15, Dwhud.CONFIG.positions.onlineY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showEvent)
                {
                    font.drawWithShadow(matrices, Text.of("\uE366"), Dwhud.CONFIG.positions.eventX, Dwhud.CONFIG.positions.eventY, 16777215);
                    font.drawWithShadow(matrices, Text.of(event), Dwhud.CONFIG.positions.eventX + 15, Dwhud.CONFIG.positions.eventY, Dwhud.CONFIG.textColor);
                }
            }
            else if (Dwhud.CONFIG.preset.equals(DwhudConfig.presetType.both_minimal))
            {
                if(Dwhud.CONFIG.stuff.showMoney)
                {
                    font.drawWithShadow(matrices, Text.of("\uE364"), Dwhud.CONFIG.positions.moneyX, Dwhud.CONFIG.positions.moneyY, 16777215);
                    font.drawWithShadow(matrices, Text.of("Баланс: " + money), Dwhud.CONFIG.positions.moneyX + 15, Dwhud.CONFIG.positions.moneyY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showShards)
                {
                    font.drawWithShadow(matrices, Text.of("\uE365"), Dwhud.CONFIG.positions.shardsX, Dwhud.CONFIG.positions.shardsY, 16777215);
                    font.drawWithShadow(matrices, Text.of("Шарды: " + shards), Dwhud.CONFIG.positions.shardsX + 15, Dwhud.CONFIG.positions.shardsY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showLevel)
                {
                    font.drawWithShadow(matrices, Text.of("\uE35F"), Dwhud.CONFIG.positions.levelX, Dwhud.CONFIG.positions.levelY, 16777215);
                    font.drawWithShadow(matrices, Text.of("Уровень: " + level), Dwhud.CONFIG.positions.levelX + 15, Dwhud.CONFIG.positions.levelY, Dwhud.CONFIG.textColor);
                }

                if(Dwhud.CONFIG.stuff.showKills)
                {
                    font.drawWithShadow(matrices, Text.of("\uE35E"), Dwhud.CONFIG.positions.killsX, Dwhud.CONFIG.positions.killsY, 16777215);
                    font.drawWithShadow(matrices, Text.of("Убийства: " + kills), Dwhud.CONFIG.positions.killsX + 15, Dwhud.CONFIG.positions.killsY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showBlocks)
                {
                    font.drawWithShadow(matrices, Text.of("\uE35D"), Dwhud.CONFIG.positions.blocksX, Dwhud.CONFIG.positions.blocksY, 16777215);
                    font.drawWithShadow(matrices, Text.of("Блоки: " + blocks), Dwhud.CONFIG.positions.blocksX + 15, Dwhud.CONFIG.positions.blocksY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showOnline)
                {
                    font.drawWithShadow(matrices, Text.of("\uE352"), Dwhud.CONFIG.positions.onlineX, Dwhud.CONFIG.positions.onlineY, 16777215);
                    font.drawWithShadow(matrices, Text.of("Онлайн: " + online), Dwhud.CONFIG.positions.onlineX + 15, Dwhud.CONFIG.positions.onlineY, Dwhud.CONFIG.textColor);
                }
                if(Dwhud.CONFIG.stuff.showEvent)
                {
                    font.drawWithShadow(matrices, Text.of("\uE366"), Dwhud.CONFIG.positions.eventX, Dwhud.CONFIG.positions.eventY, 16777215);
                    font.drawWithShadow(matrices, Text.of("Событие: " + event), Dwhud.CONFIG.positions.eventX + 15, Dwhud.CONFIG.positions.eventY, Dwhud.CONFIG.textColor);
                }
            }
        }
    }

    /**
     * @author Zaksen_
     * @reason add "hide" option
     */
    @Overwrite
    private void renderScoreboardSidebar(MatrixStack matrices, ScoreboardObjective objective)
    {
        if(!Dwhud.CONFIG.hideScoreboard)
        {
            Scoreboard scoreboard = objective.getScoreboard();
            Collection<ScoreboardPlayerScore> collection = scoreboard.getAllPlayerScores(objective);
            List<ScoreboardPlayerScore> list = collection.stream().filter((score) -> {
                return score.getPlayerName() != null && !score.getPlayerName().startsWith("#");
            }).collect(Collectors.toList());
            if (list.size() > 15) {
                collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
            } else {
                collection = list;
            }

            List<Pair<ScoreboardPlayerScore, Text>> list2 = Lists.newArrayListWithCapacity(((Collection<?>)collection).size());
            Text text = objective.getDisplayName();
            int i = this.getTextRenderer().getWidth(text);
            int j = i;
            int k = this.getTextRenderer().getWidth(": ");

            ScoreboardPlayerScore scoreboardPlayerScore;
            MutableText text2;
            for(Iterator var11 = (collection).iterator(); var11.hasNext(); j = Math.max(j, this.getTextRenderer().getWidth(text2) + k + this.getTextRenderer().getWidth(Integer.toString(scoreboardPlayerScore.getScore())))) {
                scoreboardPlayerScore = (ScoreboardPlayerScore)var11.next();
                Team team = scoreboard.getPlayerTeam(scoreboardPlayerScore.getPlayerName());
                text2 = Team.decorateName(team, Text.literal(scoreboardPlayerScore.getPlayerName()));
                list2.add(Pair.of(scoreboardPlayerScore, text2));
            }

            int var10000 = (collection).size();
            Objects.requireNonNull(this.getTextRenderer());
            int l = var10000 * 9;
            int m = this.scaledHeight / 2 + l / 3;
            int o = this.scaledWidth - j - 3;
            int p = 0;
            int q = this.client.options.getTextBackgroundColor(0.3F);
            int r = this.client.options.getTextBackgroundColor(0.4F);

            for (Pair<ScoreboardPlayerScore, Text> scoreboardPlayerScoreTextPair : list2) {
                ++p;
                ScoreboardPlayerScore scoreboardPlayerScore2 = scoreboardPlayerScoreTextPair.getFirst();
                Text text3 = scoreboardPlayerScoreTextPair.getSecond();
                Formatting var31 = Formatting.RED;
                String string = "" + var31 + scoreboardPlayerScore2.getScore();
                Objects.requireNonNull(this.getTextRenderer());
                int t = m - p * 9;
                int u = this.scaledWidth - 3 + 2;
                int var10001 = o - 2;
                Objects.requireNonNull(this.getTextRenderer());
                fill(matrices, var10001, t, u, t + 9, q);
                this.getTextRenderer().draw(matrices, text3, (float) o, (float) t, -1);
                this.getTextRenderer().draw(matrices, string, (float) (u - this.getTextRenderer().getWidth(string)), (float) t, -1);
                if (p == ( collection).size()) {
                    var10001 = o - 2;
                    Objects.requireNonNull(this.getTextRenderer());
                    fill(matrices, var10001, t - 9 - 1, u, t - 1, r);
                    fill(matrices, o - 2, t - 1, u, t, q);
                    TextRenderer var32 = this.getTextRenderer();
                    float var10003 = (float) (o + j / 2 - i / 2);
                    Objects.requireNonNull(this.getTextRenderer());
                    var32.draw(matrices, text, var10003, (float) (t - 9), -1);
                }
            }
        }
    }
}

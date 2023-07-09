package arozxmod.screens;

import arozxmod.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class ArozxScreen extends Screen {
    protected Screen parent;

    public ArozxScreen(Screen parent, GameOptions gameOptions) {
        super(Text.literal("Client Mods"));
        this.parent = parent;
    }

    Text autoFishingText() {
        if (Config.autoFishingEnabled)
            return (Text.literal("Autofishing enabled"));
        else
            return (Text.literal("Autofishing disabled"));
    }
    Text flyingText() {
        if (Config.normalFlyingEnabled)
            return (Text.literal("Flying enabled"));
        else
            return (Text.literal("Flying disabled"));
    }
    Text boatText() {
        if (Config.flyingEnabled)
            return (Text.literal("Boat flying enabled"));
        else
            return (Text.literal("Boat flying disabled"));
    }
    Text noFallText() {
        if (Config.noFallEnabled)
            return (Text.literal("No fall enabled"));
        else
            return (Text.literal("No fall disabled"));
    }
    Text nightVisionText() {
        if (Config.nightVisionEnabled)
            return (Text.literal("Night vision enabled"));
        else
            return (Text.literal("Night vision disabled"));
    }

    @Override
    protected void init() {
        // Night vision button
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 6, 200, 20,
                nightVisionText(), (button) -> {
            Config.nightVisionEnabled = !Config.nightVisionEnabled;
            button.setMessage(nightVisionText());
        }));
        // Autofishing button
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 34, 200, 20,
                autoFishingText(), (button) -> {
            Config.autoFishingEnabled = !Config.autoFishingEnabled;
            button.setMessage(autoFishingText());
        }));
        // Flying button
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 62, 200, 20,
                flyingText(), (button) -> {
            Config.normalFlyingEnabled = !Config.normalFlyingEnabled;
            button.setMessage(flyingText());
        }));
        // Boat flying button
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 90, 200, 20,
                boatText(), (button) -> {
            Config.flyingEnabled = !Config.flyingEnabled;
            button.setMessage(boatText());
        }));
        // No fall button
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 118, 200, 20,
                noFallText(), (button) -> {
            Config.noFallEnabled = !Config.noFallEnabled;
            button.setMessage(noFallText());
        }));
        // Back button
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 174, 200, 20,
                ScreenTexts.BACK, (button) -> this.client.setScreen(this.parent)));
    }
}
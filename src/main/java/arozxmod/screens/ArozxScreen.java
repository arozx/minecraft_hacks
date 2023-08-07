package arozxmod.screens;

import arozxmod.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
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
        this.addDrawableChild(
                ButtonWidget.builder(
                        nightVisionText(), button -> {
                            Config.nightVisionEnabled = !Config.nightVisionEnabled;
                            button.setMessage(nightVisionText());
                        }
                        )
                        .dimensions(this.width / 2 - 102, this.height / 6 + 24, 204, 20)
                        .build());

        // Autofishing button
        this.addDrawableChild(
                ButtonWidget.builder(
                        autoFishingText(), button -> {
                            Config.autoFishingEnabled = !Config.autoFishingEnabled;
                            button.setMessage(autoFishingText());
                        }
                        )
                        .dimensions(this.width / 2 - 102, this.height / 6 + 52, 204, 20)
                        .build());

        // Flying button
        this.addDrawableChild(
                ButtonWidget.builder(
                        flyingText(), button -> {
                            Config.normalFlyingEnabled = !Config.normalFlyingEnabled;
                            button.setMessage(flyingText());
                        }
                        )
                        .dimensions(this.width / 2 - 102, this.height / 6 + 80, 204, 20)
                        .build());

        // Boat flying button
        this.addDrawableChild(
                ButtonWidget.builder(
                        boatText(), button -> {
                            Config.flyingEnabled = !Config.flyingEnabled;
                            button.setMessage(boatText());
                        }
                        )
                        .dimensions(this.width / 2 - 102, this.height / 6 + 108, 204, 20)
                        .build());

        // No fall button
        this.addDrawableChild(
                ButtonWidget.builder(
                        noFallText(), button -> {
                            Config.noFallEnabled = !Config.noFallEnabled;
                            button.setMessage(noFallText());
                        }
                        )
                        .dimensions(this.width / 2 - 102, this.height / 6 + 136, 204, 20)
                        .build());

        // Back button
        this.addDrawableChild(
                ButtonWidget.builder(
                        Text.of("Back"), button -> {
                            this.client.setScreen(this.parent);
                            clearAndInit();
                            }
                        )
                        .dimensions(this.width / 2 - 102, this.height / 6 + 174, 204, 20)
                        .build());
    }
}
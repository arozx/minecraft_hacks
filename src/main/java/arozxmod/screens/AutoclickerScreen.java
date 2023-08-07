package arozxmod.screens;

import arozxmod.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class AutoclickerScreen extends Screen {
    protected Screen parent;

    public AutoclickerScreen(Screen parent, GameOptions gameOptions) {
        super(Text.literal("Client Mods"));
        this.parent = parent;
    }

    Text autoclickerTextL() {
        if (Config.autoclickerEnabledL)
            return (Text.literal("Left autoclicker enabled"));
        else
            return (Text.literal("Left autoclicker disabled"));
    }
    Text autoclickerTextR() {
        if (Config.autoclickerEnabledR)
            return (Text.literal("Right autoclicker enabled"));
        else
            return (Text.literal("Right autoclicker disabled"));
    }
    Text cpsText() {
        if (Config.cpsEnabled)
            return (Text.literal("Custom cps enabled"));
        else
            return (Text.literal("Custom cps disabled"));
    }

    @Override
    protected void init() {
        // CPS Slider
        this.addDrawableChild(new SliderWidget(this.width / 2 - 102, this.height / 6 + 58, 204, 20, Text.of("CPS"), 0) {
            @Override
            protected void updateMessage() {
                this.setMessage(Text.of("CPS: " + (int) (this.value*10)));
            }

            @Override
            protected void applyValue() {
                Config.cps = (int) (this.value*10);
            }
        });
        // Set cps button
        this.addDrawableChild(
                ButtonWidget.builder(
                        cpsText(), button -> {
                            Config.cpsEnabled = !Config.cpsEnabled;
                            button.setMessage(cpsText());
                        }
                        )
                        .dimensions(this.width / 2 - 102, this.height / 6 + 80, 204, 20)
                        .build());

        // Right autoclicker button
        this.addDrawableChild(
                ButtonWidget.builder(
                        autoclickerTextR(), button -> {
                            Config.autoclickerEnabledR = !Config.autoclickerEnabledR;
                            button.setMessage(autoclickerTextR());
                        }
                        )
                        .dimensions(this.width / 2 - 102, this.height / 6 + 108, 204, 20)
                        .build());

        // Left autoclicker button
        this.addDrawableChild(
                ButtonWidget.builder(
                        autoclickerTextL(), button -> {
                            Config.autoclickerEnabledL = !Config.autoclickerEnabledL;
                            button.setMessage(autoclickerTextL());
                        }
                        )
                        .dimensions(this.width / 2 - 102, this.height / 6 + 136, 204, 20)
                        .build());

        // Back button
        this.addDrawableChild(
                ButtonWidget.builder(
                        Text.of("Back"), button -> this.client.setScreen(this.parent)
                        )
                        .dimensions(this.width / 2 - 102, this.height / 6 + 174, 204, 20)
                        .build());
    }
}
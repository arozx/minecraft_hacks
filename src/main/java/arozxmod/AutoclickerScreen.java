package arozxmod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class AutoclickerScreen extends Screen {
    protected Screen parent;

    public AutoclickerScreen(Screen parent, GameOptions gameOptions) {
        super(Text.literal("Client Mods"));
        this.parent = parent;
    }

    Text autoclickerTextL() {
        if (ArozxMod.autoclickerEnabledL)
            return (Text.literal("Left autoclicker enabled"));
        else
            return (Text.literal("Left autoclicker disabled"));
    }
    Text autoclickerTextR() {
        if (ArozxMod.autoclickerEnabledR)
            return (Text.literal("Right autoclicker enabled"));
        else
            return (Text.literal("Right autoclicker disabled"));
    }
    Text cpsText() {
        if (ArozxMod.cpsEnabled)
            return (Text.literal("Custom cps enabled"));
        else
            return (Text.literal("Custom cps disabled"));
    }

    @Override
    protected void init() {
        // Set cps button
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 150, this.height / 6 + 118, 150, 20,
                cpsText(), (button) -> {
            Autoclicker.cps =5;
            ArozxMod.cpsEnabled = !ArozxMod.cpsEnabled;
            button.setMessage(cpsText());
        }));
        // Right autoclicker button
        this.addDrawableChild(new ButtonWidget(this.width / 2, this.height / 6 + 146, 150, 20,
                autoclickerTextR(), (button) -> {
            ArozxMod.autoclickerEnabledR = !ArozxMod.autoclickerEnabledR;
            button.setMessage(autoclickerTextR());
        }));
        // Left autoclicker button
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 150, this.height / 6 + 146, 150, 20,
                autoclickerTextL(), (button) -> {
            ArozxMod.autoclickerEnabledL = !ArozxMod.autoclickerEnabledL;
            button.setMessage(autoclickerTextL());
        }));
        // Back button
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 174, 200, 20,
                ScreenTexts.BACK, (button) -> this.client.setScreen(this.parent)));
    }
}
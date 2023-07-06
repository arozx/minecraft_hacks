package arozxmod.mixin;

import arozxmod.ArozxScreen;
import arozxmod.AutoclickerScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text text) {super(text);}

    @Inject(at = @At("HEAD"), method = "initWidgets")
    private void initWidgets(CallbackInfo ci) {
        this.addDrawableChild(new ButtonWidget(10, 10, 90, 20, Text.literal("Mod Menu"), (button) -> {

            this.client.setScreen(new ArozxScreen(this, this.client.options));
        }));
        this.addDrawableChild(new ButtonWidget(10, 30, 90, 20, Text.literal("Autoclicker"), (button) -> {

            this.client.setScreen(new AutoclickerScreen(this, this.client.options));
        }));
    }
}

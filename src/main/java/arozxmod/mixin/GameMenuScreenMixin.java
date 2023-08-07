package arozxmod.mixin;

import arozxmod.screens.ArozxScreen;
import arozxmod.screens.AutoclickerScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.MutableText;
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


        addDrawableChild(ButtonWidget.builder(Text.of("Mode Menu"), button -> {
                    this.client.setScreen(new ArozxScreen(this, this.client.options));
                }).dimensions(10, 10, 90, 20)
                .position(10, 10)
                .narrationSupplier((NarrationSupplier) -> (MutableText) Text.of("Toggle Setting 1"))
                .build());

        addDrawableChild(ButtonWidget.builder(Text.of("Autoclicker"), button -> {
                    this.client.setScreen(new AutoclickerScreen(this, this.client.options));
                }).dimensions(10, 10, 90, 20)
                .position(10, 40)
                .narrationSupplier((NarrationSupplier) -> (MutableText) Text.of("Toggle Setting 1"))
                .build());
    }
}

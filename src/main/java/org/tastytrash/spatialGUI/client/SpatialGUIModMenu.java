package org.tastytrash.spatialGUI.client;

//? if fabric {
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class SpatialGUIModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> SpatialGUIConfig.HANDLER.generateGui().generateScreen(parent);
    }
}
//?}
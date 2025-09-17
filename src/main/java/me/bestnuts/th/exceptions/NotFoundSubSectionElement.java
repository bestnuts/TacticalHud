package me.bestnuts.th.exceptions;

import me.bestnuts.th.TacticalHudPlugin;

public class NotFoundSubSectionElement extends RuntimeException {

    public NotFoundSubSectionElement(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        TacticalHudPlugin.getInstance().getLogger().warning("Hud 서브 섹션이 없습니다. " + getMessage());
    }
}

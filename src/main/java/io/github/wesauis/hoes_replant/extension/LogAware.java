package io.github.wesauis.hoes_replant.extension;

import io.github.wesauis.hoes_replant.HoesReplantMod;
import org.slf4j.Logger;

public interface LogAware {

    default Logger logger() {
        return HoesReplantMod.LOGGER;
    }

}

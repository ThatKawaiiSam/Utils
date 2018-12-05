package io.github.thatkawaiisam.utils;

import com.google.common.base.Preconditions;
import io.github.thatkawaiisam.utils.playerversion.PlayerVersionHandler;
import io.github.thatkawaiisam.utils.serverversion.ServerVersionHandler;

public class UtilityManager {

    private static boolean initiated = false;

    public static void init(){
        if (initiated) {
            return;
        }
        new ServerVersionHandler();
        new PlayerVersionHandler();
        initiated = true;
    }
}

package com.agameofstones.game;

public class DummyController implements AdsController {
    @Override
    public boolean isWifiConnected() { return false; }

    @Override
    public void showBannerAd() {

    }

    @Override
    public void hideBannerAd() {

    }
}

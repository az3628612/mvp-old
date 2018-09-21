package com.glh.tjfx.presenter;

import java.io.File;

public interface ITestPresenter {

    public static String token = "3a0fd2b884f742258c0b96c87d52a15d";

    void testQuery(String token, String type, File file);

    void testUpdataImage();

    void testUpdataVideo();
}

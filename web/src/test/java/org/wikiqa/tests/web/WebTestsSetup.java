package org.wikiqa.tests.web;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;

import static com.automation.remarks.video.enums.RecordingMode.ALL;
import static com.codeborne.selenide.impl.FileHelper.ensureFolderExists;

class WebTestsSetup implements BeforeAllCallback {
  @Override
  public void beforeAll(ExtensionContext context) {
    System.setProperty("selenide.baseUrl", "https://wikipedia.org");
    System.setProperty("selenide.browserSize", "1200x800");
    System.setProperty("selenide.fastSetValue", "true");
    setUpVideoRecorder();
  }

  private static void setUpVideoRecorder() {
    File videoFolder = new File(System.getProperty("selenide.reportsFolder", "build/reports/tests"));
    ensureFolderExists(videoFolder);
    System.setProperty("video.folder", videoFolder.getAbsolutePath());
    System.setProperty("video.enabled", "true");
    System.setProperty("video.mode", String.valueOf(ALL));
  }
}

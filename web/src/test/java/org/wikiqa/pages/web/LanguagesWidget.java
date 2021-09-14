package org.wikiqa.pages.web;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LanguagesWidget {
  private final SelenideElement languages = $("#p-lang");

  public void chooseLanguage(String language) {
    languages.$(byText(language)).click();
  }
}

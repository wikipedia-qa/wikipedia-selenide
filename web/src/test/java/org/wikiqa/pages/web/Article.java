package org.wikiqa.pages.web;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Article {
  public final SelenideElement title = $("#firstHeading");
  public final SelenideElement content = $("#bodyContent");
}

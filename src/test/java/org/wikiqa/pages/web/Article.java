package org.wikiqa.pages.web;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Article {

  public SelenideElement title = $("#firstHeading"),
          content = $("#bodyContent");
}

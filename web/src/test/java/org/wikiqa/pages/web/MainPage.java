package org.wikiqa.pages.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

  public ElementsCollection featuredLanguages=$$(".central-featured-lang");

  SelenideElement revealLanguagesBtn=$("#js-lang-list-button");

  /**
   *
   * @param i - position 1..10
   * @return
   */
  public SelenideElement getTopFeaturedLanguage(int i){
     return featuredLanguages.get(i-1);
  }

  public void revealLanguages(){
    revealLanguagesBtn.click();
  }

  public ElementsCollection getLanguagesToHeader(String header){
    return  $(".lang-list-container").$(withText(header)).closest("h2").$x("./following-sibling::div").$$("li").filter(visible);
  }
}

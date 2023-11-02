package com.florencehc.assignment.webelements;

import org.openqa.selenium.By;

public class Locators {

    //region Gigatron
    public By gigatronAcceptCookiesButton = By.xpath("//button[text()='Prihvati sve']");
    public By gigatronSearchField = By.xpath("//div[@class='header__center']//input[@placeholder = 'Unesite pojam za pretragu']");
    public By gigatronSearchResults = By.xpath("//div[@class='item']");

    public By gigatronSearchResultsPrices = By.xpath("//div[@class='item__bottom__prices__price']");
    public By gigatronProductNames = By.xpath("//div[@class='item']//h4[@itemprop='name']");

    public By gigatronProductName(int index) {
        return By.xpath("(//div[@class='item']//h4[@itemprop='name'])[" + index + "]");
    }

    public By gigatronProductPageName = By.xpath("//h1[@itemprop='name']");
    //endregion Gigatron
    //region Ananas
    public By ananasAcceptCookiesButton = By.xpath("//button[text()='Slažem se']");
    public By ananasSearchField = By.xpath("//input[@placeholder = 'Unesi pojam za pretragu...']");
    public By ananasSearchResults = By.xpath("//li[@class='ais-Hits-item']");

    public By ananasSearchResultsPrices = By.xpath("//li[@class = 'ais-Hits-item']//span[text()='RSD']//preceding-sibling::span");
    public By ananasProductNames = By.xpath("//li[@class = 'ais-Hits-item']//h3");

    public By ananasProductName(int index) {
        return By.xpath("(//li[@class = 'ais-Hits-item']//h3)[" + index + "]");
    }
    public By ananasProductPageLoaded = By.xpath("//h2[text()='Opis proizvoda']");

    public By ananasProductPageName = By.xpath("//h1[@class]");
    //endregion Ananas
    //region Tehnomedia
    public By tehnomediaSearchField = By.xpath("//input[@id='search-text']");
    public By tehnomediaSearchResults = By.xpath("//div[@class='product-single']");
    public By tehnomediaSearchResultsPrices = By.xpath("//div[@class='product-price']");
    public By tehnomediaProductNames = By.xpath("//div[@class='product-link']//a");

    public By tehnomediaProductName(int index) {
        return By.xpath("(//div[@class='product-link']//a)[" + index + "]");
    }

    public By tehnomediaProductPageName = By.xpath("//div[@class='product-title']//h1");
    //endregion Tehnomedia
    //region CT shop
    public By ctshopAcceptCookiesButton = By.xpath("//button[text()='Prihvatam']");
    public By ctshopSearchField = By.xpath("//input[@name='pretrazi']");
    public By ctshopSearchResults = By.xpath("//div[@class='product-list-grid-box row']");

    public By ctshopSearchResultsPrices = By.xpath("//span[@class = 'extra-discount-price']//span[@class = 'price']");
    public By ctshopProductNames = By.xpath("//h4[@class = 'product-name']//a");

    public By ctshopProductName(int index) {
        return By.xpath("(//h4[@class = 'product-name']//a)[" + index + "]");
    }
    public By ctshopProductPageName = By.xpath("//div[@class = 'product-name']//h1");
    //endregion CT shop
}

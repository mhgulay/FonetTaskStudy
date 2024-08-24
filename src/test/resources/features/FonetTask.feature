Feature: Fonet Test Automation WebUI Task

  Background: Kullanici siteyi acar
    Given Kullanici demoblaze.com sitesini acar
    When Kullanici site basligini "STORE" kontrol eder

  Scenario: Toplam urun adet kontrolu
    And Listelenen urunler loglanir
    And Phones kategorisi loglanir
    And Laptops kategorisi loglanir
    And Monitors kategorisi loglanir
    Then Toplam sayi kontrol edilir

  Scenario: Urun satin alma E2E süreci
    And Kullanici herhangi bir urun kategorisine tiklar
    And Kullanici herhangi bir urun kartina tiklar
    And Kullanici acilan urun adini ve fiyat bilgisini kontrol eder
    And Kullanici Add to cart butonuna tiklar
    And Kullanici urun eklendi bilgi pop-up onaylar
    And Kullanici Cart menusune tiklar
    And Kullanici urun adini ve fiyatini kontrol eder
    And Kullanici urun toplam fiyatini kontrol eder
    And Kullanici "Place Order" butonuna tiklar
    And Kullanici Place Order formunda fiyati kontrol eder
    And Kullanici Place Order formunda "name" alanini doldurur
    And Kullanici Place Order formunda "country" alanini doldurur
    And Kullanici Place Order formunda "city" alanini doldurur
    And Kullanici Place Order formunda "card" alanini doldurur
    And Kullanici Place Order formunda "month" alanini doldurur
    And Kullanici Place Order formunda "year" alanini doldurur
    And Kullanici "Purchase" butonuna tiklar
    And Kullanici bilgi pop-up ta adini ve fiyati kontrol eder
    And Kullanici bilgi pop-up ta "Thank you for your purchase!" mesajini görür
    Then Kullanici "OK" butonuna tiklar
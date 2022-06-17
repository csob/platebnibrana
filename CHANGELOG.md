Changelog
---

### v1.9 (06/2022)

**Nová funkcionalita**

* EPAPG-19 - eAPI 1.9
* EPAPG-25 - Rozšíření podpory ověřování transakcí pomocí 3D Secure 2
* EPAPG-24 - Platební metoda Google Pay
* EPAPG-14 - Platební metoda NEJsplátka
* EPAPG-228 - Omezení podpory hlášení tržeb EET

**Opravy chyb**

* [#562](https://github.com/csob/paymentgateway/issues/562) návratové kódy oneclick/init vs. oneclick/echo
* [#570](https://github.com/csob/paymentgateway/issues/570) expirace transakce (`ttlSec`)
* [#615](https://github.com/csob/paymentgateway/issues/615) validace délky parametru `vatin` (DIČ zákazníka) pro mallpay z 10 na 12 znaků

**Změny ve specifikaci eAPI 1.9 oproti v1.8**

* provedení platby kartou na bráně
  * parametry `payOperation`, `payMethod` a `closePayment` jsou v operaci `payment/init` v eAPI 1.9 nepovinné
  * do parametru `payMethod` je možné nastavit hodnotu `card#LVP` pro indikaci výjimky ze silného ověření pro platby nízkých částek
  * přidání nových parametrů `customer` a `order` v operaci `payment/init` pro předání dodatečných dat o nákupu
  * hodnoty parametru `language` v operaci `payment/init` jsou nyní jazykové kódy dle ISO 639-1 (ve starších eAPI byly nadefinovány kódy zemí)
  * přidání struktury `actions` v response operace `payment/status`
* platební metoda OneClick
  * doplnění rozšíření `maskClnRp` do operace `oneclick/echo`
  * parametr `clientIp` je v operaci `oneclick/init` v eAPI 1.9 nově povinný pouze pro `clientInitiated = true`
  * přidání parametru `closePayment` v operaci `oneclick/init` (ve starších verzích eAPI se hodnota přebírala z OneClick šablony)
  * přidání nových parametrů `returnUrl` a `returnMethod` v operaci `oneclick/init`
  * přidání nových parametrů `customer` a `order` v operaci `oneclick/init` pro předání dodatečných dat o nákupu
  * přidání nových parametrů `clientInitiated` a `sdkUsed` v operaci `oneclick/init`
  * přejmenování operace `oneclick/start` na `oneclick/process`, doplněna podpora pro předání dat pro provedení ověření platby (struktury `fingerprint` a `actions`)
* platební metoda Apple Pay
  * přidání nové operace `applepay/echo`
  * parametr `closePayment` je v operaci `applepay/init` v eAPI 1.9 nově nepovinný
  * předávání parametru `payload` v eAPI 1.9 ve volání  `applepay/init` (v eAPI 1.8 byl `payload` předáván v `applepay/start`)
  * přidání nových parametrů `returnUrl` a `returnMethod` v operaci `applepay/init`
  * přidání nových parametrů `customer` a `order` v operaci `applepay/init` pro předání dodatečných dat o nákupu
  * přidání nového parametru `sdkUsed` v operaci `applepay/init`
  * přejmenování operace `applepay/start` na `applepay/process`, doplněna podpora pro předání dat pro provedení ověření platby (struktury `fingerprint` a `actions`)
* platební metoda Google Pay
  * přidání nových operací `googlepay/echo`, `googlepay/init` a `googlepay/process`
* Platební tlačítko ČSOB
  * parametr `brand` v operaci `button/init` je nově nepovinný, povolená hodnota je pouze `csob` (hodnota `era` již není podporována)
* platební metoda mallpay
  * fakturační adresa je v operaci `mallpay/init` nově nepovinná
* platební metoda NEJsplátka
  * přidání nových operací `loan/init`, `loan/logistics`, `loan/cancel` a `loan/refund`
* odstranění `masterpass/*` operací z eAPI 1.9 (po ukončení platební metody MasterPass k 31.10.2020 vrací stávající eAPI 1.7 a 1.8 po provolání MasterPass platební metody příslušné chybové kódy)
* ukončení podpory `eetV3` rozšíření pro eAPI 1.9
* změny v číselníku návratových kódů (`resultCode`)
  * přidán nový návratový kód `200` Duplicate purchaseId
  * přidán nový návratový kód `750` Cardholder account closed
  * návratové kódy `230`, `240`, `250` a `270` relevantní pro ukončenou platební metodu MasterPass byly označeny jako deprecated


### v1.8 (10/2019)

**Nová funkcionalita**

* TSGPMIPS-2302 - eAPI 1.8
* TSGPMIPS-2334 - podpis eAPI 1.8 pomocí SHA256
* TSGPMIPS-2185 - platba na míru
* TSGPMIPS-2195 - platební metoda Apple Pay
* TSGPMIPS-2491 - platební metoda Mall Pay
* TSGPMIPS-2486 - podpora mikrostavů pro platební metodu Mall Pay

**Opravy chyb**

* [#349](https://github.com/csob/paymentgateway/issues/349) PHP Example - špatné zacházení s parametrem closePayment.
* [#353](https://github.com/csob/paymentgateway/issues/353) Změny chybových hlášek při nefunkční zapamatované kartě

**Změny ve specifikaci eAPI 1.8 oproti v1.7**

* oproti eAPI 1.7 byly osamostatněny operace pro založení @shop platebních metod. Týká se to operací `applepay/init`, `masterpass/init`, `button/init` a `mallpay/init`. Původní `payment/init` slouží nově pouze pro založení platby na platební bráně. 
  * operace `payment/oneclick/init` byla v eAPI 1.8 přejmenována na `oneclick/init`
  * operace `payment/oneclick/start` byla v eAPI 1.8 přejmenována na `oneclick/start`
  * operace `customer/info` byla v eAPI 1.8 přejmenována na `echo/customer`
* zrušeno pole `description` v operacích `payment/init` a `oneclick/init` (pozor, změní se tím i podpis požadavku)
* přidán nový povinný parametr `clientIp` v operaci `oneclick/init` (pozor, změní se tím i podpis požadavku)
* přidán nový parametr `statusDetail` v odpovědi na volání [`payment/*` operací](https://github.com/csob/paymentgateway/wiki/Z%C3%A1kladn%C3%AD-metody#n%C3%A1vratov%C3%A9-hodnoty-) obsahující detailní mikrostav transakce
* přidán číselník [detailní mikrostavy transakcí](https://github.com/csob/paymentgateway/wiki/Detailní-mikrostavy-transakcí)
* upraven [číselník návratových kódů](https://github.com/csob/paymentgateway/wiki/Vol%C3%A1n%C3%AD-rozhran%C3%AD-eAPI#%C4%8C%C3%ADseln%C3%ADk-n%C3%A1vratov%C3%BDch-k%C3%B3d%C5%AF-)
  * přidán nový společný návratový kód `160` (Payment method disabled), který nahrazuje všechny následující:
      * odstraněný návratový kód `220` (mpass@shop disabled) v eAPI 1.8 nahrazen kódem `160`
      * odstraněný návratový kód `400` (pt@shop (CSOB IB) disabled) v eAPI 1.8 nahrazen kódem `160`
      * odstraněný návratový kód `410` (pt@shop (ERA IB) disabled) v eAPI 1.8 nahrazen  kódem `160`
  * přidán nový společný návratový kód `170` (Payment method unavailable), který nahrazuje všechny následující:
      * odstraněný návratový kód `420` (pt@shop (CSOB IB) unavailable) v eAPI 1.8 nahrazen kódem `170`
      * odstraněný návratový kód `430` (pt@shop (ERA IB) unavailable) v eAPI 1.8 nahrazen  kódem `170`
  * přidán nový společný návratový kód `190` (Payment method error), který nahrazuje všechny následující:
      * odstraněný návratový kód `260` (MasterPass server error) v eAPI 1.8 nahrazen kódem `190`
  * přidán nový návratový kód `600` (Mall Pay payment declined in precheck)
  * přidán nový návratový kód `700` (Oneclick template not found)
  * přidán nový návratový kód `710` (Oneclick template payment expired)
  * přidán nový návratový kód `720` (Oneclick template card expired)
  * přidán nový návratový kód `730` (Oneclick template customer rejected)
  * přidán nový návratový kód `740` (Oneclick template payment reversed)


### v1.7 (01/2017)

**Nová funkcionalita**

* TSGPMIPS-1231 - eAPI 1.7
* TSGPMIPS-1327 - platební metoda MasterPass
* TSGPMIPS-1343 - platební tlačítko ČSOB 
* TSGPMIPS-1344 - platební tlačítko Poštovní spořitelny 
* TSGPMIPS-1549 - podpora EET

**Změny ve specifikaci eAPI 1.7 od vydání (bez funkčního dopadu)** 

* doplněn nový návratový kód `500` (EET rejected)
* doplněn chybějící paymentStatus v návratových parametrech operace `payment/button`
* doplněna podpora pro akceptaci nové měny `HRK` a nové lokalizace `HR` a `SI`
* upraven popis integrace `MasterPass.client.checkout` pro `mpass@shop`
* doplněn popis integrace `MasterPass.client.checkout` pro `mpass@shop` pro js callback
* doplněna podpora pro akceptaci nové měny `RON`, `NOK` a `SEK`


### v1.6 (04/2016)

**Nová funkcionalita**

* TSGSMIPS-821 - eAPI 1.6
* TSGSMIPS-779 - Multilanguage support
* TSGSMIPS-479 - Multibrand s možností volby loga & barvy přes eAPI na úrovni transakce
* TSGSMIPS-340 - Trx dates extension
* TSGSMIPS-9 - Možnost vlastního generování klíčů
* TSGSMIPS-491 - Detailnější identifikace issuera
* TSGSMIPS-479 - Nastavení životnosti transakce přes eAPI

**Opravy chyb**

* [#57](https://github.com/csob/paymentgateway/issues/57) Endpoint `customer/info` nevrací `customerId`.
* [#85](https://github.com/csob/paymentgateway/issues/85) Operace `payment/recurrent` vrací http status 500
* [#95](https://github.com/csob/paymentgateway/issues/95) Chybějící středník ve WooCommerce pluginu
* [#109](https://github.com/csob/paymentgateway/issues/109) Chyba přesměrování po úspěšné platbě (Android)
* [#111](https://github.com/csob/paymentgateway/issues/111) Platba kartou pro testování opakované platby je vždy autorizována
* [#132](https://github.com/csob/paymentgateway/issues/132) Neznámý resultCode `999`
* [#144](https://github.com/csob/paymentgateway/issues/144) Reverse/refund šablony pro recurring platbu 

**Dokumentace**

* [#50](https://github.com/csob/paymentgateway/issues/50) Barvy platební brány
* [#78](https://github.com/csob/paymentgateway/issues/78) Nezdokumentovaný resultCode
* [#83](https://github.com/csob/paymentgateway/issues/83) Nepřesná ukázka requestu v dokumentaci
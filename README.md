<p align="center">
  <img src="https://github.com/csob/platebnibrana/wiki/img/mktg/banner-cz.png/">
</p>

# Platební brána ČSOB

Platební brána ČSOB poskytuje služby akceptace karet (Visa, Visa Electron, V Pay, MasterCard, Maestro), peněženek Apple Pay, Google Pay, odložené platby Skip Pay (dříve mallpay) a platebního tlačítka ČSOB. 
Platební brána může být použita jak na webu, tak v nativních mobilních aplikacích. K využití služeb brány je nutné uzavření slouvy s bankou.

Akceptace Diners Club platebních karet byla ukončena k 30.9.2022.

Z mallpay se od 6.10.2022 stalo Skip Pay. Kromě názvu zůstává vše beze změny. V eAPI části na wiki a v kódu API je však stále použito jako mallpay (názvy operací a parametry). Metody pro platbu mallpay (resp. Skip Pay) jsou i nadále plně funkční.

Z důvodu přechodu Chorvatska na oficiální měnu euro nebude možné od 1.1.2023 zakládat platby v chorvatské kuně (HRK). Případné refundy transakcí uskutečněných v původní měně HRK budou realizovány pouze v plné výši v měně EUR.

Srovnávač spotřebitelských úvěrů NEJsplátka není aktuálně aktivní.

## 1. Platební brána ČSOB a objednávka služeb

Produktové informace najdete na [https://platbakartou.csob.cz/platebni-brana/](https://platbakartou.csob.cz/platebni-brana/). Máte-li zájem o obchodní nabídku a uzavření smlouvy o online akceptaci karet s ČSOB, vyplňte, prosím, kontaktní formulář na stránce, nebo kdykoli volejte na +420 495 800 116.

## 2. Specifikace API, jak integrovat a testovat

Rozhraní eAPI pro komunikaci e-shopu s platební bránou vychází z principů REST API a výměna dat probíhá v JSON formátu. Kombinuje v jednom interface jak transakční, tak obslužné funkce a je základem pro rozvoj nových služeb. Aktuálně je podporované ve verzích 1.0, 1.5, 1.6, 1.7, 1.8 a 1.9. Doporučujeme při implementaci používat nejnovější verzi, starší verze budou postupně [uzavírány](https://github.com/csob/platebnibrana/wiki/API-Sunset).

Než začnete bránu do vašeho shopu nebo mobilní aplikace eAPI integrovat, podívejte se, prosím, do [Wiki](https://github.com/csob/platebnibrana/wiki). Najdete zde kompletní dokumentaci k eAPI mezi e-shopem a bránou, návod jak simulovat různé transakční stavy a jaké používat testovací karty.

## 3. Repozitář

V [repozitáři](https://github.com/csob/platebnibrana/tree/master/examples) najdete ukázkovou PHP, Java a .NET integraci. Z repozitáře si také stáhněte veřejný klíč brány, který budete potřebovat pro zabezpečení komunikace s platební bránou. Vlastní sadu klíčů si pak vygenerujte pomocí online nástroje, který jsme pro vás připravili na [https://platebnibrana.csob.cz/keygen/](https://platebnibrana.csob.cz/keygen/). Klíče pro iBránu (otevřené vývojové prostředí přístupné beze smlouvy) si můžete vygenerovat na [https://iplatebnibrana.csob.cz/keygen/](https://iplatebnibrana.csob.cz/keygen/)

K dispozici jsou i řešení třetích stran, jejichž použití je pouze na vašem uvážení — např.:
- [@slevomat/csob-gateway](https://github.com/slevomat/csob-gateway)
- [@ondrakoupil/csob](https://github.com/ondrakoupil/csob)

## 4. Problémy a pomoc při jejich řešení

Nejrychleji najdete pomoc v sekci často kladených otázek. [Technická sekce](https://github.com/csob/platebnibrana/wiki/Časté-technické-dotazy) je věnována zejména integraci, ve [funkční a obchodní části](https://github.com/csob/platebnibrana/wiki/Časté-funkční-a-komerční-dotazy) se dozvíte více o bráně a službách banky. Větší detail k některým již dříve řešeným problémům najdete v issues.

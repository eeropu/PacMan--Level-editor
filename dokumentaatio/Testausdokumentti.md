# Testausdokumentti
### Valmiit automatisoidut testit:
Ohjelmassa on luotu automatisoidut testit kaikille pelilogiikkaan liittyville luokille, eli käytännössä peliobjekteille ja niiden käyttämille apuluokille (esim. coordinate). Näiden testien sekä rivi- , että mutaatiokattavuus on yli 90% ja suuri osa testaamattomasta osuudesta näissä luokissa on kuvan piirtämiseen käytettäviä metodeja. (Jokainen Gameobject-rajapinnan täyttävä luokka sisältää metodin paint, jolla kyseiseen objektiin liittyvä kuva saadaan helposti piirrettyä käyttöliittymäluokkien avulla oikeisiin koordinaatteihin).
### Käsin testattua:
Käyttöliittymän toiminta on testattu manuaalisesti. Ohjelmaa on käyttänyt loppuvaiheessa muutama henkilö, jotka ovat mahdolliseen virheeseen törmätessään ilmoittaneet siitä, jonka jälkeen olen korjannut sen. Tasoja on luotu kymmeniä erilaisilla kombinaatioilla peliobjekteja, joita kaikkia on pelattu useasti. Testailua on siis kertynyt sen verran, että tuntemattomia ongelmia ei pitäisi käyttöliittymässä tai ohjelman muussa toiminnallisuudessa enää olla.
### Tunnettuja bugeja:
-Päävalikossa näkyvä animaatio liikkuu tarkoitettua hitaammin ja pätkien joissain tietokoneissa.  
-valikkojen painikkeet joskus hitaasti vastaavia. (Korjaantuu yleensä itsestään tai ohjelman uudestaan käynnistämällä.)

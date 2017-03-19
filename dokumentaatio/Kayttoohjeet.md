## Käyttöohjeet
Ohjelman käynnistettäessä avautuu valikko, josta käyttäjä voi valita pelitilan, luontitilan tai huipputulosten tarkastelun.
### Pelitila:
Jos käyttäjä valitsee pelitilan, hänet ohjataan tasovlikkoon, josta käyttäjä voi valita haluamansa tason tai palata takaisin päävalikkoon.
#### Pelaaminen:
-Pelissä tavoite on kerätä PacManilla pistekuplia, ohjaamalla sitä nuolinäppäimillä.  
-Kun kaikki pistekuplat on kerätty, pelaaja on läpäissyt tason.  
-Peliä on hankaloittamassa haamut, joihin PacMan ei saa osua.  
-Jos PacMan osuu haamuun, haamut ja PacMan palaavat aloituspaikoilleen ja PacMan menettää elämän.  
-Jos elämät loppuvat ennen kuin kaikki pistekuplat on kerätty, taso on hävitty.  
-Pistekuplien lisäksi PacMan voi syöpä PowerPellettejä, mikä hidastaa hetkellisesti haamujen liikkumista ja mahdollistaa haamujen syömisen.  
-Haamun syönnin jälkeen se ilmestyy takaisin pelikentälle normaalissa tilassa.  
-Haamut muuttuvat normaaleiksi myös 10 sekuntia PowerPellettin syömisen jälkeen, vaikka PacMan ei olisikaan syönyt niitä.  
-PacManin ja haamujen liikkumista on rajoittamassa seinät.  
-Jos ruudun reunasta puuttuu seinä, siitä voidaan kulkea ilmestyen ruudun vastakkaiseen reunaan.  
-Pelissä saa pisteitä pistekuplista (10p), PowerPelleteistä (50p) ja haamujen syömisestä (200p) sekä tason läpäisyn jälkeen jäljellä olevista elämistä (1000p / elämä).  
-Edellä mainittujen lisäksi käyttäjä voi asettaa luontivaiheessa aikarajan tason läpäisemiselle, jonka alittamisesta saa ylimääräisiä pisteitä jokaista sekuntia kohden.  
-Käyttäjä voi myös asettaa absoluuttisen aikarajan, jonka ylittäminen johtaa tason epäonnistumiseen. (Näkyy ruudussa punaisena, muuten valkoisena)
#### Tason läpäisy:
Jos käyttäjä läpäisee tason hänet ohjataan näkymään, joka näyttää pelaajan pistemäärän ja tarjoaa mahdollisuuden pisteiden tallentamiselle. Pisteiden tallentaminen tai ohittaminen, johtaa päävalikkoon.
#### Tason epäonnistuminen:
Jos käyttäjä ei läpäise tasoa, hänet ohjataan ruutuun, joka kertoo epäonnistumisesta ja pysäyttää pelaamisen. Ruudussa on painike, joka ohjaa käyttäjän päävalikkoon.
### Luontitila:
Jos käyttäjä valitsee luontitilan hänet ohjataan valikkoon, josta käyttäjä voi valita "Create new", "Modify Existing", "delete" tai "back".
#### Create New:
Johtaa luontitilaan, jossa on tyhjä ruudukko, johon käyttäjä voi lisätä objekteja.
#### Modify Existing:
Johtaa tasovalikkoon. Tason valitseminen johtaa luontitilaan, jossa ruudukkoon on valmiiksi asetettu objektit valitun tason mukaisesti.
##### Huom!
Tason muokkaaminen poistaa sen huipputulokset.
#### Create New ja Modify Existing:
Luontitilaan löytyy erilliset ohjeet painamalla oikealla alareunassa olevaa "?"-painiketta.
Kun objektien asettelu on valmis, voidaan seuraavaan näkymään siirtyä painamalla ready-painiketta.
Avautuvassa näkymässä voidaan antaa tasolle nimi ja aikaraja, valita onko aikaraja läpäisyä rajoittava vai (ja) pisteitä antava (voidaan myös määrittää ylimääräiset pisteet / sekunti) ja täyttää tyhjät koordinaatit pistekuplilla.
#### Delete:
Johtaa tasovalikkoon, josta voidaan valita poistettava taso. Poiston yhteydessä poistetaan myös kaikki sen huipputulokset.
#### Back:
Ohjaa takaisin päävalikkoon.
### Huipputulokset:
johtaa tasovalikkoon, josta tason valitseminen näyttää kyseisen tason huipputulokset.

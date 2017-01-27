#Aiheen kuvaus ja rakenne
###Aihe:
PacMan -level editor. Ohjelma, jossa perinteisen Pacmanin pelaamisen lisäksi käyttäjä voi luoda itse uusia tasoja ja pelata niitä.
###Rakenne:
####Käyttöliittymä:
Ohjelman käynnistettäessä avautuu käyttäjälle valikko, josta hän voi valita haluaako pelata olemassa olevia tasoja vai siirtyä muokkaustilaan.
######Pelitila:
Pelitilassa käyttäjän on mahdollista aloittaa uusi peli valitsemassaan tasossa tai tarkastella haluamansa tason huipputuloksia.
######Muokkaustila:
Muokkaustilassa käyttäjä voi aloittaa uuden tason rakentamisen, muokata vanhaa aikaisemmin luomaansa tasoa tai poistaa luomiansa tasoja. (Tason muokkaaminen tyhjentää kyseisen tason huipputulostilastot).
####Pelikomponentit:
Pacman, haamut, pistekuplat, powerpelletit, seinät.
####Tietojen säilyttäminen:
Luodut tasot ja niissä saadut huipputulokset tallennetaan tietokantoihin, joka mahdollistaa luotujen tasojen pelaamisen myös ohjelman sulkemisen jälkeen. Näin myös tasoissa saadut pisteet pysyvät tiedossa.
###Määrittelyvaiheen luokkakaavio:
![määrittelyvaiheen luokkakaavio]( PacMan--Level-editor/dokumentaatio/MaarittelyvaiheenLuokkakaavio.jpg )

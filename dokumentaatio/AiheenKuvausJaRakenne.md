#Aiheen kuvaus ja rakenne
###Aihe:
PacMan -level editor. Ohjelma, jossa perinteisen Pacmanin pelaamisen lisäksi käyttäjä voi luoda itse uusia tasoja ja pelata niitä.
###Rakenne:
####Käyttöliittymä:
Ohjelman käynnistettäessä avautuu käyttäjälle valikko, josta hän voi valita haluaako pelata olemassa olevia tasoja, siirtyä muokkaustilaan vai tarkastella huipputuloksia.
######Pelitila:
Pelitilassa käyttäjälle avautuu valikko, josta hän voi valita haluamansa tason pelattavaksi.
######Muokkaustila:
Muokkaustilassa käyttäjä voi aloittaa uuden tason rakentamisen, muokata vanhaa aikaisemmin luomaansa tasoa tai poistaa luomiansa tasoja. (Tason muokkaaminen tyhjentää kyseisen tason huipputulostilastot).
######Huipputulokset:
Käyttäjän valitessa huipputulokset, hänelle näytetään samanlainen tasovalikko, kuin pelitilan valitsemisen jälkeen. Kun käyttäjä valitsee jonkin tasoista, hänelle näytetään kyseisen tason tilastot.
####Pelikomponentit:
Pacman, haamut, pistekuplat, powerpelletit, seinät.
#####Haamut:
######Blinky:
Punainen haamu, joka pyrkii saamaan Pacmanin kiinni.
######Pinky:
Vaaleanpunainen haamu, joka pyrkii sijoittamaan itsensä Pacmanin eteen.
######Clyde:
Oranssi haamu, joka tulee Pacmania kohti kunnes pääsee tarpeeksi lähelle ja lähtee karkuun.
######RandomGhost:
Vihreä haamu, joka matkii jonkin muun haamun käyttäytymistä. (Voi muuttua jokaisella tason uudelleen käynnistyksellä)
####Tietojen säilyttäminen:
Luodut tasot ja niissä saadut huipputulokset tallennetaan tietokantoihin, joka mahdollistaa luotujen tasojen pelaamisen myös ohjelman sulkemisen jälkeen. Näin myös tasoissa saadut pisteet pysyvät tiedossa.
###Määrittelyvaiheen luokkakaavio:
![määrittelyvaiheen luokkakaavio](https://raw.githubusercontent.com/eeropu/PacMan--Level-editor/master/dokumentaatio/MaarittelyvaiheenLuokkakaavio.jpg)
###Luokkakaavio:
![luokkakaavio](https://raw.githubusercontent.com/eeropu/PacMan--Level-editor/master/dokumentaatio/Luokkakaavio.jpg)
Viivan yläpuolella olevat luokat ovat käytössä pelitilassa ja liittyvät olennaisesti pelilogiikkaan. Alapuolella olevat ovat käyttöliittymään liittyviä luokkia. Alapuolella olevien luokkien väliset yhteydet ovat kaikki yhdestä yhteen, minkä vuoksi en ole niitä erikseen merkannut.
###Sekvenssikaavio tason suorittamisesta ja tuloksen tallentamisesta:
![suoritettu](https://github.com/eeropu/PacMan--Level-editor/blob/master/dokumentaatio/LevelCompleteSekvenssikaavio.jpg)
Sekvenssikaavio kuvaa tilannetta, joka alkaa collisioncheckerin metodista checkcollision, jossa huomataan, että kaikki pointbubblet on syöty. Kuvaus jatkuu päävalikkoon palaamiseen asti.
###Sekvenssikaavio tason luomisesta:
![luonti](https://github.com/eeropu/PacMan--Level-editor/blob/master/dokumentaatio/LevelCreationSekvenssikaavio.jpg)
Sekvenssikaavio kuvaa tilannetta, joka alkaa käyttäjän siirtymisestä tasonmuokkaustilaan ja päättyy kun taso on luotu ja tallennettu ja ohjelma palaa päävalikkoon.

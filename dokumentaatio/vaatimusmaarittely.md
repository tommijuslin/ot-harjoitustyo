# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on versio klassisesta Tetris-pelistä, jossa pelaajan päämääränä on pudotella erimuotoisia palikoita (tetrominoja) ja muodostaa niistä vaakasuoria rivejä.

## Käyttöliittymäluonnos

![tetris ui mockup](https://raw.githubusercontent.com/tommijuslin/ot-harjoitustyo/master/dokumentaatio/kuvat/tetris-ui-mockup.png)

## Perusversion tarjoama toiminnallisuus

### Ennen pelin aloittamista
:heavy_check_mark: käyttäjä voi aloittaa pelin  
:heavy_check_mark: käyttäjä voi tarkistaa pistetilanteen (ns. high score)  
:heavy_check_mark: käyttäjä voi sulkea pelin  

### Pelin aloittamisen jälkeen
:heavy_check_mark: käyttäjä voi liikuttaa palikkaa oikealle ja vasemmalle  
:heavy_check_mark: käyttäjä voi nopeuttaa palikan putoamista  
:heavy_check_mark: käyttäjä voi pyörittää palikoita vasemmalle tai oikealle -> **muutos:** *lopullisessa versiossa palikoita voidaan pyörittää vain myötäpäivään*  
:heavy_check_mark: käyttäjä voi kerätä pisteitä asettelemalla palikoita niin, että ne muodostavat vaakarivin  
:heavy_check_mark: käyttäjä voi siirtyä pelistä takaisin valikkoon  
:heavy_check_mark: seuraava palikka generoidaan satunnaisesti  
:heavy_check_mark: peli päättyy jos palikat yltävät ruudun yläreunaan
:heavy_check_mark: pisteet määräytyy sen mukaan, kuinka monta vaakariviä tuhotaan kerralla  

### Jatkokehitysideoita
- käyttäjä voi valita vaikeustason ennen pelin aloittamista (mitä vaikeampi, sitä nopeampi peli on)
- peli nopeutuu ajan kuluessa (esim. jonkin tietyn ajan välein)
- ns. bad luck protection. Palikoita ei generoida täysin satunnaisesti. Peli ei voi esimerkiksi generoida samaa palikkaa 5 kertaa peräkkäin
- peli näyttää seuraavan palikan

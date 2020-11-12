# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on versio klassisesta Tetris-pelistä, jossa pelaajan päämääränä on pudotella erimuotoisia palikoita (tetrominoja) ja muodostaa niistä vaakasuoria rivejä.

## Käyttöliittymäluonnos

![tetris ui mockup](https://raw.githubusercontent.com/tommijuslin/ot-harjoitustyo/master/dokumentaatio/kuvat/tetris-ui-mockup.png)

## Perusversion tarjoama toiminnallisuus

### Ennen pelin aloittamista
- käyttäjä voi aloittaa pelin
- käyttäjä voi tarkistaa pistetilanteen (ns. high score)
- käyttäjä voi sulkea pelin

### Pelin aloittamisen jälkeen
- käyttäjä voi liikuttaa palikkaa oikealle ja vasemmalle
- käyttäjä voi nopeuttaa palikan putoamista
- käyttäjä voi pyörittää palikoita vasemmalle tai oikealle
- käyttäjä voi kerätä pisteitä asettelemalla palikoita niin, että ne muodostavat vaakarivin
- käyttäjä voi siirtyä pelistä takaisin valikkoon
- seuraava palikka generoidaan satunnaisesti
- peli päättyy jos palikat yltävät ruudun yläreunaan

### Jatkokehitysideoita
- käyttäjä voi valita vaikeustason ennen pelin aloittamista (mitä vaikeampi, sitä nopeampi peli on)
- peli nopeutuu ajan kuluessa (esim. jonkin tietyn ajan välein)
- ns. bad luck protection. Palikoita ei generoida täysin satunnaisesti. Peli ei voi esimerkiksi generoida samaa palikkaa 5 kertaa peräkkäin
- peli näyttää seuraavan palikan
- pisteet määräytyy sen mukaan, kuinka monta vaakariviä tuhotaan kerralla

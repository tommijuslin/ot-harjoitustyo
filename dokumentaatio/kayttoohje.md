# Käyttöohje

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

`java -jar tetrisapp.jar`

## Alkuvalikko

Pelin alkuvalikossa on kolme vaihtoehtoa:

- `Start` aloittaa uuden pelin
- `High Score` näyttää pistetilanteen
- `Exit` sulkee ohjelman.

## Uusi peli

Peli alkaa heti Start-napin painamisen jälkeen. Palikan (tetrominon) liikuttelu onnistuu seuraavasti nuolinäppäimiä käyttämällä:

:arrow_right: liikuttaa tetrominoa oikealle  
:arrow_down: liikuttaa tetrominoa alas  
:arrow_left: liikuttaa tetrominoa vasemmalle  
:arrow_up: pyörittää tetrominoa myötäpäivään

Esc-nappia painamalla päästään takaisin alkuvalikkoon. Jos alkuvalikkoon mennään kesken pelin, niin sieltä löytyy uusi nappi "Resume" jota painamalla voi palata takaisin peliin.

## Pelin päättyminen

Peli päättyy jos tetrominot yltävät ruudun yläreunaan.

## Pistetilanne

Pistetilanteen voi tarkistaa painamalla alkuvalikon High Score -nappia. High Score -ruudulta löytyy 10 parasta tulosta.

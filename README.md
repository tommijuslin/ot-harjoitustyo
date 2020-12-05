# TetrisApp
Tämä versio Tetriksestä on Helsingin yliopiston Ohjelmistotekniikan menetelmät -kurssia varten luotu harjoitustyö.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/tommijuslin/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/tommijuslin/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/tommijuslin/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/tommijuslin/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)


## Releaset

[Viikko 5](https://github.com/tommijuslin/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/tommijuslin/ot-harjoitustyo/releases/tag/viikko6)

## Komentorivikomennot

### Testaus

Testit suoritetaan komennolla

`mvn test`

Testikattavuusraportti luodaan komennolla

`mvn jacoco:report`

### Suoritettavan jarin generointi

Komento

`mvn package`

generoi hakemistoon target jar-tiedoston TetrisApp-1.0-SNAPSHOT.jar

### JavaDoc

JavaDoc generoidaan komennolla

`mvn javadoc:javadoc`

JavaDoc löytyy tiedostosta target/site/apidocs/index.html

### CheckStyle

Checkstyle-tarkistukset generoidaan komennolla

`mvn jxr:jxr checkstyle:checkstyle`

Virheilmoitukset löytyvät tiedostosta target/site/checkstyle.html

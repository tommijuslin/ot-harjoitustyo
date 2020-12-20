# Arkkitehtuuri

## Rakenne

Ohjelma on jaettu kolmeen pakkaukseen seuraavalla tavalla

![package architecture](https://raw.githubusercontent.com/tommijuslin/ot-harjoitustyo/master/dokumentaatio/kuvat/pakkausrakenne.png)

Pakkaus fi.tommijuslin.ui sisältää käyttöliittymän, fi.tommijuslin.logic logiikan ja fi.tommijuslin.blocks kaikki tetrominoihin/muotoihin liittyvät luokat.

## Käyttöliittymä

Ohjelmassa on kolme näkymää:

- alkuvalikko
- high score
- itse peliruutu

Alkuvalikosta pelaaja voi aloittaa pelin, siirtyä tarkastelemaan saavutettuja pistemääriä tai sulkea pelin. High Score -näkymä näyttää pistetilanteen.

## Sovelluslogiikka

Pelin palikat (tetrominot) rakennetaan neljästä yksittäisestä Block-oliosta. Tetrominoon tallennetaan neljän Blockin sisältämä yksiulotteinen taulukko. Board pitää sisällään kaikki luodut Tetrominot. Boardista löytyy myös yksi Grid-olio, jonka avulla pidetään kirjaa kaikkien Tetrominojen sijainneista.

![tetris class diagram](https://raw.githubusercontent.com/tommijuslin/ot-harjoitustyo/master/dokumentaatio/kuvat/class_diagram.png)

Board vastaa suurimmasta osasta pelin toiminnallisuuksista, erityisesti kaikesta Tetrominojen manipulointiin ja rakentamiseen liittyvästä. Esim:

- spawn(Shape)
- buildTetromino()
- move(x, y)
- collides(x, y, b)
- rotate()

Grid pitää kirjaa Tetrominoista jotka on ns. lyöty lukkoon. Gridistä löytyy myös kaikki tähän taulukkoon liittyvät metodit, kuten

- addToGrid(block)
- removeFromGrid(block)
- deleteRows(tetrominos)

## Pysyväistallennus

Luokka Score hoitaa pisteiden tallentamisen tiedostoon. Pelin käynnistyksen yhteydessä luodaan tiedosto score.txt (jos sitä ei ole jo aikaisemmin luotu), johon tallennetaan 10 parasta tulosta. Tulokset on tallennettu yksinkertaisesti pelkkinä merkkijonoina. Scoressa on metodit rivien laskemiseen *getNumberOfLines()* ja pisteiden vertailuun *getLowestScore()*, joiden avulla vanha huonompi tulos voidaan korvata uudella, paremmalla tuloksella.

## Päätoiminnallisuudet

#### Tetrominon 'spawnaaminen'

Pelin alussa ensimmäinen tetromino luodaan kutsumalla Boardin initGame()-metodia luokasta Tetris, jonka jälkeen se tapahtuu silmukassa. Kun sillä hetkellä aktiivisena olevaa tetrominoa yritetään liikuttaa alaspäin, mutta siirto ei olekaan mahdollinen, 'spawnataan' uusi tetromino.

![spawn sequence diagram](https://raw.githubusercontent.com/tommijuslin/ot-harjoitustyo/master/dokumentaatio/kuvat/sq_spawn.png)

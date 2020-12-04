![tetris class diagram](https://raw.githubusercontent.com/tommijuslin/ot-harjoitustyo/master/dokumentaatio/kuvat/tetris_uml.png)

#### Tetrominon 'spawnaaminen'

Pelin alussa ensimmäinen tetromino luodaan ns. manuaalisesti spawn-metodia kutsumalla Tetris-luokasta, jonka jälkeen se tapahtuu silmukassa. Kun sillä hetkellä aktiivisena olevaa tetrominoa yritetään liikuttaa alaspäin, mutta siirto ei olekaan mahdollinen, 'spawnataan' uusi tetromino.


![spawn sequence diagram](https://raw.githubusercontent.com/tommijuslin/ot-harjoitustyo/master/dokumentaatio/kuvat/sq_spawn.png)

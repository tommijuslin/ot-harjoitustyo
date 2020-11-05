package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 10.10", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        kortti.lataaRahaa(1000);
        kortti.otaRahaa(400);
        assertEquals("saldo: 6.10", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaTrueJosRahatRiittivat() {
        assertTrue(kortti.otaRahaa(10));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosRahatEiRiittaneet() {
        assertFalse(kortti.otaRahaa(100));
    }
    
    @Test
    public void saldoMetodiToimiiOikein() {
        assertTrue(kortti.saldo() == 10);
    }
}

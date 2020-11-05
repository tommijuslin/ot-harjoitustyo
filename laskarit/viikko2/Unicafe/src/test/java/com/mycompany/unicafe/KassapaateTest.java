package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassapaate = new Kassapaate();
    Maksukortti kortti = new Maksukortti(400);
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void kassapaatteenRahamaaraAlussaOikein() {
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void kassapaatteenMyydytMaukkaatLounaatAlussaOikein() {
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kassapaatteenMyydytEdullisetLounaatAlussaOikein() {
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    // maksu riittävä
    
    @Test
    public void kassanRahamaaraKasvaaEdullisenLounaanHinnallaJosMaksuRiittava() {
        kassapaate.syoEdullisesti(240);
        assertTrue(kassapaate.kassassaRahaa() == 100000 + 240);
    }
    
    @Test
    public void kassanRahamaaraKasvaaMaukkaanLounaanHinnallaJosMaksuRiittava() {
        kassapaate.syoMaukkaasti(400);
        assertTrue(kassapaate.kassassaRahaa() == 100000 + 400);
    }
    
    @Test
    public void vaihtorahaOikeinEdullistaLounastaOstaessaJosMaksuRiittava() {
        assertTrue(kassapaate.syoEdullisesti(300) == 60);
    }
    
    @Test
    public void vaihtorahaOikeinMaukastaLounastaOstaessaJosMaksuRiittava() {
        assertTrue(kassapaate.syoMaukkaasti(500) == 100);
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaaJosMaksuRiittava() {
        kassapaate.syoEdullisesti(240);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaJosMaksuRiittava() {
        kassapaate.syoMaukkaasti(400);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 1);
    }
    
    // maksu ei riittävä
    
    @Test
    public void kassanRahamaaraEiMuutuEdullistaLounastaOstaessaJosMaksuEiRiittava() {
        kassapaate.syoEdullisesti(200);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void kassanRahamaaraEiMuutuMaukastaLounastaOstaessaJosMaksuEiRiittava() {
        kassapaate.syoMaukkaasti(200);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void vaihtorahaOikeinEdullistaLounastaOstaessaJosMaksuEiRiittava() {
        assertTrue(kassapaate.syoEdullisesti(200) == 200);
    }
    
    @Test
    public void vaihtorahaOikeinMaukastaLounastaOstaessaJosMaksuEiRiittava() {
        assertTrue(kassapaate.syoMaukkaasti(200) == 200);
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraEiKasvaaJosMaksuEiRiittava() {
        kassapaate.syoEdullisesti(200);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraEiKasvaJosMaksuEiRiittava() {
        kassapaate.syoMaukkaasti(200);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }
    
    // kortilla tarpeeksi rahaa
    
    @Test
    public void summaVeloitetaanKortiltaEdullistaLounastaOstaessaJosMaksuRiittava() {
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 400 - 240);
    }
    
    @Test
    public void summaVeloitetaanKortiltaMaukastaLounastaOstaessaJosMaksuRiittava() {
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 400 - 400);
    }
    
    @Test
    public void syoEdullisestiPalauttaaTrueJosKortillaRiittavastiRahaa() {
        assertTrue(kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void syoMaukkaastiPalauttaaTrueJosKortillaRiittavastiRahaa() {
        assertTrue(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaaJosKortillaRiittavastiRahaa() {
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaJosKortillaRiittavastiRahaa() {
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 1);
    }
    
    // kortilla ei tarpeeksi rahaa
    
    @Test
    public void kortinRahamaaraEiMuutuEdullistaLounastaOstaessaJosMaksuEiRiittava() {
        int otettavaMaara = 200;
        kortti.otaRahaa(otettavaMaara);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 400 - otettavaMaara);
    }
    
    
    @Test
    public void kortinRahamaaraEiMuutuMaukastaLounastaOstaessaJosMaksuEiRiittava() {
        int otettavaMaara = 200;
        kortti.otaRahaa(otettavaMaara);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 400 - otettavaMaara);
    }
    
    @Test
    public void syoEdullisestiPalauttaaFalseJosKortillaEiRiittavastiRahaa() {
        kortti.otaRahaa(200);
        assertFalse(kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void syoMaukkaastiPalauttaaFalseJosKortillaEiRiittavastiRahaa() {
        kortti.otaRahaa(200);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraEiMuutuJosKortillaEiRiittavastiRahaa() {
        kortti.otaRahaa(200);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraEiKasvaJosKortillaEiRiittavastiRahaa() {
        kortti.otaRahaa(200);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kassanRahamaaraEiMuutuEdullistaLounastaOstaessaJosKortillaEiTarpeeksiRahaa() {
        kortti.otaRahaa(200);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void kassanRahamaaraEiMuutuMaukastaLounastaOstaessaJosKortillaEiTarpeeksiRahaa() {
        kortti.otaRahaa(200);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void kortinSaldoKasvaaRahaaLadattaessa() {
        int ladattavaMaara = 1000;
        kassapaate.lataaRahaaKortille(kortti, ladattavaMaara);
        assertTrue(kortti.saldo() == 400 + ladattavaMaara);
    }
    
    @Test
    public void kassanRahamaaraKasvaaOikeinKorttiaLadattaessa() {
        int ladattavaMaara = 1000;
        kassapaate.lataaRahaaKortille(kortti, ladattavaMaara);
        assertTrue(kassapaate.kassassaRahaa() == 100000 + ladattavaMaara);
    }
    
    @Test
    public void kortinSaldoEiMuutuNegatiivisellaLatausmaaralla() {
        kassapaate.lataaRahaaKortille(kortti, -100);
        assertTrue(kortti.saldo() == 400);
    }
    
    @Test
    public void kassanRahamaaraEiMuutuNegatiivisellaLatausmaaralla() {
        kassapaate.lataaRahaaKortille(kortti, -100);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
}

package com.example.javafx.zahlenschloss;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ZahlenschlossModelTest {

    @Test
    void testCorrectCodeOpensLock() {
        ZahlenschlossModel model = new ZahlenschlossModel();

        assertEquals(LockState.WAITING, model.play(Zahl.SIEBEN));
        assertEquals(LockState.WAITING, model.play(Zahl.DREI));
        assertEquals(LockState.OPEN, model.play(Zahl.FUENF));
    }

    @Test
    void testIncorrectCodeClosesLock() {
        ZahlenschlossModel model = new ZahlenschlossModel();

        assertEquals(LockState.WAITING, model.play(Zahl.EINS));
        assertEquals(LockState.WAITING, model.play(Zahl.ZWEI));
        assertEquals(LockState.CLOSED, model.play(Zahl.DREI));
    }

}
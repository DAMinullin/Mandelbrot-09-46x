package ru.gr0946x.convertation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    private Converter conv1;
    private Converter conv2;
    private Converter conv3;
    @BeforeEach
    void setUp() {
        conv1=new Converter(-2,4,-2,4);
        conv1.setWidth(600);
        conv1.setHeight(600);

        conv2=new Converter(2,4,2,4);
        conv2.setWidth(400);
        conv2.setHeight(400);

        conv3=new Converter(-6,-4,-6,-4);
        conv3.setWidth(400);
        conv3.setHeight(400);
    }

    @ParameterizedTest
    @DisplayName("Корректность преобразования абсциссы из декартовой системы координат в экранную при концах отрезка разного знака")
    @CsvSource({
            "-3,-100",
            "-2,0",
            "-1,100",
            "0,200",
            "2,400",
            "4,600",
            "5,700"
    })
    void xCrtToScr1(double cx, int sx) {
        int actual = conv1.xCrtToScr(cx);
        int expected = sx;
        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @DisplayName("Корректность преобразования абсциссы из декартовой системы координат в экранную при положительных концах отрезка")
    @CsvSource({
            "3,200",
            "-2,-800",
            "-1,-600",
            "0,-400",
            "2,0",
            "4,400",
            "5,600"
    })
    void xCrtToScr2(double cx, int sx) {
        int actual = conv2.xCrtToScr(cx);
        int expected = sx;
        assertEquals(expected,actual);
    }

    @Test
    void yCrtToScr() {
    }

    @Test
    void xScrToCrt() {
    }

    @Test
    void yScrToCrt() {
    }
}
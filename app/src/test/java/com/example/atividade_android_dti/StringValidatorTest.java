package com.example.atividade_android_dti;

import com.example.atividade_android_dti.utils.DateHandler;
import com.example.atividade_android_dti.utils.StringsValidator;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class StringValidatorTest {

    @Test
    public void nameValidator_CorrectSimple_ReturnsTrue(){
        assertTrue(StringsValidator.isValidUserName("Joao.lucas"));
        assertTrue(StringsValidator.isValidUserName("Joaolucas"));
        assertTrue(StringsValidator.isValidUserName("joaolucas"));
        assertTrue(StringsValidator.isValidUserName("joao.lucas"));
    }

    @Test
    public void passwordValidator_CorrectSimple_ReturunsTrue(){
        assertTrue(StringsValidator.isValidPassword("123456"));
        assertTrue(StringsValidator.isValidPassword("abcdef"));
        assertTrue(StringsValidator.isValidPassword("12$#ad"));

    }

    @Test
    public void nameValidator_CorrectSimple_ReturnsFalse(){
        assertFalse(StringsValidator.isValidUserName("Joao_^lucas"));
        assertFalse(StringsValidator.isValidUserName("Joao*=lucas"));
        assertFalse(StringsValidator.isValidUserName("joao()lucas"));
        assertFalse(StringsValidator.isValidUserName("Joao-+lucas"));
        assertFalse(StringsValidator.isValidUserName(""));
    }

    @Test
    public void passwordValidator_CorrectSimple_ReturnsFalse(){
        assertFalse(StringsValidator.isValidPassword("1234"));
        assertFalse(StringsValidator.isValidPassword(""));
        assertFalse(StringsValidator.isValidPassword("12345"));
    }

    @Test
    public void dataHandler_FormatDat_CorrectFormatTrue(){
        assertEquals(DateHandler.getDormatedData(1567306800000L), "01/09/2019");
    }
}

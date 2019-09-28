package com.example.atividade_android_dti;

import com.example.atividade_android_dti.utils.DateHelper;
import com.example.atividade_android_dti.utils.StringsHelpers;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class StringValidatorTest {

    @Test
    public void nameValidator_CorrectSimple_ReturnsTrue(){
        assertTrue(StringsHelpers.isValidUserName("Joao.lucas"));
        assertTrue(StringsHelpers.isValidUserName("Joaolucas"));
        assertTrue(StringsHelpers.isValidUserName("joaolucas"));
        assertTrue(StringsHelpers.isValidUserName("joao.lucas"));
    }

    @Test
    public void passwordValidator_CorrectSimple_ReturunsTrue(){
        assertTrue(StringsHelpers.isValidPassword("123456"));
        assertTrue(StringsHelpers.isValidPassword("abcdef"));
        assertTrue(StringsHelpers.isValidPassword("12$#ad"));

    }

    @Test
    public void nameValidator_CorrectSimple_ReturnsFalse(){
        assertFalse(StringsHelpers.isValidUserName("Joao_^lucas"));
        assertFalse(StringsHelpers.isValidUserName("Joao*=lucas"));
        assertFalse(StringsHelpers.isValidUserName("joao()lucas"));
        assertFalse(StringsHelpers.isValidUserName("Joao-+lucas"));
        assertFalse(StringsHelpers.isValidUserName(""));
    }

    @Test
    public void passwordValidator_CorrectSimple_ReturnsFalse(){
        assertFalse(StringsHelpers.isValidPassword("1234"));
        assertFalse(StringsHelpers.isValidPassword(""));
        assertFalse(StringsHelpers.isValidPassword("12345"));
    }

    @Test
    public void dataHandler_FormatDat_CorrectFormatTrue(){
        assertEquals(DateHelper.getDormatedData(1567306800000L), "01/09/2019");
    }
}

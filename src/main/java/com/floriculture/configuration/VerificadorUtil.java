package com.floriculture.configuration;

public class VerificadorUtil {

    public static boolean verificarErro(String... valores) {
        for (String valor : valores) {
            if (valor == null || valor.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}

package com.myspringboot.services.validation.utils;
//Fonte Chat GPT kkkkkk
public class Utils {

	 public static boolean validateCPF(String cpf) {
	        // Remover caracteres não numéricos
	        cpf = cpf.replaceAll("[^0-9]", "");

	        // Verificar se o CPF tem 11 dígitos
	        if (cpf.length() != 11) {
	            return false;
	        }

	        // Verificar se todos os dígitos são iguais
	        boolean allEqual = true;
	        for (int i = 1; i < 11; i++) {
	            if (cpf.charAt(i) != cpf.charAt(0)) {
	                allEqual = false;
	                break;
	            }
	        }
	        if (allEqual) {
	            return false;
	        }

	        // Calcular o primeiro dígito verificador
	        int sum = 0;
	        for (int i = 0; i < 9; i++) {
	            int digit = cpf.charAt(i) - '0';
	            sum += digit * (10 - i);
	        }
	        int firstDigit = 11 - (sum % 11);
	        if (firstDigit > 9) {
	            firstDigit = 0;
	        }

	        // Calcular o segundo dígito verificador
	        sum = 0;
	        for (int i = 0; i < 10; i++) {
	            int digit = cpf.charAt(i) - '0';
	            sum += digit * (11 - i);
	        }
	        int secondDigit = 11 - (sum % 11);
	        if (secondDigit > 9) {
	            secondDigit = 0;
	        }

	        // Verificar se os dígitos verificadores estão corretos
	        if (cpf.charAt(9) - '0' != firstDigit || cpf.charAt(10) - '0' != secondDigit) {
	            return false;
	        }

	        return true;
	    }
	 
	    public static boolean validateCNPJ(String cnpj) {
	        // Remover caracteres não numéricos
	        cnpj = cnpj.replaceAll("[^0-9]", "");

	        // Verificar se o CNPJ tem 14 dígitos
	        if (cnpj.length() != 14) {
	            return false;
	        }

	        // Verificar se todos os dígitos são iguais
	        boolean allEqual = true;
	        for (int i = 1; i < 14; i++) {
	            if (cnpj.charAt(i) != cnpj.charAt(0)) {
	                allEqual = false;
	                break;
	            }
	        }
	        if (allEqual) {
	            return false;
	        }

	        // Calcular o primeiro dígito verificador
	        int sum = 0;
	        int[] weights = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	        for (int i = 0; i < 12; i++) {
	            int digit = cnpj.charAt(i) - '0';
	            sum += digit * weights[i];
	        }
	        int firstDigit = 11 - (sum % 11);
	        if (firstDigit > 9) {
	            firstDigit = 0;
	        }

	        // Calcular o segundo dígito verificador
	        sum = 0;
	        weights = new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	        for (int i = 0; i < 13; i++) {
	            int digit = cnpj.charAt(i) - '0';
	            sum += digit * weights[i];
	        }
	        int secondDigit = 11 - (sum % 11);
	        if (secondDigit > 9) {
	            secondDigit = 0;
	        }

	        // Verificar se os dígitos verificadores estão corretos
	        if (cnpj.charAt(12) - '0' != firstDigit || cnpj.charAt(13) - '0' != secondDigit) {
	            return false;
	        }

	        return true;
	    }
}

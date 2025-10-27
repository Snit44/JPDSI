package com.jsfcourse.calc;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class CalcBB {
    private String amount;
    private String years;
    private String interest;
    private Double result;

    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }

    public String getYears() { return years; }
    public void setYears(String years) { this.years = years; }

    public String getInterest() { return interest; }
    public void setInterest(String interest) { this.interest = interest; }

    public Double getResult() { return result; }
    public void setResult(Double result) { this.result = result; }

    private void addMessage(FacesMessage.Severity severity, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, null));
    }

    public boolean validateAndCompute() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        // Walidacja
        if (amount == null || years == null || interest == null) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Brak jednego z parametrów.");
            return false;
        }

        if (amount.isBlank()) addMessage(FacesMessage.SEVERITY_ERROR, "Nie podano kwoty kredytu.");
        if (years.isBlank()) addMessage(FacesMessage.SEVERITY_ERROR, "Nie podano okresu kredytowania.");
        if (interest.isBlank()) addMessage(FacesMessage.SEVERITY_ERROR, "Nie podano oprocentowania.");

        if (!ctx.getMessageList().isEmpty()) return false;

        double a, y, r;
        try {
            a = Double.parseDouble(amount);
            y = Double.parseDouble(years);
            r = Double.parseDouble(interest);
        } catch (NumberFormatException e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Wszystkie parametry muszą być liczbami.");
            return false;
        }

        if (a <= 0) addMessage(FacesMessage.SEVERITY_ERROR, "Kwota kredytu musi być większa od 0.");
        if (y <= 0) addMessage(FacesMessage.SEVERITY_ERROR, "Okres kredytowania musi być większy od 0.");
        if (r < 0) addMessage(FacesMessage.SEVERITY_ERROR, "Oprocentowanie nie może być ujemne.");

        if (!ctx.getMessageList().isEmpty()) return false;

        // Obliczenia
        double monthlyRate = r / 100 / 12;
        double months = y * 12;

        if (monthlyRate > 0) {
            result = (monthlyRate * a) / (1 - Math.pow(1 + monthlyRate, -months));
        } else {
            result = a / months;
        }

        result = Math.round(result * 100.0) / 100.0;

        addMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie. Miesięczna rata: " + result);
        return true;
    }

    public String calc() {
        if (validateAndCompute()) {
            return "showresult"; // przejście do widoku wyniku
        }
        return null;
    }

    public String calc_AJAX() {
        if (validateAndCompute()) {
            addMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result);
        }
        return null;
    }
}

package com.jsfcourse.calc;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class CalcBB {

    private Double amount;
    private Double years;
    private Double interest;
    private Double result;

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Double getYears() { return years; }
    public void setYears(Double years) { this.years = years; }

    public Double getInterest() { return interest; }
    public void setInterest(Double interest) { this.interest = interest; }

    public Double getResult() { return result; }
    public void setResult(Double result) { this.result = result; }

    private void addMessage(FacesMessage.Severity severity, String msg) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, msg, null));
    }

    public boolean validateAndCompute() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        if (amount == null) addMessage(FacesMessage.SEVERITY_ERROR, "Nie podano kwoty kredytu.");
        if (years == null) addMessage(FacesMessage.SEVERITY_ERROR, "Nie podano okresu kredytowania.");
        if (interest == null) addMessage(FacesMessage.SEVERITY_ERROR, "Nie podano oprocentowania.");

        if (!ctx.getMessageList().isEmpty()) return false;

        if (amount <= 0) addMessage(FacesMessage.SEVERITY_ERROR, "Kwota kredytu musi być większa od 0.");
        if (years <= 0) addMessage(FacesMessage.SEVERITY_ERROR, "Okres kredytowania musi być większy od 0.");
        if (interest < 0) addMessage(FacesMessage.SEVERITY_ERROR, "Oprocentowanie nie może być ujemne.");

        if (!ctx.getMessageList().isEmpty()) return false;

        double monthlyRate = interest / 100.0 / 12.0;
        double months = years * 12.0;

        if (monthlyRate > 0) {
            result = (monthlyRate * amount) /
                     (1 - Math.pow(1 + monthlyRate, -months));
        } else {
            result = amount / months;
        }

        result = Math.round(result * 100.0) / 100.0;

        addMessage(FacesMessage.SEVERITY_INFO,
                "Operacja wykonana poprawnie. Miesięczna rata: " + result);

        return true;
    }

    public String calc() {
        return validateAndCompute() ? "showresult" : null;
    }

    public String calc_AJAX() {
        if (validateAndCompute()) {
            addMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result);
        }
        return null;
    }
}

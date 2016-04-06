package com.example.tato.turkishlira;

public interface Converter {
    public double getConversionRate(String fromCurrencyCode,
                                    String toCurrencyCode) throws CurrencyConverterException;
}

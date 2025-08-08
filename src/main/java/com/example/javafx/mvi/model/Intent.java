package com.example.javafx.mvi.model;

public sealed interface Intent {
    record Submit(String input) implements Intent {}
}

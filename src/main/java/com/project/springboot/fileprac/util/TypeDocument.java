package com.project.springboot.fileprac.util;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum TypeDocument {
    COMPLAINT ("Файл рекламации"),
    DELIVERY ("Доставка"),
    UNKNOWN ("Неопознанный");

    private String type;

}

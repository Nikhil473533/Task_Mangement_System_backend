package com.practice.demo.dto;

public class FilterCriteria {
    private String field;
    private String value;
    private String matchMode; // startsWith, endsWith, contains, equals
    private String operator; // and, or

    public FilterCriteria() {
    }

    public FilterCriteria(String field, String value, String matchMode, String operator) {
        this.field = field;
        this.value = value;
        this.matchMode = matchMode;
        this.operator = operator;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMatchMode() {
        return matchMode;
    }

    public void setMatchMode(String matchMode) {
        this.matchMode = matchMode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}


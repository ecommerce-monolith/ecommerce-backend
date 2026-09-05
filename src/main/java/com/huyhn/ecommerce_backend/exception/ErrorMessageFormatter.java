package com.huyhn.ecommerce_backend.exception;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class ErrorMessageFormatter {
    private static final Pattern PLACEHOLDER = Pattern.compile("\\{\\{(\\w+)\\}\\}");

    private ErrorMessageFormatter() {
    }

    static String format(String template, Map<String, ?> args) {
        if (template == null || args == null || args.isEmpty()) {
            return template;
        }

        Matcher matcher = PLACEHOLDER.matcher(template);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String key = matcher.group(1);
            Object value = args.get(key);
            String replacement = value != null ? String.valueOf(value) : "";
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);
        return result.toString();
    }
}

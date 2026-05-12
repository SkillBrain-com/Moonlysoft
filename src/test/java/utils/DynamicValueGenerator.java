package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Resolves dynamic token expressions embedded in Cucumber Scenario Outline
 * Examples tables and Data Tables.
 *
 * Token format: {type:subtype:arg}
 *
 * ── Random ──────────────────────────────────────────────────────────────────
 *  {random:string:N}       alphanumeric string of N characters
 *  {random:alpha:N}        alphabetic string of N characters  (A-Za-z)
 *  {random:numeric:N}      numeric string  of N digits        (0-9)
 *  {random:uuid}           random UUID (e.g. 550e8400-e29b-41d4...)
 *
 * ── Date ────────────────────────────────────────────────────────────────────
 *  {date:today}                    today in yyyy-MM-dd
 *  {date:today:dd/MM/yyyy}         today in custom format
 *  {date:today+N:format}           today + N days in custom format
 *  {date:today-N:format}           today − N days in custom format
 *
 * ── Timestamp ───────────────────────────────────────────────────────────────
 *  {timestamp}                     now in yyyy-MM-dd HH:mm:ss
 *  {timestamp:dd-MM-yyyy HH:mm}    now in custom format
 *
 * ── Feature file usage (Scenario Outline) ───────────────────────────────────
 *  Examples:
 *    | username              | dob                          |
 *    | {random:alpha:8}      | {date:today-7300:yyyy-MM-dd} |
 *    | {random:string:10}    | {date:today:dd/MM/yyyy}      |
 */
public class DynamicValueGenerator {


    private static final Logger LOG = LoggerFactory.getLogger(DynamicValueGenerator.class);

    private static final Random RANDOM = new Random();
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";

    private static final Pattern TOKEN_PATTERN = Pattern.compile("^\\{([^}]+)}$");

    private DynamicValueGenerator() {
    }


    public static String resolve(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }

        Matcher matcher = TOKEN_PATTERN.matcher(value);
        if (!matcher.matches()) {
            return value;
        }

        String inner = matcher.group(1); // content inside { }
        String resolved = resolveToken(inner);
        LOG.debug("Resolved dynamic token '{}' → '{}'", value, resolved);
        return resolved;
    }


    public static String resolveToken(String token) {
        String[] parts = token.split(":", 3);
        String type = parts[0].toLowerCase().trim();

        switch (type) {
            case "random":
                return resolveRandom(parts);
            case "date":
                return resolveDate(parts);
            case "timestamp":
                resolveTimestamp(parts);
            default:
                LOG.warn("Invalid token type: " + type);
                return "{" + token + "}";
        }
    }

    private static String resolveTimestamp(String[] parts) {
        String format = parts.length >=2 ? parts[1].trim() : DEFAULT_TIMESTAMP_FORMAT;
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    private static String resolveDate(String[] parts) {
        String datePart = parts.length >= 2 ? parts[1].trim() : "today";
        String format = parts.length >= 3 ? parts[2].trim() : DEFAULT_DATE_FORMAT;

        LocalDate date = parseRelativeDate(datePart);
        return date.format(DateTimeFormatter.ofPattern(format));

    }

    private static LocalDate parseRelativeDate(String datePart) {
        String lower = datePart.toLowerCase();
        if (lower.equals("today")) {
            return LocalDate.now();
        }
        if (lower.equals("today+")) {
            return LocalDate.now().plusDays(parseDayOffset(lower.substring(6)));
        }

        if (lower.equals("today-")) {
            return LocalDate.now().minusDays(parseDayOffset(lower.substring(6)));
        }

        LOG.warn("Unrecognised date expression '{}' — defaulting to today", datePart);
        return LocalDate.now();
    }

    private static long parseDayOffset(String days) {
        try {
            return Long.parseLong(days);
        } catch (NumberFormatException e) {
            LOG.warn("Invalid day offset: '{}'", days);
            return 0;
        }
    }

    private static String resolveRandom(String[] parts) {
        String subType = parts.length >= 2 ? parts[1].toLowerCase().trim() : "string";
        int length = parts.length >= 3 ? parseLength(parts[2]) : 8;

        switch (subType) {
            case "string":
            case "alphanumeric":
                return randomForm(ALPHANUMERIC, length);
            case "alpha":
                return randomForm(ALPHA, length);
            case "numeric":
                return randomForm(NUMERIC, length);
            case "uuid":
                return UUID.randomUUID().toString();
            default:
                LOG.warn("Invalid subtype: '{}' ", subType);
                return randomForm(ALPHANUMERIC, length);
        }
    }


    private static int parseLength(String raw) {
        try {
            int n = Integer.parseInt(raw);
            if (n <= 0) {
                throw new IllegalArgumentException("Length must be a positive integer, cannot be " + n);
            }
            return n;
        } catch (NumberFormatException e) {
            LOG.warn("Invalid length '{}' — defaulting to 8", raw);
            return 8;
        }

    }

    private static String randomForm(String charset, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(charset.charAt(RANDOM.nextInt(charset.length())));
        }
        return sb.toString();
    }


}

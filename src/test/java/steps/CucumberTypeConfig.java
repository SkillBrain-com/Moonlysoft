package steps;

import io.cucumber.java.ParameterType;
import utils.DynamicValueGenerator;


/**
 * Registers custom Cucumber parameter types and data table transformers.
 *
 * ── {dyn} parameter type ────────────────────────────────────────────────────
 * Use {dyn} in step definitions wherever the value in the Examples table
 * might be a dynamic token expression.  Unlike {string}, no quotes are
 * needed in the feature file.
 *
 *   Step definition:
 *     @When("the user registers with username {dyn} and date of birth {dyn}")
 *
 *   Feature file — static value:
 *     When the user registers with username standard_user and date of birth 1990-01-01
 *
 *   Feature file — Scenario Outline with dynamic tokens:
 *     When the user registers with username <username> and date of birth <dob>
 *     Examples:
 *       | username            | dob                          |
 *       | {random:alpha:8}    | {date:today-7300:yyyy-MM-dd} |
 *       | {random:string:10}  | {date:today:dd/MM/yyyy}      |
 *       | staticUser          | 1995-06-15                   |   ← plain values still work
 *
 * ── Data Tables ─────────────────────────────────────────────────────────────
 * Dynamic tokens inside Cucumber Data Tables are resolved automatically —
 * no changes needed in step definitions that consume DataTable or Map<> params.
 *
 *   Feature file:
 *     | field     | value                 |
 *     | firstName | {random:alpha:6}      |
 *     | dob       | {date:today-9000:iso} |
 */

public class CucumberTypeConfig {

    /**
     * {dyn} — resolves a dynamic token or returns the value unchanged.
     *
     * Pattern breakdown:
     *   "([^"]*)"    → quoted value  (e.g. "some text" or "{random:alpha:8}")
     *   |            → OR
     *   ([^\s,]+)    → unquoted word (e.g. standard_user or {random:alpha:8})
     *
     * Both quoted and unquoted forms pass through DynamicValueGenerator.resolve(),
     * so plain strings are returned as-is while token expressions are expanded.
     */

    @ParameterType(name ="dyn", value = "\"([^\"]*)\"|([^,]+)")
    public String dyn(String quoted, String unquoted) {
        String raw = quoted != null? quoted: unquoted;
        return DynamicValueGenerator.resolve(raw !=null ? raw.trim() : "");
    }
}

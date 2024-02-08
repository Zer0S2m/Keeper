package com.zer0s2m.keeper.validation

/**
 * Validator for an empty value in a field
 *
 * @param inputMsg Message text for error output
 */
open class NotEmptyValidator(
    inputMsg: String = "This field is required"
) : Validator {

    /**
     * Validation error message
     */
    final override val msg: String = inputMsg

    /**
     * Field Validation
     *
     * @param value The string value that must be validated
     */
    override fun validate(value: Any): Boolean {
        if (value is String) {
            return value.toString().trim().isNotEmpty()
        }
        return true
    }

}

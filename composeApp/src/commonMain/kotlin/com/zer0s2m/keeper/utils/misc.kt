package com.zer0s2m.keeper.utils

/**
 * Open link in default browser.
 *
 * @param url Link.
 */
internal expect fun openUrl(url: String?)

/**
 * Collect a name to draw an organization card.
 *
 * @param title Name of company.
 * @return The collected name for drawing the organization card.
 */
internal expect fun formatTitleOrganization(title: String): String

package com.zer0s2m.keeper.utils

import java.awt.Desktop
import java.net.URI

/**
 * Open link in default browser.
 *
 * @param url Link.
 */
internal actual fun openUrl(url: String?) {
    val uri = url?.let { URI.create(it) } ?: return
    Desktop.getDesktop().browse(uri)
}

/**
 * Collect a name to draw an organization card.
 *
 * @param title Name of company.
 * @return The collected name for drawing the organization card.
 */
internal actual fun formatTitleOrganization(title: String): String {
    val splitTitle: List<String> = title.split(" ")
    return if (splitTitle.size >= 2) {
        splitTitle[0][0].uppercase() + splitTitle[1][0].uppercase()
    } else if (splitTitle.size == 1) {
        splitTitle[0][0].uppercase()
    } else {
        return "-"
    }
}

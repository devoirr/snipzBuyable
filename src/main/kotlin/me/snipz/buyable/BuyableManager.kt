package me.snipz.buyable

import org.bukkit.entity.Player

interface BuyableItem {
    val id: String

    suspend fun purchase(player: Player)
    fun isAvailable(player: Player): Boolean
}

enum class BuyableCategory {
    SHOP_ITEM,
    TITLE,
    GRADIENT
}

object BuyableManager {

    private val buyableItems = mutableMapOf<BuyableCategory, MutableList<BuyableItem>>()

    fun registerBuyableItem(category: BuyableCategory, item: BuyableItem) {
        buyableItems.computeIfAbsent(category) { mutableListOf() }.add(item)
    }

    fun getBuyableItems(category: BuyableCategory): List<BuyableItem> {
        return buyableItems[category] ?: emptyList()
    }

    fun clearCategory(category: BuyableCategory) {
        buyableItems[category]?.clear()
    }

}
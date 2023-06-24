package com.example.mynavigation.Utils
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mynavigation.models.CartItem

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "cart.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "cart"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_IMAGE = "image"
        private const val COLUMN_INGREDIENTS = "ingredients"
        private const val COLUMN_RESTAURANT_ID = "restaurantId"
        private const val COLUMN_QUANTITY = "quantity"
    }



    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY,
                $COLUMN_NAME TEXT,
                $COLUMN_PRICE REAL,
                $COLUMN_QUANTITY INTEGER,
                $COLUMN_IMAGE INTEGER,
                $COLUMN_INGREDIENTS TEXT,
                $COLUMN_RESTAURANT_ID INTEGER
            )
        """.trimIndent()
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Perform any necessary migrations
    }

    fun addToCart(item: CartItem) {
        val database = writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_ID, item.id)
            put(COLUMN_NAME, item.name)
            put(COLUMN_PRICE, item.price)
            put(COLUMN_IMAGE, item.image)
            put(COLUMN_INGREDIENTS, item.ingredients)
            put(COLUMN_RESTAURANT_ID, item.restaurantId)
            put(COLUMN_QUANTITY, item.quantity)
        }

        database.insert(TABLE_NAME, null, values)
    }

    // Update quantity of an item in the cart
    fun updateQuantity(itemId: Int, newQuantity: Int) {
        val database = writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_QUANTITY, newQuantity)
        }

        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(itemId.toString())

        database.update(TABLE_NAME, values, selection, selectionArgs)
    }

    // Retrieve all cart items
    fun getCartItems(): List<CartItem> {
        val database = readableDatabase
        val cursor: Cursor = database.query(TABLE_NAME, null, null, null, null, null, null)

        val cartItems = mutableListOf<CartItem>()

        val idColumnIndex = cursor.getColumnIndex(COLUMN_ID)
        val nameColumnIndex = cursor.getColumnIndex(COLUMN_NAME)
        val priceColumnIndex = cursor.getColumnIndex(COLUMN_PRICE)
        val quantityColumnIndex = cursor.getColumnIndex(COLUMN_QUANTITY)
        val imageColumnIndex = cursor.getColumnIndex(COLUMN_IMAGE)
        val ingredientsColumnIndex = cursor.getColumnIndex(COLUMN_INGREDIENTS)
        val restaurantIdColumnIndex = cursor.getColumnIndex(COLUMN_RESTAURANT_ID)

        while (cursor.moveToNext()) {
            val id = if (idColumnIndex != -1) cursor.getInt(idColumnIndex) else 0
            val name = if (nameColumnIndex != -1) cursor.getString(nameColumnIndex) else ""
            val price = if (priceColumnIndex != -1) cursor.getInt(priceColumnIndex) else 0
            val quantity = if (quantityColumnIndex != -1) cursor.getInt(quantityColumnIndex) else 0
            val image = if (imageColumnIndex != -1) cursor.getString(imageColumnIndex) else ""
            val ingredients = if (ingredientsColumnIndex != -1) cursor.getString(ingredientsColumnIndex) else ""
            val restaurantId = if (restaurantIdColumnIndex != -1) cursor.getInt(restaurantIdColumnIndex) else 0

            val cartItem = CartItem(id, name, price, image, ingredients, restaurantId ,quantity)
            cartItems.add(cartItem)
        }

        cursor.close()
        return cartItems
    }


    // Delete an item from the cart
    fun deleteItemFromDatabase(itemId: Int) {
        val database = writableDatabase

        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(itemId.toString())

        database.delete(TABLE_NAME, selection, selectionArgs)
    }

    fun updateQuantityInDatabase(itemId: Int, newQuantity: Int) {
        val database = writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_QUANTITY, newQuantity)
        }

        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(itemId.toString())

        database.update(TABLE_NAME, values, selection, selectionArgs)
    }

}
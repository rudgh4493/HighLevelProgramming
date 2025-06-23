// 장바구니 클래스 정의
class Cart {
    // 상품과 수량을 저장
    val items = mutableMapOf<Product, Int>()

    // 상품을 장바구니에 추가하는 함수
    fun addProduct(product: Product, quantity: Int) {
        if (product.stock >= quantity) {
            items[product] = items.getOrDefault(product, 0) + quantity
            println("장바구니에 ${product.name} ${quantity} 개 추가")
        } else {
            println("재고 부족: ${product.name}")
        }
    }

    // 장바구니에서 특정 상품을 제거하는 함수
    fun removeProduct(product: Product) {
        items.remove(product)
        println("장바구니에서 ${product.name} 삭제")
    }


    // 장바구니에 있는 상품의 수량을 변경하는 함수
    fun changeQuantity(product: Product, quantity: Int) {
        if (product.stock >= quantity) {
            items[product] = quantity
            println("장바구니에서 ${product.name} 수량 $quantity 개로 변경")
        } else {
            println("재고 부족")
        }
    }

    // 현재 장바구니의 총 금액을 계산하는 함수
    fun calculateTotal(): Double = items.map { (product, qty) -> product.price * qty }.sum()

    // 장바구니를 비우는 함수
    fun clear() = items.clear()

    // 장바구니 내용을 출력하는 함수
    fun showCart() {
        println("장바구니 목록:")
        items.forEach { (product, qty) ->
            println("${product.name} x $qty = ${product.price * qty}")
        }
        println("총액: ${calculateTotal()}원")
    }

    // 외부에서 장바구니 아이템을 조회할 수 있도록 복사본을 반환
    fun getCartItems(): Map<Product, Int> = items.toMap()

}

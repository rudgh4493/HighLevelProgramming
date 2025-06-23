// 상점 클래스
class Shop(private val products: MutableList<Product>) {
    // 상품별 누적 판매 수량
    private val soldCount = mutableMapOf<Product, Int>()

    // 현재 상점의 전체 상품 목록을 출력
    fun listProducts() {
        println("상품 목록:")
        products.forEachIndexed { index, product ->
            println("${index + 1}. ${product.name} - ${product.price}원 (재고: ${product.stock})")
        }
        println("-----------------------")
    }
    // 상품 이름으로 상품을 검색하고, 검색 결과가 있을 경우 상품 정보를 출력
    fun findProductByName(name: String): Product? {
        val product = products.find { it.name.contains(name, ignoreCase = true) }
        if (product != null) {
            println("검색된 상품:")
            println("이름: ${product.name}")
            println("가격: ${product.price}원")
            println("재고: ${product.stock}개")
            println("-----------------------")
        } else {
            println("해당 이름의 상품을 찾을 수 없습니다.")
            println("-----------------------")
        }
        return product
    }


    // 장바구니의 상품들을 결제
    fun checkout(cart: Cart) {
        val items = cart.getCartItems()
        // 각 상품에 대해 재고가 충분한지 확인 후 처리
        for ((product, qty) in items) {
            if (product.stock >= qty) {
                product.stock -= qty
                soldCount[product] = soldCount.getOrDefault(product, 0) + qty
            } else {
                // 재고 부족 시 전체 결제 취소
                println("${product.name} 재고 부족. 결제 실패.")
                return
            }
        }
        // 모든 상품 결제 성공 시, 총액 출력 후 장바구니 비우기
        println("결제 완료! 총액: ${cart.calculateTotal()}원")
        println("----------------------------------------")
        cart.clear()
    }

    // 지금까지 가장 많이 팔린 상품을 출력하는 함수
    fun showPopularProducts() {
        println("인기 상품:")
        soldCount.entries.sortedByDescending { it.value }
            .forEach { (product, count) ->
                println("${product.name}: $count 개 판매")
            }
    }

    // 가격 범위에 포함되는 상품을 필터링하고 결과를 출력
    fun filterSearch(min: Double, max: Double): List<Product> {
        val filtered = products.filter { it.price in min..max }

        if (filtered.isEmpty()) {
            println("💸 ${min}원 ~ ${max}원 사이에 해당하는 상품이 없습니다.")
        } else {
            println("💸 ${min}원 ~ ${max}원 사이의 상품:")
            filtered.forEach { product ->
                println("이름: ${product.name} / 가격: ${product.price}원 / 재고: ${product.stock}개")
            }
        }

        return filtered
    }


}

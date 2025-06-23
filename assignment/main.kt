fun main() {
    val p1 = Product("노트북", 1200000.0, 5)
    val p2 = Product("마우스", 20000.0, 10)
    val p3 = Product("키보드", 50000.0, 7)

    val shop = Shop(mutableListOf(p1, p2, p3))

    val cart = Cart()

    shop.listProducts()                     // 상품 리스트 출력
    cart.addProduct(p1, 1)          // 장바구니에 물품 추가
    cart.addProduct(p2, 2)          // 장바구니에 물품 추가
    cart.removeProduct(p2)                  // 장바구니에 물품 삭제
    cart.changeQuantity(p1, 3)      // 장바구미 물품 수량 변경
    cart.showCart()                         // 장바구니 보이기
    shop.checkout(cart)                     // 구매

    shop.listProducts()                     // 구매 후 상품 리스트 (재고변경)

    shop.showPopularProducts()              // 많이 팔린 인기상품 출력
    shop.findProductByName("노트북")   // 상품 검색
    shop.filterSearch(1000.0, 40000.0)      // 해당 가격대의 물품 출력

}

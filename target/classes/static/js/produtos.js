let wishlist = [];

let cart = [];

function addToWishlist(productId) {
    if (!wishlist.includes(productId)) {
        wishlist.push(productId);
        alert("Produto" + productId + " foi adicionado à sua lista de desejos.");
    } else {
        alert("Este produto já está na sua lista de desejos.");
    }
}

function addToCart(productId) {
    const productInfo = getProductInfoById(productId);
    if (productInfo) {
        cart.push(productInfo);
        alert("Produto" + productId + " foi adicionado ao seu carrinho");
    } else {
        alert("Este produto já está no seu carrinho");
    }
}

function getProductInfoById(productId) {
    const products = [
        { id: 1, name: "Produto 1", price: 5.0 },
        { id: 2, name: "Produto 2", price: 5.0 },
        { id: 3, name: "Produto 3", price: 5.0 },
        { id: 4, name: "Produto 4", price: 5.0 },
    ];

    const product = products.find((p) => p.id === productId);
    return product;
}

const productImages = document.querySelectorAll('.img-prod');

const heartIcons = document.querySelectorAll('.heart');

const cartIcons = document.querySelectorAll('.ion-ios-cart');

productImages.forEach((productImage) => {
    productImage.addEventListener('click', function () {
        const productId = parseInt(productImage.getAttribute('data-product-id'));
        window.location.href = 'detalhesproduto.html?id=' + productId;
    });
});

heartIcons.forEach((heartIcon) => {
    heartIcon.addEventListener('click', function () {
        const productId = parseInt(heartIcon.getAttribute('data-product-id'));
        addToWishlist(productId);
    });
});

cartIcons.forEach((cartIcon) => {
    cartIcon.addEventListener('click', function () {
        const productId = parseInt(cartIcon.getAttribute('data-product-id'));
        addToCart(productId);
    });
});

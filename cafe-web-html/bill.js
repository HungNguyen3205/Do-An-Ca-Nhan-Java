document.addEventListener('DOMContentLoaded', function() {
    // Get order data from sessionStorage
    const orderData = JSON.parse(sessionStorage.getItem('orderData'));
    if (!orderData) {
        window.location.href = 'CafeShop.html';
        return;
    }

    // Display order details
    const billDetails = document.getElementById('bill-details');
    const menuItems = {
        'coffee-black': { name: 'Cà phê đen', price: 12000 },
        'coffee-milk': { name: 'Cà phê sữa', price: 15000 },
        'bacc-xiu': { name: 'Bạc xỉu', price: 18000 },
        'cacao-ice': { name: 'Ca cao đá', price: 20000 },
        'cacao-hot': { name: 'Ca cao nóng', price: 22000 },
        'coffee-salt': { name: 'Cà phê muối', price: 20000 }
    };

    let totalAmount = 0;
    orderData.items.forEach(item => {
        const itemDetails = menuItems[item.id];
        const itemTotal = itemDetails.price * item.quantity;
        totalAmount += itemTotal;

        const itemElement = document.createElement('div');
        itemElement.className = 'bill-item';
        itemElement.innerHTML = `
            <span class="item-name">${itemDetails.name}</span>
            <span class="item-quantity">x${item.quantity}</span>
            <span class="item-price">${itemTotal.toLocaleString()} VNĐ</span>
        `;
        billDetails.appendChild(itemElement);
    });

    // Display total
    document.getElementById('total').innerHTML = `
        <h3>Tổng cộng:</h3>
        <p>${totalAmount.toLocaleString()} VNĐ</p>
    `;
});

function resetBill() {
    sessionStorage.clear();
    window.location.href = 'CafeShop.html';
} 
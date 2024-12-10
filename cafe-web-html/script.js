document.addEventListener('DOMContentLoaded', function() {
    // Add click event listeners to all quantity buttons
    const quantityButtons = document.querySelectorAll('.increase-quantity, .decrease-quantity');
    
    quantityButtons.forEach(button => {
        button.addEventListener('click', function() {
            const item = this.getAttribute('data-item');
            const quantityElement = document.getElementById(`quantity-${item}`);
            const currentQuantity = parseInt(quantityElement.textContent);
            
            if (this.classList.contains('increase-quantity')) {
                // Decrease because your HTML has - sign for increase button
                if (currentQuantity > 0) {
                    quantityElement.textContent = currentQuantity - 1;
                }
            } else {
                // Increase because your HTML has + sign for decrease button
                quantityElement.textContent = currentQuantity + 1;
            }
            
            // Add animation class
            quantityElement.classList.add('quantity-change');
            // Remove animation class after animation completes
            setTimeout(() => {
                quantityElement.classList.remove('quantity-change');
            }, 300);
            
            // Update the live total after quantity change
            updateLiveTotal();
        });
    });

    // Add click handler for checkout button
    const checkoutBtn = document.getElementById('checkout-btn');
    if (checkoutBtn) {
        checkoutBtn.addEventListener('click', calculateBill);
    }

    // Initialize total display
    updateLiveTotal();
});

// Single menu button handler
let navbar = document.querySelector('.header .navbar');
document.querySelector('#menu-btn').onclick = () => {
    navbar.classList.toggle('active');
}

function scrollMenu(direction) {
    const container = document.querySelector('.box-container');
    const scrollAmount = 300;
    container.scrollLeft += direction * scrollAmount;
}

function calculateBill() {
    const billData = [];
    let hasItems = false;
    
    document.querySelectorAll('.box').forEach(box => {
        const quantityElement = box.querySelector('.quantity');
        const quantity = parseInt(quantityElement.textContent);
        
        if (quantity > 0) {
            hasItems = true;
            const name = box.querySelector('h3').textContent;
            const price = parseInt(box.querySelector('.price').getAttribute('data-price'));
            
            billData.push({
                name: name,
                quantity: quantity,
                price: price
            });
        }
    });
    
    if (!hasItems) {
        alert('Vui lòng chọn số lượng đồ uống trước khi thanh toán!');
        return;
    }

    // Store bill data in localStorage
    localStorage.setItem('billData', JSON.stringify(billData));
    
    // Redirect to bill page
    window.location.href = 'bill.html';
}

function updateLiveTotal() {
    let totalAmount = 0;
    let totalItems = 0;
    
    document.querySelectorAll('.quantity').forEach(quantityElement => {
        const quantity = parseInt(quantityElement.textContent);
        if (quantity > 0) {
            const box = quantityElement.closest('.box');
            const price = parseInt(box.querySelector('.price').getAttribute('data-price'));
            totalAmount += quantity * price;
            totalItems += quantity;
        }
    });
    
    // Update the display
    document.getElementById('live-total-amount').textContent = 
        new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        }).format(totalAmount);
    document.getElementById('live-total-items').textContent = `Số lượng: ${totalItems}`;
}
  
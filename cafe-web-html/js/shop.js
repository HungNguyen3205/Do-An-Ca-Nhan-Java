// Function to update quantity
function updateQuantity(itemId, change) {
    const quantityElement = document.getElementById(`quantity-${itemId}`);
    let currentQuantity = parseInt(quantityElement.textContent);
    
    // Update quantity
    currentQuantity = Math.max(0, currentQuantity + change);
    quantityElement.textContent = currentQuantity;
    
    // Store in sessionStorage
    sessionStorage.setItem(itemId, currentQuantity);
    
    // Update total display and checkout button
    updateTotal();
    updateCheckoutButton();
}

// Function to update checkout button state
function updateCheckoutButton() {
    const checkoutBtn = document.getElementById('checkout-btn');
    let totalItems = 0;

    // Calculate total items
    const menuItems = ['coffee-black', 'coffee-milk', 'bacc-xiu', 
                      'cacao-ice', 'cacao-hot', 'coffee-salt'];
    
    menuItems.forEach(itemId => {
        const quantity = parseInt(sessionStorage.getItem(itemId) || '0');
        totalItems += quantity;
    });

    // Enable/disable button based on total items
    if (totalItems > 0) {
        checkoutBtn.disabled = false;
        checkoutBtn.style.opacity = '1';
        checkoutBtn.style.cursor = 'pointer';
        // Add click event listener
        checkoutBtn.onclick = function() {
            // Store order data before redirecting
            const orderData = {
                items: menuItems.map(itemId => ({
                    id: itemId,
                    quantity: parseInt(sessionStorage.getItem(itemId) || '0')
                })).filter(item => item.quantity > 0)
            };
            sessionStorage.setItem('orderData', JSON.stringify(orderData));
            window.location.href = 'bill.html';
        };
    } else {
        checkoutBtn.disabled = true;
        checkoutBtn.style.opacity = '0.5';
        checkoutBtn.style.cursor = 'not-allowed';
        checkoutBtn.onclick = null;
    }
}

// Menu scroll functionality
function scrollMenu(direction) {
    const container = document.querySelector('.box-container');
    const scrollAmount = 300;
    container.scrollLeft += direction * scrollAmount;
}

// Single menu button handler
let navbar = document.querySelector('.header .navbar');
document.querySelector('#menu-btn').onclick = () => {
    navbar.classList.toggle('active');
}

// Initialize when page loads
document.addEventListener('DOMContentLoaded', function() {
    updateCheckoutButton();
}); 
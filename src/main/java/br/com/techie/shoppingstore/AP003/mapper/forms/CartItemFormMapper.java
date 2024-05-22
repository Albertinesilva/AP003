package br.com.techie.shoppingstore.AP003.mapper.forms;

import br.com.techie.shoppingstore.AP003.dto.form.CartItemFORM;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.CartItem;
import br.com.techie.shoppingstore.AP003.model.Product;
import br.com.techie.shoppingstore.AP003.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartItemFormMapper implements Mapper<CartItemFORM, CartItem> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public CartItem map(CartItemFORM i) {
        CartItem cartItem = new CartItem();
        Product product = productRepository.findById(i.product_id())
                .orElseThrow(() -> new EntityNotFoundException("Product not found!"));

        cartItem.setProduct(product);
        cartItem.setQuantity(i.quantity());
        cartItem.setPrice(product.getPrice());

        return cartItem;
    }
}

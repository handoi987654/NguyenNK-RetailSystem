package com.Desert.RestController;

import com.Desert.Entity.Bill;
import com.Desert.Entity.BillDetail;
import com.Desert.Entity.Product;
import com.Desert.Payload.Message;
import com.Desert.Payload.ProductBarcode;
import com.Desert.Service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/product")
    public ResponseEntity<Object> getProduct(@RequestBody ProductBarcode barcode) {
        Product product = clientService.getProduct(barcode.getBarcode());
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Wrong barcode!"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/cart")
    public Map<Product, Long> getCart(HttpSession session) {
        Map<Product, Long> cart = (Map<Product, Long>) session.getAttribute("cart");
        if (cart == null) cart = new HashMap<>();

        return cart;
    }

    @PostMapping("/cart/{productID}")
    public ResponseEntity<Message> addProduct(@PathVariable long productID, HttpSession session) {
        Map<Product, Long> cart = (Map<Product, Long>) session.getAttribute("cart");
        if (cart == null) cart = new HashMap<>();

        Product product = clientService.getProduct(productID);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Cannot find this Product!"));
        }
        cart.put(product, 1L);
        return ResponseEntity.status(HttpStatus.OK).body(new Message(product.getProductName() + " added to cart!"));
    }

    @GetMapping("/bill")
    public ResponseEntity<Object> getBills(HttpSession session) {
        String username = (String) session.getAttribute("username");
        List<Bill> billList = clientService.getBills(username);
        if (billList == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Error while retrieving bills!"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(billList);
    }

    @GetMapping("/bill/{billID}")
    public ResponseEntity<Object> getBillDetail(@PathVariable long billID) {
        List<BillDetail> details = clientService.getBillDetails(billID);
        if (details == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message("Error while retrieving Bill!"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(details);
    }
}


package cart.controller;

import cart.auth.AuthInfo;
import cart.auth.AuthData;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartApiController {


    @PostMapping("/cart/{productId}")
    public ResponseEntity<Void> addCart(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @AuthInfo AuthData authData,
            @PathVariable Long productId) {

        System.out.println("authData = " + authData);


        return ResponseEntity.ok().build();
    }
}

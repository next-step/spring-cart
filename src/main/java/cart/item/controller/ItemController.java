package cart.item.controller;

import cart.item.dto.request.ItemAddRequest;
import cart.item.dto.request.ItemDeleteRequest;
import cart.item.dto.response.ItemResponse;
import cart.item.service.ItemService;
import cart.user.domain.Member;
import cart.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cart")
@RestController
public class ItemController {

    private final AuthService authService;
    private final ItemService itemService;
    private final ModelAndView modelAndView = new ModelAndView();

    @GetMapping
    public ModelAndView index() {
        modelAndView.setViewName("cart");
        return modelAndView;
    }
    @GetMapping("/items")
    public List<ItemResponse> getItemsByMemberId(@RequestHeader String authorization) {
        Member member = authService.validate(authorization);
        return itemService.findAllByMemberId(member.getId());
    }

    @PostMapping("/items/{productId}")
    public void addCarItem(@PathVariable("productId") Long productId, @RequestHeader String authorization) {
        Member member = authService.validate(authorization);
        itemService.addCarItem(new ItemAddRequest(member.getId(), productId));
    }

    @DeleteMapping("/item/{id}")
    public void deleteCarItem(@PathVariable("id") Long id, @RequestHeader String authorization) {
        Member member = authService.validate(authorization);
        itemService.deleteCarItem(new ItemDeleteRequest(id, member.getId()));
    }



}

package by.sam.mvc.controllers.cook;


import by.sam.mvc.service.order.OrderService;
import by.sam.mvc.service.user.CookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrdersController {

    private CookService cookService;
    private OrderService orderService;

    public OrdersController(CookService cookService, OrderService orderService) {
        this.cookService = cookService;
        this.orderService = orderService;
    }

    @GetMapping(path = "orders")
    public String getOrdersPage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("orders", cookService.getAuthenticationCook(currentUser).getOrders());
        return "cook/orders";
    }


    @PostMapping(path = "order")
    public String getOrderPage(Model model, @RequestParam int ordersId) {
        model.addAttribute("order", orderService.read(ordersId));
        return "cook/order";
    }
}

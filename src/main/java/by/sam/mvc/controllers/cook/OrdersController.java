package by.sam.mvc.controllers.cook;


import by.sam.mvc.entity.user.Cook;
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
        Cook cook = cookService.getAuthenticationCook(currentUser);
        model.addAttribute("orders",orderService.sortOrders(cook.getOrders()));
        return "cook/orders";
    }


    @PostMapping(path = "order")
    public String getOrderPage(Model model, @RequestParam int orderId) {
        model.addAttribute("order", orderService.read(orderId));
        return "cook/order";
    }


    @PostMapping(path = "confirmOrder", params = {"_new"})
    public String confirmOrder(@AuthenticationPrincipal UserDetails currentUser, @RequestParam int _new) {
        orderService.makeOrderInProcess(_new);
        return "redirect:/orders";
    }

    @PostMapping(path = "confirmOrder", params = {"inProcess"})
    public String closeOrder(@AuthenticationPrincipal UserDetails currentUser, @RequestParam int inProcess) {
        orderService.sendMailClientToConfirmService(inProcess);
        return "redirect:/orders";
    }
    @PostMapping(path = "confirmOrder", params = {"closed"})
    public String back() {
        return "redirect:/orders";
    }
}

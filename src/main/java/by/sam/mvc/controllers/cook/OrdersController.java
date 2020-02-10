package by.sam.mvc.controllers.cook;


import by.sam.mvc.entity.order.Order;
import by.sam.mvc.entity.user.Cook;
import by.sam.mvc.model.OrdersDto;
import by.sam.mvc.service.order.OrderService;
import by.sam.mvc.service.user.CookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        model.addAttribute("orders", orderService.getNextListOrders(cook.getOrders(), 1).getOrders());
        model.addAttribute("page", 1);
        return "cook/orders";
    }

    @PostMapping(path = "orders/page", params = {"next"})
    public String getOrdersNextPage(@RequestParam int next, Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Cook cook = cookService.getAuthenticationCook(currentUser);
        OrdersDto orders = orderService.getNextListOrders(cook.getOrders(), next + 1);
        if (orders.isCorrectPage()) {
            model.addAttribute("page", next + 1);
        } else {
            model.addAttribute("page", next);
        }
        model.addAttribute("orders", orders.getOrders());
        return "cook/orders";
    }

    @PostMapping(path = "orders/page", params = {"previous"})
    public String getOrdersPreviousPage(@RequestParam int previous, Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Cook cook = cookService.getAuthenticationCook(currentUser);
        OrdersDto orders = orderService.getPreviousListOrders(cook.getOrders(), previous - 1);
        if (orders.isCorrectPage()) {
            model.addAttribute("page", previous - 1);
        } else {
            model.addAttribute("page", previous);
        }
        model.addAttribute("orders", orders.getOrders());
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
        orderService.sendMailClientToConfirmService(_new);
        return "redirect:/orders";
    }

    @PostMapping(path = "confirmOrder", params = {"inProcess"})
    public String closeOrder(@AuthenticationPrincipal UserDetails currentUser, @RequestParam int inProcess) {
        return "redirect:/orders";
    }


    @PostMapping(path = "confirmOrder", params = {"closed"})
    public String back() {
        return "redirect:/orders";
    }


}

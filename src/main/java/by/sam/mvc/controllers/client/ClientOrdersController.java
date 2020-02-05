package by.sam.mvc.controllers.client;

import by.sam.mvc.entity.order.Order;
import by.sam.mvc.entity.user.Client;
import by.sam.mvc.service.order.OrderService;
import by.sam.mvc.service.user.ClientService;
import by.sam.mvc.service.user.CookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientOrdersController {

    private ClientService clientService;
    private OrderService orderService;
    private CookService cookService;

    public ClientOrdersController(ClientService clientService, OrderService orderService, CookService cookService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.cookService = cookService;
    }

    @GetMapping(path = "client/orders")
    public String getOrdersPage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Client client = clientService.getAuthenticationClient(currentUser);
        model.addAttribute("orders", orderService.sortOrders(client.getOrders()));
        return "client/orders";
    }

    @PostMapping(path = "clientOrder")
    public String getOrderPage(Model model, @RequestParam int orderId) {
        Order order = orderService.read(orderId);
        model.addAttribute("order", order);
        model.addAttribute("cook", order.getCook());
        return "client/order";
    }

    @PostMapping(path = "confirmOrder", params = {"confirm"})
    public String confirmOrder(@RequestParam int confirm) {
        orderService.makeOrderClosed(confirm);
        return "redirect:/client/orders";
    }

    @PostMapping(path = "confirmOrder", params = {"back"})
    public String back() {
        return "redirect:/client/orders";
    }




}

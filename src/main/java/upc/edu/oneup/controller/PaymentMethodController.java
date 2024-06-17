package upc.edu.oneup.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upc.edu.oneup.exception.ValidationException;
import upc.edu.oneup.model.PaymentMethod;
import upc.edu.oneup.model.User;
import upc.edu.oneup.repository.UserRepository;
import upc.edu.oneup.service.PaymentMethodService;

import java.util.List;

@Tag(name = "PaymentMethods", description = "the payment methods API")
@RestController
@RequestMapping("/api/oneup/v1") //@RequestMapping("/api/oneup/v1")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;
    private final UserRepository userRepository;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService, UserRepository userRepository) {
        this.paymentMethodService = paymentMethodService;
        this.userRepository = userRepository;
    }

    @GetMapping("/paymentmethod")
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodService.getAllPaymentMethods();
    }
    @GetMapping("/paymentmethod/{id}")
    public PaymentMethod getPaymentMethod(@PathVariable int id) {
        return paymentMethodService.getPaymentMethodById(id);
    }
    @PostMapping("/paymentmethod/{user_id}")
    @Transactional
    public PaymentMethod createPaymentMethod(@RequestBody PaymentMethod paymentMethod, @PathVariable int user_id) {
        User user = userRepository.findById(user_id).get();
        paymentMethod.setUser(user);
        validatePaymentMethod(paymentMethod);
        return paymentMethodService.savePaymentMethod(paymentMethod);
    }
    @PutMapping("/paymentmethod/{id}")
    @Transactional
    public PaymentMethod updatePaymentMethod(@PathVariable int id, @RequestBody PaymentMethod paymentMethod) {
        validatePaymentMethod(paymentMethod);
        return paymentMethodService.updatePaymentMethod(id, paymentMethod);
    }
    @DeleteMapping("/paymentmethod/{id}")
    public void deletePaymentMethod(@PathVariable int id) {
        paymentMethodService.deletePaymentMethod(id);
    }

    public void validatePaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod.getCardNumber()==null || paymentMethod.getCardNumber().trim().isEmpty()) {
            throw new ValidationException("Card Number is required");
        }
    }
}

package freevoice.shared.utils.payment.paypal;

import freevoice.shared.constants.URLS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Slf4j
@RestController
@RequestMapping(URLS.paypal)
@RequiredArgsConstructor
public class PaypalController {

    @Autowired
    private PaypalService service;

    @PostMapping(URLS.initiatePayment)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> initiatePayment(@RequestBody Order order) {
        try {
            Payment payment = service.createPayment(order.getPrice(),
                                                    order.getCurrency(),
                                                    order.getMethod(),
                                                    order.getIntent(),
                                                    order.getDescription(),
                                                    URLS.paypalPayFailure,
                                                    URLS.paypalPaySuccess);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return new ResponseEntity<>(link.getHref(), HttpStatus.OK) ;
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("redirects:/", HttpStatus.OK) ;
    }

    @GetMapping(URLS.paymentSuccess)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> paymentSuccess(
            @PathVariable("paymentId") String paymentId,
            @PathVariable("payerId") String payerId
    ) {

        try {
            log.info("initiated payment execution");
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return new ResponseEntity<>("success", HttpStatus.OK) ;
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("failed", HttpStatus.OK) ;
    }

}
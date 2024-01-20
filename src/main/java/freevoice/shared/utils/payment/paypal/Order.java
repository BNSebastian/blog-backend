package freevoice.shared.utils.payment.paypal;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Order {
    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;
}
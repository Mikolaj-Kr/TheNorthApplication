package theNorthApplication.app.dto;

import lombok.Data;

@Data
public class AvailabilityDto {

    private Long id;
    private boolean maskAvailability;
    private boolean glovesAvailability;
    private boolean gelAvailability;
    private Double maskPrize;
    private Double glovesPrize;
    private Double gelPrize;
}

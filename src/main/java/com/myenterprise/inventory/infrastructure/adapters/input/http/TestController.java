package com.myenterprise.inventory.infrastructure.adapters.input.http;

import java.util.Map;

import javax.measure.Dimension;
import javax.measure.MetricPrefix;
import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Length;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public ResponseEntity<?> getAllProducts() {

        Unit<Length> meter = Units.METRE;
        Unit<Length> kilometer = MetricPrefix.KILO(Units.METRE);

        Quantity<Length> quantity = Quantities.getQuantity(1000, meter);

        Dimension dimension = meter.getDimension();

        return ResponseEntity.ok(Map.of(
                "quantity", quantity.getValue(),
                "unit", meter.getSymbol(),
                "dimension", dimension.toString(),
                "quantityInKilometers", quantity.to(kilometer).getValue()
        ));
    }

}

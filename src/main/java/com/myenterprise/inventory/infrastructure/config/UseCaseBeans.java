package com.myenterprise.inventory.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myenterprise.inventory.application.usecases.ProductTransactionUseCase;
import com.myenterprise.inventory.application.usecases.ProductUseCase;
import com.myenterprise.inventory.application.usecases.StockUseCase;
import com.myenterprise.inventory.domain.ports.input.ProductInPort;
import com.myenterprise.inventory.domain.ports.input.ProductTransactionInPort;
import com.myenterprise.inventory.domain.ports.input.StockInPort;
import com.myenterprise.inventory.domain.ports.output.ProductOutPort;
import com.myenterprise.inventory.domain.ports.output.ProductTransactionOutPort;
import com.myenterprise.inventory.domain.ports.output.StockOutPort;

@Configuration
public class UseCaseBeans {

    @Bean
    public ProductInPort productInPort(ProductOutPort productOutPort) {
        return new ProductUseCase(productOutPort);
    }

    @Bean
    public StockInPort stockInPort(StockOutPort stockOutPort) {
        return new StockUseCase(stockOutPort);
    }

    @Bean
    public ProductTransactionInPort productTransactionInPort(ProductTransactionOutPort productTransactionOutPort) {
        return new ProductTransactionUseCase(productTransactionOutPort);
    }

}

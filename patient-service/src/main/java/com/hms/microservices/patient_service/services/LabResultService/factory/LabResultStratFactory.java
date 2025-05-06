package com.hms.microservices.patient_service.services.LabResultService.factory;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hms.microservices.patient_service.common.enums.TestType;
import com.hms.microservices.patient_service.services.LabResultService.strategy.BloodTestStrategy;
import com.hms.microservices.patient_service.services.LabResultService.strategy.LabResultStrategy;
import com.hms.microservices.patient_service.services.LabResultService.strategy.UrineTestStrategy;

@Component
public class LabResultStratFactory {

    private final Map<TestType, LabResultStrategy> strategies = new EnumMap<>(TestType.class);

    public LabResultStratFactory(List<LabResultStrategy> strategyList) {
        for (LabResultStrategy strategy : strategyList) {
            if (strategy instanceof BloodTestStrategy) {
                strategies.put(TestType.BLOOD, strategy);
            }
            if (strategy instanceof UrineTestStrategy) {
                strategies.put(TestType.URINE, strategy);
            }
            // Add more strategies here
        }
    }

    public LabResultStrategy getStrategy(TestType type) {
        return strategies.get(type);
    }

}

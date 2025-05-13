package com.hms.microservices.patient_service.services.LabResultService.factory;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.hms.microservices.patient_service.common.annotations.TestStrategy;
import com.hms.microservices.patient_service.common.enums.TestType;
import com.hms.microservices.patient_service.services.LabResultService.strategy.LabResultStrategy;

import jakarta.annotation.PostConstruct;

// Factory pattern using reflections and Spring's ApplicationContext to dynamically load strategies
// Annotate your strategy classes with @TestStrategy and @Component
@Component
public class LabResultStratFactory implements ApplicationContextAware {

    private final Map<TestType, LabResultStrategy> strategies = new EnumMap<>(TestType.class);
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void initLabResultStratFactory() {
        Reflections reflections = new Reflections(
                "com.hms.microservices.patient_service.services.LabResultService.strategy");
        Set<Class<? extends LabResultStrategy>> strategyClasses = reflections.getSubTypesOf(LabResultStrategy.class);
        for (Class<? extends LabResultStrategy> strategyClass : strategyClasses) {
            TestStrategy annotation = strategyClass.getAnnotation(TestStrategy.class);
            if (annotation != null) {
                TestType testType = annotation.value();
                try {
                    LabResultStrategy strategyInstance = applicationContext.getBean(strategyClass);
                    strategies.put(testType, strategyInstance);
                } catch (Exception e) {
                    // Revise this later
                }
            }
        }
    }

    public LabResultStrategy getStrategy(TestType type) {
        return strategies.get(type);
    }

}

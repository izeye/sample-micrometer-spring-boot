package com.izeye.sample;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Aspect extending {@link ObservedAspect} directly.
 *
 * @author Johnny Lim
 */
// See https://github.com/micrometer-metrics/micrometer/issues/3575
//@Component
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ConcreteClassExtendingObservedAspect extends ObservedAspect {

    public ConcreteClassExtendingObservedAspect(ObservationRegistry registry) {
        super(registry);
    }

}

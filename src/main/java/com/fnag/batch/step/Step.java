package com.fnag.batch.step;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Step is a stereotype to identify {@code LineStep} bean and registered them via component-scan
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Step {
}

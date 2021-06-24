package com.example.annotation_introspection;

import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.core.annotation.AnnotationValueBuilder;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.reflect.ClassUtils;
import io.micronaut.inject.annotation.NamedAnnotationMapper;
import io.micronaut.inject.ast.ClassElement;
import io.micronaut.inject.visitor.VisitorContext;

import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;

@Singleton
public class ConsumedIntrospectedAnnotationMapper implements NamedAnnotationMapper {
  @Override
  public String getName() {
    return "com.example.around_advice.Consumed";
  }

  @Override
  public List<AnnotationValue<?>> map(AnnotationValue<Annotation> annotation, VisitorContext visitorContext) {
    final AnnotationValueBuilder<Introspected> builder = AnnotationValue.builder(Introspected.class)
      .member("excludedAnnotations", "javax.persistence.Transient");
    return Collections.singletonList(builder.build());
  }
  public boolean isValidResultType(ClassElement returnType) {
    return returnType.hasStereotype(Introspected.class) || ClassUtils.isJavaBasicType(returnType.getName()) || returnType.isPrimitive();
  }
}

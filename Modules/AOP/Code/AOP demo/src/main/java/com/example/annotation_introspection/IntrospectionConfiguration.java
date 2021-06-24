package com.example.annotation_introspection;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.core.annotation.AnnotationValueBuilder;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.version.annotation.Version;

import java.beans.Transient;

public class IntrospectionConfiguration {
  public AnnotationValue<Introspected> buildIntrospectionConfiguration() {
    final AnnotationValueBuilder<Introspected> builder = AnnotationValue.builder(Introspected.class)
      // don't bother with transients properties
      .member("excludedAnnotations", Transient.class)
      // following are indexed for fast lookups
      .member("indexed",
        AnnotationValue.builder(Introspected.IndexedAnnotation.class)
          .member("annotation", JsonTypeInfo.Id.class).build(),
        AnnotationValue.builder(Introspected.IndexedAnnotation.class)
          .member("annotation", Version.class).build()
        // todo: Check later
//        AnnotationValue.builder(Introspected.IndexedAnnotation.class)
//          .member("annotation", DateCreated.class).build(),
//        AnnotationValue.builder(Introspected.IndexedAnnotation.class)
//          .member("annotation", DateUpdated.class).build(),
//        AnnotationValue.builder(Introspected.IndexedAnnotation.class)
//          .member("annotation", MappedProperty.class)
//          .member("member", "value").build()
      );
    return builder.build();
  }

}

package com.example.around_advice;

import com.example.annotation_introspection.ConsumedIntrospectedAnnotationMapper;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.BeanLocator;
import io.micronaut.core.annotation.AnnotationClassValue;
import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.core.annotation.AnnotationValueBuilder;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.inject.BeanDefinition;
import io.micronaut.inject.annotation.AnnotationMapper;
import io.micronaut.inject.qualifiers.Qualifiers;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@MicronautTest
public class ExampleTest {
  @Inject
  Example example;
  @Inject
  BeanLocator beanLocator;
  //    @Inject
//    List<Event> eventList = new ArrayList<>();
  @Inject
  ApplicationContext beanContext;
  @Inject
  AnnotationMapper annotationMapper;
  @Inject
  ConsumedIntrospectedAnnotationMapper consumedIntrospectedAnnotationMapper;
  private static Logger log = LoggerFactory.getLogger(ExampleTest.class);
//  @Inject
//  VisitorContext visitorContext;
//  @Inject
//  IntrospectionConfiguration introspectionConfiguration;

  @Test
  void testResult() {
    String result = example.result();
    Assertions.assertEquals("Hello World", result);
  }

  @Test
  void testResultIsNull() {
    String result = example.resultIsNull();
    Assertions.assertNull(result);
  }

  @Test
  void testResultParameterIsNull() {
    String result = example.result(null);
    Assertions.assertNull(result);
  }

  @Test
  void testResultParameterIsNotNull() {
    String name = "shetu";
    String result = example.result(name);
    Assertions.assertEquals("Hello " + name, result);
  }

//    @Test
//    void printEventsList(){
//        List<Event> events = eventList;
//        for (Event event : events) {
//            System.out.println(event);
//        }
//    }

  @Test
  void testGetAllTheSubclassesOfASuperClass() {
    final Collection<Event> beansOfType = beanLocator.getBeansOfType(Event.class);
    for (Event event : beansOfType) {
      System.out.println(event.getClass().getSimpleName());
    }
//        beansOfType.forEach(beanTypeName -> beanTypeName.getClass().getSimpleName());
  }

  @Test
  void printEventsUsingApplicationContext() {

  }

  /**
   * Working only when those classes are annotated with: @Singleton and RetentionPolicy.Runtime is added to @Consumed
   */
  @Test
  void testBeanContext() throws IllegalAccessException {
    Collection<BeanDefinition<?>> definitions =
    beanContext.getBeanDefinitions(Qualifiers.byStereotype(Consumed.class));
    for (BeanDefinition definition : definitions) {
      Class<?> clazz = definition.getBeanType();
      Set<String> fields = getFields(clazz);
      log.info("Fields:[{}]", fields);
      //-----------------------------
//      getFieldsWithSuperClass(clazz);

      //-----------------------------

    }
  }

  private Set<String> getFields(Class<?> clazz) {
    Set<String> fieldsSet = new HashSet<>();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      String fieldName = field.getName();
      fieldsSet.add(fieldName);
    }
    return fieldsSet;
  }

  private void getFieldsWithSuperClass(Class<?> clazz) {
    Set<String> fieldsSet = new HashSet<>();
    Field[] myFields = clazz.getDeclaredFields();
    for (Field myField : myFields) {
      System.out.println(clazz.getSimpleName() + "->" + myField.getType().getSimpleName() + ":" + myField.getName());
      Class<?> fieldClass = myField.getType();
      getFieldsWithSuperClass(fieldClass);
    }
  }

  private Set<String> getIntrospectedProperties(Class<?> clazz) {
    final BeanIntrospection<?> introspection = BeanIntrospection.getIntrospection(clazz);
    return Arrays.stream(introspection.getPropertyNames()).collect(Collectors.toSet());
  }

  private Set<String> getIntrospectedPropertiesForEvent(Event event) {
    final BeanIntrospection<?> introspection = BeanIntrospection.getIntrospection(event.getClass());
    return Arrays.stream(introspection.getPropertyNames()).collect(Collectors.toSet());
  }

  /********************** Experimental test cases ***************************/

  @Test
  void testIntrospectedProperties(){
    Set<String> properties = getIntrospectedPropertiesForEvent(new SimpleEvent());
    log.info("Properties:{}",properties);
  }

  @Test
  void findAnnotatedClass() {

    AnnotationValue annotationValue = new AnnotationValue(Consumed.class.toString());
    final Class<?>[] classes = annotationValue.classValues();
    for (Class<?> aClass : classes) {
      System.out.println(aClass.getName());
    }
  }

  @Test
  void printSubclassesOfAClass() {
    final Class<?>[] classes = Event.class.getClasses();
    System.out.println(classes);
  }


  @Test
  void testBeanIntrospectionWithIntrospected() {
    final BeanIntrospection<DemoEvent> introspection = BeanIntrospection.getIntrospection(DemoEvent.class);
    System.out.println(introspection);
  }

  @Test
  void testBeanValidation() {

  }

  @Test
  void testAnnotationClassValue() {
    AnnotationClassValue<Consumed> annotationClassValue = new AnnotationClassValue(Consumed.class);
    System.out.println(annotationClassValue);
  }

//  @Test
//  void testAnnotationMapper() {
//    String name = consumedIntrospectedAnnotationMapper.getName();
//    consumedIntrospectedAnnotationMapper.map(,visitorContext);
//    log.info("Name:{}",name);
//  }


//  @Test
//  void testIsValidResultType(){
//    consumedIntrospectedAnnotationMapper.isValidResultType(Introspected.class);
//  }

  // --------------------
//  public List<AnnotationValue<?>> map(AnnotationValue<RolesAllowed> annotation) {
//    String[] values = annotation.get("value", String[].class).orElse(new String[0]);
//
//    List<AnnotationValue<?>> annotationValues = new ArrayList<>(1);
//    annotationValues.add(
//      AnnotationValue.builder(Secured.class)
//        .values(values)
//        .build()
//    );
//    return annotationValues;
//  }
  // --------------------

  @Test
  void testAnnotationValue() {
    AnnotationValueBuilder<Consumed> consumedAnnotationValue = AnnotationValue.builder(Consumed.class);
    log.info("Members name:{}", consumedAnnotationValue);
  }

  //--------------------------------------------------------- Using Introspection --------------------------------
  @Test
  void testUsingBeanContextOnIntrospected() throws IllegalAccessException {
    Collection<BeanDefinition<?>> definitions =
    beanContext.getBeanDefinitions(Qualifiers.byStereotype(Consumed.class));
    for (BeanDefinition definition : definitions) {
      Class<?> clazz = definition.getBeanType();
//      Set<String> fields = getFields(clazz);
//      log.info("Fields:[{}]", fields);
      //-----------------------------
      final Set<String> introspectedProperties = getIntrospectedProperties(clazz);
      log.info("Introspected Properties:{}",introspectedProperties);

      //-----------------------------

    }
  }
  // -------------------------------------------------------------------------------------------------------------
  // Gradle plugin method
  @Test
  void helloWorld(){
    System.out.println("**** Hello World from ExampleTest.java ******");
  }
  // -------------------------------------------------------------------------------------------------------------
}

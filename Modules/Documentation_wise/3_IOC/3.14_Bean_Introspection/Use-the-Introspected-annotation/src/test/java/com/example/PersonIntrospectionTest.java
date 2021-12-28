package com.example;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.beans.*;
import io.micronaut.core.beans.BeanWrapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@MicronautTest
public class PersonIntrospectionTest {
  @Inject
  private PersonIntrospection introspection;

  @Test
  void introspect() {
//    introspection.introspectName("Shahariar");
    introspection.introspectAge(28);
  }

  @Test
  void beanWrapping() {
    BeanIntrospection<Person> introspection = BeanIntrospection.getIntrospection(Person.class);
    BeanWrapper<Person> wrapper = BeanWrapper.getWrapper(introspection.instantiate());
    String[] propertyNames = wrapper.getPropertyNames();
    System.out.println(propertyNames);
    Optional<Integer> age = wrapper.getProperty("age", int.class);
    if (age.isPresent()) {
      int newAge = age.get();
      System.out.println("Age: " + newAge);
    }
  }

  @Test
  void getIntrospectedProperties() {
    BeanIntrospection<Person> introspection = BeanIntrospection.getIntrospection(Person.class);
    String[] propertyNames = introspection.getPropertyNames();
    Arrays.stream(propertyNames).forEach(System.out::println);
  }

  /*
  * !beanProperty.getType().isPrimitive()
      && !beanProperty.getType().equals(String.class)
      && !beanProperty.getType().equals(Integer.class)
      && !beanProperty.getType().equals(Boolean.class)
      && !(beanProperty.getType().equals(List.class))
      && !(beanProperty.getType().equals(Set.class))
      && !(beanProperty.getType().equals(Map.class))
      && !(beanProperty.getType().equals(Queue.class))
      && !beanProperty.getType().equals(Double.class)
      && !beanProperty.getType().equals(Float.class)
      && !beanProperty.getType().isArray()
  * */

  @Test
  void getBeanProperties() {
    List<String> properties = new ArrayList<>();
    BeanIntrospection<Person> introspection = BeanIntrospection.getIntrospection(Person.class);
    final Collection<BeanProperty<Person, Object>> beanProperties = introspection.getBeanProperties();
    for (BeanProperty<Person, Object> beanProperty : beanProperties) {
      if (beanProperty.getType().getAnnotation(Introspected.class) != null) {
        final BeanIntrospection<Object> demoIntrospection = BeanIntrospection.getIntrospection(beanProperty.getType());
        final Collection<BeanProperty<Object, Object>> demoProperties = demoIntrospection.getBeanProperties();
        for (BeanProperty<Object, Object> demoProperty : demoProperties) {
          properties.add(beanProperty.getType().getSimpleName().toLowerCase() + "." + demoProperty.getName());
        }
      } else {
        properties.add(beanProperty.getName());
      }
    }
    System.out.println(properties);
  }


  @Test
  void printNestedBeanProperties() {
    List<String> properties = new ArrayList<>();
    BeanIntrospection<?> introspection = getBeanIntrospection(Person.class);
    for (BeanProperty<?, Object> beanProperty : introspection.getBeanProperties()) {
      if (beanProperty.getType().getAnnotation(Introspected.class) != null) {
        final Collection<? extends BeanProperty<?, Object>> beanProperties = getBeanIntrospection(beanProperty.getType()).getBeanProperties();
        properties.addAll(getProperties(beanProperty.getType().getSimpleName().toLowerCase(), (Collection<BeanProperty<?, Object>>) beanProperties));
      } else {
        properties.add(beanProperty.getName());
      }
    }
    System.out.println(properties);
  }

  private List<String> getProperties(String motherClass, Collection<BeanProperty<?, Object>> introspectedProps) {
    return introspectedProps.stream().map(prop -> motherClass + "." + prop.getName()).collect(Collectors.toList());
  }


  private BeanIntrospection<?> getBeanIntrospection(Class<?> clazz) {
    return BeanIntrospection.getIntrospection(clazz);
  }

  @Test
  void printNestedProperties() {
//    System.out.println(getIntrospectedProperties(Person.class));
//    System.out.println(getIntroProps(Person.class, new ArrayList<>(), null));
    System.out.println(getIntrospectionProps(Person.class, new ArrayList<>(), null));
  }

  private List<String> getProps(BeanProperty<?, Object> beanProperty) {
    return getIntrospection(beanProperty.getType()).getBeanProperties().stream().map(prop -> beanProperty.getType().getSimpleName().toLowerCase() + "." + prop.getName()).collect(Collectors.toList());
  }


  private BeanIntrospection<?> getIntrospection(Class<?> clazz) {
    return BeanIntrospection.getIntrospection(clazz);
  }

  private List<String> getIntrospectedProperties(Class<?> clazz) {
    List<String> properties = new ArrayList<>();
    BeanIntrospection<?> introspection = getBeanIntrospection(clazz);
    for (BeanProperty<?, Object> beanProperty : introspection.getBeanProperties()) {
      if (beanProperty.getType().getAnnotation(Introspected.class) != null) {
        properties.addAll(getProps(beanProperty));
      } else {
        properties.add(beanProperty.getName());
      }
    }
    return properties;
  }

  private List<String> getIntroProps(Class<?> clazz, List<String> properties, String root) {
    for (BeanProperty<?, Object> beanProperty : BeanIntrospection.getIntrospection(clazz).getBeanProperties()) {
      if (beanProperty.getType().getAnnotation(Introspected.class) != null) {
        getIntroProps(beanProperty.getType(), properties, beanProperty.getType().getSimpleName().toLowerCase());
      } else {
        properties.add((root == null) ? beanProperty.getName() : root + "." + beanProperty.getName());
      }
    }
    return properties;
  }

  private List<String> getIntrospectionProps(Class<?> clazz, List<String> properties, String root) {
    final Collection<? extends BeanProperty<?, Object>> beanProperties = BeanIntrospection.getIntrospection(clazz).getBeanProperties();
    beanProperties.forEach(
    prop -> {
      if (prop.getType().getAnnotation(Introspected.class) != null) {
        getIntrospectionProps(prop.getType(), properties, (root == null) ? prop.getType().getSimpleName().toLowerCase(): root + "." + prop.getType().getSimpleName().toLowerCase());
      } else {
        String attribute = (root == null) ? prop.getName() : root + "." + prop.getName();
        System.out.println(attribute);
        properties.add(attribute);
      }
    }
    );
    return properties;
  }

  private String concateProp(String root, BeanProperty<?, Object> prop){
    return (root == null) ? prop.getType().getSimpleName().toLowerCase(): root + "." + prop.getType().getSimpleName().toLowerCase();
  }
}



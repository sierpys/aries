package com.alibaba.spring.annotation;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * @author sier.pys 9/21/18
 */
public class AnnotationUtilsT {
    public static void main(String[] args) {
        try {
            Method method = TestParent.TestChildren.class.getMethod("test", HttpServletRequest.class);
            StaticTextAnnotation staticTextAnnot = AnnotationUtils.findAnnotation(method, StaticTextAnnotation.class);

            System.out.println("@StaticTextAnnotation of method is: " + staticTextAnnot);
            System.out.println("@StaticTextAnnotation method value: " + AnnotationUtils.getValue(staticTextAnnot, "text"));
            System.out.println("@StaticTextAnnotation method default value: " + AnnotationUtils.getDefaultValue(staticTextAnnot, "text"));
            System.out.println("@StaticTextAnnotation value: " + AnnotationUtils.getValue(staticTextAnnot));


            Map<String, Object> annotationAttributes = AnnotationUtils.getAnnotationAttributes(staticTextAnnot);
            System.out.println(annotationAttributes);

            ClassNameAnnotation classNameAnnotation = AnnotationUtils.findAnnotation(TestParent.TestChildren.class, ClassNameAnnotation.class);

            ClassNameAnnotation nameAnnotation = AnnotationUtils.getAnnotation(TestParent.TestChildren.class, ClassNameAnnotation.class);
            AnnotationAttributes attributes = AnnotationUtils.getAnnotationAttributes(TestParent.TestChildren.class, nameAnnotation);
            System.out.println(AnnotationUtils.getValue(nameAnnotation, "className"));

            ClassNameAnnotation mergedAnnotation = AnnotatedElementUtils.findMergedAnnotation(TestParent.TestChildren.class, ClassNameAnnotation.class);

            System.out.println("=======");
            Set<Schedule> mergedRepeatableAnnotations = AnnotatedElementUtils.findMergedRepeatableAnnotations(method, Schedule.class);
            mergedRepeatableAnnotations.forEach(x -> {
                System.out.println(AnnotationUtils.getValue(x, "dayOfMonth"));
            });

            Schedule schedule = AnnotatedElementUtils.findMergedAnnotation(method, Schedule.class);
            ClassNameAnnotation annotation = AnnotationUtils.findAnnotation(TestParent.TestChildren.class, ClassNameAnnotation.class);

            System.out.println(AnnotationUtils.getValue(annotation, "className"));

//            boolean annotationDeclaredLocally = AnnotationUtils.isAnnotationDeclaredLocally(ClassNameAnnotation.class, TestParent.TestChildren.class);
//            System.out.println(annotationDeclaredLocally);
//            boolean annotationInherited = AnnotationUtils.isAnnotationInherited(ClassNameAnnotation.class, TestParent.TestChildren.class);
//            System.out.println(annotationInherited);
//            System.out.println(classNameAnnotation);
//            System.out.println(AnnotationUtils.getValue(classNameAnnotation, "className"));
//
//            System.out.println(AnnotationUtils.getDefaultValue(classNameAnnotation, "className"));
//
//            System.out.println(AnnotationUtils.getValue(classNameAnnotation));
//
//            AnnotationUtils.get

        } catch (NoSuchMethodException e) {

        }
    }
}

package com.hms.microservices.patient_service.common.util.deserializer;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.reflections.Reflections;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public abstract class CustomDeserializer<T> extends JsonDeserializer<T> {
    private final Class<T> baseClass;
    private final String discriminator;
    private final Class<? extends Annotation> subtypeAnnotationClass;

    public CustomDeserializer(Class<T> baseClass, String discriminator,
            Class<? extends Annotation> subtypeAnnotationClass) {
        super();
        this.baseClass = baseClass;
        this.discriminator = discriminator;
        this.subtypeAnnotationClass = subtypeAnnotationClass;
    }

    @Override
    public T deserialize(JsonParser parser, DeserializationContext ctx)
            throws IOException, JsonProcessingException {
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        String discriminatorValue = node.get(discriminator).asText();

        Reflections reflections = new Reflections("com.hms.microservices.patient_service.common.dto");
        Set<Class<? extends T>> subtypes = reflections
                .getSubTypesOf(baseClass);
        for (Class<? extends T> subtype : subtypes) {
            Annotation subtypeAnnotation = subtype.getAnnotation(subtypeAnnotationClass);
            String subtypeDiscriminatorValue = null;
            try {
                if (subtypeAnnotation != null) {
                    Object val = subtypeAnnotation.annotationType().getMethod("value").invoke(subtypeAnnotation);
                    subtypeDiscriminatorValue = (val instanceof Enum) ? val.toString() : (String) val;
                }
                if (subtypeDiscriminatorValue != null)
                    subtypeDiscriminatorValue = subtypeDiscriminatorValue.toString();
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                // Revise this later
                throw new IOException("Error getting discriminator value for subtype: " + subtype.getName(), e);
            }

            if (discriminatorValue.equals(subtypeDiscriminatorValue)) {
                return codec.treeToValue(node, subtype);
            }
        }

        // If no subtype was found
        return null;
    }
}

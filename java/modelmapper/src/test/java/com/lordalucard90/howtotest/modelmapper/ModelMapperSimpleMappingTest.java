package com.lordalucard90.howtotest.modelmapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import static org.junit.jupiter.api.Assertions.*;


public class ModelMapperSimpleMappingTest {
    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    public void setUp() {
        modelMapper = new ModelMapper();
    }

    @Test
    public void givenSrcSimple_whenMapNew_thenCopyValues() {
        SrcSimpleEntity src = new SrcSimpleEntity("a", "b", "src");

        DstSimpleEntity dst = modelMapper.map(src, DstSimpleEntity.class);

        assertEquals(src.str1, dst.str1);
        assertEquals(src.str2, dst.str2);
        assertThrows(
                NoSuchFieldException.class,
                () -> dst.getClass().getDeclaredField("onlySrc")
        );
        assertNull(dst.onlyDst);
    }

    @Test
    public void givenSrcSimple_whenMapExisting_thenCopyValuesAndOverridesExisting() {
        SrcSimpleEntity src = new SrcSimpleEntity(null, "b", "c");
        DstSimpleEntity dst = new DstSimpleEntity("d", "e", "dst");

        modelMapper.map(src, dst);

        assertNull(dst.str1);
        assertEquals(src.str2, dst.str2);
        assertThrows(
                NoSuchFieldException.class,
                () -> dst.getClass().getDeclaredField("onlySrc")
        );
    }

    @Test
    public void givenSrcSimple_whenMapExistingWithSkipNull_thenCopyValuesAndOverridesExistingIfNotNull() {
        // set skip null
        modelMapper.getConfiguration().setPropertyCondition(context -> context.getSource()!=null);

        SrcSimpleEntity src = new SrcSimpleEntity(null, "b", "c");
        DstSimpleEntity dst = new DstSimpleEntity("d", "e", "dst");

        modelMapper.map(src, dst);

        assertEquals("d", dst.str1);
    }

    @Test
    public void givenSrcComplex_whenMapNewToComplex_thenCopyValues() {
        SrcComplexEntity src = new SrcComplexEntity(new ComplexField("a", "b"), "c");

        DstComplexEntity dst = modelMapper.map(src, DstComplexEntity.class);

        assertEquals("a", dst.complex.a);
        assertEquals("b", dst.complex.b);
        assertEquals("c", dst.string);
    }

    @Test
    public void givenSrcComplex_whenMapNewToFlatWithExplicitMapping_thenCopyValues() {
        modelMapper.addMappings(
                new PropertyMap<SrcComplexEntity, DstFlatEntity>() {
                    @Override
                    protected void configure() {
                        map().setComplexA(source.complex.a);
                        map(source.complex.b, destination.complexB);
                    }
                }
        );

        SrcComplexEntity src = new SrcComplexEntity(new ComplexField("a", "b"), "c");

        DstFlatEntity dst = modelMapper.map(src, DstFlatEntity.class);

        assertEquals("a", dst.complexA);
        assertEquals("b", dst.complexB);
        assertEquals("c", dst.string);
    }



}

/*
    Testes Classes
 */
@Data
@AllArgsConstructor
class SrcSimpleEntity {
    String str1;
    String str2;
    String onlySrc = "src";
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class DstSimpleEntity {
    String str1;
    String str2;
    String onlyDst;
}


@Data
@AllArgsConstructor
class SrcComplexEntity {
    ComplexField complex;
    String string;
}

@AllArgsConstructor
class ComplexField {
    String a;
    String b;
}

@Data
class DstComplexEntity {
    ComplexField complex;
    String string;
}

@Data
class DstFlatEntity {
    String complexA;
    String complexB;
    String string;
}



package com.lordalucard90.howtotest.modelmapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;


public class ModelMapperGettersSettersAndConstructorTest {
    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    public void setUp() {
        modelMapper = new ModelMapper();
    }

    @Test
    public void givenNoArgsConstructorEntity_whenMapNew_thenThrowsMappingException() {
        SrcNoPublicNoArgsConstructorEntity src = new SrcNoPublicNoArgsConstructorEntity("string", "string");

        assertThrows(MappingException.class, () -> modelMapper.map(src, DstNoPublicNoArgsConstructorEntity.class));
    }

    @Test
    public void givenSrcAndDstWithNoGetters_whenMapNew_thenFails() {
        SrcNoGettersEntity src = new SrcNoGettersEntity("string");

        DstNoGettersEntity dst = modelMapper.map(src, DstNoGettersEntity.class);

        assertNull(dst.string);
    }

    @Test
    public void givenSrcAndDstWithNoSetters_whenMapNew_thenFails() {
        SrcNoSettersEntity src = new SrcNoSettersEntity("string");

        DstNoSettersEntity dst = modelMapper.map(src, DstNoSettersEntity.class);

        assertNull(dst.string);
    }

    @Test
    public void givenSrcNoGettersAndDstWithNoSetters_whenMapNew_thenFails() {
        SrcNoGettersEntity src = new SrcNoGettersEntity("string");

        DstNoSettersEntity dst = modelMapper.map(src, DstNoSettersEntity.class);

        assertNull(dst.string);
    }

    @Test
    public void givenSrcNoSettersAndDstWithNoGetters_whenMapNew_thenWorks() {
        SrcNoSettersEntity src = new SrcNoSettersEntity("string");

        DstNoGettersEntity dst = modelMapper.map(src, DstNoGettersEntity.class);

        assertEquals(src.string, dst.string);
    }
}
/*
    Testes Classes
 */
@AllArgsConstructor
class SrcNoPublicNoArgsConstructorEntity {
    String string1;
    String string2;
}

@AllArgsConstructor
class DstNoPublicNoArgsConstructorEntity {
    String string1;
    String string2;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
class SrcNoSettersEntity {
    String string;
}

@AllArgsConstructor
@NoArgsConstructor
@Setter
class SrcNoGettersEntity {
    String string;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
class DstNoSettersEntity {
    String string;
}

@AllArgsConstructor
@NoArgsConstructor
@Setter
class DstNoGettersEntity {
    String string;
}

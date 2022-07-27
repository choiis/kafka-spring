package org.example.data;


import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Vo implements Serializable {

    private String name;

    private int number;
}

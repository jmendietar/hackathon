package com.code.challenger.output;

import com.code.challenger.entry.ObjectType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String     id;
    private ObjectType outputType;
    private String     output;
}

package com.code.challenger.testcase;

import com.code.challenger.entry.Entry;
import com.code.challenger.output.Output;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String       name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entry_id", referencedColumnName = "id")
    private Entry  entry;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "output_id", referencedColumnName = "id")
    private Output output;
}

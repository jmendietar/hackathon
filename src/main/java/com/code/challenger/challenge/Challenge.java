package com.code.challenger.challenge;

import com.code.challenger.testcase.TestCase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Challenge {
    @Id
    private String id;
    private String name;
    private String description;
    private String language;
    @OneToMany
    @JoinColumn(name = "id")
    private List<TestCase> publicTestCases;
    @OneToMany
    @JoinColumn(name = "id")
    private List<TestCase> privateTestCases;
}

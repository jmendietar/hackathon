package com.code.challenger.challenge;

import com.code.challenger.testcase.TestCase;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;

public class ChallengeRequest {
    private String         name;
    private String         description;
    private String         language;
    private List<TestCase> publicTestCases;
    private List<TestCase> privateTestCases;
}

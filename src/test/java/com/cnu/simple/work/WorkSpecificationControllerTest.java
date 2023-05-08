package com.cnu.simple.work;

import com.cnu.simple.exception.MemberVaildateDuplicateException;
import com.cnu.simple.member.MemberRequestDto;
import com.cnu.simple.member.MemberResponseDto;
import com.cnu.simple.member.MemberService;
import com.cnu.simple.robot.RobotRequestDto;
import com.cnu.simple.robot.RobotResponseDto;
import com.cnu.simple.robot.RobotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WorkSpecificationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    RobotService robotService;

    @Autowired
    MemberService memberService;

    @Autowired
    WorkSpecificationService workService;

    UUID memberId;
    Long workSpecId;

    @BeforeAll
    void setUp() throws MemberVaildateDuplicateException {

        MemberRequestDto memberRequestDto = new MemberRequestDto("test-member", "test@test.com", "testtestmember!@#234");
        MemberResponseDto member = memberService.saveMember(memberRequestDto);
        log.info("member -> {}", member);
        memberId = member.getId();

        WorkSpecRequestDto workSpecRequestDto = new WorkSpecRequestDto(
                "test-workspec", "memomemo", "script anything", member.getId());

        WorkSpecResponseDto workSpec = workService.addWorkSpec(workSpecRequestDto);
        log.info("work spec -> {}", workSpec);
        workSpecId = workSpec.getId();
    }

    @Test
    @DisplayName("작업 명세서 입력")
    void testWorkSpecInsert() throws Exception {
        WorkSpecRequestDto workSpecRequestDto = new WorkSpecRequestDto(
                "test-workspec", "memomemo", "script anything", memberId);
        mockMvc.perform(post("/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(workSpecRequestDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("작업 명세서 목록: 전부")
    void testWorkSpecFindAll() throws Exception {
        mockMvc.perform(get("/task/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("작업 명세서 목록: 하나")
    void testWorkSpecFindById() throws Exception {
        mockMvc.perform(get("/task/{id}", workSpecId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("작업 명세서 목록: 회원")
    void testWorkSpecFindByMemberId() throws Exception {
        mockMvc.perform(get("/task/all/{memberId}", memberId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("작업 명세서 수정")
    void testWorkSpecUpdate() throws Exception{
        WorkSpecRequestDto workSpecRequestDto = new WorkSpecRequestDto(
                "update-workspec", "update memo!", "script anything", memberId);
        mockMvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(workSpecRequestDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("작업 명세서 삭제")
    void testWorkSpecDelete() throws Exception{
        mockMvc.perform(delete("/task/{id}", workSpecId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
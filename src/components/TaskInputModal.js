import { useState } from "react";
import Modal from "react-modal";
import styled from "styled-components";
import CodeEditor from "@uiw/react-textarea-code-editor";
import { Button, ButtonContainer } from "./Button";
import StyleInput from "./StyledInput";

const Label = styled.label`
  display: block;
  margin-top: 10px;
  margin-bottom: -10px;
  margin-left: 10px;
  font-size: 15px;
  color: rgba(0, 0, 0, 0.5);
`;

const InputsContainer = styled.div`
  display: flex;
  flex-direction: column;
  flex: 1;
`;

const EditorContainer = styled.div`
  flex: 1;
  margin-left: 20px;
`;

const ContentContainer = styled.div`
  display: flex;
`;

const TaskInputModal = ({ isModify = false, open, onClick, ...props }) => {
  const { name, curMemo, curStartDate, curStartTime, curRepetition } = props;
  const [taskName, setTaskName] = useState(isModify ? name : "");
  const [memo, setMemo] = useState(isModify ? curMemo : "");
  const [startDate, setStartDate] = useState(isModify ? curStartDate : "");
  const [startTime, setStartTime] = useState(isModify ? curStartTime : "");
  const [repetition, setRepetition] = useState(isModify ? curRepetition : "");

  const [code, setCode] = useState(`#!/bin/bash`);

  const handleReset = () => {
    setTaskName("");
    setMemo("");
    setStartDate("");
    setStartTime("");
    setRepetition("");
    setCode(`#!/bin/bash`);
    onClick();
  };

  const validateForm = () => {
    if (!taskName || !memo || !startDate || !startTime || !repetition) {
      alert("모든 필드를 입력해 주세요.");
      return false;
    }

    return true;
  };

  const handleSubmit = () => {
    if (validateForm()) {
      // todo 성공 시 서버로 전송
    }
  };

  return (
    <Modal
      isOpen={open}
      onRequestClose={handleReset}
      style={{
        overlay: {
          position: "fixed",
          top: 0,
          left: 0,
          right: 0,
          bottom: 0,
          zIndex: 100,
          backgroundColor: "rgba(0, 0, 0, 0.3)",
        },
        content: {
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          border: "1px solid #ccc",
          background: "#fff",
          overflow: "auto",
          WebkitOverflowScrolling: "touch",
          borderRadius: "4px",
          outline: "none",
          padding: "50px",
          width: "1000px",
          height: "550px",
        },
      }}
    >
      <h2>작업 추가</h2>
      <ContentContainer>
        <InputsContainer>
          <StyleInput
            placeholder="작업 명"
            name="taskName"
            type="text"
            value={taskName}
            onChange={(e) => {
              setTaskName(e.target.value);
            }}
          />
          <StyleInput
            placeholder="Memo"
            name="memo"
            type="text"
            value={memo}
            onChange={(e) => {
              setMemo(e.target.value);
            }}
          />
          <Label htmlFor="startDate">시작 일:</Label>
          <StyleInput
            placeholder="시작 일"
            name="startDate"
            type="date"
            value={startDate}
            onChange={(e) => {
              setStartDate(e.target.value);
            }}
          />
          <Label htmlFor="startTime">시작 시간:</Label>
          <StyleInput
            placeholder="시작 시간"
            name="startTime"
            type="time"
            value={startTime}
            onChange={(e) => {
              setStartTime(e.target.value);
            }}
          />
          <StyleInput
            placeholder="반복 주기(일)"
            name="repetition"
            type="number"
            value={repetition}
            onChange={(e) => {
              setRepetition(e.target.value);
            }}
          />
        </InputsContainer>
        <EditorContainer>
          <p>Script 입력</p>
          <CodeEditor
            value={code}
            language="shell"
            placeholder="Please enter JS code."
            onChange={(evn) => setCode(evn.target.value)}
            padding={15}
          />
        </EditorContainer>
      </ContentContainer>
      <ButtonContainer>
        <Button
          onClick={handleReset}
          style={{ backgroundColor: "black", color: "white" }}
        >
          취소
        </Button>
        <Button onClick={handleSubmit}>완료</Button>
      </ButtonContainer>
    </Modal>
  );
};

export default TaskInputModal;

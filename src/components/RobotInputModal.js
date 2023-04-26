import { useState } from "react";
import Modal from "react-modal";
import styled from "styled-components";
import { FiLink, FiCheck, FiSlash } from "react-icons/fi";
import { Button, ButtonContainer } from "./Button";
import StyleInput from "./StyledInput";

const TestConnectionContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;

const InputModal = ({ isModify = false, open, onClick, ...props }) => {
  const { name, curSshId, curSshPw, curPort, curIp } = props;
  const [robotName, setRobotName] = useState(isModify ? name : "");
  const [ip, setIp] = useState(isModify ? curIp : "");
  const [port, setPort] = useState(isModify ? curPort : "");
  const [sshId, setSshId] = useState(isModify ? curSshId : "");
  const [sshPw, setSshPw] = useState(isModify ? curSshPw : "");

  const [isConnectionTestPassed, setIsConnectionTestPassed] = useState(false);

  const handleReset = () => {
    setRobotName("");
    setIp("");
    setPort("");
    setSshId("");
    setSshPw("");
    setIsConnectionTestPassed(false);
    onClick();
  };

  const validateForm = () => {
    if (!robotName || !ip || !port || !sshId || !sshPw) {
      alert("모든 필드를 입력해 주세요.");
      return false;
    }

    if (
      !/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(
        ip
      )
    ) {
      alert("유효하지 않은 IP 입력입니다.");
      return false;
    }

    if (!isConnectionTestPassed) {
      alert("접속 테스트를 완료해 주세요.");
      return false;
    }

    return true;
  };

  const connectionTestValidation = () => {
    if (!robotName || !ip || !port || !sshId || !sshPw) {
      alert("모든 필드를 입력해 주세요.");
      return false;
    }

    if (
      !/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(
        ip
      )
    ) {
      alert("유효하지 않은 IP 입력입니다.");
      return false;
    }

    return true;
  };

  const handleConnectionTest = () => {
    if (connectionTestValidation()) {
      // 서버로 전송하여 접속 테스트결과 확인
      // todo 서버로 전송하여 접속 테스트 결과 반환 받기
      setIsConnectionTestPassed(true);
    } else {
      setIsConnectionTestPassed(false);
    }
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
          width: "450px",
          height: "550px",
        },
      }}
    >
      <h2>로봇 추가</h2>
      <StyleInput
        placeholder="로봇 명"
        name="robotName"
        type="text"
        value={robotName}
        onChange={(e) => {
          setRobotName(e.target.value);
          setIsConnectionTestPassed(false);
        }}
      />
      <StyleInput
        placeholder="IP"
        name="ip"
        type="text"
        value={ip}
        onChange={(e) => {
          setIp(e.target.value);
          setIsConnectionTestPassed(false);
        }}
      />
      <StyleInput
        placeholder="Port"
        name="port"
        type="number"
        value={port}
        onChange={(e) => {
          setPort(e.target.value);
          setIsConnectionTestPassed(false);
        }}
      />
      <StyleInput
        placeholder="SSH ID"
        name="sshId"
        type="text"
        value={sshId}
        onChange={(e) => {
          setSshId(e.target.value);
          setIsConnectionTestPassed(false);
        }}
      />
      <StyleInput
        placeholder="SSH PW"
        name="sshPw"
        type="password"
        value={sshPw}
        onChange={(e) => {
          setSshPw(e.target.value);
          setIsConnectionTestPassed(false);
        }}
      />
      <ButtonContainer>
        <TestConnectionContainer>
          <span
            style={{
              borderBottom: "1px solid rgba(0,0,0,0.5",
              color: isConnectionTestPassed ? "green" : "red",
            }}
          >
            접속테스트
            {isConnectionTestPassed ? <FiCheck /> : <FiSlash />}
          </span>
          <Button onClick={handleConnectionTest}>
            <FiLink style={{ fontSize: "20px" }} />
          </Button>
        </TestConnectionContainer>
      </ButtonContainer>
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

export default InputModal;

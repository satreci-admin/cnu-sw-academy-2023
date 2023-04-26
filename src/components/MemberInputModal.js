import { useState } from "react";
import Modal from "react-modal";
import styled from "styled-components";

const StyleInput = styled.input`
  border: none;
  border-bottom: 1px solid rgba(0, 0, 0, 0.5);
  margin: 10px;
  width: 420px;
  height: 50px;
  font-size: 15px;
`;

const Button = styled.button`
  border-radius: 5px;
  background-color: white;
  width: 70px;
  height: 40px;
  font-size: 16px;
  font-weight: 800;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 20px;
`;

const MemberInputModal = ({ isModify = false, open, onClick, ...props }) => {
  const { name, curEmail, curPw } = props;
  const [memberName, setMemberName] = useState(isModify ? name : "");
  const [email, setEmail] = useState(isModify ? curEmail : "");
  const [pw, setPw] = useState(isModify ? curPw : "");

  const handleReset = () => {
    setMemberName("");
    setEmail("");
    setPw("");
    onClick();
  };

  const validateForm = () => {
    if (!memberName || !email || !pw) {
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
          width: "450px",
          height: "350px",
        },
      }}
    >
      <h2>사용자 추가</h2>
      <StyleInput
        placeholder="사용자 명"
        name="memberName"
        type="text"
        value={memberName}
        onChange={(e) => {
          setMemberName(e.target.value);
        }}
      />
      <StyleInput
        placeholder="Email"
        name="email"
        type="email"
        value={email}
        onChange={(e) => {
          setEmail(e.target.value);
        }}
      />
      <StyleInput
        placeholder="Password"
        name="password"
        type="password"
        value={pw}
        onChange={(e) => {
          setPw(e.target.value);
        }}
      />
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

export default MemberInputModal;

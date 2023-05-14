import styled from "styled-components";
import { FiPlus } from "react-icons/fi";

const Button = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 70px;
  height: 35px;
  background-color: black;
  color: white;
  border-radius: 5px;
  font-weight: 800;
  font-size: 16px;
  cursor: pointer;
`;

const ButtonWrapper = styled.div`
  margin-top: 150px;
  margin-right: 50px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
`;

const AddButton = ({ onClick }) => {
  return (
    <ButtonWrapper>
      <Button onClick={onClick}>
        <FiPlus style={{ fontSize: "20px", fontWeight: "800" }} />
        Add
      </Button>
    </ButtonWrapper>
  );
};

export default AddButton;

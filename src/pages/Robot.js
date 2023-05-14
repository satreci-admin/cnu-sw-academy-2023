import React, { useState } from "react";
import MainContentWrapper from "../components/MainContentWrapper";
import Header from "../components/Header";
import List from "../components/List";
import { DUMMYRobot } from "../DUMMY";
import AddButton from "../components/AddButton";
import RobotInputModal from "../components/RobotInputModal";

function Robot() {
  const [open, setOpen] = useState(false);
  const handleClick = () => {
    setOpen(!open);
  };

  return (
    <MainContentWrapper>
      <Header
        mainHeader={"로봇 관리"}
        subHeader={"Supporting robot management"}
      ></Header>
      <AddButton onClick={handleClick} />
      <RobotInputModal open={open} onClick={handleClick} />
      <List type="robot" info={DUMMYRobot.robot} />
    </MainContentWrapper>
  );
}

export default Robot;

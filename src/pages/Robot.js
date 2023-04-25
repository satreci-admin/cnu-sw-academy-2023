import React from "react";
import MainContentWrapper from "../components/MainContentWrapper";
import Header from "../components/Header";
import List from "../components/List";
import { DUMMYRobot } from "../DUMMY";

function Robot() {
  return (
    <MainContentWrapper>
      <Header
        mainHeader={"로봇 관리"}
        subHeader={"Supporting robot management"}
      ></Header>
      <List type="robot" info={DUMMYRobot.robot} />
    </MainContentWrapper>
  );
}

export default Robot;
